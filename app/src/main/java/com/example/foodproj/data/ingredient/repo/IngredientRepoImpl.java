package com.example.foodproj.data.ingredient.repo;

import com.example.foodproj.data.ingredient.datasource.IngredientService;
import com.example.foodproj.data.ingredient.datasource.IngredientsNetworkResponse;
import com.example.foodproj.data.ingredient.datasource.IngredientsRemoteData;

public class IngredientRepoImpl implements IngredientRepo{
    private final IngredientsRemoteData ingredientsRemoteData;

    public IngredientRepoImpl() {
        this.ingredientsRemoteData = new IngredientsRemoteData();
    }

    @Override
    public void getIngredientMeals(IngredientsNetworkResponse ingredientsNetworkResponse) {
        ingredientsRemoteData.getIngredientsMeals(ingredientsNetworkResponse);
    }
}
