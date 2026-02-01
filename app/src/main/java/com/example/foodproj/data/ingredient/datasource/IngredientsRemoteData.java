package com.example.foodproj.data.ingredient.datasource;


import com.example.foodproj.network.Network;

import java.io.IOException;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientsRemoteData {
    private final IngredientService ingredientsRemoteData;
    public IngredientsRemoteData() {
        this.ingredientsRemoteData = Network.getInstance().ingredientService;
    }
    public Single<IngredientsMealResponse> getIngredientsMeals(){
       return ingredientsRemoteData.getIngredients();
    }

}
