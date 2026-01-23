package com.example.foodproj.data.categories.datasource;

import com.example.foodproj.data.categories.model.CategoryMeals;
import com.example.foodproj.data.countries.datasource.CountriesNetworkResponse;
import com.example.foodproj.data.countries.datasource.CountriesResponse;
import com.example.foodproj.data.countries.model.CountriesMeals;
import com.example.foodproj.data.home.datasource.CategoriesResponse;
import com.example.foodproj.network.Network;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesRemoteData {
    private final CategoriesService categoriesService;

    public CategoriesRemoteData() {
        this.categoriesService = Network.getInstance().categoriesService;
    }
    public void getCategoriesMeals(CategoriesNetworkResponse categoriesNetworkResponse){
        categoriesService.getCountries().enqueue(new Callback<CategoriesMealsResponse>() {
            @Override
            public void onResponse(Call<CategoriesMealsResponse> call, Response<CategoriesMealsResponse> response) {
                List<CategoryMeals> categoryMeals=response.body().getCategoriesMeals();
                categoriesNetworkResponse.onCategoriesMealsSuccess(categoryMeals);
            }

            @Override
            public void onFailure(Call<CategoriesMealsResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    categoriesNetworkResponse.onCategoriesMealsMealsError("error , check network");
                } else {
                    categoriesNetworkResponse.onCategoriesMealsMealsError("error , try later");

                }
            }
        });
    }

}
