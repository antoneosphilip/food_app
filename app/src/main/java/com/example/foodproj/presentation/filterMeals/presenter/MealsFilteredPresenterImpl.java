package com.example.foodproj.presentation.filterMeals.presenter;

import com.example.foodproj.data.mealsfilterd.datasource.MealsFilteredNetworkResponse;
import com.example.foodproj.data.mealsfilterd.model.MealsFiltered;
import com.example.foodproj.data.mealsfilterd.repo.MealsFilteredRepo;
import com.example.foodproj.data.mealsfilterd.repo.MealsFilteredRepoImpl;
import com.example.foodproj.presentation.filterMeals.view.MealsFilteredView;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsFilteredPresenterImpl implements MealsFilteredPresenter{
   private final MealsFilteredRepo mealsFilteredRepo;
   private final MealsFilteredView mealsFilteredView;

    public MealsFilteredPresenterImpl(MealsFilteredView mealsFilteredView) {
        this.mealsFilteredRepo = new MealsFilteredRepoImpl();
        this.mealsFilteredView=mealsFilteredView;
    }

    @Override
    public void getMealsFiltered(Map<String, String> filter) {
        mealsFilteredRepo.getMealsFiltered(filter).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> mealsFilteredView.getMealsFilteredSuccess(meals.getMeals()),
                        throwable -> mealsFilteredView.getMealsFilteredError()
                );
    }
}
