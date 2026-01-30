package com.example.foodproj.data.search.repo;

import com.example.foodproj.data.mealsfilterd.datasource.MealsFilteredResponse;
import com.example.foodproj.data.search.datasource.SearchResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

public interface SearchRepo {
    Observable<SearchResponse> getSearchedMeals(Map<String, String> filter);

}
