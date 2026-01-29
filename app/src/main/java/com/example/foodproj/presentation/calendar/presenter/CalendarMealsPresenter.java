package com.example.foodproj.presentation.calendar.presenter;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.calendar.model.MealPlan;
import com.example.foodproj.data.home.model.Meal;

import java.util.List;

public interface CalendarMealsPresenter
{
    LiveData<List<MealPlan>> getMeals(String date);

    void deleteMeal(MealPlan mealPlan);

    void getRemoteCalendar();

    void uploadRemoteCalendar(MealPlan mealPlan);

    void deletePlanMeal(String id);
}
