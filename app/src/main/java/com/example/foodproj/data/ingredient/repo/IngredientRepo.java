package com.example.foodproj.data.ingredient.repo;

import com.example.foodproj.data.ingredient.datasource.IngredientsMealResponse;

import io.reactivex.rxjava3.core.Observable;

public interface IngredientRepo {
    Observable<IngredientsMealResponse> getIngredientMeals();
}
