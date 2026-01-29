package com.example.foodproj.data.home.datasource;

import com.example.foodproj.data.home.model.Category;
import com.example.foodproj.data.home.model.Meal;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeServices {
    @GET("random.php")
    Call<MealResponse> getRandomMealData();

    @GET("categories.php")
    Call<CategoriesResponse> getCategories();


}
