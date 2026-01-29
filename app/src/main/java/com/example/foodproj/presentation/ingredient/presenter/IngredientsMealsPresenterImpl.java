package com.example.foodproj.presentation.ingredient.presenter;

import com.example.foodproj.data.ingredient.datasource.IngredientsNetworkResponse;
import com.example.foodproj.data.ingredient.model.IngredientMeals;
import com.example.foodproj.data.ingredient.repo.IngredientRepo;
import com.example.foodproj.data.ingredient.repo.IngredientRepoImpl;
import com.example.foodproj.presentation.ingredient.view.IngredientsMealsView;

import java.util.List;

public class IngredientsMealsPresenterImpl implements IngredientsMealsPresenter{
    final private IngredientRepo ingredientRepo;
    final private IngredientsMealsView ingredientsMealsView;
    public IngredientsMealsPresenterImpl(IngredientsMealsView ingredientsMealsView) {
        this.ingredientRepo = new IngredientRepoImpl();
        this.ingredientsMealsView=ingredientsMealsView;
    }

    @Override
    public void getIngredientsMeals() {
        ingredientRepo.getIngredientMeals(new IngredientsNetworkResponse() {
            @Override
            public void onIngredientsMealsSuccess(List<IngredientMeals> ingredientsMealsList) {
                ingredientsMealsView.getIngredientsMealsSuccess(ingredientsMealsList);
            }

            @Override
            public void onIngredientsMealsError(String message) {
                ingredientsMealsView.getIngredientsMealsError();
            }
        });
    }
}
