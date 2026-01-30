package com.example.foodproj.data.home.datasource;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.example.foodproj.data.home.model.Category;
import com.example.foodproj.data.home.model.Ingredient;
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.network.Network;

import java.io.IOException;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRemoteData{
    private HomeServices homeServices;
    public HomeRemoteData(){
        homeServices= Network.getInstance().homeServices;
    }
    public Observable<MealResponse> getMeals() {
       return homeServices.getRandomMealData();
    }


    public Observable<CategoriesResponse> getCategories (){
       return homeServices.getCategories();
    }
}
