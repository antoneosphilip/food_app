package com.example.foodproj.data.ingredient.datasource;

import com.example.foodproj.data.categories.datasource.CategoriesMealsResponse;
import com.example.foodproj.data.categories.datasource.CategoriesNetworkResponse;
import com.example.foodproj.data.categories.model.CategoryMeals;
import com.example.foodproj.data.ingredient.model.IngredientMeals;
import com.example.foodproj.network.Network;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientsRemoteData {
    private final IngredientService ingredientsRemoteData;

    public IngredientsRemoteData() {
        this.ingredientsRemoteData = Network.getInstance().ingredientService;
    }
    public void getIngredientsMeals(IngredientsNetworkResponse ingredientsNetworkResponse){
        ingredientsRemoteData.getIngredients().enqueue(new Callback<IngredientsMealResponse>() {
            @Override
            public void onResponse(Call<IngredientsMealResponse> call, Response<IngredientsMealResponse> response) {
                List<IngredientMeals> ingredientMeals=response.body().getIngredientsMeals();
                ingredientsNetworkResponse.onIngredientsMealsSuccess(ingredientMeals);
            }

            @Override
            public void onFailure(Call<IngredientsMealResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    ingredientsNetworkResponse.onIngredientsMealsError("error , check network");
                } else {
                    ingredientsNetworkResponse.onIngredientsMealsError("error , try later");

                }
            }
        });
    }

}
