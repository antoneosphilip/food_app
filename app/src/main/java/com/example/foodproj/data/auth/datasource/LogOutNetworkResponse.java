package com.example.foodproj.data.auth.datasource;

import com.google.firebase.auth.FirebaseUser;

public interface LogOutNetworkResponse {
    void onSuccess();
    void onError(String e);
}
