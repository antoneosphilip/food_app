package com.example.foodproj.data.home.repo;

import com.example.foodproj.data.home.datasource.HomeNetworkResponse;
import com.example.foodproj.data.home.datasource.HomeRemoteData;

public class HomeRepoImpl implements HomeRepo{
    private final HomeRemoteData homeRemoteData;

    public HomeRepoImpl() {
        this.homeRemoteData = new HomeRemoteData();
    }

    @Override
    public void getMeals(HomeNetworkResponse homeNetworkResponse) {
        homeRemoteData.getMeals(homeNetworkResponse);
    }
}
