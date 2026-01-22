package com.example.foodproj.presentation.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.foodproj.R;
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.presentation.home.presenter.HomePresenter;
import com.example.foodproj.presentation.home.presenter.HomePresenterImpl;

public class Home extends Fragment implements HomeView {

    private HomePresenter homePresenter;
    private ImageView mealImage;
    private TextView mealTitle;
    private TextView categoryTag;
    private TextView areaTag;
    private ImageView refreshButton;
    private GridView categoriesGridView;
    private Meal currentMeal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initViews(view);
          homePresenter = new HomePresenterImpl(this);
        //  homePresenter.getMeals();

      //  refreshButton.setOnClickListener(v -> homePresenter.getMeals());

        return view;
    }

    private void initViews(View view) {
        mealImage = view.findViewById(R.id.mealImage);
        mealTitle = view.findViewById(R.id.mealTitle);
        categoryTag = view.findViewById(R.id.categoryTag);
        areaTag = view.findViewById(R.id.areaTag);
        refreshButton = view.findViewById(R.id.refreshButton);
        //   categoriesGridView = view.findViewById(R.id.categoriesGridView);
    }

    @Override
    public void mealFetchedSuccessfully(Meal meal) {
        this.currentMeal = meal;
        displayMealData(meal);
    }

    @Override
    public void mealFetchedFailure() {
        Toast.makeText(getContext(), "Failed to fetch meal", Toast.LENGTH_SHORT).show();
    }

    private void displayMealData(Meal meal) {
        mealTitle.setText(meal.getStrMeal());
        categoryTag.setText(meal.getStrCategory());
        areaTag.setText(meal.getStrArea());

        Glide.with(this)
                .load(meal.getStrMealThumb())
                .centerCrop()
                .into(mealImage);
    }
}