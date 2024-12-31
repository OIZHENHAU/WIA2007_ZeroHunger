package com.example.wia2007_zerohunger.Part3.SubBookingDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class SubBookingViewModel extends AndroidViewModel {

    private SubBookingRepository repository;
    private LiveData<List<SubBooking>> subBookings;

    public SubBookingViewModel(@NonNull Application application) {
        super(application);

        repository = new SubBookingRepository(application);
        subBookings = repository.getAllSubBookings();

    }

    public void insert(SubBooking subBooking) {
        repository.insert(subBooking);
    }

    public void update(SubBooking subBooking) { repository.update(subBooking); }

    public void delete(SubBooking subBooking) {
        repository.delete(subBooking);
    }

    public LiveData<List<SubBooking>> getAllSubBookings() {
        return subBookings;
    }

}
