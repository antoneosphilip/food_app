package com.example.foodproj.presentation.ingredient.view;

import com.example.foodproj.data.categories.model.CategoryMeals;
import com.example.foodproj.data.ingredient.model.IngredientMeals;

import java.util.List;

public interface IngredientsMealsView {
    void getIngredientsMealsSuccess(List<IngredientMeals> ingredientMeals);
    void getIngredientsMealsError();
}
