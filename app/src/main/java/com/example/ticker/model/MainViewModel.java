package com.example.ticker.model;

import android.graphics.Color;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ticker.network.data.IncomeData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class MainViewModel extends ViewModel {
    private OkHttpClient mClient;
    private WebSocket mWebSocket;
    private long mCounter = 0;

    private MutableLiveData<List<ILineDataSet>> mDataLiveData = new MutableLiveData<>();

    @Inject
    MainViewModel(OkHttpClient client) {
        super();
        mClient = client;
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(new LineDataSet(new ArrayList<>(), "Price of last highest bid"));
        dataSets.add(new LineDataSet(new ArrayList<>(), "Price of last lowest ask"));
        dataSets.get(0).setHighlightEnabled(true);
        dataSets.get(0).setValueTextColor(Color.GRAY);
        ((LineDataSet)dataSets.get(0)).setColor(Color.GREEN);
        dataSets.get(1).setHighlightEnabled(true);
        dataSets.get(1).setValueTextColor(Color.RED);
        ((LineDataSet)dataSets.get(1)).setColor(Color.RED);

        //dataSets.add(new LineDataSet(new ArrayList<>(), "Daily high"));
        //dataSets.add(new LineDataSet(new ArrayList<>(), "Daily low"));
        mDataLiveData.setValue(dataSets);
    }

    public MutableLiveData<List<ILineDataSet>> getData(){
        return mDataLiveData;
    }

    public void startData() {
        if(mWebSocket != null) return;

        Request request = new Request.Builder().url("wss://api.bitfinex.com/ws/2").build();
        mWebSocket = mClient.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                webSocket.send("{\"event\":\"subscribe\",\"channel\":\"ticker\",\"pair\":\"BTCUSD\"}");
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
                try {
                    IncomeData data = new IncomeData(text);
                    if (data.isValid()) {
                        List<ILineDataSet> current = mDataLiveData.getValue();
                        if (current != null) {
                            if(current.get(0).getEntryCount() > 9){
                                for (ILineDataSet ds: current ) {
                                    ds.removeFirst();
                                }
                            }
                            mCounter++;
                            current.get(0).addEntry(new Entry(mCounter, data.getPriceHi()));
                            current.get(1).addEntry(new Entry(mCounter, data.getPriceLo()));
                            //current.get(2).addEntry(new Entry(mCounter, data.getDailyHi()));
                            //current.get(3).addEntry(new Entry(mCounter, data.getDailyLo()));
                            mDataLiveData.postValue(current);
                        }
                    }
                } catch (Exception e){
                    Log.i("WS", "Data parsing exception. Data: " + text);
                }
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
                Log.i("WS", "Closed");
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
                super.onFailure(webSocket, t, response);
                Log.i("WS", "Failure" + t.getMessage());
            }
        });
        mClient.dispatcher().executorService().shutdown();
    }

    @Override
    protected void onCleared() {
        Log.i("WS", "Closing...");
        mWebSocket.close(1000, "mobile app terminate connection");
        super.onCleared();
    }



}
