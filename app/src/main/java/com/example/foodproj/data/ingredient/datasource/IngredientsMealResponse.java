package com.example.foodproj.data.ingredient.datasource;

import com.example.foodproj.data.ingredient.model.IngredientMeals;

import java.util.List;

public class IngredientsMealResponse {
    List<IngredientMeals> meals;
    public List<IngredientMeals> getIngredientsMeals(){
        return meals;
    }
}
