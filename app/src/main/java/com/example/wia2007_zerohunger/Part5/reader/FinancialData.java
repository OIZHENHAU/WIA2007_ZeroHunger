package com.example.wia2007_zerohunger.Part5.reader;

public class FinancialData {
    private String year;
    private String month;
    private String fips;
    private String locality;
    private String householdsServed;
    private String individualsServed;
    private String poundsOfFoodDistributed;
    private double lat;
    private double lon;

    public FinancialData(String year, String month, String fips, String locality, String householdsServed, String individualsServed, String poundsOfFoodDistributed, double lat, double lon) {
        this.year = year;
        this.month = month;
        this.fips = fips;
        this.locality = locality;
        this.householdsServed = householdsServed;
        this.individualsServed = individualsServed;
        this.poundsOfFoodDistributed = poundsOfFoodDistributed;
        this.lat = lat;
        this.lon = lon;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getFips() {
        return fips;
    }

    public void setFips(String fips) {
        this.fips = fips;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getHouseholdsServed() {
        return householdsServed;
    }

    public void setHouseholdsServed(String householdsServed) {
        this.householdsServed = householdsServed;
    }

    public String getIndividualsServed() {
        return individualsServed;
    }

    public void setIndividualsServed(String individualsServed) {
        this.individualsServed = individualsServed;
    }

    public String getPoundsOfFoodDistributed() {
        return poundsOfFoodDistributed;
    }

    public void setPoundsOfFoodDistributed(String poundsOfFoodDistributed) {
        this.poundsOfFoodDistributed = poundsOfFoodDistributed;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
