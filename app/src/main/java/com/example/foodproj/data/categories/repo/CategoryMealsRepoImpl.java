package com.example.foodproj.data.categories.repo;

import com.example.foodproj.data.categories.datasource.CategoriesMealsResponse;
import com.example.foodproj.data.categories.datasource.CategoriesNetworkResponse;
import com.example.foodproj.data.categories.datasource.CategoriesRemoteData;

import io.reactivex.rxjava3.core.Observable;

public class CategoryMealsRepoImpl implements CategoryMealsRepo{
   private final CategoriesRemoteData categoriesRemoteData;

    public CategoryMealsRepoImpl() {
        this.categoriesRemoteData = new CategoriesRemoteData();
    }

    @Override
    public Observable<CategoriesMealsResponse> getCategoriesMeals() {
        return categoriesRemoteData.getCategoriesMeals();
    }
}
