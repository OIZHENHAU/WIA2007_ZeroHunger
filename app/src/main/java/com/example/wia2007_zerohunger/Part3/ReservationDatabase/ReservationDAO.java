package com.example.wia2007_zerohunger.Part3.ReservationDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface ReservationDAO {
    @Insert
    public void insert(Reservation reservation);

    @Update
    public void update(Reservation reservation);

    @Delete
    public void delete(Reservation reservation);

    @Query("SELECT * FROM reservation_table ORDER BY reservationId ASC")
    LiveData<List<Reservation>> getAllReservations();
}
