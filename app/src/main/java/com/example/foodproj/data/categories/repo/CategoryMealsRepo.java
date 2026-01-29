package com.example.foodproj.data.categories.repo;

import com.example.foodproj.data.categories.datasource.CategoriesMealsResponse;
import com.example.foodproj.data.categories.datasource.CategoriesNetworkResponse;

public interface CategoryMealsRepo {
    void getCategoriesMeals(CategoriesNetworkResponse categoriesMealsResponse);
}

