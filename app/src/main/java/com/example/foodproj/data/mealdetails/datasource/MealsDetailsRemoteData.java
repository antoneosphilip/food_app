package com.example.foodproj.data.mealdetails.datasource;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.example.foodproj.data.home.datasource.MealResponse;

import com.example.foodproj.network.Network;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;


public class MealsDetailsRemoteData {
    private final MealDetailsService mealDetailsService;


    public MealsDetailsRemoteData() {
        this.mealDetailsService = Network.getInstance().mealDetailsService;
    }
    public Observable<MealResponse> getMealsDetails(Map<String, String> filters) {
      return   mealDetailsService.getMealsByFilter(filters);
    }
}
