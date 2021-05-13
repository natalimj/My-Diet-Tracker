package com.natali.mydiettracker.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.google.firebase.auth.FirebaseUser;
import com.natali.mydiettracker.data.entry.EntryRepository;
import com.natali.mydiettracker.data.exercise.ExerciseRepository;
import com.natali.mydiettracker.data.user.UserRepository;
import com.natali.mydiettracker.data.userInfo.UserInfoRepository;
import com.natali.mydiettracker.model.Exercise;


public class MainActivityViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private EntryRepository entryRepository;
    private UserInfoRepository userInfoRepository;
    private ExerciseRepository exerciseRepository;


    public MainActivityViewModel(Application app){
        super(app);
        userRepository = UserRepository.getInstance(app);
        entryRepository=EntryRepository.getInstance(app);
        userInfoRepository=UserInfoRepository.getInstance(app);
        exerciseRepository = ExerciseRepository.getInstance(app);

    }

    public void init() {

        String userId = userRepository.getCurrentUser().getValue().getUid();
        entryRepository.init(userId);
        userInfoRepository.init(userId);

        /*
        if(exerciseRepository.getAllExercises().getValue()==null){
            exerciseRepository.insert(new Exercise("Cycling",5.5));
            exerciseRepository.insert(new Exercise("Tennis",6.0));
            exerciseRepository.insert(new Exercise("Running",11.5));
            exerciseRepository.insert(new Exercise("Swimming",7.8));
            exerciseRepository.insert(new Exercise("Aerobic",7.4));
            exerciseRepository.insert(new Exercise("Hiking",4.5));
            exerciseRepository.insert(new Exercise("Basketball",7.5));
            exerciseRepository.insert(new Exercise("Walking",6.5));
            exerciseRepository.insert(new Exercise("Golf",4.6));
            exerciseRepository.insert(new Exercise("Jogging",9.3));
        }
*/
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public void signOut() {
        userRepository.signOut();
    }
}