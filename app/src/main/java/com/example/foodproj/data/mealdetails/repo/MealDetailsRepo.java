package com.example.foodproj.data.mealdetails.repo;

import com.example.foodproj.data.home.datasource.MealResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

public interface MealDetailsRepo {
    Observable<MealResponse> getMealDetails(Map<String, String> filters);
}
