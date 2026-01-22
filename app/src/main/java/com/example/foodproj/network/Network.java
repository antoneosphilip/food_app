package com.example.foodproj.network;

import com.example.foodproj.data.home.datasource.HomeServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    public HomeServices homeServices;
    private static Network instance=null;
    public Network(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        homeServices=retrofit.create(HomeServices.class);

    }
    public static Network getInstance(){
        if(instance==null)
            instance=new Network();
        return instance;
    }

}
