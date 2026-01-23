package com.example.foodproj.data.home.repo;

import com.example.foodproj.data.home.datasource.CategoryNetworkResponse;
import com.example.foodproj.data.home.datasource.HomeNetworkResponse;
import com.example.foodproj.data.home.datasource.HomeRemoteData;
import com.example.foodproj.data.home.datasource.MealsNetworkResponse;

public class HomeRepoImpl implements HomeRepo{
    private final HomeRemoteData homeRemoteData;

    public HomeRepoImpl() {
        this.homeRemoteData = new HomeRemoteData();
    }

    @Override
    public void getMeals(MealsNetworkResponse mealNetworkResponse) {
        homeRemoteData.getMeals(mealNetworkResponse);
    }

    @Override
    public void getCategories(CategoryNetworkResponse categoryNetworkResponse) {
        homeRemoteData.getCategories(categoryNetworkResponse);
    }
}
