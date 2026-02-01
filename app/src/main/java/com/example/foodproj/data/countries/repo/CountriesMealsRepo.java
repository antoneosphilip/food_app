package com.example.foodproj.data.countries.repo;

import com.example.foodproj.data.countries.datasource.CountriesResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface CountriesMealsRepo {
     Single<CountriesResponse> getCountriesMeals();
}
