package com.natali.mydiettracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.natali.mydiettracker.data.user.UserRepository;
import com.natali.mydiettracker.data.userInfo.UserInfoRepository;
import com.natali.mydiettracker.model.UserInfo;

public class PlanViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private UserInfoRepository userInfoRepository;

    public PlanViewModel(@NonNull Application application) {
        super(application);
        userRepository=UserRepository.getInstance(application);
        userInfoRepository=UserInfoRepository.getInstance(application);
    }

    public void addUserInfo(UserInfo userInfo){
        userInfo.setUserId(userRepository.getCurrentUser().getValue().getUid());
        userInfo.setTargetWeight(calculateTargetWeight(userInfo));
        userInfo.setDailyCalories(calculateDailyCalories(userInfo));
        userInfoRepository.insert(userInfo);
    }

    public double calculateTargetWeight(UserInfo userInfo){

        double target;
        if(userInfo.getGender().equalsIgnoreCase("male")){
            target= 50+ (0.91*(userInfo.getHeight()-152.4));
        }
        else{
            target= 45.5 + (0.91*(userInfo.getHeight()-152.4));
        }
        return target;
    }


    public int calculateDailyCalories(UserInfo userInfo ){

        double bmr;
        double activityEffect = 0;

        switch (userInfo.getActivityLevel()){
            case "Lightly active":
                activityEffect=1.375;
                break;
            case "Moderately active":
                activityEffect=1.55;
                break;
            case "Active":
                activityEffect=1.725;
                break;
            case "Very Active":
                activityEffect=1.9;
                break;
        }

        if(userInfo.getGender().equalsIgnoreCase("male")){
            bmr=66.47 + (13.75*userInfo.getStartWeight()) + (5.003*userInfo.getHeight()) - (6.755*userInfo.getAge());
        }
        else{
            bmr=65.51  + (9.563 *userInfo.getStartWeight()) + (1.850*userInfo.getHeight()) - (4.676*userInfo.getAge());
        }
        return (int) (activityEffect*bmr);
    }

    public LiveData<Integer> getDailyCalories() {
        return userInfoRepository.getCalorie();
    }

    public LiveData<Double> getTargetWeight() {
        return userInfoRepository.getWeight();
    }

   public void updateUserInfo(UserInfo userInfo){
       userInfo.setUserId(userRepository.getCurrentUser().getValue().getUid());
       userInfo.setTargetWeight(calculateTargetWeight(userInfo));
       userInfo.setDailyCalories(calculateDailyCalories(userInfo));
       userInfoRepository.update(userInfo);
   }

}

