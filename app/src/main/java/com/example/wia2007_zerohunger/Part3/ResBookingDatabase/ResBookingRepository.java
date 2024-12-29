package com.example.wia2007_zerohunger.Part3.ResBookingDatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ResBookingRepository {
    private ResBookingDAO resBookingDao;
    private LiveData<List<ResBooking>> resBookings;
    ExecutorService executors = Executors.newSingleThreadExecutor();

    public ResBookingRepository(Application application) {
        ResBookingDatabase database = ResBookingDatabase.getInstance(application);
        resBookingDao = database.resBookingDao();
        resBookings = resBookingDao.getAllResBookings();

    }

    public void insert(ResBooking booking) {
        //new InsertReservationAsyncTask(reservationDao).execute(reservation);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                resBookingDao.insert(booking);
            }
        });
    }

    public void update(ResBooking booking) {
        //new UpdateReservationAsyncTask(reservationDao).execute(reservation);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                resBookingDao.update(booking);
            }
        });
    }

    public void delete(ResBooking booking) {
        //new DeleteReservationAsyncTask(reservationDao).execute(reservation);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                resBookingDao.delete(booking);
            }
        });
    }

    public LiveData<List<ResBooking>> getAllResBookings() {
        return resBookings;
    }
}
