package com.example.foodproj.data.home.datasource;

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
    public void getMeals(HomeNetworkResponse homeNetworkResponse){
        homeServices.getRandomMealData().enqueue(new Callback<Meal>() {
            @Override
            public void onResponse(Call<Meal> call, Response<Meal> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Meal meal=response.body();
                    homeNetworkResponse.onSuccess(meal);

                }
            }

            @Override
            public void onFailure(Call<Meal> call, Throwable t) {
                if(t instanceof IOException){
                    homeNetworkResponse.onError("error , check network");
                }
                else{
                    homeNetworkResponse.onError("error , try later");

                }
            }
        });
    }
}
