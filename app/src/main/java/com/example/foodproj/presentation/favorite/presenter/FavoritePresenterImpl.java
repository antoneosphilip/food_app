package com.example.foodproj.presentation.favorite.presenter;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.favorite.repo.FavoriteRepo;
import com.example.foodproj.data.favorite.repo.FavoriteRepoImpl;
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.presentation.favorite.view.FavoriteView;
import com.google.android.gms.tasks.Task;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritePresenterImpl implements FavoritePresenter{
    private final FavoriteRepo favoriteRepo;
    private final FavoriteView favoriteView;

    private static final String TAG = "FavoritePresenterImpl";
    public FavoritePresenterImpl(Context context,FavoriteView favoriteView) {
        this.favoriteRepo = new FavoriteRepoImpl(context);
        this.favoriteView=favoriteView;
    }

    @Override
    public void InsertMeal(Meal meal) {
        favoriteRepo.InsertMeal(meal).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            favoriteView.insertDataSuccess();
                        }
                );
    }

    @Override
    public void getMeals() {
         favoriteRepo.getMeals().subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(
                         meals -> favoriteView.getFavoriteDataSuccess(meals),
                         throwable -> favoriteView.getDataError()
                 );
    }

    @Override
    public void deleteMeal(Meal meal) {
        favoriteRepo.deleteMeal(meal).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            favoriteView.deleteDataSuccess();
                            deleteFavoriteRemote(meal.getIdMeal());
                        }
                );;
    }

    @Override
    public void getRemoteFavorites() {
        favoriteRepo.getRemoteFavorites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            if (meals != null && !meals.isEmpty()) {
                                for (Meal meal : meals) {
                                    Log.i(TAG, "getRemoteFavorites: " + meal.getStrMeal());
                                    InsertMeal(meal);
                                }
                            }
                            favoriteView.getFavoriteRemoteSuccess();
                        },
                        error -> {
                            favoriteView.getFavoriteRemoteError(error.getMessage());
                        }
                );
    }

    @Override
    public void deleteFavoriteRemote(String id) {
        favoriteRepo.deleteFavoriteRemote(id);
    }

    @Override
    public void deleteAllFavorites() {
        favoriteRepo.deleteAllFavorite().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {

                        }
                );
    }


}
