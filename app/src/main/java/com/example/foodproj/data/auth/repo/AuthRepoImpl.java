package com.example.foodproj.data.auth.repo;

import android.content.Context;
import android.content.Intent;

import com.example.foodproj.data.auth.datasource.AuthNetworkResponse;
import com.example.foodproj.data.auth.datasource.AuthRemoteDataSource;
import com.example.foodproj.prefs.UserPrefs;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class AuthRepoImpl implements AuthRepo {

    private final AuthRemoteDataSource dataSource;


    public AuthRepoImpl(GoogleSignInClient googleSignInClient, Context context) {
        this.dataSource = new AuthRemoteDataSource(googleSignInClient);

    }

    @Override
    public void signIn(String email, String password, AuthNetworkResponse callback) {
        dataSource.signInWithEmail(email, password, callback);

    }

    @Override
    public void signUp(String email, String password, AuthNetworkResponse callback) {
        dataSource.signUpWithEmail(email, password, callback);
    }

    @Override
    public void signInWithGoogle(String idToken, AuthNetworkResponse callback) {
        dataSource.firebaseAuthWithGoogle(idToken, callback);
    }

    @Override
    public Intent getGoogleSignInIntent() {
        return dataSource.getGoogleSignInIntent();
    }


}