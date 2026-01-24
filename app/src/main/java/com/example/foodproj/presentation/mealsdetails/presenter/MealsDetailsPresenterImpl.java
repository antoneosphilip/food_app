package com.example.foodproj.presentation.mealsdetails.presenter;

import com.example.foodproj.data.mealdetails.datasource.MealsDetailsNetworkResponse;
import com.example.foodproj.data.mealdetails.repo.MealDetailsRepo;
import com.example.foodproj.data.mealdetails.repo.MealDetailsRepoImpl;
import com.example.foodproj.presentation.mealsdetails.view.MealsDetailsView;

import java.util.Map;

public class MealsDetailsPresenterImpl implements MealDetailsRepo {
    private final MealDetailsRepo mealDetailsRepo;
    public final MealsDetailsView mealsDetailsView;
    public MealsDetailsPresenterImpl(MealDetailsRepo mealDetailsRepo,MealsDetailsView mealsDetailsView) {
        this.mealDetailsRepo = new MealDetailsRepoImpl();
        this.mealsDetailsView=mealsDetailsView;
    }

    @Override
    public void getMealDetails(MealsDetailsNetworkResponse mealsDetailsNetworkResponse, Map<String, String> filters) {
        mealDetailsRepo.getMealDetails(mealsDetailsNetworkResponse,filters);
    }
}
