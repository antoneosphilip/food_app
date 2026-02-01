package com.example.foodproj.presentation.mealsdetails.presenter;

import com.example.foodproj.data.calendar.model.MealPlan;
import com.example.foodproj.data.home.model.Meal;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

public interface MealsDetailsPresenter {
    void getMealDetails( Map<String, String> filters);
    void insertMeal(Meal meal);

    void insertMealPlan(MealPlan mealPlan);

    void uploadFavoriteMeal(Meal meal);

    void uploadPlansMeal(MealPlan mealPlan);

     Observable<List<Meal>> getRemoteFavorites();

     Task<List<MealPlan>> getRemotePlans();

}
