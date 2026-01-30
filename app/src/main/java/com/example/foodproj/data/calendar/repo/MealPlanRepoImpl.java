package com.example.foodproj.data.calendar.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.calendar.datasource.MealPlanLocalDataBase;
import com.example.foodproj.data.calendar.model.MealPlan;
import com.example.foodproj.data.favorite.datasource.FirebaseRemoteDataSource;
import com.example.foodproj.data.home.model.Meal;
import com.google.android.gms.tasks.Task;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class MealPlanRepoImpl implements MealPlanRepo {
   private final MealPlanLocalDataBase mealPlanLocalDataBase;
    final private FirebaseRemoteDataSource firebaseRemoteDataSource;
    public MealPlanRepoImpl(Context context) {
        this.mealPlanLocalDataBase = new MealPlanLocalDataBase(context);
        this.firebaseRemoteDataSource= new FirebaseRemoteDataSource();
    }

    @Override
    public Completable InsertMeal(MealPlan mealPlan) {
       return mealPlanLocalDataBase.insertProduct(mealPlan);
    }

    @Override
    public Observable<List<MealPlan>> getMeals(String date) {
        return mealPlanLocalDataBase.getProducts(date);
    }

    @Override
    public Completable deleteMeal(MealPlan mealPlan) {
       return mealPlanLocalDataBase.deleteProduct(mealPlan);
    }

    @Override
    public Task<List<MealPlan>> getRemotePlans() {
        return firebaseRemoteDataSource.getCalendarMeals();
    }

    @Override
    public void uploadPlansData(MealPlan mealPlan) {
        firebaseRemoteDataSource.uploadPlansData(mealPlan);
    }

    @Override
    public void deletePlanMeal(String id) {
        firebaseRemoteDataSource.deleteCalendarMeal(id);
    }


}
