package com.example.foodproj.data.ingredient.repo;

import com.example.foodproj.data.ingredient.datasource.IngredientsMealResponse;
import com.example.foodproj.data.ingredient.datasource.IngredientsNetworkResponse;
import com.example.foodproj.data.ingredient.model.IngredientMeals;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface IngredientRepo {
    Observable<IngredientsMealResponse> getIngredientMeals();
}
