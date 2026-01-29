package com.example.foodproj.data.calendar.datasource;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.calendar.model.MealPlan;
import com.example.foodproj.data.favorite.datasource.MealDao;
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.db.AppDataBase;

import java.util.List;

public class MealPlanLocalDataBase {
    public CalendarDao calendarDao;
    public MealPlanLocalDataBase(Context context){
        AppDataBase appDataBase=AppDataBase.getInstance(context);
        calendarDao=appDataBase.calendarDao();
    }
    public void insertProduct(MealPlan mealPlan){
        new Thread(new Runnable() {
            @Override
            public void run() {
                calendarDao.InsertMeal(mealPlan);

            }
        }).start();
    }
    public void deleteProduct(MealPlan mealPlan){
        new Thread(new Runnable() {
            @Override
            public void run() {
                calendarDao.deleteMeal(mealPlan);

            }
        }).start();
    }
    public LiveData<List<MealPlan>> getProducts(String date){
        return calendarDao.getMeals(date);
    }
}
