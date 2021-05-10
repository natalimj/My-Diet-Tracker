package com.natali.mydiettracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.natali.mydiettracker.data.entry.EntryRepository;
import com.natali.mydiettracker.data.user.UserRepository;
import com.natali.mydiettracker.model.Entry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProgressViewModel extends AndroidViewModel {

    private EntryRepository entryRepository;
    private UserRepository userRepository;

    public ProgressViewModel(@NonNull Application application) {
        super(application);
        entryRepository=EntryRepository.getInstance(application);
        userRepository=UserRepository.getInstance(application);
    }

    public LiveData<List<Entry>> getWeightForUser(){
        return entryRepository.getWeightListForUser();
    }

    public void addWeight(double weight){
        String userId=userRepository.getCurrentUser().getValue().getUid();
        entryRepository.insert(new Entry(userId,"weight","weight",weight));
    }

    public void updateWeight(double weight){
          String userId=userRepository.getCurrentUser().getValue().getUid();
        entryRepository.update(new Entry(userId,"weight","weight",weight));
    }

    public String setDate(){
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

    public LiveData<Entry> getWeightForGivenDate(String date){
      return  entryRepository.getWeightForGivenDate(date);
    }
}
