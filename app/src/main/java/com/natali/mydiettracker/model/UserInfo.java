package com.natali.mydiettracker.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "userInfo_table")
public class UserInfo {

    @PrimaryKey
    @NonNull
    private String userId;
    private String gender;
    private int age;
    private double startWeight;
    private double targetWeight;
    private double height;
    private String activityLevel;
    private int dailyCalories;

    public UserInfo(String userId, String gender, int age, double startWeight, double targetWeight, double height, String activityLevel, int dailyCalories) {
        this.userId = userId;
        this.gender = gender;
        this.age = age;
        this.startWeight = startWeight;
        this.targetWeight = targetWeight;
        this.height = height;
        this.activityLevel = activityLevel;
        this.dailyCalories = dailyCalories;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(double startWeight) {
        this.startWeight = startWeight;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public int getDailyCalories() {
        return dailyCalories;
    }

    public void setDailyCalories(int dailyCalories) {
        this.dailyCalories = dailyCalories;
    }
}
