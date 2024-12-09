package com.example.wia2007_zerohunger.UserDatabase;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UserAccount.class}, version = 1)
public abstract class UserAccountDatabase extends RoomDatabase {

    private static UserAccountDatabase instance;

    public abstract UserAccountDAO userAccountDao();

    public static synchronized UserAccountDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            UserAccountDatabase.class, "user_account_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallBack)
                    .build();

        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //new PopulateDbAsyncTask(instance).execute();

            UserAccountDAO userAccountDao = instance.userAccountDao();

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    userAccountDao.insert(new UserAccount("zhenhau", "zhenhau8072@gmail.com",
                            "123456", 0));

                }
            });
        }
    };

}
