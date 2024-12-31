package com.example.wia2007_zerohunger.Part3.SubBookingDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sub_booking_table")
public class SubBooking {
    @PrimaryKey(autoGenerate = true)
    private int subBookingId;
    private String subBookingName;
    private int subBookingImageId;
    private int subBookingPlanCode;
    private double subBookingPrice;

    public SubBooking(String subBookingName, int subBookingImageId, int subBookingPlanCode, double subBookingPrice) {
        this.subBookingName = subBookingName;
        this.subBookingImageId = subBookingImageId;
        this.subBookingPlanCode = subBookingPlanCode;
        this.subBookingPrice = subBookingPrice;
    }

    public int getSubBookingId() {
        return subBookingId;
    }

    public void setSubBookingId(int subBookingId) {
        this.subBookingId = subBookingId;
    }

    public String getSubBookingName() {
        return subBookingName;
    }

    public void setSubBookingName(String subBookingName) {
        this.subBookingName = subBookingName;
    }

    public int getSubBookingImageId() {
        return subBookingImageId;
    }

    public void setSubBookingImageId(int subBookingImageId) {
        this.subBookingImageId = subBookingImageId;
    }

    public int getSubBookingPlanCode() {
        return subBookingPlanCode;
    }

    public void setSubBookingPlanCode(int subBookingPlanCode) {
        this.subBookingPlanCode = subBookingPlanCode;
    }

    public double getSubBookingPrice() {
        return subBookingPrice;
    }

    public void setSubBookingPrice(double subBookingPrice) {
        this.subBookingPrice = subBookingPrice;
    }
}
