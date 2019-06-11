package com.example.ticker;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.example.ticker.application.ViewModelFactory;
import com.example.ticker.model.MainViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelFactory mViewModelFactory;

    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        mainViewModel.startData();
        setContentView(R.layout.activity_main);

        LineChart chart =  findViewById(R.id.line_chart);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        ArrayList<Entry> temp= new ArrayList<>();
        temp.add(new Entry(3,4));
        dataSets.add(new LineDataSet(temp, "1"));

        chart.setData(new LineData(dataSets));

        mainViewModel.getData().observe(this, isData -> {
                chart.setData(new LineData(isData));
                chart.invalidate();
        });

    }

}