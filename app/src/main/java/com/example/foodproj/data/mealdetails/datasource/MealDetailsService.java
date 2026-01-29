package com.example.foodproj.data.mealdetails.datasource;

import com.example.foodproj.data.home.datasource.MealResponse;
import com.example.foodproj.data.mealsfilterd.datasource.MealsFilteredResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MealDetailsService {
    @GET("lookup.php")
    Call<MealResponse> getMealsByFilter(
            @QueryMap Map<String, String> filters
    );
}
