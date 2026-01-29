package com.example.foodproj.data.favorite.datasource;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodproj.data.home.model.Meal;

import java.util.List;

@Dao
public interface MealDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     void InsertMeal(Meal meal);
    @Query("SELECT * FROM meals")
    LiveData<List<Meal>> getMeals();
    @Delete
    void deleteMeal(Meal meal);
}
