package com.example.foodproj.data.mealdetails.repo;

import com.example.foodproj.data.home.datasource.MealResponse;
import com.example.foodproj.data.mealdetails.datasource.MealsDetailsRemoteData;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

public class MealDetailsRepoImpl implements MealDetailsRepo{
    private final MealsDetailsRemoteData mealsDetailsRemoteData;

    public MealDetailsRepoImpl() {
        this.mealsDetailsRemoteData = new MealsDetailsRemoteData();
    }

    @Override
    public Observable<MealResponse> getMealDetails( Map<String, String> filters) {
      return  mealsDetailsRemoteData.getMealsDetails(filters);
    }

}
