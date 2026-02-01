package com.example.foodproj.presentation.mealsdetails.presenter;

import android.content.Context;

import com.example.foodproj.data.calendar.model.MealPlan;
import com.example.foodproj.data.calendar.repo.MealPlanRepo;
import com.example.foodproj.data.calendar.repo.MealPlanRepoImpl;
import com.example.foodproj.data.favorite.datasource.MealsLocalDataBase;
import com.example.foodproj.data.favorite.repo.FavoriteRepo;
import com.example.foodproj.data.favorite.repo.FavoriteRepoImpl;
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.data.mealdetails.repo.MealDetailsRepo;
import com.example.foodproj.data.mealdetails.repo.MealDetailsRepoImpl;
import com.example.foodproj.presentation.mealsdetails.view.MealsDetailsView;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
        mealDetailsRepo.getMealDetails(filters).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> mealsDetailsView.getMealsDetailsSuccess(meals.getMeals()),
                        throwable -> mealsDetailsView.getMealsDetailsError()
                );
    }

    @Override
    public void insertMeal(Meal meal){
        favoriteRepo.InsertMeal(meal).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ()-> mealsDetailsView.insertMeal()
                );

    }

    @Override
    public void insertMealPlan(MealPlan mealPlan) {
        mealPlanRepo.InsertMeal(mealPlan).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ()-> mealsDetailsView.insertMealPlan()
                );
    }

    @Override
    public void uploadFavoriteMeal(Meal meal) {
        favoriteRepo.uploadFavoriteData(meal);
    }

    @Override
    public void uploadPlansMeal(MealPlan mealPlan) {
        mealPlanRepo.uploadPlansData(mealPlan);

    }

    @Override
    public Observable<List<Meal>> getRemoteFavorites() {
        return favoriteRepo.getRemoteFavorites();
    }

    @Override
    public Task<List<MealPlan>> getRemotePlans() {
        return mealPlanRepo.getRemotePlans();
    }




}
