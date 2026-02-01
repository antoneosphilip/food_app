package com.example.foodproj.data.countries.repo;

import com.example.foodproj.data.countries.datasource.CountriesRemoteData;
import com.example.foodproj.data.countries.datasource.CountriesResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class CountriesRepoImpl implements CountriesMealsRepo{
    private final CountriesRemoteData countriesRemoteData;

    public CountriesRepoImpl() {
        this.countriesRemoteData =new CountriesRemoteData();
    }

    @Override
    public Single<CountriesResponse> getCountriesMeals() {
       return countriesRemoteData.getCountriesMeals();
    }
}
