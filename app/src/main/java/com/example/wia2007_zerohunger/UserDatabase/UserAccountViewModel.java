package com.example.wia2007_zerohunger.UserDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserAccountViewModel extends AndroidViewModel {

    private UserAccountRepository repository;
    private LiveData<List<UserAccount>> userAccountList;

    public UserAccountViewModel(@NonNull Application application) {
        super(application);

        repository = new UserAccountRepository(application);
        userAccountList = repository.getAllUserAccount();
    }

    public void insert(UserAccount account) {
        repository.insert(account);
    }

    public void update(UserAccount account) {
        repository.update(account);
    }

    public void delete(UserAccount account) {
        repository.delete(account);
    }

    public LiveData<List<UserAccount>> getAllUserAccount() {
        return userAccountList;
    }

    public LiveData<UserAccount> getUserAccountByEmail(String email) {
        return repository.getUserAccountByEmail(email);
    }
}

