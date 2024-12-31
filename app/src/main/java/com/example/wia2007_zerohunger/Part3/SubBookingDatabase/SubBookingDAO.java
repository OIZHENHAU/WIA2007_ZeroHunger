package com.example.wia2007_zerohunger.Part3.SubBookingDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface SubBookingDAO {

    @Insert
    public void insert(SubBooking subBooking);

    @Update
    public void update(SubBooking subBooking);

    @Delete
    public void delete(SubBooking subBooking);

    @Query("SELECT * FROM sub_booking_table ORDER BY subBookingId ASC")
    LiveData<List<SubBooking>> getAllSubBookings();
}
