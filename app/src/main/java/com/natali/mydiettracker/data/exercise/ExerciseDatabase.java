package com.natali.mydiettracker.data.exercise;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.natali.mydiettracker.model.Exercise;

@Database(entities = {Exercise.class}, version =1 )
public abstract class ExerciseDatabase extends RoomDatabase {

    private static ExerciseDatabase instance;

    public abstract ExerciseDao exerciseDao();

    public static synchronized ExerciseDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context,
                    ExerciseDatabase.class, "exercise_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}