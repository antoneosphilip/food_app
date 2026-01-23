package com.example.foodproj.data.categories.datasource;

import com.example.foodproj.data.categories.model.CategoryMeals;
import com.example.foodproj.data.countries.model.CountriesMeals;

import java.util.List;

public interface CategoriesNetworkResponse {
    void onCategoriesMealsSuccess(List<CategoryMeals> categoryMeals);
    void onCategoriesMealsMealsError(String message);
}
