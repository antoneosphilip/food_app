package com.example.foodproj.presentation.mealsdetails.presenter;

import com.example.foodproj.data.home.model.Meal;

import java.util.Map;

public interface MealsDetailsPresenter {
    void getMealDetails( Map<String, String> filters);
    void insertMeal(Meal meal);

}
