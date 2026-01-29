package com.example.foodproj.presentation.countries.view;

import com.example.foodproj.data.countries.model.CountriesMeals;

import java.util.List;

public interface CountriesMealsView {
    void getCountriesMealsSucess(List<CountriesMeals> countriesMealsList);

    void getCountriesMealsError();
}
