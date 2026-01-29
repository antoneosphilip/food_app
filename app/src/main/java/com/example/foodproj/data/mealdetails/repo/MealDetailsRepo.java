package com.example.foodproj.data.mealdetails.repo;

import com.example.foodproj.data.mealdetails.datasource.MealsDetailsNetworkResponse;

import java.util.Map;

public interface MealDetailsRepo {
    void getMealDetails(MealsDetailsNetworkResponse mealsDetailsNetworkResponse, Map<String, String> filters);
}
