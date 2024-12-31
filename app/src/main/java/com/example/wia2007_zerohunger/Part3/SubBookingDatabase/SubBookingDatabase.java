package com.example.wia2007_zerohunger.Part3.SubBookingDatabase;

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

@Database(entities = {SubBooking.class}, version = 1)
public abstract class SubBookingDatabase extends RoomDatabase {
    private static SubBookingDatabase instance;

    public abstract SubBookingDAO subBookingDao();

    public static synchronized SubBookingDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            SubBookingDatabase.class, "sub_booking_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallBack).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //new PopulateDbAsyncTask(instance).execute();

            SubBookingDAO subBookingDao = instance.subBookingDao();

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.execute(new Runnable() {
                @Override
                public void run() {


                }
            });
        }
    };
}
