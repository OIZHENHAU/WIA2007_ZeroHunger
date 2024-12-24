package com.example.wia2007_zerohunger.Part5.FinancialDatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {

    private NoteDAO noteDao;
    private LiveData<List<Note>> notes;

    ExecutorService executors = Executors.newSingleThreadExecutor();

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        notes = noteDao.getAllNotes();

    }

    public void insert(Note note) {
        //new InsertNoteAsyncTask(noteDao).execute(note);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.insert(note);
            }
        });
    }

    public void update(Note note) {
        //new UpdateNoteAsyncTask(noteDao).execute(note);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.update(note);
            }
        });
    }

    public void delete(Note note) {
        //new DeleteNoteAsyncTask(noteDao).execute(note);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.delete(note);
            }
        });
    }

    public LiveData<List<Note>> getAllNotes() {
        return notes;
    }

    /*private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDAO noteDao;

        private InsertNoteAsyncTask(NoteDAO noteDao) {

        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.insert(notes[0]);
            return null;
        }

    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDAO noteDao;

        private UpdateNoteAsyncTask(NoteDAO noteDao) {

        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDAO noteDao;

        private DeleteNoteAsyncTask(NoteDAO noteDao) {

        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.delete(notes[0]);
            return null;
        }
    }
    */

}

