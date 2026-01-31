package com.example.foodproj.data.categories.datasource;


import com.example.foodproj.data.categories.model.Category;

import java.util.List;

public interface CategoriesNetworkResponse {
    void onCategoriesMealsSuccess(List<Category> categoryMeals);
    void onCategoriesMealsMealsError(String message);
}
