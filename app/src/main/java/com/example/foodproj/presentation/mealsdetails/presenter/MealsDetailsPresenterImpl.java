package com.example.foodproj.presentation.mealsdetails.presenter;

import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.data.mealdetails.datasource.MealsDetailsNetworkResponse;
import com.example.foodproj.data.mealdetails.repo.MealDetailsRepo;
import com.example.foodproj.data.mealdetails.repo.MealDetailsRepoImpl;
import com.example.foodproj.presentation.mealsdetails.view.MealsDetailsView;

import java.util.List;
import java.util.Map;

public class MealsDetailsPresenterImpl implements MealsDetailsPresenter {
    private final MealDetailsRepo mealDetailsRepo;
    public final MealsDetailsView mealsDetailsView;
    public MealsDetailsPresenterImpl(MealsDetailsView mealsDetailsView) {
        this.mealDetailsRepo = new MealDetailsRepoImpl();
        this.mealsDetailsView=mealsDetailsView;
    }

    @Override
    public void getMealDetails( Map<String, String> filters) {
        mealDetailsRepo.getMealDetails(new MealsDetailsNetworkResponse() {
            @Override
            public void onMealsDetailsSuccess(List<Meal> meals) {
                mealsDetailsView.getMealsDetailsSuccess(meals);
            }

            @Override
            public void onMealsDetailsError(String message) {
                mealsDetailsView.getMealsDetailsError();
            }
        }, filters);
    }
}
