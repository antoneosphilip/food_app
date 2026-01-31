package com.example.foodproj.presentation.favorite.presenter;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.home.model.Meal;
import com.google.android.gms.tasks.Task;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface FavoritePresenter {
     void InsertMeal(Meal meal);

     void getMeals();

     void deleteMeal(Meal meal);

     void getRemoteFavorites();

     void deleteFavoriteRemote(String id);

     void deleteAllFavorites();


}
