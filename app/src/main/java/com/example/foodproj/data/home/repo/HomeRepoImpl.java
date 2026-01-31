package com.example.foodproj.data.home.repo;

import android.content.Context;

import com.example.foodproj.data.categories.datasource.CategoriesRemoteData;
import com.example.foodproj.data.categories.datasource.CategoriesResponse;
import com.example.foodproj.data.home.datasource.HomeRemoteData;
import com.example.foodproj.data.home.datasource.MealResponse;
import com.example.foodproj.data.home.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class HomeRepoImpl implements HomeRepo{
    private final HomeRemoteData homeRemoteData;
    private final CategoriesRemoteData categoriesRemoteData;


    public HomeRepoImpl(Context context) {
        this.homeRemoteData = new HomeRemoteData();
        this.categoriesRemoteData=new CategoriesRemoteData();
    }

    @Override
    public Observable<MealResponse> getMeals() {
        return homeRemoteData.getMeals();
    }

    @Override
    public Observable<CategoriesResponse> getCategories() {
       return categoriesRemoteData.getCategoriesMeals();
    }

}
