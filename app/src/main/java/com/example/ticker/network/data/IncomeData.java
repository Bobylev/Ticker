package com.example.ticker.network.data;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

public class IncomeData {

    private float mPriceHi = Float.NaN;
    private float mPriceLo = Float.NaN;
    private float mDailyHi = Float.NaN;
    private float mDailyLo = Float.NaN;
    private boolean isValid;

    public IncomeData(String rawData){
        try {
            isValid = true;
            Gson gson = new Gson();
            ArrayList result = gson.fromJson(rawData, ArrayList.class);
            mPriceHi = Float.valueOf(((ArrayList)result.get(1)).get(2).toString());
            mPriceLo = Float.valueOf(((ArrayList)result.get(1)).get(0).toString());
            mDailyHi = Float.valueOf(((ArrayList)result.get(1)).get(8).toString());
            mDailyLo = Float.valueOf(((ArrayList)result.get(1)).get(9).toString());
            Log.i("TICKER", "data : " + rawData);
        } catch(Exception e) {
            Log.i("TICKER", "wrong data : " + rawData);
            isValid = false;
        }
    }

    public float getPriceHi(){
        return mPriceHi;
    }

    public float getPriceLo(){
        return mPriceLo;
    }

    public float getDailyLo() {
        return mDailyLo;
    }

    public float getDailyHi() {
        return mDailyHi;
    }

    public boolean isValid() { return isValid; }
}
