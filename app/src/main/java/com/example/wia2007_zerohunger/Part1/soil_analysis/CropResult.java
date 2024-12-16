package com.example.wia2007_zerohunger.Part1.soil_analysis;

public class CropResult {
    private String cropName;
    private String cropPlantingTime;
    private String cropDescription;

    public CropResult(String cropName, String cropPlantingTime, String cropDescription) {
        this.cropName = cropName;
        this.cropPlantingTime = cropPlantingTime;
        this.cropDescription = cropDescription;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropPlantingTime() {
        return cropPlantingTime;
    }

    public void setCropPlantingTime(String cropPlantingTime) {
        this.cropPlantingTime = cropPlantingTime;
    }

    public String getCropDescription() {
        return cropDescription;
    }

    public void setCropDescription(String cropDescription) {
        this.cropDescription = cropDescription;
    }
}
