package com.example.foodproj.presentation.favorite.view;

import com.example.foodproj.data.home.model.Meal;

import java.util.List;

public interface FavoriteView {
    void getFavoriteDataSuccess();
    void getDataError();
    void insertDataError();
    void deleteDataSuccess();
    void deleteDataError();

    void getFavoriteRemoteSuccess();
    void getFavoriteRemoteError(String error);

}
