package com.example.foodproj.presentation.favorite.presenter;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.home.model.Meal;
import com.google.android.gms.tasks.Task;

import java.util.List;

public interface FavoritePresenter {
     void InsertMeal(Meal meal);

     LiveData<List<Meal>> getMeals();

     void deleteMeal(Meal meal);

     void getRemoteFavorites();

     void deleteFavoriteRemote(String id);

}
