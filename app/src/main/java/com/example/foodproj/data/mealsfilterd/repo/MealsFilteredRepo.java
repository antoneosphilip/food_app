package com.example.foodproj.data.mealsfilterd.repo;

import com.example.foodproj.data.mealsfilterd.datasource.MealsFilteredResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

public interface MealsFilteredRepo {
    Observable<MealsFilteredResponse> getMealsFiltered(Map<String, String> filter);
}
