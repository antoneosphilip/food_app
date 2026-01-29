package com.example.foodproj.data.auth.repo;

import android.content.Intent;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.auth.datasource.AuthNetworkResponse;
import com.example.foodproj.data.auth.datasource.LogOutNetworkResponse;
import com.example.foodproj.data.home.model.Meal;

import java.util.List;

public interface AuthRepo {
    void signIn(String email, String password, AuthNetworkResponse callback);
    void signUp(String email, String password, AuthNetworkResponse callback);
    void signInWithGoogle(String idToken, AuthNetworkResponse callback);
    Intent getGoogleSignInIntent();




}