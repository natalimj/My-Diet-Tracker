package com.natali.mydiettracker.data.exercise;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.natali.mydiettracker.model.Exercise;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Insert
    void insert(Exercise exercise);

    @Query("SELECT * FROM exercise_table")
    LiveData<List<Exercise>> getAllExercises();


    @Query("SELECT * FROM exercise_table where exerciseName=:name")
    LiveData<Exercise> getExercise(String name);
}
