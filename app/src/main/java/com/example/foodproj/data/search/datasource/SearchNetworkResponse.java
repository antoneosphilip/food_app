package com.example.foodproj.data.search.datasource;

import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.data.mealsfilterd.model.MealsFiltered;

import java.util.List;

public interface SearchNetworkResponse {
    void onMealsSearchedSuccess(List<Meal> mealsFiltereds);
    void onMealsSearchedMealsError(String message);
}
