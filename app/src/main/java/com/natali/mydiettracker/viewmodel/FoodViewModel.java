package com.natali.mydiettracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.natali.mydiettracker.data.entry.EntryRepository;
import com.natali.mydiettracker.data.food.FoodRepository;
import com.natali.mydiettracker.data.user.UserRepository;
import com.natali.mydiettracker.model.Entry;
import com.natali.mydiettracker.model.Food;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodViewModel extends AndroidViewModel {


    private FoodRepository foodRepository;
    private EntryRepository entryRepository;
    private UserRepository userRepository;
    private MutableLiveData<Double> totalCalories;

    public FoodViewModel(@NonNull Application application) {
        super(application);
        foodRepository = FoodRepository.getInstance();
        entryRepository=EntryRepository.getInstance(application);
        userRepository=UserRepository.getInstance(application);
    }

    public LiveData<Food> getSearchedFood() {
        return foodRepository.getSearchedFood();
    }

    public void searchForFoodByName(String name) {
        foodRepository.searchForFoodByName(name);
    }

    public void addFoodToDiary(Food food){
        String userId=userRepository.getCurrentUser().getValue().getUid();
        entryRepository.insert(new Entry(userId,food.getName(),"food",food.getCalorie()));
    }

    public String setDate(){
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

}
