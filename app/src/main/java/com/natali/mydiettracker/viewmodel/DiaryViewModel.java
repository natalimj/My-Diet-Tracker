package com.natali.mydiettracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.natali.mydiettracker.data.entry.EntryRepository;
import com.natali.mydiettracker.data.userInfo.UserInfoRepository;
import com.natali.mydiettracker.model.Entry;

import java.util.List;

public class DiaryViewModel extends AndroidViewModel {

    private EntryRepository entryRepository;
    private UserInfoRepository userInfoRepository;

    private MutableLiveData<String> selectedDate;

    public DiaryViewModel(@NonNull Application application) {
        super(application);
        selectedDate=new MutableLiveData<>();
        entryRepository=EntryRepository.getInstance(application);
       userInfoRepository=UserInfoRepository.getInstance(application);
    }

    public void setSelectedDate(String strDate) {
        entryRepository.setSelectedDate(strDate);
        selectedDate.setValue(strDate);
    }


    public LiveData<Entry> getWeight(){
       return entryRepository.getWeightForGivenDate(selectedDate.getValue());
    }

     public LiveData<Integer> getDailyCalories() {
            return userInfoRepository.getCalorie();
        }

    public LiveData<Double> getTotalFoodCalories(String date){
        return  entryRepository.totalFoodCalories(date);
    }

    public  LiveData<Double> getTotalExerciseCalories(String date){
        return entryRepository.totalExerciseCalories(date);
    }

    public MutableLiveData<String> getSelectedDate() {
        return selectedDate;
    }

    //getting entries for selected day
    public LiveData<List<Entry>> getFoodsForUser(){
        return entryRepository.getFoodsForGivenDate(selectedDate.getValue());
    }
    public LiveData<List<Entry>> getExercisesForUser(){
        return entryRepository.getExercisesForGivenDate(selectedDate.getValue());
    }
    public LiveData<Integer> getWaterForUser(){
        return entryRepository.getWaterForGivenDate(selectedDate.getValue());
    }
}
