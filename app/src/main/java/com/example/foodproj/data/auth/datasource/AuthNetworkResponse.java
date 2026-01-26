package com.example.foodproj.data.auth.datasource;

import com.google.firebase.auth.FirebaseUser;

public interface AuthNetworkResponse {
    void onSuccess(FirebaseUser user);
    void onError(Exception e);
}