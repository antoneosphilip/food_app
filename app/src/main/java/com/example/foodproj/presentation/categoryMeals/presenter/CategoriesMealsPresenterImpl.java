package com.example.foodproj.presentation.categoryMeals.presenter;

import com.example.foodproj.data.categories.repo.CategoryMealsRepo;
import com.example.foodproj.data.categories.repo.CategoryMealsRepoImpl;
import com.example.foodproj.presentation.categoryMeals.view.CategoriesMealsView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CategoriesMealsPresenterImpl implements CategoriesMealsPresenter{
    private final CategoryMealsRepo categoryMealsRepo;
    private final CategoriesMealsView categoriesMealsView;

    public CategoriesMealsPresenterImpl(CategoriesMealsView categoriesMealsView) {
        this.categoryMealsRepo = new CategoryMealsRepoImpl();
        this.categoriesMealsView=categoriesMealsView;
    }

    @Override
    public void getCategoriesMeals() {
        categoryMealsRepo.getCategoriesMeals().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> categoriesMealsView.getCategoryMealsSuccess(meals.getCategories()),
                        throwable -> categoriesMealsView.getCategoryMealsError(throwable.getMessage())
                );;
    }
}
