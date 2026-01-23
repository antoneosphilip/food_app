package com.example.foodproj.data.mealsfilterd.repo;

import com.example.foodproj.data.mealsfilterd.datasource.MealsFilteredNetworkResponse;

import java.util.Map;

public interface MealsFilteredRepo {
    void getMealsFiltered(Map<String, String> filter, MealsFilteredNetworkResponse mealsFilteredNetworkResponse);
}
