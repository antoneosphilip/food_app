package com.example.foodproj.data.auth.repo;

import android.content.Context;
import android.content.Intent;

import com.example.foodproj.data.auth.datasource.AuthNetworkResponse;
import com.example.foodproj.data.auth.datasource.AuthRemoteDataSource;
import com.example.foodproj.data.favorite.datasource.FirebaseRemoteDataSource;
import com.example.foodproj.data.favorite.datasource.MealsLocalDataBase;
import com.example.foodproj.data.favorite.repo.FavoriteRepo;
import com.example.foodproj.data.favorite.repo.FavoriteRepoImpl;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class AuthRepoImpl implements AuthRepo {

    private final AuthRemoteDataSource dataSource;
    private final FirebaseRemoteDataSource firebaseRemote;

    private  final FavoriteRepo favoriteRepo;
    private final MealsLocalDataBase mealsLocalDataBase;


    public AuthRepoImpl(GoogleSignInClient googleSignInClient, Context context) {
        this.dataSource = new AuthRemoteDataSource(googleSignInClient);
        this.firebaseRemote=new FirebaseRemoteDataSource();
        this.mealsLocalDataBase=new MealsLocalDataBase(context);
        this.favoriteRepo= new FavoriteRepoImpl(context);
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