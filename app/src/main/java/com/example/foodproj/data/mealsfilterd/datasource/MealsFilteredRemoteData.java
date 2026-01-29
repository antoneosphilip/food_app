package com.example.foodproj.data.mealsfilterd.datasource;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.example.foodproj.data.categories.datasource.CategoriesMealsResponse;
import com.example.foodproj.data.mealsfilterd.model.MealsFiltered;
import com.example.foodproj.network.Network;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealsFilteredRemoteData {
    private final MealsFilteredService mealsFilteredService;

    public MealsFilteredRemoteData() {
        this.mealsFilteredService = Network.getInstance().mealsFilteredService;
    }

    public Observable<MealsFilteredResponse> getMealsFiltered(Map<String, String> filters){
       return mealsFilteredService.getMealsByFilter(filters);
    }
}
