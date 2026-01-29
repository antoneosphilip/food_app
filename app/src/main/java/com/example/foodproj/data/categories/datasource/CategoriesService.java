package com.example.foodproj.data.categories.datasource;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriesService {
    @GET("list.php?c=list")
    Call<CategoriesMealsResponse> getCountries();

}
