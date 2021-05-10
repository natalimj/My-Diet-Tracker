package com.natali.mydiettracker.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "entry_table")
public class Entry {

    @PrimaryKey(autoGenerate = true)
    private int entryId;
    private String userId;
    private String name;
    private String type;
    private double calorie;
    private String dateString;


    public Entry(String userId, String name, String type, double calorie) {
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.calorie = calorie;
        dateString=new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    }

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}
