package com.example.foodproj.presentation.mealsdetails.presenter;

import android.content.Context;

import com.example.foodproj.data.calendar.model.MealPlan;
import com.example.foodproj.data.calendar.repo.MealPlanRepo;
import com.example.foodproj.data.calendar.repo.MealPlanRepoImpl;
import com.example.foodproj.data.favorite.datasource.MealsLocalDataBase;
import com.example.foodproj.data.favorite.repo.FavoriteRepo;
import com.example.foodproj.data.favorite.repo.FavoriteRepoImpl;
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
    private final FavoriteRepo favoriteRepo;
    private final MealPlanRepo mealPlanRepo;

    public MealsDetailsPresenterImpl(MealsDetailsView mealsDetailsView, Context context) {
        this.mealDetailsRepo = new MealDetailsRepoImpl();
        this.mealsDetailsView=mealsDetailsView;
        this.favoriteRepo= new FavoriteRepoImpl(context);
        this.mealPlanRepo=new MealPlanRepoImpl(context);
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

    @Override
    public void insertMeal(Meal meal) {
        favoriteRepo.InsertMeal(meal);
        mealsDetailsView.insertMeal();

    }

    @Override
    public void insertMealPlan(MealPlan mealPlan) {
        mealPlanRepo.InsertMeal(mealPlan);
        mealsDetailsView.insertMealPlan();
    }


}
