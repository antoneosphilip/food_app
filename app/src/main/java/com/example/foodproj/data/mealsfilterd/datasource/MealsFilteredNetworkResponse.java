package com.example.foodproj.data.mealsfilterd.datasource;

import com.example.foodproj.data.categories.model.CategoryMeals;
import com.example.foodproj.data.mealsfilterd.model.MealsFiltered;

import java.util.List;

public interface MealsFilteredNetworkResponse {
    void onMealsFilteredSuccess(List<MealsFiltered> mealsFiltereds);
    void onMealsFilteredMealsError(String message);
}
