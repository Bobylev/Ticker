package com.example.ticker.application;

import com.example.ticker.database.DatabaseModule;
import com.example.ticker.network.NetworkModule;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class App extends DaggerApplication {

    private AppComponent comp;

    public AppComponent getComp(){
        return comp;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

        CommonModule contextModule = new CommonModule(this);
        comp = DaggerAppComponent.builder()
                .application(this)
                .context(contextModule)
                .network(new NetworkModule(
                        contextModule.getContext()
                ))
                .database(new DatabaseModule())
                .build();

        return comp;
    }
}
