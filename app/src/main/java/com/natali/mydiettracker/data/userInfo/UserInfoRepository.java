package com.natali.mydiettracker.data.userInfo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.natali.mydiettracker.data.exercise.ExerciseDatabase;
import com.natali.mydiettracker.model.UserInfo;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserInfoRepository {

    private static UserInfoRepository  instance;
    private final UserInfoDao userInfoDao;
    private final LiveData<List<UserInfo>> allUserInfo;
    private LiveData<UserInfo> userInfo;
    private final ExecutorService executorService;
    private String userId;

    public UserInfoRepository(Application application) {

        UserInfoDatabase userInfoDatabase=UserInfoDatabase.getInstance(application);
        userInfoDao=userInfoDatabase.userInfoDao();
        ExerciseDatabase database = ExerciseDatabase.getInstance(application);
        allUserInfo=userInfoDao.getAllUserInfo();
        userId=getUserId();
        userInfo=userInfoDao.getUserInfo(userId);
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized UserInfoRepository getInstance(Application application) {
        if (instance == null)
            instance = new UserInfoRepository(application);
        return instance;
    }

    public void init(String userId) {
        this.userId = userId;
    }

    public LiveData<List<UserInfo>> getAllUserInfo() {
        return allUserInfo;
    }

    public LiveData<UserInfo> getUserInfo() {
        return userInfo;
    }

    public LiveData<Double> getWeight() {
        return userInfoDao.getTargetWeight(userId);
    }

    public LiveData<Integer> getCalorie() {
        return userInfoDao.getDailyCalories(userId);
    }

    public void insert(UserInfo userInfo) {
        executorService.execute(() ->userInfoDao.insert(userInfo));
    }

    public void update(UserInfo userInfo){
        executorService.execute(() ->userInfoDao.update(userInfo));
    }

    public String getUserId() {
        return userId;
    }
}
