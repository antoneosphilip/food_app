package com.example.foodproj.data.calendar.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal_plans")
public class MealPlan {

    private int id;

    @NonNull
    @PrimaryKey
    private String mealId;

    private String mealName;
    private String mealThumb;
    private String mealCategory;
    private String mealArea;

    @NonNull
    private String planDate;

    private long timestamp;

    public MealPlan(@NonNull String mealId, String mealName, String mealThumb,
                    String mealCategory, String mealArea, @NonNull String planDate, long timestamp) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealThumb = mealThumb;
        this.mealCategory = mealCategory;
        this.mealArea = mealArea;
        this.planDate = planDate;
        this.timestamp = timestamp;
    }
    public MealPlan() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getMealId() {
        return mealId;
    }

    public void setMealId(@NonNull String mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealThumb() {
        return mealThumb;
    }

    public void setMealThumb(String mealThumb) {
        this.mealThumb = mealThumb;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    public String getMealArea() {
        return mealArea;
    }

    public void setMealArea(String mealArea) {
        this.mealArea = mealArea;
    }

    @NonNull
    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(@NonNull String planDate) {
        this.planDate = planDate;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}