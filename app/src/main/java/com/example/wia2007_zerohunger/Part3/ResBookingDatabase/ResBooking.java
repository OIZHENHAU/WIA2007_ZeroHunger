package com.example.wia2007_zerohunger.Part3.ResBookingDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "reservation_booking_table")
public class ResBooking {
    @PrimaryKey(autoGenerate = true)
    private int resBookingId;
    private String resBookingName;
    private int resBookingImageId;
    private String resBookingLocation;
    private String resBookingDate;
    private String resBookingTimeSlots;
    private int resBookingNumParticipants;

    public ResBooking(String resBookingName, int resBookingImageId, String resBookingLocation, String resBookingDate,
                      String resBookingTimeSlots, int resBookingNumParticipants) {
        this.resBookingName = resBookingName;
        this.resBookingImageId = resBookingImageId;
        this.resBookingLocation = resBookingLocation;
        this.resBookingDate = resBookingDate;
        this.resBookingTimeSlots = resBookingTimeSlots;
        this.resBookingNumParticipants = resBookingNumParticipants;

    }

    public int getResBookingId() {
        return resBookingId;
    }

    public void setResBookingId(int resBookingId) {
        this.resBookingId = resBookingId;
    }

    public String getResBookingName() {
        return resBookingName;
    }

    public void setResBookingName(String resBookingName) {
        this.resBookingName = resBookingName;
    }

    public String getResBookingLocation() {
        return resBookingLocation;
    }

    public void setResBookingLocation(String resBookingLocation) {
        this.resBookingLocation = resBookingLocation;
    }

    public String getResBookingDate() {
        return resBookingDate;
    }

    public void setResBookingDate(String resBookingDate) {
        this.resBookingDate = resBookingDate;
    }

    public String getResBookingTimeSlots() {
        return resBookingTimeSlots;
    }

    public void setResBookingTimeSlots(String resBookingTimeSlots) {
        this.resBookingTimeSlots = resBookingTimeSlots;
    }

    public int getResBookingNumParticipants() {
        return resBookingNumParticipants;
    }

    public void setResBookingNumParticipants(int resBookingNumParticipants) {
        this.resBookingNumParticipants = resBookingNumParticipants;
    }

    public int getResBookingImageId() {
        return resBookingImageId;
    }

    public void setResBookingImageId(int resBookingImageId) {
        this.resBookingImageId = resBookingImageId;
    }
}
