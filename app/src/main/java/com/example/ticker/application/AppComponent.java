package com.example.ticker.application;

import android.app.Application;

import com.example.ticker.database.DatabaseModule;
import com.example.ticker.network.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        NetworkModule.class,
        //RepositoryModule.class,
        ViewModelModule.class,
        ActivityBindingModule.class,
        CommonModule.class,
        DatabaseModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        Builder network(NetworkModule network);

        Builder context(CommonModule contextModule);

        Builder database(DatabaseModule databaseModule);

        AppComponent build();
    }

}