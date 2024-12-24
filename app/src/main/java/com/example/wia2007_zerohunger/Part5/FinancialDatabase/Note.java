package com.example.wia2007_zerohunger.Part5.FinancialDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int aidId;
    public String aidName;
    public int aidAmount;
    public int aidSlots;
    public String aidDateLine;

    public int imageID;

    public Note(String aidName, int aidAmount, int aidSlots, String aidDateLine, int imageID) {
        this.aidName = aidName;
        this.aidAmount = aidAmount;
        this.aidSlots = aidSlots;
        this.aidDateLine = aidDateLine;
        this.imageID = imageID;
    }

    public int getAidId() {
        return aidId;
    }

    public void setAidId(int aidId) {
        this.aidId = aidId;
    }

    public String getAidName() {
        return aidName;
    }

    public void setAidName(String aidName) {
        this.aidName = aidName;
    }

    public int getAidAmount() {
        return aidAmount;
    }

    public void setAidAmount(int aidAmount) {
        this.aidAmount = aidAmount;
    }

    public int getAidSlots() {
        return aidSlots;
    }

    public void setAidSlots(int aidSlots) {
        this.aidSlots = aidSlots;
    }

    public String getAidDateLine() {
        return aidDateLine;
    }

    public void setAidDateLine(String aidDateLine) {
        this.aidDateLine = aidDateLine;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
