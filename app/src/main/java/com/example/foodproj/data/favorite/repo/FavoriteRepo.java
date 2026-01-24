package com.example.foodproj.data.favorite.repo;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.home.model.Meal;

import java.util.List;

public interface FavoriteRepo {

    public void InsertMeal(Meal meal);

    public LiveData<List<Meal>> getMeals();

    public void deleteMeal(Meal meal);
}
