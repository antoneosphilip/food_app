package com.example.foodproj.presentation.favorite.view;

import com.example.foodproj.data.home.model.Meal;

public interface FavoriteOnClickListener {
    void onMealClick(Meal meal);
    void onDeleteClick(Meal meal);
}
