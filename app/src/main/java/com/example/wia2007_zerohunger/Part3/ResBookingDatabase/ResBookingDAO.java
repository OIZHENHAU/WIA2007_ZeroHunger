package com.example.wia2007_zerohunger.Part3.ResBookingDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface ResBookingDAO {
    @Insert
    public void insert(ResBooking booking);

    @Update
    public void update(ResBooking booking);

    @Delete
    public void delete(ResBooking booking);

    @Query("SELECT * FROM reservation_booking_table ORDER BY resBookingId ASC")
    LiveData<List<ResBooking>> getAllResBookings();
}
