package com.example.foodproj.data.categories.datasource;

import com.example.foodproj.data.home.datasource.CategoriesResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriesService {
    @GET("categories.php")
    Observable<CategoriesResponse> getCountries();

}
