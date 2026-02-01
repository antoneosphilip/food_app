package com.example.foodproj.presentation.filterMeals.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.foodproj.presentation.categoryMeals.view.CategoriesMeals;
import com.example.foodproj.presentation.countries.view.CountriesMealsFragment;
import com.example.foodproj.presentation.ingredient.view.IngredientsFragment;
import com.example.foodproj.presentation.search.view.NameSearch;


public class FilterPagerAdapter extends FragmentStateAdapter {

    public FilterPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new NameSearch();
            case 1:
                return new CategoriesMeals();
            case 2:
                return new CountriesMealsFragment();
            case 3:
                return new IngredientsFragment();
            default:
                return new CategoriesMeals();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}