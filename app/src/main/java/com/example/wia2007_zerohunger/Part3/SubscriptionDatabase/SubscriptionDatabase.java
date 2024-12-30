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
                            "Vegetable", "Jalan 20/16, Taman Paramount, 46300 Petaling Jaya, Selangor",
                            20, 35));
                    subscriptionDao.insert(new Subscription("Kangkung", 2,
                            "Vegetable", "S33, Second Floor Encorp Strand Mall, Kota Damansara, 47810 Petaling Jaya, Selangor",
                            13, 30));
                    subscriptionDao.insert(new Subscription("Carrot", 3,
                            "Vegetable", "Jln Bukit Bintang, Bukit Bintang, 55100 Kuala Lumpur, Federal Territory of Kuala Lumpur",
                            15, 25));
                    subscriptionDao.insert(new Subscription("Banana", 4,
                            "Fruit", "Jalan 1/127 Basement 2, Off Jalan Kuchai Lama Kuchai Entrepreneurs Park 57100 Kuala Lumpur Malaysia",
                            20, 35));
                    subscriptionDao.insert(new Subscription("Fresh Milk", 5,
                            "Product", "No. 20, Jalan Ss24/13 47301 Petaling Jaya, Selangor Malaysia",
                            40, 65));

                }
            });
        }
    };
}
