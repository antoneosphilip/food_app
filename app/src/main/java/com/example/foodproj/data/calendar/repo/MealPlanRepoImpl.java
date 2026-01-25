package com.example.foodproj.data.calendar.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.calendar.datasource.MealPlanLocalDataBase;
import com.example.foodproj.data.calendar.model.MealPlan;

import java.util.List;

public class MealPlanRepoImpl implements MealPlanRepo {
   private final MealPlanLocalDataBase mealPlanLocalDataBase;

    public MealPlanRepoImpl(Context context) {
        this.mealPlanLocalDataBase = new MealPlanLocalDataBase(context);
    }

    @Override
    public void InsertMeal(MealPlan mealPlan) {
        mealPlanLocalDataBase.insertProduct(mealPlan);
    }

    @Override
    public LiveData<List<MealPlan>> getMeals() {
        return mealPlanLocalDataBase.getProducts();
    }

    @Override
    public void deleteMeal(MealPlan mealPlan) {
        mealPlanLocalDataBase.deleteProduct(mealPlan);
    }
}
