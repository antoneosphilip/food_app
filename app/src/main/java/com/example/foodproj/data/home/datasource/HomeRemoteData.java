package com.example.foodproj.data.home.datasource;


import com.example.foodproj.network.Network;

import java.io.IOException;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class HomeRemoteData{
    private HomeServices homeServices;
    public HomeRemoteData(){
        homeServices= Network.getInstance().homeServices;
    }
    public Observable<MealResponse> getMeals() {
       return homeServices.getRandomMealData();
    }

}
