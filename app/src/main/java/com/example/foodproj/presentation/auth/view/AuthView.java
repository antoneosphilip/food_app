package com.example.foodproj.presentation.auth.view;

import android.content.Intent;

public interface AuthView {
    void showLoading();
    void hideLoading();
    void startActivityForResult(Intent intent, int requestCode);
}
