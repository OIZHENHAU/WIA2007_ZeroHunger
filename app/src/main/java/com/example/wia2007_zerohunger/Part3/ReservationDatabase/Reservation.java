package com.example.wia2007_zerohunger.Part3.ReservationDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reservation_table")
public class Reservation {
    @PrimaryKey(autoGenerate = true)
    private int reservationId;
    private String reservationName;
    private String reservationDescription;
    private double rating;
    private int imageId;
    private String location;

    public Reservation(String reservationName, String reservationDescription, double rating, int imageId, String location) {
        this.reservationName = reservationName;
        this.reservationDescription = reservationDescription;
        this.rating = rating;
        this.imageId = imageId;
        this.location = location;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    public String getReservationDescription() {
        return reservationDescription;
    }

    public void setReservationDescription(String reservationDescription) {
        this.reservationDescription = reservationDescription;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
