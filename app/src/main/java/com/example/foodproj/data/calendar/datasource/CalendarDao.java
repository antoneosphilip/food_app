package com.example.foodproj.data.calendar.datasource;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodproj.data.calendar.model.MealPlan;
import com.example.foodproj.data.home.model.Meal;

import java.util.List;

@Dao
public interface CalendarDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void InsertMeal(MealPlan mealPlan);
    @Query("SELECT * FROM meal_plans")
    LiveData<List<MealPlan>> getMeals();
    @Delete
    void deleteMeal(MealPlan mealPlan);
}
