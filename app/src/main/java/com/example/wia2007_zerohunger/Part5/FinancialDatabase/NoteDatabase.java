package com.example.wia2007_zerohunger.Part5.FinancialDatabase;

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

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDAO noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallBack).build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //new PopulateDbAsyncTask(instance).execute();

            NoteDAO noteDao = instance.noteDao();

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    noteDao.insert(new Note("Mani Group Financial Aid",
                            500, 2, "02/12/2024", 1));
                    noteDao.insert(new Note("Wesley Financial Aid",
                            10200, 4, "15/12/2024", 2));
                    noteDao.insert(new Note("Hua Chai Aid",
                            15400, 7, "18/12/2024", 3));
                    noteDao.insert(new Note("Ahmad and Co Fund",
                            31900, 19, "22/12/2024", 4));
                    noteDao.insert(new Note("Rodrigo Cooperation Fund",
                            24700, 10, "29/12/2024", 5));
                }
            });
        }
    };

    /*private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private NoteDAO noteDao;

        private PopulateDbAsyncTask(NoteDatabase database) {
            noteDao = database.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            noteDao.insert(new Note("Title 1", "Description 1"));
            noteDao.insert(new Note("Title 2", "Description 2"));
            noteDao.insert(new Note("Title 3", "Description 3"));
            noteDao.insert(new Note("Title 4", "Description 4"));
            noteDao.insert(new Note("Title 5", "Description 5"));

            return null;
        }
    }
    */

}

