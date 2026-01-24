package com.example.foodproj.data.favorite.datasource;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.db.AppDataBase;

import java.util.List;

public class MealsLocalDataBase {
    public MealDao mealDao;
    public MealsLocalDataBase(Context context){
        AppDataBase appDataBase=AppDataBase.getInstance(context);
        mealDao=appDataBase.mealDao();
    }
    public void insertProduct(Meal meal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDao.InsertMeal(meal);

            }
        }).start();
    }
    public void deleteProduct(Meal meal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDao.deleteMeal(meal);

            }
        }).start();
    }
    public LiveData<List<Meal>> getProducts(){
        return mealDao.getMeals();
    }
}
