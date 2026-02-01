package com.example.foodproj.data.mealsfilterd.datasource;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MealsFilteredService {
    @GET("filter.php")
    Observable<MealsFilteredResponse> getMealsByFilter(
            @QueryMap Map<String, String> filters
    );
}
