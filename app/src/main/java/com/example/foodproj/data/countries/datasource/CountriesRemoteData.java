package com.example.foodproj.data.countries.datasource;

import com.example.foodproj.data.countries.model.CountriesMeals;
import com.example.foodproj.network.Network;

import java.io.IOException;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountriesRemoteData {
    private CountriesService countriesServices;
    public CountriesRemoteData(){
        countriesServices= Network.getInstance().countriesServices;
    }
    public Observable<CountriesResponse> getCountriesMeals(){
       return countriesServices.getCountries();
    }
}
