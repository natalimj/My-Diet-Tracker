package com.natali.mydiettracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.natali.mydiettracker.data.entry.EntryRepository;
import com.natali.mydiettracker.data.user.UserRepository;
import com.natali.mydiettracker.model.Entry;
import com.natali.mydiettracker.model.Exercise;
import com.natali.mydiettracker.model.Food;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {


    private EntryRepository entryRepository;
    private UserRepository userRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        entryRepository=EntryRepository.getInstance(application);
        userRepository=UserRepository.getInstance(application);
    }

  //  public LiveData<List<Entry>> getAllEntries(){  return entryRepository.getAllEntries(); }
/*
    //getting entries for today --food/exercise/water
    public LiveData<List<Entry>> getFoodsForToday(){
        return entryRepository.getFoodsForToday();
    }

    public LiveData<List<Entry>> getExercisesForToday(){
        return entryRepository.getExercisesForToday();
    }

    public LiveData<Integer> getWaterForToday(){
        return entryRepository.getWaterForToday();
    }
*/
}