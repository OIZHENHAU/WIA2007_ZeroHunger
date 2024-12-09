package com.example.wia2007_zerohunger.UserDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_account")
public class UserAccount {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String email;
    private String password;
    private double amount;

    public UserAccount(String name, String email, String password, double amount) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
