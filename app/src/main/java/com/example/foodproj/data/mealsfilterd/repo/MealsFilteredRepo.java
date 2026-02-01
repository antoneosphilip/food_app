package com.example.foodproj.data.mealsfilterd.repo;

import com.example.foodproj.data.mealsfilterd.datasource.MealsFilteredResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface MealsFilteredRepo {
    Single<MealsFilteredResponse> getMealsFiltered(Map<String, String> filter);
}
