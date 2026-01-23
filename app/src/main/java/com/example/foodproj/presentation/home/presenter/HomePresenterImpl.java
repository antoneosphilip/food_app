package com.example.foodproj.presentation.home.presenter;

import android.content.Context;

import com.example.foodproj.data.home.datasource.CategoryNetworkResponse;
import com.example.foodproj.data.home.datasource.HomeNetworkResponse;
import com.example.foodproj.data.home.datasource.MealResponse;
import com.example.foodproj.data.home.datasource.MealsNetworkResponse;
import com.example.foodproj.data.home.model.Category;
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.data.home.repo.HomeRepo;
import com.example.foodproj.data.home.repo.HomeRepoImpl;
import com.example.foodproj.presentation.home.view.HomeView;

import java.util.List;

public class HomePresenterImpl implements HomePresenter{
    HomeRepo homeRepo;
    HomeView homeView;
    public HomePresenterImpl(HomeView homeView){
      this.homeView=homeView;
      this.homeRepo= new HomeRepoImpl();
    }


    @Override
    public void getMeals() {
        homeRepo.getMeals(new MealsNetworkResponse() {
            @Override
            public void onMealsSuccess(List<Meal> meals) {
                homeView.mealFetchedSuccessfully(meals);
            }

            @Override
            public void onMealsError(String message) {
                homeView.mealFetchedFailure();
            }
        });
    }

    @Override
    public void getCategories() {
        homeRepo.getCategories(new CategoryNetworkResponse() {
            @Override
            public void onCategorySuccess(List<Category> categories) {
                homeView.categoryFetchedSuccessfully(categories);
            }

            @Override
            public void onCategoryError(String message) {
                homeView.categoryFetchedFailure();
            }
        });
    }
}
