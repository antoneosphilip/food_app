package com.example.foodproj.data.categories.repo;


import com.example.foodproj.data.categories.datasource.CategoriesRemoteData;
import com.example.foodproj.data.categories.datasource.CategoriesResponse;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class CategoryMealsRepoImpl implements CategoryMealsRepo{
   private final CategoriesRemoteData categoriesRemoteData;

    public CategoryMealsRepoImpl() {
        this.categoriesRemoteData = new CategoriesRemoteData();
    }

    @Override
    public Single<CategoriesResponse> getCategoriesMeals() {
        return categoriesRemoteData.getCategoriesMeals();
    }
}
