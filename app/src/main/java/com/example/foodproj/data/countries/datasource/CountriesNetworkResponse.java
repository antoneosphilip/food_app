package com.example.foodproj.data.countries.datasource;

import com.example.foodproj.data.countries.model.CountriesMeals;
import com.example.foodproj.data.home.model.Category;

import java.util.List;

public interface CountriesNetworkResponse {
    void onCountriesMealsSuccess(List<CountriesMeals> countriesResponses);
    void onCountriesMealsError(String message);
}
