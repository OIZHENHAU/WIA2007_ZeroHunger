package com.example.wia2007_zerohunger.Part3.SubscriptionDatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SubscriptionRepository {
    private SubscriptionDAO subscriptionDao;
    private LiveData<List<Subscription>> subscriptions;

    ExecutorService executors = Executors.newSingleThreadExecutor();

    public SubscriptionRepository(Application application) {
        SubscriptionDatabase database = SubscriptionDatabase.getInstance(application);
        subscriptionDao = database.subscriptionDao();
        subscriptions = subscriptionDao.getAllSubscriptions()   ;

    }

    public void insert(Subscription subscription) {
        //new InsertReservationAsyncTask(reservationDao).execute(reservation);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                subscriptionDao.insert(subscription);
            }
        });
    }

    public void update(Subscription subscription) {
        //new UpdateReservationAsyncTask(reservationDao).execute(reservation);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                subscriptionDao.update(subscription);
            }
        });
    }

    public void delete(Subscription subscription) {
        //new DeleteReservationAsyncTask(reservationDao).execute(reservation);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                subscriptionDao.delete(subscription);
            }
        });
    }

    public LiveData<List<Subscription>> getAllSubscriptions() {
        return subscriptions;
    }
}
