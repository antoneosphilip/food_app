package com.example.foodproj.data.ingredient.repo;

import com.example.foodproj.data.ingredient.datasource.IngredientsMealResponse;
import com.example.foodproj.data.ingredient.datasource.IngredientsRemoteData;


import io.reactivex.rxjava3.core.Observable;

public class IngredientRepoImpl implements IngredientRepo{
    private final IngredientsRemoteData ingredientsRemoteData;

    public IngredientRepoImpl() {
        this.ingredientsRemoteData = new IngredientsRemoteData();
    }

    @Override
    public Observable<IngredientsMealResponse> getIngredientMeals() {
        return ingredientsRemoteData.getIngredientsMeals();
    }
}
