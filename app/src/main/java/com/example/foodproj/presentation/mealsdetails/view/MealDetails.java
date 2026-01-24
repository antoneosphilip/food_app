package com.example.foodproj.presentation.mealsdetails.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodproj.R;
import com.example.foodproj.data.home.model.Ingredient;
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.presentation.favorite.presenter.FavoritePresenter;
import com.example.foodproj.presentation.mealsdetails.presenter.MealsDetailsPresenter;
import com.example.foodproj.presentation.mealsdetails.presenter.MealsDetailsPresenterImpl;
import com.google.android.material.chip.Chip;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealDetails extends Fragment implements MealsDetailsView {

    private ImageView backButton;
    private TextView mealNameText;
    private ImageView recipeImage;
    private Chip chipVegetarian;
    private Chip chipBritish;
    private RecyclerView ingredientsGrid;
    private TextView instructionText;
    private YouTubePlayerView youtubePlayerView;
    private ProgressBar progressBar;
    private Meal meal;
    private String mealId;
    private MealsDetailsPresenter presenter;


    private Button favoriteButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_details, container, false);

        initViews(view);
        getMealId();
        loadMealDetails();

        return view;
    }

    private void initViews(View view) {
        backButton = view.findViewById(R.id.backButton);
        mealNameText = view.findViewById(R.id.mealName);
        recipeImage = view.findViewById(R.id.recipeImage);
        chipVegetarian = view.findViewById(R.id.chipVegetarian);
        chipBritish = view.findViewById(R.id.chipBritish);
        ingredientsGrid = view.findViewById(R.id.ingredientsRecyclerView);
        instructionText = view.findViewById(R.id.instructionText);
        youtubePlayerView = view.findViewById(R.id.youtubePlayerView);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        favoriteButton=view.findViewById(R.id.btnAddToFav);
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.insertMeal(meal);
            }
        });
        getLifecycle().addObserver(youtubePlayerView);

        backButton.setOnClickListener(v -> requireActivity().onBackPressed());
    }

    private void getMealId() {
        if (getArguments() != null) {
            mealId = getArguments().getString("meal_id");
        }
    }

    private void loadMealDetails() {
        presenter = new MealsDetailsPresenterImpl(this,getContext());

        Map<String, String> filter = new HashMap<>();
        filter.put("i", mealId);
        presenter.getMealDetails(filter);
    }

    @Override
    public void getMealsDetailsSuccess(List<Meal> meals) {
        progressBar.setVisibility(View.GONE);
        if (meals != null && !meals.isEmpty()) {
            meal = meals.get(0);
            displayMealDetails();
        }
    }

    @Override
    public void getMealsDetailsError() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), "Failed to fetch meal details", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void insertMeal() {
        Toast.makeText(getContext(), "Inserted Meal to favorite", Toast.LENGTH_SHORT).show();

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