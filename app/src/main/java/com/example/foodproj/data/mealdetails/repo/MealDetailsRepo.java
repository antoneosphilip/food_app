package com.example.foodproj.data.mealdetails.repo;

import com.example.foodproj.data.home.datasource.MealResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface MealDetailsRepo {
    Single<MealResponse> getMealDetails(Map<String, String> filters);
}
