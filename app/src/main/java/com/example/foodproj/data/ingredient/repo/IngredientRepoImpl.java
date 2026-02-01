package com.example.foodproj.data.ingredient.repo;

import com.example.foodproj.data.ingredient.datasource.IngredientsMealResponse;
import com.example.foodproj.data.ingredient.datasource.IngredientsRemoteData;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class IngredientRepoImpl implements IngredientRepo{
    private final IngredientsRemoteData ingredientsRemoteData;

    public IngredientRepoImpl() {
        this.ingredientsRemoteData = new IngredientsRemoteData();
    }

    @Override
    public Single<IngredientsMealResponse> getIngredientMeals() {
        return ingredientsRemoteData.getIngredientsMeals();
    }
}
