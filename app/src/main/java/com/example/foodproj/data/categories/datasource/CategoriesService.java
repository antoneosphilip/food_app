package com.example.foodproj.data.categories.datasource;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriesService {
    @GET("categories.php")
    Single<CategoriesResponse> getCountries();

}
