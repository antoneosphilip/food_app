package com.example.foodproj.data.categories.datasource;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriesService {
    @GET("list.php?c=list")
    Observable<CategoriesMealsResponse> getCountries();

}
