package com.example.foodproj.data.home.datasource;

import com.example.foodproj.data.home.model.Meal;

import java.util.List;

public interface HomeNetworkResponse {
    void onSuccess(Meal meals);
    void onError(String message);
}
