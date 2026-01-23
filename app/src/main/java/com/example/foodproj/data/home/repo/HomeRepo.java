package com.example.foodproj.data.home.repo;

import com.example.foodproj.data.home.datasource.CategoryNetworkResponse;
import com.example.foodproj.data.home.datasource.HomeNetworkResponse;
import com.example.foodproj.data.home.datasource.MealsNetworkResponse;

public interface HomeRepo {
    void getMeals(MealsNetworkResponse mealsNetworkResponse);
    void getCategories(CategoryNetworkResponse categoryNetworkResponse);

}
