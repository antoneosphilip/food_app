package com.example.foodproj.data.favorite.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.favorite.datasource.FirebaseRemoteDataSource;
import com.example.foodproj.data.favorite.datasource.MealsLocalDataBase;
import com.example.foodproj.data.home.model.Meal;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class FavoriteRepoImpl implements FavoriteRepo {
   final private MealsLocalDataBase mealsLocalDataBase;

   final private FirebaseRemoteDataSource firebaseRemoteDataSource;
    public FavoriteRepoImpl(Context context) {
        this.mealsLocalDataBase = new MealsLocalDataBase(context);
        this.firebaseRemoteDataSource=new FirebaseRemoteDataSource();
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

}
