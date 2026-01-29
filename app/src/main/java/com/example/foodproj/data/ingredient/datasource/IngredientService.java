package com.example.foodproj.data.ingredient.datasource;

import com.example.foodproj.data.categories.datasource.CategoriesMealsResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IngredientService {
    @GET("list.php?i=list")
    Observable<IngredientsMealResponse> getIngredients();

}
