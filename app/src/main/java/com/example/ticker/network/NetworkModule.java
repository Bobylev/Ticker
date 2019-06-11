package com.example.ticker.network;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class NetworkModule {
    private Context mContext;

    public NetworkModule(@NonNull Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    OkHttpClient getClient(){
        return new OkHttpClient();
    }

}

