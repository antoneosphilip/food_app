package com.example.foodproj.data.calendar.repo;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.calendar.model.MealPlan;
import com.example.foodproj.data.home.model.Meal;
import com.google.android.gms.tasks.Task;

import java.util.List;

public interface MealPlanRepo {
     void InsertMeal(MealPlan mealPlan);

     LiveData<List<MealPlan>> getMeals(String date);

     void deleteMeal(MealPlan mealPlan);


    Task<List<MealPlan>> getRemotePlans();

     void uploadPlansData(MealPlan mealPlan);

     void deletePlanMeal(String id);
}
