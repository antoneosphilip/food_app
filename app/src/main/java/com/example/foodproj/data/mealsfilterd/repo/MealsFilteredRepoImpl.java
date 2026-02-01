package com.example.foodproj.data.mealsfilterd.repo;

import com.example.foodproj.data.mealsfilterd.datasource.MealsFilteredRemoteData;
import com.example.foodproj.data.mealsfilterd.datasource.MealsFilteredResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class MealsFilteredRepoImpl implements MealsFilteredRepo{
  final private MealsFilteredRemoteData mealsFilteredRemoteData;

    public MealsFilteredRepoImpl() {
        this.mealsFilteredRemoteData = new MealsFilteredRemoteData();
    }

    @Override
    public Single<MealsFilteredResponse> getMealsFiltered(Map<String, String> filter) {
        return mealsFilteredRemoteData.getMealsFiltered(filter);
    }
}
