package com.example.foodproj.data.ingredient.datasource;

import com.example.foodproj.data.ingredient.model.IngredientMeals;

import java.util.List;

public interface IngredientsNetworkResponse {
    void onIngredientsMealsSuccess(List<IngredientMeals> ingredientsMealsList);
    void onIngredientsMealsError(String message);
}
