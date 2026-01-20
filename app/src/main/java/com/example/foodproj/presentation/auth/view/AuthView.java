package com.example.foodproj.presentation.auth.view;

import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;

public interface AuthView {
    void showLoading();
    void hideLoading();
    void startActivityForResult(Intent intent, int requestCode);
    void onLoginSuccess(FirebaseUser user);
    void onLoginError(String error);
    void onSignUpSuccess(FirebaseUser user);
    void onSignUpError(String error);
}
