package com.natali.mydiettracker.data.food;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.natali.mydiettracker.model.Food;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class FoodRepository {

    private static FoodRepository instance;
    private final MutableLiveData<Food> searchedFood;

    public FoodRepository() {
        this.searchedFood = new MutableLiveData<>();
    }


    public static synchronized FoodRepository getInstance() {
        if (instance == null) {
            instance = new FoodRepository();
        }
        return instance;
    }

    public LiveData<Food> getSearchedFood() {
        return searchedFood;
    }


     public void searchForFoodByName(String name) {
        FoodApi foodApi = ServiceGenerator.getFoodApi();
        Call<FoodResponse> call = foodApi.getFood(name);
        call.enqueue(new Callback<FoodResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                if (response.isSuccessful()) {
                    searchedFood.setValue(response.body().getFood());
                }
                else{
                    System.out.println("response is not successful!");
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }
}
