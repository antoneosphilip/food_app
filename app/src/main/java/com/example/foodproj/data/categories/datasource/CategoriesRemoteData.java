package com.example.foodproj.data.categories.datasource;

import com.example.foodproj.network.Network;

import io.reactivex.rxjava3.core.Observable;

public class CategoriesRemoteData {
    private final CategoriesService categoriesService;

    public CategoriesRemoteData() {
        this.categoriesService = Network.getInstance().categoriesService;
    }
    public Observable<CategoriesResponse> getCategoriesMeals(){
      return   categoriesService.getCountries();
    }

}
