package com.example.foodproj.data.mealdetails.repo;

import com.example.foodproj.data.home.datasource.HomeRemoteData;
import com.example.foodproj.data.mealdetails.datasource.MealsDetailsNetworkResponse;
import com.example.foodproj.data.mealdetails.datasource.MealsDetailsRemoteData;

import java.util.Map;

public class MealDetailsRepoImpl implements MealDetailsRepo{
    private final MealsDetailsRemoteData mealsDetailsRemoteData;

    public MealDetailsRepoImpl() {
        this.mealsDetailsRemoteData = new MealsDetailsRemoteData();
    }

    @Override
    public void getMealDetails(MealsDetailsNetworkResponse mealsDetailsNetworkResponse,Map<String, String> filters) {
        mealsDetailsRemoteData.getMealsDetails(mealsDetailsNetworkResponse,filters);

    }
}
