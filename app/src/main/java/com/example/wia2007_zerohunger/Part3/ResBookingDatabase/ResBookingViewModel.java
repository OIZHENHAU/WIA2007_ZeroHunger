package com.example.wia2007_zerohunger.Part3.ResBookingDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;
public class ResBookingViewModel extends AndroidViewModel {
    private ResBookingRepository repository;
    private LiveData<List<ResBooking>> resBookings;

    public ResBookingViewModel(@NonNull Application application) {
        super(application);

        repository = new ResBookingRepository(application);
        resBookings = repository.getAllResBookings();

    }

    public void insert(ResBooking booking) {
        repository.insert(booking);
    }

    public void update(ResBooking booking) { repository.update(booking); }

    public void delete(ResBooking booking) {
        repository.delete(booking);
    }

    public LiveData<List<ResBooking>> getAllResBookings() {
        return resBookings;
    }
}
