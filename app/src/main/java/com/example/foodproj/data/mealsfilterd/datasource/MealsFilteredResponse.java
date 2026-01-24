package com.example.foodproj.data.mealsfilterd.datasource;

import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.data.mealsfilterd.model.MealsFiltered;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealsFilteredResponse {
    @SerializedName("meals")

    List<MealsFiltered> meals;

    public List<MealsFiltered> getMeals() {
        return meals;
    }
}
