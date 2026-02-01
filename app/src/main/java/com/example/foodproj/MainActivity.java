package com.example.foodproj;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodproj.prefs.UserPrefs;
import com.example.foodproj.presentation.auth.view.LoginActivity;
import com.example.foodproj.presentation.home.view.home.HomeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        UserPrefs.init(getApplicationContext());
        Intent intent = new Intent(MainActivity.this, SplashActivity.class);
        startActivity(intent);
        finish();

    }

}