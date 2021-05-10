package com.natali.mydiettracker.data.userInfo;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.natali.mydiettracker.data.exercise.ExerciseDao;
import com.natali.mydiettracker.data.exercise.ExerciseDatabase;
import com.natali.mydiettracker.model.Entry;
import com.natali.mydiettracker.model.UserInfo;

@Database(entities = {UserInfo.class}, version = 1)
public abstract class UserInfoDatabase extends RoomDatabase {

    private static UserInfoDatabase instance;

    public abstract UserInfoDao userInfoDao();

    public static synchronized UserInfoDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context,
                    UserInfoDatabase.class, "userInfo_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
