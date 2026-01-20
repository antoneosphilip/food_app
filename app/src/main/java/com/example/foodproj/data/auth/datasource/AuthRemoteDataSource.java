package com.example.foodproj.data.auth.datasource;

import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthRemoteDataSource {
    private final FirebaseAuth mAuth;
    private final GoogleSignInClient googleSignInClient;

    public AuthRemoteDataSource(GoogleSignInClient googleSignInClient) {
        this.mAuth = FirebaseAuth.getInstance();
        this.googleSignInClient = googleSignInClient;
    }

    public Intent getGoogleSignInIntent() {
        return googleSignInClient.getSignInIntent();
    }

    public void signInWithEmail(String email, String password, AuthNetworkResponse callback) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callback.onSuccess(mAuth.getCurrentUser());
            } else {
                callback.onError(task.getException());
            }
        });
    }

    public void signUpWithEmail(String email, String password, AuthNetworkResponse callback) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callback.onSuccess(mAuth.getCurrentUser());
            } else {
                callback.onError(task.getException());
            }
        });
    }

    public void firebaseAuthWithGoogle(String idToken, AuthNetworkResponse callback) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callback.onSuccess(mAuth.getCurrentUser());
            } else {
                callback.onError(task.getException());
            }
        });
    }
}
