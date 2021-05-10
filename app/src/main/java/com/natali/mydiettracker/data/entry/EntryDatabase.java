package com.natali.mydiettracker.data.entry;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import com.natali.mydiettracker.model.Entry;

import java.util.List;

@Database(entities = {Entry.class}, version = 2)
public abstract class EntryDatabase extends RoomDatabase {
    private static EntryDatabase instance;

    public abstract EntryDao entryDao();

    public static synchronized EntryDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context,
                    EntryDatabase.class, "entry_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
