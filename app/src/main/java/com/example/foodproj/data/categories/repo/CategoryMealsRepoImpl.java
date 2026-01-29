package com.example.foodproj.data.categories.repo;

import com.example.foodproj.data.categories.datasource.CategoriesMealsResponse;
import com.example.foodproj.data.categories.datasource.CategoriesNetworkResponse;
import com.example.foodproj.data.categories.datasource.CategoriesRemoteData;

public class CategoryMealsRepoImpl implements CategoryMealsRepo{
   private final CategoriesRemoteData categoriesRemoteData;

    public CategoryMealsRepoImpl() {
        this.categoriesRemoteData = new CategoriesRemoteData();
    }

    @Override
    public void getCategoriesMeals(CategoriesNetworkResponse categoriesNetworkResponse) {
        categoriesRemoteData.getCategoriesMeals(categoriesNetworkResponse);
    }
}
