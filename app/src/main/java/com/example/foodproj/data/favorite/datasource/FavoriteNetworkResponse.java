package com.example.foodproj.data.favorite.datasource;

import com.example.foodproj.data.home.model.Meal;

import java.util.List;

public interface FavoriteNetworkResponse {

    void onSuccess(List<Meal> mealList);

    void onError(String message);

}
