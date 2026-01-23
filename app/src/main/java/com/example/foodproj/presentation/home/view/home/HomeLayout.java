package com.example.foodproj.presentation.home.view.home;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.foodproj.Faveorite;
import com.example.foodproj.Profile;
import com.example.foodproj.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeLayout extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_layout);
        bottomNavigationView = findViewById(R.id.bottomNav);
        loadFragment(new Home());

        bottomNavigationView.setOnItemSelectedListener(item -> {

            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.nav_home) {
                selectedFragment = new Home();
            } else if (item.getItemId() == R.id.nav_favorites) {
                selectedFragment = new Faveorite();
            } else if (item.getItemId() == R.id.nav_setting) {
                selectedFragment = new Profile();
            }


            loadFragment(selectedFragment);
            return true;
        });


    }
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}