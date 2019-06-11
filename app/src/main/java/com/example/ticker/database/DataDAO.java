package com.example.ticker.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import java.util.List;

@Dao
interface DataDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addData(List<DataEntity> categories);
}
