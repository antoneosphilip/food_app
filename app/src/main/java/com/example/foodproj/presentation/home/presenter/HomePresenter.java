package com.example.foodproj.presentation.home.presenter;

import com.example.foodproj.data.home.datasource.HomeNetworkResponse;
import com.example.foodproj.data.home.model.Meal;

import java.util.List;

public interface HomePresenter {
    void getMeals();
    void getCategories();

}
