package com.natali.mydiettracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.natali.mydiettracker.data.entry.EntryRepository;
import com.natali.mydiettracker.data.exercise.ExerciseRepository;
import com.natali.mydiettracker.data.food.FoodRepository;
import com.natali.mydiettracker.data.user.UserRepository;
import com.natali.mydiettracker.model.Entry;
import com.natali.mydiettracker.model.Exercise;
import com.natali.mydiettracker.model.Food;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EntryViewModel extends AndroidViewModel {
    private FoodRepository foodRepository;
    private EntryRepository entryRepository;
    private UserRepository userRepository;
    private ExerciseRepository exerciseRepository;

    public EntryViewModel(@NonNull Application application) {
        super(application);
        foodRepository = FoodRepository.getInstance();
        entryRepository=EntryRepository.getInstance(application);
        userRepository=UserRepository.getInstance(application);
        exerciseRepository = ExerciseRepository.getInstance(application);
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

    public void addWater(){
        String userId=userRepository.getCurrentUser().getValue().getUid();
        entryRepository.insert(new Entry(userId,"water","water",0));
    }

    public LiveData<Integer> getWaterAmount() {
        return entryRepository.getWaterForGivenDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
    }
    public String setDate(){
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

    public LiveData<List<Exercise>> getExercises(){

        return exerciseRepository.getAllExercises();
    }

    public LiveData<Exercise> getSearchedExercise() {
        return exerciseRepository.getSearchedExercise();
    }

    public void searchExerciseByName(String name) {
        exerciseRepository.searchExerciseByExercise(name);
    }

    public void addExerciseToDiary(Exercise exercise,double duration) {
        String userId=userRepository.getCurrentUser().getValue().getUid();
        entryRepository.insert(new Entry(userId,exercise.getExerciseName(),"exercise",duration*exercise.getCalorie()));
    }
}
