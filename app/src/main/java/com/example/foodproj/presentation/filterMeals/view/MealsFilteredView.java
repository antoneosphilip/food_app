package com.example.foodproj.presentation.filterMeals.view;

import com.example.foodproj.data.mealsfilterd.model.MealsFiltered;

import java.util.List;

public interface MealsFilteredView {
    void getMealsFilteredSuccess(List<MealsFiltered> mealsFilteredList);

    void getMealsFilteredError();
}
