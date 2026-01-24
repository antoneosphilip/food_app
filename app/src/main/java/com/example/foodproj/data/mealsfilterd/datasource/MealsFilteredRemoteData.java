package com.example.foodproj.data.mealsfilterd.datasource;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.example.foodproj.data.categories.datasource.CategoriesMealsResponse;
import com.example.foodproj.data.mealsfilterd.model.MealsFiltered;
import com.example.foodproj.network.Network;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealsFilteredRemoteData {
    private final MealsFilteredService mealsFilteredService;

    public MealsFilteredRemoteData() {
        this.mealsFilteredService = Network.getInstance().mealsFilteredService;
    }

    public void getMealsFiltered(MealsFilteredNetworkResponse mealsFilteredNetworkResponse, Map<String, String> filters){
        mealsFilteredService.getMealsByFilter(filters).enqueue(new Callback<MealsFilteredResponse>() {
            @Override
            public void onResponse(Call<MealsFilteredResponse> call, Response<MealsFilteredResponse> response) {
                Log.i(TAG, "respssssfff"+response.body());
                List<MealsFiltered> mealsFilteredList = response.body() != null ? response.body().getMeals() : null;
                mealsFilteredNetworkResponse.onMealsFilteredSuccess(mealsFilteredList);
            }

            @Override
            public void onFailure(Call<MealsFilteredResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    mealsFilteredNetworkResponse.onMealsFilteredMealsError("error , check network");
                } else {
                    mealsFilteredNetworkResponse.onMealsFilteredMealsError("error , try later");

                }
            }
        });
    }
}
