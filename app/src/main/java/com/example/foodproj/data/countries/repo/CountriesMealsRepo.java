package com.example.foodproj.data.countries.repo;

import com.example.foodproj.data.countries.datasource.CountriesResponse;

import io.reactivex.rxjava3.core.Observable;

public interface CountriesMealsRepo {
     Observable<CountriesResponse> getCountriesMeals();
}
