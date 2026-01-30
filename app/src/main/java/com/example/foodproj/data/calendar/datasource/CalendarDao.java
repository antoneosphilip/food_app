package com.example.foodproj.data.calendar.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodproj.data.calendar.model.MealPlan;
import com.example.foodproj.data.home.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface CalendarDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable InsertMeal(MealPlan mealPlan);
    @Query("SELECT * FROM meal_plans WHERE planDate = :date")
    Observable<List<MealPlan>> getMeals(@NonNull String date);

    @Delete
    Completable deleteMeal(MealPlan mealPlan);
}
