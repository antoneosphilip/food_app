package com.example.foodproj.data.countries.datasource;


import androidx.lifecycle.Observer;

import com.example.foodproj.data.home.datasource.CategoriesResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CountriesService {
    @GET("list.php?a=list")
    Observable<CountriesResponse> getCountries();
}
