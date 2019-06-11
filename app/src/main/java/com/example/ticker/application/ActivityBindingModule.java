package com.example.ticker.application;

import com.example.ticker.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
interface ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector()
    MainActivity contributesMainActivity();
}