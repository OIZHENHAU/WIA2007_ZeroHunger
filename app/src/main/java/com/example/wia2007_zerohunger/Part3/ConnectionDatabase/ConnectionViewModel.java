package com.example.wia2007_zerohunger.Part3.ConnectionDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ConnectionViewModel extends AndroidViewModel {
    private ConnectionRepository repository;
    private LiveData<List<Connection>> connections;

    public ConnectionViewModel(@NonNull Application application) {
        super(application);

        repository = new ConnectionRepository(application);
        connections = repository.getAllConnections();

    }

    public void insert(Connection connection) {
        repository.insert(connection);
    }

    public void update(Connection connection) {
        repository.update(connection);
    }

    public void delete(Connection connection) {
        repository.delete(connection);
    }

    public LiveData<List<Connection>> getAllConnections() {
        return connections;
    }

}
