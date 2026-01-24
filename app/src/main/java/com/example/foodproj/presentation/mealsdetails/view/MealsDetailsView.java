package com.example.foodproj.presentation.mealsdetails.view;

import com.example.foodproj.data.home.model.Meal;

import java.util.List;

public interface MealsDetailsView {
    void getMealsDetailsSuccess(List<Meal> meals);
    void getMealsDetailsError();

}
