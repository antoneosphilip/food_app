package com.example.foodproj;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodproj.prefs.UserPrefs;
import com.example.foodproj.presentation.auth.presenter.AuthPresenter;
import com.example.foodproj.presentation.auth.presenter.AuthPresenterImpl;
import com.example.foodproj.presentation.auth.view.LoginActivity;
import com.example.foodproj.presentation.home.view.home.HomeLayout;
import com.example.foodproj.presentation.home.view.home.HomeView;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class MainActivity extends AppCompatActivity {
    AuthPresenter authPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_Id))
                .requestEmail()
                .build();
        UserPrefs.init(getApplicationContext());
        Intent intent = new Intent(MainActivity.this, UserPrefs.getToken()!=null? HomeLayout.class: LoginActivity.class);
        startActivity(intent);
        finish();

    }
}