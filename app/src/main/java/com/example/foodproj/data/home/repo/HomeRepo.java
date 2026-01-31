package com.example.foodproj.data.home.repo;

import com.example.foodproj.data.categories.datasource.CategoriesResponse;
import com.example.foodproj.data.favorite.datasource.MealsLocalDataBase;
import com.example.foodproj.data.home.datasource.MealResponse;
import com.example.foodproj.data.home.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface HomeRepo {
    Observable<MealResponse> getMeals();
    Observable<CategoriesResponse> getCategories();
}
