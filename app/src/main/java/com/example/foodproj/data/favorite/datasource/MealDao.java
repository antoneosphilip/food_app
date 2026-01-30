package com.example.foodproj.data.favorite.datasource;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodproj.data.home.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface MealDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable InsertMeal(Meal meal);
    @Query("SELECT * FROM meals")
    Observable<List<Meal>> getMeals();
    @Delete
    Completable deleteMeal(Meal meal);
}
