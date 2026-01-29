package com.example.foodproj.data.countries.repo;

import com.example.foodproj.data.countries.datasource.CountriesNetworkResponse;
import com.example.foodproj.data.countries.model.CountriesMeals;

import java.util.List;

public interface CountriesMealsRepo {
     void getCountriesMeals(CountriesNetworkResponse countriesNetworkResponse);
}
