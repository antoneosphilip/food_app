package com.example.foodproj.presentation.categoryMeals.presenter;

import com.example.foodproj.data.categories.datasource.CategoriesNetworkResponse;
import com.example.foodproj.data.categories.model.CategoryMeals;
import com.example.foodproj.data.categories.repo.CategoryMealsRepo;
import com.example.foodproj.data.categories.repo.CategoryMealsRepoImpl;
import com.example.foodproj.presentation.categoryMeals.view.CategoriesMealsView;

import java.util.List;

public class CategoriesMealsPresenterImpl implements CategoriesMealsPresenter{
    private final CategoryMealsRepo categoryMealsRepo;
    private final CategoriesMealsView categoriesMealsView;

    public CategoriesMealsPresenterImpl(CategoriesMealsView categoriesMealsView) {
        this.categoryMealsRepo = new CategoryMealsRepoImpl();
        this.categoriesMealsView=categoriesMealsView;
    }

    @Override
    public void getCategoriesMeals() {
        categoryMealsRepo.getCategoriesMeals(new CategoriesNetworkResponse() {
            @Override
            public void onCategoriesMealsSuccess(List<CategoryMeals> categoryMeals) {
                categoriesMealsView.getCategoryMealsSuccess(categoryMeals);
            }

            @Override
            public void onCategoriesMealsMealsError(String message) {
                categoriesMealsView.getCategoryMealsError();
            }
        });
    }
}
