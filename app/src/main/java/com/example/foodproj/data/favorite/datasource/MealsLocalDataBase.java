package com.example.foodproj.data.favorite.datasource;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.db.AppDataBase;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class MealsLocalDataBase {
    public MealDao mealDao;
    public MealsLocalDataBase(Context context){
        AppDataBase appDataBase=AppDataBase.getInstance(context);
        mealDao=appDataBase.mealDao();
    }
    public Completable insertProduct(Meal meal){
        return mealDao.InsertMeal(meal);
    }
    public Completable deleteProduct(Meal meal){
        return  mealDao.deleteMeal(meal);
    }
    public Observable<List<Meal>> getProducts(){
        return mealDao.getMeals();
    }

    public Completable deleteAllFavorites(){
        return mealDao.deleteAllMeals();
    }
}
