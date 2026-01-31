package com.example.foodproj.data.favorite.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.favorite.datasource.FirebaseRemoteDataSource;
import com.example.foodproj.data.favorite.datasource.MealsLocalDataBase;
import com.example.foodproj.data.home.model.Meal;
import com.google.android.gms.tasks.Task;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class FavoriteRepoImpl implements FavoriteRepo {
   final private MealsLocalDataBase mealsLocalDataBase;

   final private FirebaseRemoteDataSource firebaseRemoteDataSource;
    public FavoriteRepoImpl(Context context) {
        this.mealsLocalDataBase = new MealsLocalDataBase(context);
        this.firebaseRemoteDataSource=new FirebaseRemoteDataSource();
    }

    @Override
    public Completable InsertMeal(Meal meal) {
        return mealsLocalDataBase.insertProduct(meal);
    }

    @Override
    public Observable<List<Meal>> getMeals() {
        return mealsLocalDataBase.getProducts();
    }

    @Override
    public Completable deleteMeal(Meal meal) {
       return mealsLocalDataBase.deleteProduct(meal);
    }

    @Override
    public void uploadFavoriteData(Meal meal) {
        firebaseRemoteDataSource.uploadFavoriteData(meal);
    }

    @Override
    public Task<List<Meal>> getRemoteFavorites() {
        return firebaseRemoteDataSource.getFavoriteMeals();
    }

    @Override
    public void deleteFavoriteRemote(String id) {
        firebaseRemoteDataSource.deleteFavoriteMeal(id);
    }

    @Override
    public Completable deleteAllFavorite() {
        return mealsLocalDataBase.deleteAllFavorites();
    }


}
