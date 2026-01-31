package com.example.foodproj.data.home.datasource;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeServices {
    @GET("random.php")
    Observable<MealResponse> getRandomMealData();


}
