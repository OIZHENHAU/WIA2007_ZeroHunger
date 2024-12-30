package com.example.wia2007_zerohunger.Part3.SubscriptionDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Subscription.class}, version = 1)
public abstract class SubscriptionDatabase extends RoomDatabase {
    private static SubscriptionDatabase instance;

    public abstract SubscriptionDAO subscriptionDao();

    public static synchronized SubscriptionDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            SubscriptionDatabase.class, "subscription_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallBack).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //new PopulateDbAsyncTask(instance).execute();

            SubscriptionDAO subscriptionDao = instance.subscriptionDao();

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.execute(new Runnable() {
                @Override
                public void run() {

                    subscriptionDao.insert(new Subscription("Cabbage",1,
                            "Vegetables", 20, 35));
                    subscriptionDao.insert(new Subscription("Kangkung", 2,
                            "Vegetables", 13, 30));
                    subscriptionDao.insert(new Subscription("Carrot", 3,
                            "Vegetables", 15, 25));
                    subscriptionDao.insert(new Subscription("Banana", 4,
                            "Fruits", 20, 35));
                    subscriptionDao.insert(new Subscription("Fresh Milk", 5,
                            "Product", 40, 65));

                }
            });
        }
    };
}
