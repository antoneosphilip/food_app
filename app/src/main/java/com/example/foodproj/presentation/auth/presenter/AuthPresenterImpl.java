package com.example.foodproj.presentation.auth.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.foodproj.data.auth.repo.AuthRepo;
import com.example.foodproj.data.auth.datasource.AuthNetworkResponse;
import com.example.foodproj.data.auth.repo.AuthRepoImpl;
import com.example.foodproj.presentation.auth.view.AuthView;
import com.example.foodproj.presentation.auth.view.LoginView;
import com.example.foodproj.presentation.auth.view.SignUpView;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseUser;

public class AuthPresenterImpl implements AuthPresenter {

    private final AuthRepo repository;
    private final AuthView view;

    public AuthPresenterImpl(AuthView view, GoogleSignInClient googleSignInClient, Context context) {
        this.repository = new AuthRepoImpl(googleSignInClient,context);
        this.view = view;
    }

    @Override
    public void onEmailSignIn(String email, String password) {
        view.showLoading();
        repository.signIn(email, password, new AuthNetworkResponse() {
            @Override
            public void onSuccess(FirebaseUser user) {
                view.hideLoading();
                if (view instanceof LoginView) {
                    ((LoginView) view).onLoginSuccess(user);
                }

            }

            @Override
            public void onError(Exception e) {
                view.hideLoading();
                if (view instanceof LoginView) {
                    ((LoginView) view).onLoginError(e.getMessage());
                }
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
                if (view instanceof SignUpView) {
                    ((SignUpView) view).onSignUpSuccess(user);
                }
            }

            @Override
            public void onError(Exception e) {
                view.hideLoading();
                if (view instanceof SignUpView) {
                    ((SignUpView) view).onSignUpError(e.getMessage());
                }
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
                if (view instanceof LoginView) {
                    ((LoginView) view).onLoginSuccess(user);
                }
            }

            @Override
            public void onError(Exception e) {
                view.hideLoading();
                if (view instanceof LoginView) {
                    ((LoginView) view).onLoginError(e.getMessage());
                }
            }
        });
    }

    @Override
    public void saveToken(String token) {
        repository.saveToken(token);
    }

    @Override
    public String getToken() {
        return repository.getToken();
    }

    @Override
    public Boolean isLoggedIn() {
        return repository.isLoggedIn();
    }

    @Override
    public void logOut() {
        repository.logOut();
    }
}
