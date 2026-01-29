package com.example.foodproj.data.countries.datasource;


import com.example.foodproj.data.home.datasource.CategoriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountriesService {
    @GET("list.php?a=list")
    Call<CountriesResponse> getCountries();
}
