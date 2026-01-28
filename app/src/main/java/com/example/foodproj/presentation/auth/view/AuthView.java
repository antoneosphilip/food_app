package com.example.foodproj.presentation.auth.view;

import android.content.Intent;

import com.example.foodproj.data.home.model.Meal;

import java.util.List;

public interface AuthView {
    void showLoading();
    void hideLoading();
    void startActivityForResult(Intent intent, int requestCode);


}
