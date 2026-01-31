package com.example.foodproj.presentation.categoryMeals.view;



import com.example.foodproj.data.categories.model.Category;

import java.util.List;

public interface CategoriesMealsView {
    void getCategoryMealsSuccess(List<Category> categoryMeals);
    void getCategoryMealsError(String message);

}
