package com.example.foodproj.data.home.datasource;

import com.example.foodproj.data.home.model.Meal;

import java.util.List;

public interface MealsNetworkResponse extends HomeNetworkResponse{
    void onMealsSuccess(List<Meal> meals);
    void onMealsError(String message);
    
}
