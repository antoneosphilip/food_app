package com.example.foodproj.presentation.filterMeals.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.foodproj.R;

import com.example.foodproj.presentation.categoryMeals.view.CategoriesMeals;
import com.example.foodproj.presentation.countries.view.CountriesMealsFragment;
import com.example.foodproj.presentation.ingredient.view.IngredientsFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class FilterFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private EditText searchEditText;
    private Button searchButton;
    private FilterPagerAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_layout, container, false);

        initViews(view);
        setupViewPager();

        searchButton.setOnClickListener(v -> performSearch());

        return view;
    }

    private void initViews(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        searchEditText = view.findViewById(R.id.searchEditText);
        searchButton = view.findViewById(R.id.searchButton);
    }

    private void setupViewPager() {
        pagerAdapter = new FilterPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Categories");
                    break;
                case 1:
                    tab.setText("Countries");
                    break;
                case 2:
                    tab.setText("Ingredient");
                    break;
            }
        }).attach();
    }

    private void performSearch() {
        String query = searchEditText.getText().toString().trim();
        if (!query.isEmpty()) {
            int currentPosition = viewPager.getCurrentItem();
            Fragment fragment = getChildFragmentManager().findFragmentByTag("f" + currentPosition);

            if (fragment instanceof CategoriesMeals) {
                ((CategoriesMeals) fragment).searchCategories(query);

            }
            else if (fragment instanceof CountriesMealsFragment) {
                ((CountriesMealsFragment) fragment).searchCountries(query);
            } else if (fragment instanceof IngredientsFragment) {
                ((IngredientsFragment) fragment).searchIngredients(query);
            }
        }
    }
}