package com.example.foodproj.data.ingredient.datasource;

import com.example.foodproj.data.categories.datasource.CategoriesMealsResponse;
import com.example.foodproj.data.categories.datasource.CategoriesNetworkResponse;
import com.example.foodproj.data.categories.model.CategoryMeals;
import com.example.foodproj.data.ingredient.model.IngredientMeals;
import com.example.foodproj.network.Network;

import java.io.IOException;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientsRemoteData {
    private final IngredientService ingredientsRemoteData;
    public IngredientsRemoteData() {
        this.ingredientsRemoteData = Network.getInstance().ingredientService;
    }
    public Observable<IngredientsMealResponse> getIngredientsMeals(){
       return ingredientsRemoteData.getIngredients();
    }

}
