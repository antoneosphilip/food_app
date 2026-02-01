package com.example.foodproj;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodproj.prefs.UserPrefs;
import com.example.foodproj.presentation.auth.view.LoginActivity;
import com.example.foodproj.presentation.home.view.home.HomeLayout;
import com.example.foodproj.R;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this,
                    UserPrefs.getToken() != null ? HomeLayout.class : LoginActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DELAY);
    }
}
