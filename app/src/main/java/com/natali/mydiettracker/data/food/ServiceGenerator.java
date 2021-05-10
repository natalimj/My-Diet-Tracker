package com.natali.mydiettracker.data.food;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static FoodApi foodApi;

    public static FoodApi getFoodApi() {
        if (foodApi == null) {
            foodApi = new Retrofit.Builder()
                    .baseUrl("https://api.calorieninjas.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(FoodApi.class);
        }
        return foodApi;
    }
}
