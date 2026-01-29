package com.example.foodproj.presentation.categoryMeals.view;

import com.example.foodproj.data.categories.model.CategoryMeals;

import java.util.List;

public interface CategoriesMealsView {
    void getCategoryMealsSuccess(List<CategoryMeals> categoryMeals);
    void getCategoryMealsError();

}
