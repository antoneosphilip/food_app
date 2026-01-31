package com.example.foodproj.presentation.home.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.foodproj.R;
import com.example.foodproj.data.auth.repo.AuthRepo;
import com.example.foodproj.data.auth.repo.AuthRepoImpl;
import com.example.foodproj.prefs.UserPrefs;
import com.example.foodproj.presentation.auth.presenter.AuthPresenter;
import com.example.foodproj.presentation.auth.presenter.AuthPresenterImpl;
import com.example.foodproj.presentation.auth.view.LoginActivity;
import com.example.foodproj.presentation.calendar.presenter.CalendarMealsPresenter;
import com.example.foodproj.presentation.calendar.presenter.CalendarMealsPresenterImpl;
import com.example.foodproj.presentation.calendar.view.Calendar;
import com.example.foodproj.presentation.favorite.presenter.FavoritePresenter;
import com.example.foodproj.presentation.favorite.presenter.FavoritePresenterImpl;
import com.example.foodproj.presentation.favorite.view.Favorite;
import com.example.foodproj.presentation.filterMeals.view.FilterFragment;
import com.example.foodproj.presentation.home.presenter.HomePresenter;
import com.example.foodproj.presentation.home.presenter.HomePresenterImpl;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeLayout extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView logOut;

    FavoritePresenter favoritePresenter;

    CalendarMealsPresenter calendarMealsPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_layout);
        bottomNavigationView = findViewById(R.id.bottomNav);
        logOut=findViewById(R.id.logoutButton);
        loadFragment(new Home());
        favoritePresenter= new FavoritePresenterImpl(getApplicationContext(),null);
        calendarMealsPresenter=new CalendarMealsPresenterImpl(getApplicationContext(),null);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // authPresenter.logOut();
                favoritePresenter.deleteAllFavorites();
                calendarMealsPresenter.deleteAllPlans();
                UserPrefs.clear();

                Intent intent = new Intent(HomeLayout.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bottomNavigationView.setOnItemSelectedListener(item -> {

            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.nav_home) {
                selectedFragment = new Home();
            } else if (item.getItemId() == R.id.nav_calendar) {
                selectedFragment = new Calendar();
            }
            else if (item.getItemId() == R.id.nav_favorites) {
                selectedFragment = new Favorite();
            }
            else if (item.getItemId() == R.id.nav_search) {
                selectedFragment = new FilterFragment();
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