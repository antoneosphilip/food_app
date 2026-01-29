package com.example.foodproj.data.favorite.repo;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.home.model.Meal;
import com.google.android.gms.tasks.Task;

import java.util.List;

public interface FavoriteRepo {

     void InsertMeal(Meal meal);

     LiveData<List<Meal>> getMeals();

     void deleteMeal(Meal meal);

     void uploadFavoriteData(Meal meal);

     Task<List<Meal>> getRemoteFavorites();

     void deleteFavoriteRemote(String id);





}
