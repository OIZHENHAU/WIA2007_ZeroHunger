package com.example.wia2007_zerohunger.Part3.SubscriptionDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "subscription_table")
public class Subscription {
    @PrimaryKey(autoGenerate = true)
    private int subscriptionId;
    private String subscriptionName;
    private int subscriptionImageId;
    private String subscriptionCategory;
    private double weeklyPrice;
    private double monthlyPrice;

    public Subscription(String subscriptionName, int subscriptionImageId, String subscriptionCategory,
                        double weeklyPrice, double monthlyPrice) {
        this.subscriptionName = subscriptionName;
        this.subscriptionImageId = subscriptionImageId;
        this.subscriptionCategory = subscriptionCategory;
        this.weeklyPrice = weeklyPrice;
        this.monthlyPrice = monthlyPrice;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public int getSubscriptionImageId() {
        return subscriptionImageId;
    }

    public void setSubscriptionImageId(int subscriptionImageId) {
        this.subscriptionImageId = subscriptionImageId;
    }

    public String getSubscriptionCategory() {
        return subscriptionCategory;
    }

    public void setSubscriptionCategory(String subscriptionCategory) {
        this.subscriptionCategory = subscriptionCategory;
    }

    public double getWeeklyPrice() {
        return weeklyPrice;
    }

    public void setWeeklyPrice(double weeklyPrice) {
        this.weeklyPrice = weeklyPrice;
    }

    public double getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(double monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }
}
