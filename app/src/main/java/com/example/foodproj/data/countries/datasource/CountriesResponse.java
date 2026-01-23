package com.example.foodproj.data.countries.datasource;

import com.example.foodproj.data.countries.model.CountriesMeals;

import java.util.List;

public class CountriesResponse {
    private List<CountriesMeals> meals;

    public List<CountriesMeals> getCountriesMeals() {
        return meals;
    }
}
