package com.example.foodproj.presentation.home.view.home;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodproj.R;
import com.example.foodproj.data.categories.model.Category;
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.network.NetworkMonitor;
import com.example.foodproj.prefs.UserPrefs;
import com.example.foodproj.presentation.categoryMeals.view.CategoriesMealsAdapter;
import com.example.foodproj.presentation.categoryMeals.view.CategoryOnClickListener;
import com.example.foodproj.presentation.filterMeals.view.MealsFilteredFragment;
import com.example.foodproj.presentation.home.presenter.HomePresenter;
import com.example.foodproj.presentation.home.presenter.HomePresenterImpl;
import com.example.foodproj.presentation.mealsdetails.view.MealDetails;
import com.example.foodproj.presentation.mealsdetails.view.MealOnClickListener;

import java.util.List;

public class Home extends Fragment implements HomeView, MealOnClickListener, CategoryOnClickListener {

    private HomePresenter homePresenter;
    private ImageView mealImage;
    private TextView mealTitle;
    private TextView categoryTag;
    private TextView areaTag;
    private ImageView refreshButton;
    private RecyclerView categoriesRecyclerView;
    private List<Meal> currentMeal;
    private View noInternetLayout;
    private View contentLayout;
    private NetworkMonitor networkMonitor;
    private TextView name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initViews(view);
        homePresenter = new HomePresenterImpl(this, getContext());

        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        setupNetworkMonitor();

        refreshButton.setOnClickListener(v -> {
            if (networkMonitor.isNetworkAvailable()) {
                homePresenter.getMeals();
                homePresenter.getCategories();
            } else {
                Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        mealImage.setOnClickListener(v -> mealClickListener());

        return view;
    }

    private void initViews(View view) {
        mealImage = view.findViewById(R.id.mealImage);
        mealTitle = view.findViewById(R.id.mealTitle);
        categoryTag = view.findViewById(R.id.categoryTag);
        areaTag = view.findViewById(R.id.areaTag);
        refreshButton = view.findViewById(R.id.refreshButton);
        categoriesRecyclerView = view.findViewById(R.id.categoriesRecyclerView);
        noInternetLayout = view.findViewById(R.id.noInternetLayout);
        contentLayout = view.findViewById(R.id.contentLayout);
        name = view.findViewById(R.id.welcomeText);
    }

    private void setupNetworkMonitor() {
        networkMonitor = new NetworkMonitor(requireContext(), new NetworkMonitor.NetworkCallback() {
            @Override
            public void onNetworkAvailable() {
                showContent();
                loadData();
            }

            @Override
            public void onNetworkUnavailable() {
                showNoInternet();
            }
        });

        networkMonitor.register();
    }

    private void loadData() {
        homePresenter.getMeals();
        homePresenter.getCategories();
    }

    private void showNoInternet() {
        if (noInternetLayout != null && contentLayout != null) {
            noInternetLayout.setVisibility(View.VISIBLE);
            contentLayout.setVisibility(View.GONE);
        }
    }

    private void showContent() {
        if (noInternetLayout != null && contentLayout != null) {
            noInternetLayout.setVisibility(View.GONE);
            contentLayout.setVisibility(View.VISIBLE);
        }
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
        CategoriesMealsAdapter adapter = new CategoriesMealsAdapter(getContext(), categories, this);
        categoriesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void categoryFetchedFailure() {
        Toast.makeText(getContext(), "Failed to fetch category", Toast.LENGTH_SHORT).show();
    }

    private void displayMealData(List<Meal> meal) {
        mealTitle.setText(meal.get(0).getStrMeal());
        categoryTag.setText(meal.get(0).getStrCategory());
        areaTag.setText(meal.get(0).getStrArea());
        Log.i(TAG, "meeeeal" + meal.get(0).getStrMeal());

        Glide.with(requireContext())
                .load(meal.get(0).getStrMealThumb())
                .placeholder(R.drawable.ic_empty_favorite)
                .centerCrop()
                .into(mealImage);

        if(UserPrefs.getName()==null){
            name.setText("Welcome back ");

        }
        else {
            name.setText("Welcome , " + UserPrefs.getName());
        }
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
    public void onDestroyView() {
        super.onDestroyView();
        if (networkMonitor != null) {
            networkMonitor.unregister();
        }
    }

    @Override
    public void categoryOnClickListener(Category categoriesMeals) {
        Bundle bundle = new Bundle();
        bundle.putString("filter_type", "c");
        bundle.putString("filter_value", categoriesMeals.getStrCategory());

        MealsFilteredFragment fragment = new MealsFilteredFragment();
        fragment.setArguments(bundle);

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
