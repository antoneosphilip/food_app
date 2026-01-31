package com.example.foodproj.data.categories.repo;


import com.example.foodproj.data.categories.datasource.CategoriesResponse;

import io.reactivex.rxjava3.core.Observable;

public interface CategoryMealsRepo {
    Observable<CategoriesResponse> getCategoriesMeals();
}

