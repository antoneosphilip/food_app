package com.example.foodproj.data.mealsfilterd.datasource;


import com.example.foodproj.network.Network;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealsFilteredRemoteData {
    private final MealsFilteredService mealsFilteredService;

    public MealsFilteredRemoteData() {
        this.mealsFilteredService = Network.getInstance().mealsFilteredService;
    }

    public Single<MealsFilteredResponse> getMealsFiltered(Map<String, String> filters){
       return mealsFilteredService.getMealsByFilter(filters);
    }
}
