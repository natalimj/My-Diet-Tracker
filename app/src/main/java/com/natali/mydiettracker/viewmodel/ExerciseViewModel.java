package com.natali.mydiettracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.natali.mydiettracker.data.entry.EntryRepository;
import com.natali.mydiettracker.data.exercise.ExerciseRepository;
import com.natali.mydiettracker.data.user.UserRepository;
import com.natali.mydiettracker.model.Entry;
import com.natali.mydiettracker.model.Exercise;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ExerciseViewModel extends AndroidViewModel {

    private ExerciseRepository exerciseRepository;
    private EntryRepository entryRepository;
    private UserRepository userRepository;

    public ExerciseViewModel(@NonNull Application application) {
        super(application);
        exerciseRepository = ExerciseRepository.getInstance(application);
        entryRepository=EntryRepository.getInstance(application);
        userRepository= UserRepository.getInstance(application);
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
