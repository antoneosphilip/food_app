package com.example.foodproj.data.countries.repo;

import com.example.foodproj.data.countries.datasource.CountriesNetworkResponse;
import com.example.foodproj.data.countries.datasource.CountriesResponse;
import com.example.foodproj.data.countries.model.CountriesMeals;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface CountriesMealsRepo {
     Observable<CountriesResponse> getCountriesMeals();
}
