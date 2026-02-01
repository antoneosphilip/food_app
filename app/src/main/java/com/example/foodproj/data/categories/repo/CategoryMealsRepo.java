package com.example.foodproj.data.categories.repo;


import com.example.foodproj.data.categories.datasource.CategoriesResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface CategoryMealsRepo {
    Single<CategoriesResponse> getCategoriesMeals();
}

