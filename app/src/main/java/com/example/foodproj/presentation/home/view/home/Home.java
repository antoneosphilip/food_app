package com.example.foodproj.presentation.home.view.home;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.foodproj.R;
import com.example.foodproj.data.auth.repo.AuthRepo;
import com.example.foodproj.data.auth.repo.AuthRepoImpl;
import com.example.foodproj.data.home.model.Category;
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.presentation.auth.presenter.AuthPresenter;
import com.example.foodproj.presentation.auth.presenter.AuthPresenterImpl;
import com.example.foodproj.presentation.auth.view.LoginActivity;
import com.example.foodproj.presentation.filterMeals.view.MealsFilteredFragment;
import com.example.foodproj.presentation.home.presenter.HomePresenter;
import com.example.foodproj.presentation.home.presenter.HomePresenterImpl;
import com.example.foodproj.presentation.home.view.category.CategoryListener;
import com.example.foodproj.presentation.mealsdetails.view.MealDetails;
import com.example.foodproj.presentation.mealsdetails.view.MealOnClickListener;
import com.example.foodproj.presentation.home.view.category.CategoryAdapter;

import java.util.List;

public class Home extends Fragment implements HomeView, MealOnClickListener, CategoryListener {

    private HomePresenter homePresenter;
    private ImageView mealImage;
    private TextView mealTitle;
    private TextView categoryTag;
    private TextView areaTag;
    private ImageView refreshButton;
    private GridView categoriesGridView;
    private List<Meal> currentMeal;

    private  AuthRepo authRepo;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

          initViews(view);
          homePresenter = new HomePresenterImpl(this,getContext());
          homePresenter.getMeals();
          homePresenter.getCategories();

          refreshButton.setOnClickListener(v -> homePresenter.getMeals());
          mealImage.setOnClickListener(v -> mealClickListener());
        return view;
    }

    private void initViews(View view) {
        mealImage = view.findViewById(R.id.mealImage);
        mealTitle = view.findViewById(R.id.mealTitle);
        categoryTag = view.findViewById(R.id.categoryTag);
        areaTag = view.findViewById(R.id.areaTag);
        refreshButton = view.findViewById(R.id.refreshButton);
        categoriesGridView = view.findViewById(R.id.categoriesGridView);
    }

    @Override
    public void mealFetchedSuccessfully(List<Meal> meal) {
        this.currentMeal = meal;
        displayMealData(meal);
    }

    @Override
    public void mealFetchedFailure() {
        Toast.makeText(getContext(), "Failed to fetch meal", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void categoryFetchedSuccessfully(List<Category> categories) {
        CategoryAdapter adapter =
                new CategoryAdapter(getContext(), categories,this);
        categoriesGridView.setAdapter(adapter);
    }

    @Override
    public void categoryFetchedFailure() {
        Toast.makeText(getContext(), "Failed to fetch category", Toast.LENGTH_SHORT).show();
    }



    private void displayMealData(List<Meal> meal) {
        mealTitle.setText(meal.get(0).getStrMeal());
        categoryTag.setText(meal.get(0).getStrCategory());
        areaTag.setText(meal.get(0).getStrArea());
        Log.i(TAG, "meeeeal"+meal.get(0).getStrMeal() );
            Glide.with(requireContext())
                    .load(meal.get(0).getStrMealThumb())
                    .centerCrop()
                    .into(mealImage);

    }

    @Override
    public void mealClickListener() {
        if (currentMeal == null || currentMeal.isEmpty()) return;

        Bundle bundle = new Bundle();
        bundle.putSerializable("meal_id", currentMeal.get(0).getIdMeal());

        MealDetails fragment = new MealDetails();
        fragment.setArguments(bundle);

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCategoryClick(Category category) {

        Bundle bundle = new Bundle();
        bundle.putString("filter_type", "c");
        bundle.putString("filter_value", category.getStrCategory());

        MealsFilteredFragment fragment = new MealsFilteredFragment();
        fragment.setArguments(bundle);

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}