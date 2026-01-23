package com.example.foodproj.presentation.home.view;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.foodproj.R;
import com.example.foodproj.data.home.model.Ingredient;
import com.example.foodproj.data.home.model.Meal;
import com.google.android.material.chip.Chip;

import java.util.List;

public class MealDetails extends Fragment {

    private ImageView backButton;
    private TextView mealNameText;
    private ImageView recipeImage;
    private Chip chipVegetarian;
    private Chip chipBritish;
    private GridView ingredientsGrid;
    private TextView instructionText;
    private VideoView videoView;
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
        mealNameText = view.findViewById(R.id.mealTitle);
        recipeImage = view.findViewById(R.id.recipeImage);
        chipVegetarian = view.findViewById(R.id.chipVegetarian);
        chipBritish = view.findViewById(R.id.chipBritish);
        ingredientsGrid = view.findViewById(R.id.ingredientsGrid);
        instructionText = view.findViewById(R.id.instructionText);
        videoView = view.findViewById(R.id.videoView);

        backButton.setOnClickListener(v -> requireActivity().onBackPressed());
    }

    private void getMealData() {
        if (getArguments() != null) {
            meal = (Meal) getArguments().getSerializable("meal_key");
        }
    }

    private void displayMealDetails() {
        if (meal == null) return;

        mealNameText.setText(meal.getStrMeal());

        Glide.with(requireContext())
                .load(meal.getStrMealThumb())
                .centerCrop()
                .into(recipeImage);

        chipVegetarian.setText(meal.getStrCategory());
        chipBritish.setText(meal.getStrArea());

        List<Ingredient> ingredients = meal.getIngredientsList();
        IngredientsAdapter adapter = new IngredientsAdapter(getContext(), ingredients);
        ingredientsGrid.setAdapter(adapter);

        instructionText.setText(meal.getStrInstructions());

        if (meal.getStrYoutube() != null && !meal.getStrYoutube().isEmpty()) {
            String videoId = extractYoutubeVideoId(meal.getStrYoutube());
            if (videoId != null) {
                String videoUrl = "https://www.youtube.com/watch?v=" + videoId;
                videoView.setVideoURI(Uri.parse(videoUrl));
                MediaController mediaController = new MediaController(requireContext());
                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);
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