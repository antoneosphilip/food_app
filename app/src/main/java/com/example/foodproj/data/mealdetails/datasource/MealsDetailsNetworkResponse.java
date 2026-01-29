package com.example.foodproj.data.mealdetails.datasource;

import com.example.foodproj.data.home.model.Meal;

import java.util.List;


public interface MealsDetailsNetworkResponse {
    void onMealsDetailsSuccess(List<Meal> meals);
    void onMealsDetailsError(String message);
}
