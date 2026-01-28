package com.example.foodproj.presentation.auth.presenter;

import com.example.foodproj.data.auth.datasource.AuthNetworkResponse;
import com.example.foodproj.data.home.model.Meal;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public interface AuthPresenter {
    void onEmailSignIn(String email, String password);

    void onEmailSignUp(String email, String password);

    void onGoogleSignIn();

    void handleGoogleSignInResult(String idToken);



}
