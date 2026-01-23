package com.example.foodproj.presentation.home.view;

import com.example.foodproj.data.home.datasource.MealResponse;
import com.example.foodproj.data.home.model.Category;
import com.example.foodproj.data.home.model.Meal;

import java.util.List;

public interface HomeView {
    void mealFetchedSuccessfully(List<Meal> meal);
    void mealFetchedFailure();
    void categoryFetchedSuccessfully(List<Category> categories);
    void categoryFetchedFailure();
}
