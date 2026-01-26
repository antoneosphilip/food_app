package com.example.foodproj.presentation.auth.presenter;

import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class AuthChecker {

    private final AuthPresenter presenter;

    public AuthChecker(Context context, GoogleSignInClient googleSignInClient) {
        this.presenter = new AuthPresenterImpl(null, googleSignInClient, context);
    }

    public boolean isLoggedIn() {
        return presenter.isLoggedIn();
    }
}
