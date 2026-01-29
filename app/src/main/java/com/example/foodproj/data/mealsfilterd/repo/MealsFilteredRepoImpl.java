package com.example.foodproj.data.mealsfilterd.repo;

import com.example.foodproj.data.mealsfilterd.datasource.MealsFilteredNetworkResponse;
import com.example.foodproj.data.mealsfilterd.datasource.MealsFilteredRemoteData;

import java.util.Map;

public class MealsFilteredRepoImpl implements MealsFilteredRepo{
  final private MealsFilteredRemoteData mealsFilteredRemoteData;

    public MealsFilteredRepoImpl() {
        this.mealsFilteredRemoteData = new MealsFilteredRemoteData();
    }

    @Override
    public void getMealsFiltered(Map<String, String> filter, MealsFilteredNetworkResponse mealsFilteredNetworkResponse) {
        mealsFilteredRemoteData.getMealsFiltered(mealsFilteredNetworkResponse,filter);
    }
}
