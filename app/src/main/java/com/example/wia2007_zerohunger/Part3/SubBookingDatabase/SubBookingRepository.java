package com.example.wia2007_zerohunger.Part3.SubBookingDatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SubBookingRepository {
    private SubBookingDAO subBookingDao;
    private LiveData<List<SubBooking>> subBookings;

    ExecutorService executors = Executors.newSingleThreadExecutor();

    public SubBookingRepository(Application application) {
        SubBookingDatabase database = SubBookingDatabase.getInstance(application);
        subBookingDao = database.subBookingDao();
        subBookings = subBookingDao.getAllSubBookings();

    }

    public void insert(SubBooking subBooking) {
        //new InsertReservationAsyncTask(reservationDao).execute(reservation);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                subBookingDao.insert(subBooking);
            }
        });
    }

    public void update(SubBooking subBooking) {
        //new UpdateReservationAsyncTask(reservationDao).execute(reservation);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                subBookingDao.update(subBooking);
            }
        });
    }

    public void delete(SubBooking subBooking) {
        //new DeleteReservationAsyncTask(reservationDao).execute(reservation);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                subBookingDao.delete(subBooking);
            }
        });
    }

    public LiveData<List<SubBooking>> getAllSubBookings() {
        return subBookings;
    }
}
