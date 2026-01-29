package com.example.foodproj.data.mealsfilterd.datasource;

import com.example.foodproj.data.categories.datasource.CategoriesMealsResponse;
import com.example.foodproj.data.mealsfilterd.model.MealsFiltered;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MealsFilteredService {
    @GET("filter.php")
    Call<MealsFilteredResponse> getMealsByFilter(
            @QueryMap Map<String, String> filters
    );
}
