package com.example.wia2007_zerohunger.Part3.ConnectionDatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionRepository {
    private ConnectionDAO connectionDao;
    private LiveData<List<Connection>> connections;

    ExecutorService executors = Executors.newSingleThreadExecutor();

    public ConnectionRepository(Application application) {
        ConnectionDatabase database = ConnectionDatabase.getInstance(application);
        connectionDao = database.connectionDao();
        connections = connectionDao.getAllConnections();

    }

    public void insert(Connection connection) {
        //new InsertConnectionAsyncTask(connectionDao).execute(connection);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                connectionDao.insert(connection);
            }
        });
    }

    public void update(Connection connection) {
        //new UpdateConnectionAsyncTask(connectionDao).execute(connection);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                connectionDao.update(connection);
            }
        });
    }

    public void delete(Connection connection) {
        //new DeleteConnectionAsyncTask(connectionDao).execute(connection);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                connectionDao.delete(connection);
            }
        });
    }

    public LiveData<List<Connection>> getAllConnections() {
        return connections;
    }

}
