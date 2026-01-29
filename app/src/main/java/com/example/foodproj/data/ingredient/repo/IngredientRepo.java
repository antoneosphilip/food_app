package com.example.foodproj.data.ingredient.repo;

import com.example.foodproj.data.ingredient.datasource.IngredientsNetworkResponse;

public interface IngredientRepo {
    void getIngredientMeals(IngredientsNetworkResponse ingredientsNetworkResponse);
}
