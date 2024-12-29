package com.example.wia2007_zerohunger.Part3.ConnectionDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.wia2007_zerohunger.Part5.FinancialDatabase.Note;
import com.example.wia2007_zerohunger.Part5.FinancialDatabase.NoteDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Connection.class}, version = 1)
public abstract class ConnectionDatabase extends RoomDatabase {

    private static ConnectionDatabase instance;

    public abstract ConnectionDAO connectionDao();

    public static synchronized ConnectionDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ConnectionDatabase.class, "connection_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallBack).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //new PopulateDbAsyncTask(instance).execute();

            ConnectionDAO connectionDao = instance.connectionDao();

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    connectionDao.insert(new Connection(1, "Happy Farm", 13.5,
                            "Vegetable", 1, 2.5, 1, 5.6));
                    connectionDao.insert(new Connection(2, "Fresh Supermarket", 21.3,
                            "Fruit", 2, 3.9, 2, 4.9));
                    connectionDao.insert(new Connection(3, "Durian Orchard", 57.8,
                            "Dessert", 3, 69.9, 3, 89.9));
                    connectionDao.insert(new Connection(4,"TMG Industry", 104.5,
                            "Vegetable", 4, 29.5, 4, 55.6));

                }
            });
        }
    };
}
