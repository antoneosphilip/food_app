package com.example.foodproj.data.calendar.datasource;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.calendar.model.MealPlan;
import com.example.foodproj.data.favorite.datasource.MealDao;
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.db.AppDataBase;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class MealPlanLocalDataBase {
    public CalendarDao calendarDao;
    public MealPlanLocalDataBase(Context context){
        AppDataBase appDataBase=AppDataBase.getInstance(context);
        calendarDao=appDataBase.calendarDao();
    }
    public Completable insertProduct(MealPlan mealPlan){
       return calendarDao.InsertMeal(mealPlan);

    }
    public Completable deleteProduct(MealPlan mealPlan){

        return calendarDao.deleteMeal(mealPlan);
    }
    public Observable<List<MealPlan>> getProducts(String date){
        return calendarDao.getMeals(date);
    }
    public Completable deleteAllMeals(){

        return calendarDao.deleteAllMeals();
    }
}
