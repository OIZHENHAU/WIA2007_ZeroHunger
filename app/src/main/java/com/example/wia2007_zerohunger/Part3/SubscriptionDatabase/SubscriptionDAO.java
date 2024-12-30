package com.example.wia2007_zerohunger.Part3.SubscriptionDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface SubscriptionDAO {

    @Insert
    public void insert(Subscription subscription);

    @Update
    public void update(Subscription subscription);

    @Delete
    public void delete(Subscription subscription);

    @Query("SELECT * FROM subscription_table ORDER BY subscriptionId ASC")
    LiveData<List<Subscription>> getAllSubscriptions();
}
