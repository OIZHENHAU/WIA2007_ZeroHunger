package com.example.wia2007_zerohunger.Part3.ConnectionDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ConnectionDAO {

    @Insert
    public void insert(Connection connection);

    @Update
    public void update(Connection connection);

    @Delete
    public void delete(Connection connection);

    @Query("SELECT * FROM connection_table ORDER BY connectionId ASC")
    LiveData<List<Connection>> getAllConnections();

}
