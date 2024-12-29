package com.example.wia2007_zerohunger.Part3.ReservationDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wia2007_zerohunger.Part3.ConnectionDatabase.Connection;
import com.example.wia2007_zerohunger.Part3.ConnectionDatabase.ConnectionRepository;

import java.util.List;
public class ReservationViewModel extends AndroidViewModel {

    private ReservationRepository repository;
    private LiveData<List<Reservation>> reservations;

    public ReservationViewModel(@NonNull Application application) {
        super(application);

        repository = new ReservationRepository(application);
        reservations = repository.getAllReservations();

    }

    public void insert(Reservation reservation) {
        repository.insert(reservation);
    }

    public void update(Reservation reservation) {
        repository.update(reservation);
    }

    public void delete(Reservation reservation) {
        repository.delete(reservation);
    }

    public LiveData<List<Reservation>> getAllReservations() {
        return reservations;
    }
}
