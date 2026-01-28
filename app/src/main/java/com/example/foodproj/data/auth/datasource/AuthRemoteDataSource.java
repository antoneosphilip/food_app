package com.example.foodproj.data.auth.datasource;

import android.content.Intent;
import android.service.autofill.UserData;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.home.model.Meal;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class
AuthRemoteDataSource {
    private final FirebaseAuth mAuth;
    private final GoogleSignInClient googleSignInClient;
    private final FirebaseFirestore firestore;

    public AuthRemoteDataSource(GoogleSignInClient googleSignInClient) {
        this.mAuth = FirebaseAuth.getInstance();
        this.googleSignInClient = googleSignInClient;
        firestore=FirebaseFirestore.getInstance();
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
