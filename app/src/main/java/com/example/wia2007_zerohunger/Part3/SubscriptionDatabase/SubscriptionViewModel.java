package com.example.wia2007_zerohunger.Part3.SubscriptionDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;
public class SubscriptionViewModel extends AndroidViewModel {
    private SubscriptionRepository repository;
    private LiveData<List<Subscription>> subscriptions;

    public SubscriptionViewModel(@NonNull Application application) {
        super(application);

        repository = new SubscriptionRepository(application);
        subscriptions = repository.getAllSubscriptions();

    }

    public void insert(Subscription subscription) {
        repository.insert(subscription);
    }

    public void update(Subscription subscription) { repository.update(subscription); }

    public void delete(Subscription subscription) {
        repository.delete(subscription);
    }

    public LiveData<List<Subscription>> getAllSubscriptions() {
        return subscriptions;
    }
}
