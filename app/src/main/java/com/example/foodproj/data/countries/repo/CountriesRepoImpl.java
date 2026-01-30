package com.example.foodproj.data.countries.repo;

import com.example.foodproj.data.countries.datasource.CountriesNetworkResponse;
import com.example.foodproj.data.countries.datasource.CountriesRemoteData;
import com.example.foodproj.data.countries.datasource.CountriesResponse;
import com.example.foodproj.data.countries.model.CountriesMeals;
import com.example.foodproj.data.home.datasource.HomeRemoteData;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class CountriesRepoImpl implements CountriesMealsRepo{
    private final CountriesRemoteData countriesRemoteData;

    public CountriesRepoImpl() {
        this.countriesRemoteData =new CountriesRemoteData();
    }

    @Override
    public Observable<CountriesResponse> getCountriesMeals() {
       return countriesRemoteData.getCountriesMeals();
    }
}
