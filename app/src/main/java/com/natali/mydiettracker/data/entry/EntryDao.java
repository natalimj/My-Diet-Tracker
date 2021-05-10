package com.natali.mydiettracker.data.entry;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.natali.mydiettracker.model.Entry;

import java.util.List;

@Dao
public interface EntryDao {

    @Insert
    void insert(Entry entry);

    @Update
    void update(Entry entry);

    @Delete
    void delete(Entry entry);

    @Query("DELETE FROM entry_table")
    void deleteAllEntries();

    @Query("SELECT * FROM entry_table where type='food' and userId=:id and dateString=:date")
    LiveData<List<Entry>> getAllFoodsForUser(String id,String date);

    @Query("SELECT * FROM entry_table  where type='exercise' and userId=:id and dateString=:date")
    LiveData<List<Entry>> getAllExercisesForUser(String id,String date);

    @Query("SELECT COUNT(*) FROM entry_table where type='water' and userId=:id and dateString=:date")
    LiveData<Integer> getWaterForUser(String id,String date);

    @Query("SELECT * FROM entry_table where type='weight' and userId=:id")
    LiveData<List<Entry>> getWeightListForUser(String id);

    @Query("SELECT * FROM entry_table where type='weight' and dateString=:date")
    LiveData<Entry> getWeight(String date);

    @Query("SELECT SUM(calorie) FROM entry_table where type='food' and dateString=:date")
    LiveData<Double> totalFoodCalories(String date);

    @Query("SELECT SUM(calorie) FROM entry_table where type='exercise' and dateString=:date")
    LiveData<Double> totalExerciseCalories(String date);

}
