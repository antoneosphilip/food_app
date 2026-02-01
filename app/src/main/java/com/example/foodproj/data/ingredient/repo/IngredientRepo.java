package com.example.foodproj.data.ingredient.repo;

import com.example.foodproj.data.ingredient.datasource.IngredientsMealResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface IngredientRepo {
    Single<IngredientsMealResponse> getIngredientMeals();
}
