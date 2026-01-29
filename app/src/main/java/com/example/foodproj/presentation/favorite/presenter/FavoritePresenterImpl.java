package com.example.foodproj.presentation.favorite.presenter;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.favorite.repo.FavoriteRepo;
import com.example.foodproj.data.favorite.repo.FavoriteRepoImpl;
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.presentation.favorite.view.FavoriteView;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class FavoritePresenterImpl implements FavoritePresenter{
    private final FavoriteRepo favoriteRepo;
    private final FavoriteView favoriteView;
    public FavoritePresenterImpl(Context context,FavoriteView favoriteView) {
        this.favoriteRepo = new FavoriteRepoImpl(context);
        this.favoriteView=favoriteView;
    }

    @Override
    public void InsertMeal(Meal meal) {
        favoriteRepo.InsertMeal(meal);
    }

    @Override
    public LiveData<List<Meal>> getMeals() {
        favoriteView.getFavoriteDataSuccess();

        return favoriteRepo.getMeals();
    }

    @Override
    public void deleteMeal(Meal meal) {
        favoriteView.deleteDataSuccess();
        favoriteRepo.deleteMeal(meal);
        deleteFavoriteRemote(meal.getIdMeal());
    }

    @Override
    public void getRemoteFavorites() {
        favoriteRepo.getRemoteFavorites()
                .addOnSuccessListener(meals -> {

                    if (meals != null && !meals.isEmpty()) {
                        for (Meal meal : meals) {
                            InsertMeal(meal);
                        }
                    }

                    favoriteView.getFavoriteRemoteSuccess();

                })
                .addOnFailureListener(e ->
                        favoriteView.getFavoriteRemoteError(e.getMessage())
                );

    }

    @Override
    public void deleteFavoriteRemote(String id) {
        favoriteRepo.deleteFavoriteRemote(id);
    }


}
