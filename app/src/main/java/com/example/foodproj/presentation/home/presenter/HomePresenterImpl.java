package com.example.foodproj.presentation.home.presenter;

import android.content.Context;

import com.example.foodproj.data.auth.datasource.LogOutNetworkResponse;
import com.example.foodproj.data.auth.repo.AuthRepo;
import com.example.foodproj.data.auth.repo.AuthRepoImpl;
import com.example.foodproj.data.favorite.repo.FavoriteRepoImpl;
import com.example.foodproj.data.home.datasource.CategoryNetworkResponse;
import com.example.foodproj.data.home.datasource.MealsNetworkResponse;
import com.example.foodproj.data.home.model.Category;
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.data.home.repo.HomeRepo;
import com.example.foodproj.data.home.repo.HomeRepoImpl;
import com.example.foodproj.presentation.home.view.home.HomeView;

import java.util.List;

public class HomePresenterImpl implements HomePresenter{
    HomeRepo homeRepo;
    HomeView homeView;


    public HomePresenterImpl(HomeView homeView, Context context){
      this.homeView=homeView;
      this.homeRepo= new HomeRepoImpl(context);
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
