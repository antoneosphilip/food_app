package com.example.foodproj.presentation.home.presenter;

import android.content.Context;

import com.example.foodproj.data.home.datasource.HomeNetworkResponse;
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.data.home.repo.HomeRepo;
import com.example.foodproj.data.home.repo.HomeRepoImpl;
import com.example.foodproj.presentation.home.view.HomeView;

public class HomePresenterImpl implements HomePresenter{
    HomeRepo homeRepo;
    HomeView homeView;
    public HomePresenterImpl(HomeView homeView){
      this.homeView=homeView;
      this.homeRepo= new HomeRepoImpl();
    }


    @Override
    public void getMeals() {
        homeRepo.getMeals(new HomeNetworkResponse() {
            @Override
            public void onSuccess(Meal meals) {
                homeView.mealFetchedSuccessfully(meals);
            }

            @Override
            public void onError(String message) {
                homeView.mealFetchedFailure();
            }
        });
    }
}
