package com.example.foodproj.presentation.home.view;

import com.example.foodproj.data.home.model.Meal;

public interface HomeView {
    void mealFetchedSuccessfully(Meal meal);
    void mealFetchedFailure();
}
