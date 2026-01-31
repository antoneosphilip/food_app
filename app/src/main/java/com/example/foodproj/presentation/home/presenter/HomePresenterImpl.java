package com.example.foodproj.presentation.home.presenter;

import android.content.Context;

import com.example.foodproj.data.auth.datasource.LogOutNetworkResponse;
import com.example.foodproj.data.auth.repo.AuthRepo;
import com.example.foodproj.data.auth.repo.AuthRepoImpl;
import com.example.foodproj.data.categories.repo.CategoryMealsRepo;
import com.example.foodproj.data.categories.repo.CategoryMealsRepoImpl;

import com.example.foodproj.data.home.repo.HomeRepo;
import com.example.foodproj.data.home.repo.HomeRepoImpl;
import com.example.foodproj.presentation.home.view.home.HomeView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImpl implements HomePresenter{
    HomeRepo homeRepo;
    HomeView homeView;

    CategoryMealsRepo categoryMealsRepo;

    public HomePresenterImpl(HomeView homeView, Context context){
      this.homeView=homeView;
      this.homeRepo= new HomeRepoImpl(context);
      this.categoryMealsRepo=new CategoryMealsRepoImpl();
    }


    @Override
    public void getMeals() {
        homeRepo.getMeals().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> homeView.mealFetchedSuccessfully(meals.getMeals()),
                        throwable -> homeView.mealFetchedFailure()
                );
    }

    @Override
    public void getCategories() {
        categoryMealsRepo.getCategoriesMeals().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> homeView.categoryFetchedSuccessfully(meals.getCategories()),
                        throwable -> homeView.mealFetchedFailure()
                );
    }


}
