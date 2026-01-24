package com.example.foodproj.data.mealdetails.datasource;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.example.foodproj.data.home.datasource.MealResponse;
import com.example.foodproj.data.home.datasource.MealsNetworkResponse;
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.data.mealsfilterd.datasource.MealsFilteredService;
import com.example.foodproj.network.Network;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealsDetailsRemoteData {
    private final MealDetailsService mealDetailsService;


    public MealsDetailsRemoteData(MealDetailsService mealDetailsService) {
        this.mealDetailsService = Network.getInstance().mealDetailsService;
    }
    public void getMealsDetails(MealsDetailsNetworkResponse mealsDetailsNetworkResponse, Map<String, String> filters) {
        mealDetailsService.getMealsByFilter(filters).enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meal = response.body().getMeals();
                    Log.i(TAG, "responseeee: " + response.body());

                    mealsDetailsNetworkResponse.onMealsDetailsSuccess(meal);

                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    mealsDetailsNetworkResponse.onMealsDetailsError("error , check network");
                } else {
                    mealsDetailsNetworkResponse.onMealsDetailsError("error , try later");

                }
            }
        });


    }
}
