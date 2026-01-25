package com.example.foodproj.presentation.favorite.presenter;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.home.model.Meal;

import java.util.List;

public interface FavoritePresenter {
     void InsertMeal(Meal meal);

     LiveData<List<Meal>> getMeals();

     void deleteMeal(Meal meal);
}
