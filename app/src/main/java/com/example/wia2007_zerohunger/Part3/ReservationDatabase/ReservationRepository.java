package com.example.wia2007_zerohunger.Part3.ReservationDatabase;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wia2007_zerohunger.Part3.ConnectionDatabase.Connection;
import com.example.wia2007_zerohunger.Part3.ConnectionDatabase.ConnectionDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReservationRepository {

    private ReservationDAO reservationDao;
    private LiveData<List<Reservation>> reservations;

    ExecutorService executors = Executors.newSingleThreadExecutor();

    public ReservationRepository(Application application) {
        ReservationDatabase database = ReservationDatabase.getInstance(application);
        reservationDao = database.reservationDao();
        reservations = reservationDao.getAllReservations();

    }

    public void insert(Reservation reservation) {
        //new InsertReservationAsyncTask(reservationDao).execute(reservation);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                reservationDao.insert(reservation);
            }
        });
    }

    public void update(Reservation reservation) {
        //new UpdateReservationAsyncTask(reservationDao).execute(reservation);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                reservationDao.update(reservation);
            }
        });
    }

    public void delete(Reservation reservation) {
        //new DeleteReservationAsyncTask(reservationDao).execute(reservation);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                reservationDao.delete(reservation);
            }
        });
    }

    public LiveData<List<Reservation>> getAllReservations() {
        return reservations;
    }
}
