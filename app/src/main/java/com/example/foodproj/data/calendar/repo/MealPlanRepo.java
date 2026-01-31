package com.example.foodproj.data.calendar.repo;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.calendar.model.MealPlan;
import com.example.foodproj.data.home.model.Meal;
import com.google.android.gms.tasks.Task;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public interface MealPlanRepo {
    Completable InsertMeal(MealPlan mealPlan);

     Observable<List<MealPlan>> getMeals(String date);

     Completable deleteMeal(MealPlan mealPlan);


    Task<List<MealPlan>> getRemotePlans();

     void uploadPlansData(MealPlan mealPlan);

     void deletePlanMeal(String id);

    Completable deleteAllPlans();

}
