package com.natali.mydiettracker.data.exercise;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.natali.mydiettracker.model.Exercise;
import com.natali.mydiettracker.model.Food;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExerciseRepository {

    private static ExerciseRepository instance;
    private final ExerciseDao exerciseDao;
    private final LiveData<List<Exercise>> allExercises;
    private final ExecutorService executorService;
    private final MutableLiveData<Exercise> searchedExercise;

    private ExerciseRepository(Application application) {
        ExerciseDatabase database = ExerciseDatabase.getInstance(application);
        exerciseDao = database.exerciseDao();
        searchedExercise= new MutableLiveData<>();
        allExercises = exerciseDao.getAllExercises();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized ExerciseRepository getInstance(Application application) {
        if (instance == null)
            instance = new ExerciseRepository(application);
        return instance;
    }

    public void insert(Exercise exercise) {
        executorService.execute(() -> exerciseDao.insert(exercise));
    }

    public MutableLiveData<Exercise> getSearchedExercise() {
        return searchedExercise;
    }

    public LiveData<List<Exercise>> getAllExercises() {
        return allExercises;
    }


    public void searchExerciseByExercise(String name){

        if(allExercises.getValue()!=null)
        {
            for(Exercise exercise:allExercises.getValue()){
                if(exercise.getExerciseName().equalsIgnoreCase(name)){
                    searchedExercise.setValue(exercise);
                }
            }
        }
    }
}