package com.example.wia2007_zerohunger.UserDatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserAccountRepository {

    private UserAccountDAO userAccountDao;
    private LiveData<List<UserAccount>> userAccountList;

    ExecutorService executors = Executors.newSingleThreadExecutor();

    public UserAccountRepository(Application application) {
        UserAccountDatabase database = UserAccountDatabase.getInstance(application);
        userAccountDao = database.userAccountDao();
        userAccountList = userAccountDao.getAllUserAccount();

    }

    public void insert(UserAccount account) {
        //new InsertNoteAsyncTask(noteDao).execute(note);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                userAccountDao.insert(account);
            }
        });
    }

    public void update(UserAccount account) {
        //new UpdateNoteAsyncTask(noteDao).execute(note);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                userAccountDao.update(account);
            }
        });
    }

    public void delete(UserAccount account) {
        //new DeleteNoteAsyncTask(noteDao).execute(note);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                userAccountDao.delete(account);
            }
        });
    }

    public LiveData<List<UserAccount>> getAllUserAccount() {
        return userAccountList;
    }

    public LiveData<UserAccount> getUserAccountByEmail(String email) {
        return userAccountDao.getUserAccountByEmail(email);
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

