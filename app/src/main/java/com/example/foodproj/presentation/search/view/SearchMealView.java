package com.example.foodproj.presentation.search.view;

import com.example.foodproj.data.home.model.Meal;

import java.util.List;

public interface SearchMealView {
    void getSearchMealsSuccess(List<Meal> meals);
    void getSearchMealsError();
}
