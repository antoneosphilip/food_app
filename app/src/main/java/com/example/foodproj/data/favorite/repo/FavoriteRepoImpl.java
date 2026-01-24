package com.example.foodproj.data.favorite.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.favorite.datasource.MealsLocalDataBase;
import com.example.foodproj.data.home.model.Meal;

import java.util.List;

public class FavoriteRepoImpl implements FavoriteRepo {
   final private MealsLocalDataBase mealsLocalDataBase;

    public FavoriteRepoImpl(Context context) {
        this.mealsLocalDataBase = new MealsLocalDataBase(context);
    }

    @Override
    public void InsertMeal(Meal meal) {
        mealsLocalDataBase.insertProduct(meal);
    }

    @Override
    public LiveData<List<Meal>> getMeals() {
        return mealsLocalDataBase.getProducts();
    }

    @Override
    public void deleteMeal(Meal meal) {
        mealsLocalDataBase.deleteProduct(meal);
    }
}
