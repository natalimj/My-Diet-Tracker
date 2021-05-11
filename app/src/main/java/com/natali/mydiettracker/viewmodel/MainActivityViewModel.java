package com.natali.mydiettracker.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.google.firebase.auth.FirebaseUser;
import com.natali.mydiettracker.data.entry.EntryRepository;
import com.natali.mydiettracker.data.exercise.ExerciseRepository;
import com.natali.mydiettracker.data.user.UserRepository;
import com.natali.mydiettracker.data.userInfo.UserInfoRepository;


public class MainActivityViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private EntryRepository entryRepository;
    private UserInfoRepository userInfoRepository;


    public MainActivityViewModel(Application app){
        super(app);
        userRepository = UserRepository.getInstance(app);
        entryRepository=EntryRepository.getInstance(app);
        userInfoRepository=UserInfoRepository.getInstance(app);
    }

    public void init() {
        String userId = userRepository.getCurrentUser().getValue().getUid();
        entryRepository.init(userId);
        userInfoRepository.init(userId);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public void signOut() {
        userRepository.signOut();
    }
}