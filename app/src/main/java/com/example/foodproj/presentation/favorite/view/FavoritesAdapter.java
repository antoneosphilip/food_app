package com.example.foodproj.presentation.favorite.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodproj.R;
import com.example.foodproj.data.home.model.Meal;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private Context context;
    private List<Meal> meals;
    private FavoriteOnClickListener favoriteOnClickListener;

    public FavoritesAdapter(Context context, FavoriteOnClickListener listener) {
        this.context = context;
        this.meals = new ArrayList<>();
        this.favoriteOnClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = meals.get(position);

        holder.mealName.setText(meal.getStrMeal());
        holder.countryName.setText(meal.getStrArea());
        holder.categoryChip.setText(meal.getStrCategory());

        Glide.with(context)
                .load(meal.getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.mealImage);

        holder.itemView.setOnClickListener(v -> {
            if (favoriteOnClickListener != null) {
                favoriteOnClickListener.onMealClick(meal);
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (favoriteOnClickListener != null) {
                favoriteOnClickListener.onDeleteClick(meal);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void updateMeals(List<Meal> newMeals) {
        this.meals = newMeals;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImage;
        TextView mealName;
        TextView countryName;
        Chip categoryChip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.mealImage);
            mealName = itemView.findViewById(R.id.mealName);
            countryName = itemView.findViewById(R.id.countryName);
            categoryChip = itemView.findViewById(R.id.categoryChip);
        }
    }
}