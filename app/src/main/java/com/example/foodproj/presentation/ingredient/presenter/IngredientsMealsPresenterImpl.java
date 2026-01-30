
package com.example.foodproj.presentation.ingredient.presenter;

import com.example.foodproj.data.ingredient.datasource.IngredientsNetworkResponse;
import com.example.foodproj.data.ingredient.model.IngredientMeals;
import com.example.foodproj.data.ingredient.repo.IngredientRepo;
import com.example.foodproj.data.ingredient.repo.IngredientRepoImpl;
import com.example.foodproj.presentation.ingredient.view.IngredientsMealsView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class IngredientsMealsPresenterImpl implements IngredientsMealsPresenter{
    final private IngredientRepo ingredientRepo;
    final private IngredientsMealsView ingredientsMealsView;
    public IngredientsMealsPresenterImpl(IngredientsMealsView ingredientsMealsView) {
        this.ingredientRepo = new IngredientRepoImpl();
        this.ingredientsMealsView=ingredientsMealsView;
    }

    @Override
    public void getIngredientsMeals() {
        ingredientRepo.getIngredientMeals().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> ingredientsMealsView.getIngredientsMealsSuccess(meals.getIngredientsMeals()),
                        throwable -> ingredientsMealsView.getIngredientsMealsError()
                );
    }
}
