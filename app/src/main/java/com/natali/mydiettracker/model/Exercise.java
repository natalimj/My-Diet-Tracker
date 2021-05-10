package com.natali.mydiettracker.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise_table")
public class Exercise {

    @PrimaryKey
    @NonNull
    private String exerciseName;
    private double calorie;

    public Exercise(String exerciseName, double calorie) {
        this.exerciseName = exerciseName;
        this.calorie = calorie;
    }


    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }


}
