package com.example.foodproj.data.search.datasource;

import com.example.foodproj.data.mealsfilterd.datasource.MealsFilteredResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface SearchService {
    @GET("search.php")
    Observable<SearchResponse> getMealsByFilter(
            @QueryMap Map<String, String> filters
    );
}
