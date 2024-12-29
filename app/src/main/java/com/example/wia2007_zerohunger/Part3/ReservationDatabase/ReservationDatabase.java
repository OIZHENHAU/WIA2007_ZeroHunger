package com.example.wia2007_zerohunger.Part3.ReservationDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.wia2007_zerohunger.Part3.ConnectionDatabase.Connection;
import com.example.wia2007_zerohunger.Part3.ConnectionDatabase.ConnectionDAO;
import com.example.wia2007_zerohunger.Part3.ConnectionDatabase.ConnectionDatabase;
import com.example.wia2007_zerohunger.Part5.FinancialDatabase.Note;
import com.example.wia2007_zerohunger.Part5.FinancialDatabase.NoteDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Reservation.class}, version = 1)
public abstract class ReservationDatabase extends RoomDatabase {
    private static ReservationDatabase instance;

    public abstract ReservationDAO reservationDao();

    public static synchronized ReservationDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ReservationDatabase.class, "reservation_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallBack).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //new PopulateDbAsyncTask(instance).execute();

            ReservationDAO reservationDao = instance.reservationDao();

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    /*connectionDao.insert(new Connection(1, "Happy Farm", 13.5,
                            "Vegetable", 1, 2.5, 1, 5.6));
                    connectionDao.insert(new Connection(2, "Fresh Supermarket", 21.3,
                            "Fruit", 2, 3.9, 2, 4.9));
                    connectionDao.insert(new Connection(3, "Durian Orchard", 57.8,
                            "Dessert", 3, 69.9, 3, 89.9));
                    connectionDao.insert(new Connection(4,"TMG Industry", 104.5,
                            "Vegetable", 4, 29.5, 4, 55.6));

                     */
                    reservationDao.insert(new Reservation("Strawberry Picking",
                            "Enjoy strawberry picking experience at strawberry farm!", 5, 1,
                            "42nd Mile, Kea Farm, 39100, Malaysia", 20));
                    reservationDao.insert(new Reservation("Farm Volunteer",
                            "Volunteer while being in nature to have a taste of what we do in an urban farm.",
                            3.5, 2, "Lot 40187-40188, Jalan Prima Tropika Barat, Bandar Putra Permai, " +
                            "Seri Kembangan. 43300 Selangor Darul Ehsan. Malaysia", 30.5));
                    reservationDao.insert(new Reservation("Organic Farming Workshop",
                            "In this environment workshop, you will forge a connection with the soil and seeds.",
                            4.5, 3, "29, Jalan 19/15, Seksyen 19, 47300 Petaling Jaya, Selangor", 12.9));
                    reservationDao.insert(new Reservation("Farm Tour",
                            "Offer team building activities for children and adults, " +
                                    "such as treasure hunts, and tractor driving", 4, 4,
                            "Utama Farm Between Avanté Hotel & Shell Station, Persiaran Bandar Utama, Bandar Utama, " +
                                    "47800 Petaling Jaya, Selangor", 19.9));

                }
            });
        }
    };
}
