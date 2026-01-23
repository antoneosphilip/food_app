package com.example.foodproj.presentation.home.view.mealDetils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.foodproj.R;
import com.example.foodproj.data.home.model.Ingredient;
import com.example.foodproj.data.home.model.Meal;
import com.google.android.material.chip.Chip;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

public class MealDetails extends Fragment {

    private ImageView backButton;
    private TextView mealNameText;
    private ImageView recipeImage;
    private Chip chipVegetarian;
    private Chip chipBritish;
    private GridView ingredientsGrid;
    private TextView instructionText;
    private YouTubePlayerView youtubePlayerView;
    private Meal meal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_details, container, false);

        initViews(view);
        getMealData();
        displayMealDetails();

        return view;
    }

    private void initViews(View view) {
        backButton = view.findViewById(R.id.backButton);
        mealNameText = view.findViewById(R.id.mealName);
        recipeImage = view.findViewById(R.id.recipeImage);
        chipVegetarian = view.findViewById(R.id.chipVegetarian);
        chipBritish = view.findViewById(R.id.chipBritish);
        ingredientsGrid = view.findViewById(R.id.ingredientsGrid);
        instructionText = view.findViewById(R.id.instructionText);
        youtubePlayerView = view.findViewById(R.id.youtubePlayerView);

        // ضروري تضيف ال lifecycle observer
        getLifecycle().addObserver(youtubePlayerView);

        backButton.setOnClickListener(v -> requireActivity().onBackPressed());
    }

    private void getMealData() {
        if (getArguments() != null) {
            meal = (Meal) getArguments().getSerializable("meal_key");
        }
    }

    private void displayMealDetails() {
        if (meal == null) return;

        // الاسم
        mealNameText.setText(meal.getStrMeal());

        // الصورة
        Glide.with(requireContext())
                .load(meal.getStrMealThumb())
                .centerCrop()
                .into(recipeImage);

        // التصنيف والمنطقة
        chipVegetarian.setText(meal.getStrCategory());
        chipBritish.setText(meal.getStrArea());

        // المكونات
        List<Ingredient> ingredients = meal.getIngredientsList();
        IngredientsAdapter adapter = new IngredientsAdapter(getContext(), ingredients);
        ingredientsGrid.setAdapter(adapter);

        // التعليمات
        instructionText.setText(meal.getStrInstructions());

        if (meal.getStrYoutube() != null && !meal.getStrYoutube().isEmpty()) {
            String videoId = extractYoutubeVideoId(meal.getStrYoutube());
            if (videoId != null) {
                youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(YouTubePlayer youTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0);
                    }
                });
            }
        }
    }

    private String extractYoutubeVideoId(String youtubeUrl) {
        if (youtubeUrl.contains("v=")) {
            String[] parts = youtubeUrl.split("v=");
            if (parts.length > 1) {
                String videoId = parts[1];
                int ampersandPosition = videoId.indexOf('&');
                if (ampersandPosition != -1) {
                    return videoId.substring(0, ampersandPosition);
                }
                return videoId;
            }
        }
        return null;
    }
}
