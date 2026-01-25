package com.example.foodproj.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodproj.data.calendar.datasource.CalendarDao;
import com.example.foodproj.data.favorite.datasource.MealDao;
import com.example.foodproj.data.home.model.Meal;

@Database(entities = {Meal.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract MealDao mealDao();
    public abstract CalendarDao calendarDao();

    private static AppDataBase instance=null;
    public static AppDataBase getInstance(Context context){
        if(instance==null) {
            instance=
                    Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class,"mealsDp").build();
        }
        return instance;
    }

}
