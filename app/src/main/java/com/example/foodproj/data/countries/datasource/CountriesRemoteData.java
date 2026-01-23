package com.example.foodproj.data.countries.datasource;

import com.example.foodproj.data.countries.model.CountriesMeals;
import com.example.foodproj.network.Network;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountriesRemoteData {
    private CountriesService countriesServices;
    public CountriesRemoteData(){
        countriesServices= Network.getInstance().countriesServices;
    }
    public void getCountriesMeals(CountriesNetworkResponse countriesNetworkResponse){
        countriesServices.getCountries().enqueue(new Callback<CountriesResponse>() {
            @Override
            public void onResponse(Call<CountriesResponse> call, Response<CountriesResponse> response) {
                List<CountriesMeals> countriesMeals=response.body().getCountriesMeals();
                countriesNetworkResponse.onCountriesMealsSuccess(countriesMeals);
            }

            @Override
            public void onFailure(Call<CountriesResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    countriesNetworkResponse.onCountriesMealsError("error , check network");
                } else {
                    countriesNetworkResponse.onCountriesMealsError("error , try later");

                }
            }
        });
    }
}
