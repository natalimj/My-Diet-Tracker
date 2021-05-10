package com.natali.mydiettracker.data.food;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface FoodApi {

    @Headers("X-Api-Key: utiRWgabvjCoCLCm2DeXpsqkOd4fbQvAWq4U1Gvd")
    @GET ("v1/nutrition")
    Call<FoodResponse> getFood(@Query("query") String name);

}
