package com.example.wia2007_zerohunger.UserDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserAccountDAO {
    @Insert
    public void insert(UserAccount account);

    @Delete
    public void delete(UserAccount account);

    @Update
    public void update(UserAccount account);

    @Query("SELECT * FROM user_account")
    LiveData<List<UserAccount>> getAllUserAccount();

    @Query("SELECT * FROM user_account WHERE email = :email")
    LiveData<UserAccount> getUserAccountByEmail(String email);
}

