package com.example.foodproj.data.ingredient.datasource;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface IngredientService {
    @GET("list.php?i=list")
    Single<IngredientsMealResponse> getIngredients();

}
