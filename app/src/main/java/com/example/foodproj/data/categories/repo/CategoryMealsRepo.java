package com.example.foodproj.data.categories.repo;

import com.example.foodproj.data.categories.datasource.CategoriesMealsResponse;
import com.example.foodproj.data.categories.datasource.CategoriesNetworkResponse;

import io.reactivex.rxjava3.core.Observable;

public interface CategoryMealsRepo {
    Observable<CategoriesMealsResponse> getCategoriesMeals();
}

