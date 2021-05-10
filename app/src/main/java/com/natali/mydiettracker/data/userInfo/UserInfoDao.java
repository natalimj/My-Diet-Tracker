package com.natali.mydiettracker.data.userInfo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.natali.mydiettracker.model.UserInfo;

import java.util.List;

@Dao
public interface UserInfoDao {

    @Insert
    void insert(UserInfo userInfo);

    @Update
    void update(UserInfo userInfo);

    @Delete
    void delete(UserInfo userInfo);

    @Query("SELECT * FROM userInfo_table")
    LiveData<List<UserInfo>> getAllUserInfo();


    @Query("SELECT * FROM userInfo_table where userId=:id")
    LiveData<UserInfo> getUserInfo(String id);

    @Query("SELECT targetWeight FROM userInfo_table where userId=:id")
    LiveData<Double> getTargetWeight(String id);

    @Query("SELECT dailyCalories FROM userInfo_table where userId=:id")
    LiveData<Integer> getDailyCalories(String id);

}
