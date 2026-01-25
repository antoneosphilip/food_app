package com.example.foodproj.data.calendar.repo;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.calendar.model.MealPlan;
import com.example.foodproj.data.home.model.Meal;

import java.util.List;

public interface MealPlanRepo {
    public void InsertMeal(MealPlan mealPlan);

    public LiveData<List<MealPlan>> getMeals(String date);

    public void deleteMeal(MealPlan mealPlan);
}
