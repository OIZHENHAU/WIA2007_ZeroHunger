package com.example.wia2007_zerohunger.Part1.soil_analysis;

public class CropData {
    private int N;
    private int P;
    private int K;
    private double temperature;
    private double humidity;
    private double ph;

    private double rainfall;
    private String label;

    public CropData(int n, int p, int k, double temperature, double humidity, double ph, double rainfall, String label) {
        N = n;
        P = p;
        K = k;
        this.temperature = temperature;
        this.humidity = humidity;
        this.ph = ph;
        this.rainfall = rainfall;
        this.label = label;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public int getP() {
        return P;
    }

    public void setP(int p) {
        P = p;
    }

    public int getK() {
        return K;
    }

    public void setK(int k) {
        K = k;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public double getRainfall() {
        return rainfall;
    }

    public void setRainfall(double rainfall) {
        this.rainfall = rainfall;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
