package com.example.foodproj.data.categories.repo;

import com.example.foodproj.data.categories.datasource.CategoriesMealsResponse;
import com.example.foodproj.data.categories.datasource.CategoriesNetworkResponse;
import com.example.foodproj.data.home.datasource.CategoriesResponse;

import io.reactivex.rxjava3.core.Observable;

public interface CategoryMealsRepo {
    Observable<CategoriesResponse> getCategoriesMeals();
}

