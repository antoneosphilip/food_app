package com.example.foodproj.network;

import com.example.foodproj.data.categories.datasource.CategoriesService;
import com.example.foodproj.data.countries.datasource.CountriesService;
import com.example.foodproj.data.home.datasource.HomeServices;
import com.example.foodproj.data.ingredient.datasource.IngredientService;
import com.example.foodproj.data.mealdetails.datasource.MealDetailsService;
import com.example.foodproj.data.mealsfilterd.datasource.MealsFilteredService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    public HomeServices homeServices;
    public CountriesService countriesServices;
    public CategoriesService categoriesService;
    public IngredientService ingredientService;
    public MealsFilteredService mealsFilteredService;

    public MealDetailsService mealDetailsService;

    private static Network instance=null;
    public Network(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        homeServices=retrofit.create(HomeServices.class);
        countriesServices=retrofit.create(CountriesService.class);
        categoriesService=retrofit.create(CategoriesService.class);
        ingredientService=retrofit.create(IngredientService.class);
        mealsFilteredService=retrofit.create(MealsFilteredService.class);
        mealDetailsService=retrofit.create(MealDetailsService.class);
    }
    public static Network getInstance(){
        if(instance==null)
            instance=new Network();
        return instance;
    }

}
