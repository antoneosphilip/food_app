package com.example.foodproj.data.search.datasource;

import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.data.mealsfilterd.model.MealsFiltered;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {
    @SerializedName("meals")

    List<Meal> meals;

    public List<Meal> getMeals() {
        return meals;
    }
}
