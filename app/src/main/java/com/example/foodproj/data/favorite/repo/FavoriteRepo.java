package com.example.foodproj.data.favorite.repo;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.home.model.Meal;
import com.google.android.gms.tasks.Task;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public interface FavoriteRepo {

     Completable InsertMeal(Meal meal);

     Observable<List<Meal>> getMeals();

     Completable deleteMeal(Meal meal);

     void uploadFavoriteData(Meal meal);

     Task<List<Meal>> getRemoteFavorites();

     void deleteFavoriteRemote(String id);

     Completable deleteAllFavorite();




}
