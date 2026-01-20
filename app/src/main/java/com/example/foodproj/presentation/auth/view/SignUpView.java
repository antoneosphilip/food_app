package com.example.foodproj.presentation.auth.view;

import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;

public interface SignUpView extends AuthView{

    void onSignUpSuccess(FirebaseUser user);
    void onSignUpError(String error);
}
