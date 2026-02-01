package com.example.foodproj.presentation.filterMeals.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.foodproj.R;
import com.example.foodproj.network.NetworkMonitor;
import com.example.foodproj.presentation.categoryMeals.view.CategoriesMeals;
import com.example.foodproj.presentation.countries.view.CountriesMealsFragment;
import com.example.foodproj.presentation.ingredient.view.IngredientsFragment;
import com.example.foodproj.presentation.search.view.NameSearch;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class FilterFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private EditText searchEditText;
    private MaterialButton searchButton;
    private FilterPagerAdapter pagerAdapter;
    private View noInternetLayout;
    private View contentLayout;
    private NetworkMonitor networkMonitor;
    private MaterialButton retryButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_layout, container, false);

        initViews(view);
        setupViewPager();
        setupSearchListeners();
        setupNetworkMonitor();

        retryButton.setOnClickListener(v -> {
            if (networkMonitor.isNetworkAvailable()) {
                showContent();
            }
        });

        return view;
    }

    private void initViews(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        searchEditText = view.findViewById(R.id.searchEditText);
        searchButton = view.findViewById(R.id.searchButton);
        noInternetLayout = view.findViewById(R.id.noInternetLayout);
        contentLayout = view.findViewById(R.id.contentLayout);
        retryButton = view.findViewById(R.id.retryButton);
    }

    private void setupNetworkMonitor() {
        networkMonitor = new NetworkMonitor(requireContext(), new NetworkMonitor.NetworkCallback() {
            @Override
            public void onNetworkAvailable() {
                showContent();
            }

            @Override
            public void onNetworkUnavailable() {
                showNoInternet();
            }
        });

        networkMonitor.register();
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

    private void setupViewPager() {
        pagerAdapter = new FilterPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(3);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Name");
                    break;
                case 1:
                    tab.setText("Categories");
                    break;
                case 2:
                    tab.setText("Countries");
                    break;
                case 3:
                    tab.setText("Ingredient");
                    break;
            }
        }).attach();
    }

    private void setupSearchListeners() {
        searchButton.setOnClickListener(v -> performSearch());

        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch();
                return true;
            }
            return false;
        });
    }

    private void performSearch() {
        String query = searchEditText.getText().toString().trim();
        if (!query.isEmpty()) {
            int currentPosition = viewPager.getCurrentItem();
            Fragment fragment = getChildFragmentManager().findFragmentByTag("f" + currentPosition);

            if (fragment instanceof NameSearch) {
                ((NameSearch) fragment).searchMeals(query);
            }
            else if (fragment instanceof CategoriesMeals) {
                ((CategoriesMeals) fragment).searchCategories(query);
            }
            else if (fragment instanceof CountriesMealsFragment) {
                ((CountriesMealsFragment) fragment).searchCountries(query);
            }
            else if (fragment instanceof IngredientsFragment) {
                ((IngredientsFragment) fragment).searchIngredients(query);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (networkMonitor != null) {
            networkMonitor.unregister();
        }
    }
}