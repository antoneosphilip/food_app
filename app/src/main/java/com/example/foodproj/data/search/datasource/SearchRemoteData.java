package com.example.foodproj.data.search.datasource;

import com.example.foodproj.data.mealsfilterd.datasource.MealsFilteredResponse;
import com.example.foodproj.data.mealsfilterd.datasource.MealsFilteredService;
import com.example.foodproj.network.Network;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

public class SearchRemoteData {
    private final SearchService searchService;

    public SearchRemoteData() {
        this.searchService = Network.getInstance().searchService;
    }

    public Observable<SearchResponse> getMealsSearched(Map<String, String> filters){
        return searchService.getMealsByFilter(filters);
    }
}
