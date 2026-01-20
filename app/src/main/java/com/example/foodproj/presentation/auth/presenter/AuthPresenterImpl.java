package com.example.foodproj.presentation.auth.presenter;

import android.content.Intent;

import com.example.foodproj.data.auth.repo.AuthRepo;
import com.example.foodproj.data.auth.datasource.AuthNetworkResponse;
import com.example.foodproj.data.auth.repo.AuthRepoImpl;
import com.example.foodproj.presentation.auth.view.AuthView;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseUser;

public class AuthPresenterImpl implements AuthPresenter {

    private final AuthRepo repository;
    private final AuthView view;

    public AuthPresenterImpl(AuthView view, GoogleSignInClient googleSignInClient) {
        this.repository = new AuthRepoImpl(googleSignInClient);
        this.view = view;
    }

    @Override
    public void onEmailSignIn(String email, String password) {
        view.showLoading();
        repository.signIn(email, password, new AuthNetworkResponse() {
            @Override
            public void onSuccess(FirebaseUser user) {
                view.hideLoading();
                view.onLoginSuccess(user);
            }

            @Override
            public void onError(Exception e) {
                view.hideLoading();
                view.onLoginError(e.getMessage());
            }
        });
    }

    @Override
    public void onEmailSignUp(String email, String password) {
        view.showLoading();
        repository.signUp(email, password, new AuthNetworkResponse() {
            @Override
            public void onSuccess(FirebaseUser user) {
                view.hideLoading();
                view.onSignUpSuccess(user);
            }

            @Override
            public void onError(Exception e) {
                view.hideLoading();
                view.onSignUpError(e.getMessage());
            }
        });
    }

    @Override
    public void onGoogleSignIn() {
        Intent intent = repository.getGoogleSignInIntent();
        view.startActivityForResult(intent, 9001);
    }

    @Override
    public void handleGoogleSignInResult(String idToken) {
        view.showLoading();
        repository.signInWithGoogle(idToken, new AuthNetworkResponse() {
            @Override
            public void onSuccess(FirebaseUser user) {
                view.hideLoading();
                view.onLoginSuccess(user);
            }

            @Override
            public void onError(Exception e) {
                view.hideLoading();
                view.onLoginError(e.getMessage());
            }
        });
    }
}
