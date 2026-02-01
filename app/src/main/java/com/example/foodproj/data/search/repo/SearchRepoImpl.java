package com.example.foodproj.data.search.repo;

import com.example.foodproj.data.mealsfilterd.datasource.MealsFilteredRemoteData;
import com.example.foodproj.data.search.datasource.SearchRemoteData;
import com.example.foodproj.data.search.datasource.SearchResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class SearchRepoImpl implements SearchRepo{
    final private SearchRemoteData searchRemoteData;

    public SearchRepoImpl() {
        this.searchRemoteData = new SearchRemoteData();
    }

    @Override
    public Single<SearchResponse> getSearchedMeals(Map<String, String> filter) {
        return searchRemoteData.getMealsSearched(filter);
    }
}
