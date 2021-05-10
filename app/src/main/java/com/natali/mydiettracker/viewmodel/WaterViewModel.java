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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WaterViewModel extends AndroidViewModel {

    private EntryRepository entryRepository;
    private UserRepository userRepository;
    private MutableLiveData<Integer> waterAmount;

    public WaterViewModel(@NonNull Application application) {
        super(application);
        entryRepository=EntryRepository.getInstance(application);
        userRepository=UserRepository.getInstance(application);
    }

    public String setDate(){
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

    public void addWater(){
        String userId=userRepository.getCurrentUser().getValue().getUid();
        entryRepository.insert(new Entry(userId,"water","water",0));
    }

    public LiveData<Integer> getWaterAmount() {
        return entryRepository.getWaterForGivenDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
    }
}
