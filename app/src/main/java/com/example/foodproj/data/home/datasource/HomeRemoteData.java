package com.example.foodproj.data.home.datasource;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.example.foodproj.data.home.model.Category;
import com.example.foodproj.data.home.model.Ingredient;
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.network.Network;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRemoteData{
    private HomeServices homeServices;
    public HomeRemoteData(){
        homeServices= Network.getInstance().homeServices;
    }
    public void getMeals(MealsNetworkResponse mealsNetworkResponse) {
        homeServices.getRandomMealData().enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meal = response.body().getMeals();
                    Log.i(TAG, "responseeee: " + response.body());

                    mealsNetworkResponse.onMealsSuccess(meal);

                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    mealsNetworkResponse.onMealsError("error , check network");
                } else {
                    mealsNetworkResponse.onMealsError("error , try later");

                }
            }
        });


    }


    public void getCategories (CategoryNetworkResponse categoryNetworkResponse){
        homeServices.getCategories().enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                List<Category> categories=response.body().getCategories();
                categoryNetworkResponse.onCategorySuccess(categories);
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    categoryNetworkResponse.onCategoryError("error , check network");
                } else {
                    categoryNetworkResponse.onCategoryError("error , try later");

                }
            }
        });
    }
}
