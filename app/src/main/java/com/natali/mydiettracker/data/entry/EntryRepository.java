package com.natali.mydiettracker.data.entry;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.firebase.database.FirebaseDatabase;
import com.natali.mydiettracker.model.Entry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EntryRepository {
    private static EntryRepository instance;
    private final EntryDao entryDao;

    private final ExecutorService executorService;
    private String userId;
    private String selectedDate;

    private EntryRepository(Application application) {
        EntryDatabase database =  EntryDatabase.getInstance(application);
        entryDao = database.entryDao();
        userId=getUserId();
        selectedDate=getSelectedDate();
        executorService = Executors.newFixedThreadPool(2);
    }

    public void init(String userId) {
        this.userId=userId;
    }

    public static synchronized EntryRepository getInstance(Application application) {
        if (instance == null)
            instance = new EntryRepository(application);
        return instance;
    }

    public String getSelectedDate() {
        return selectedDate;
    }
    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    //for given date

    public LiveData<List<Entry>> getFoodsForGivenDate(String date){
        return entryDao.getAllFoodsForUser(userId,date);
    }

    public  LiveData<List<Entry>> getExercisesForGivenDate(String date){
        return   entryDao.getAllExercisesForUser(userId,date);
    }

    public LiveData<Integer> getWaterForGivenDate(String date){
        return  entryDao.getWaterForUser(userId,date);
    }

    public LiveData<Entry> getWeightForGivenDate(String date){
        return entryDao.getWeight(date);
    }

    public LiveData<List<Entry>> getWeightListForUser() {
        return entryDao.getWeightListForUser(userId);
    }

    public void insert(Entry entry) {
        executorService.execute(() -> entryDao.insert(entry));
    }

    public void update(Entry entry) {
        executorService.execute(() -> entryDao.update(entry));
    }

    public void deleteAllEntries() {
        executorService.execute(entryDao::deleteAllEntries);
    }

    public String getUserId() {
        return userId;
    }

    public LiveData<Double> totalFoodCalories(String date){
      return  entryDao.totalFoodCalories(date);
    }

    public LiveData<Double> totalExerciseCalories(String date){
       return entryDao.totalExerciseCalories(date);
    }

}
