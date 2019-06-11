package com.example.ticker.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.ticker.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    private final RoomDatabase.Callback INITIALIZER = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    @Provides
    @Singleton
    AppDatabase getDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, BuildConfig.DB_NAME)
                .addCallback(INITIALIZER)
                .build();
    }
}
