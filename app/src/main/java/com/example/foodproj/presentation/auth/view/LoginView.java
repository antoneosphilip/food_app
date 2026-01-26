package com.example.foodproj.presentation.auth.view;

import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;

public interface LoginView extends AuthView {


    void onLoginSuccess(FirebaseUser user);
    void onLoginError(String error);

}
