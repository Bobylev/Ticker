package com.example.ticker.application;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.annotations.NonNull;

@Module
class CommonModule {

    private Context mContext;

    CommonModule(@NonNull App app) {
        mContext = app;
    }

    @Provides
    @Singleton
    Context getContext() {
        return mContext;
    }

}