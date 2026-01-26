package com.example.foodproj.data.auth.repo;

import android.content.Intent;

import com.example.foodproj.data.auth.datasource.AuthNetworkResponse;

public interface AuthRepo {
    void signIn(String email, String password, AuthNetworkResponse callback);
    void signUp(String email, String password, AuthNetworkResponse callback);
    void signInWithGoogle(String idToken, AuthNetworkResponse callback);
    Intent getGoogleSignInIntent();

    void saveToken(String token);

    String getToken();

    Boolean isLoggedIn();

    void logOut();

}