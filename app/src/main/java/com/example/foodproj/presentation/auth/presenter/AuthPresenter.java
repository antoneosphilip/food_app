package com.example.foodproj.presentation.auth.presenter;

import com.example.foodproj.data.auth.datasource.AuthNetworkResponse;
import com.google.firebase.auth.FirebaseUser;

public interface AuthPresenter {
    void onEmailSignIn(String email, String password);

    void onEmailSignUp(String email, String password);

    void onGoogleSignIn();

    void handleGoogleSignInResult(String idToken);

    void saveToken(String token);

    String getToken();
    
    Boolean isLoggedIn();

    void logOut();

}
