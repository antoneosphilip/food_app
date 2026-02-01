package com.example.foodproj.data.home.datasource;


import com.example.foodproj.network.Network;

import java.io.IOException;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class HomeRemoteData{
    private HomeServices homeServices;
    public HomeRemoteData(){
        homeServices= Network.getInstance().homeServices;
    }
    public Single<MealResponse> getMeals() {
       return homeServices.getRandomMealData();
    }

}
