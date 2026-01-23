package com.example.foodproj.presentation.filterMeals.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodproj.R;
import com.example.foodproj.data.mealsfilterd.model.MealsFiltered;
import com.example.foodproj.presentation.filterMeals.view.OnMealClickListener;

import java.util.List;

public class MealsFilteredAdapter extends RecyclerView.Adapter<MealsFilteredAdapter.MealViewHolder> {

    private Context context;
    private List<MealsFiltered> meals;
    private OnMealClickListener listener;

    public MealsFilteredAdapter(Context context, List<MealsFiltered> meals, OnMealClickListener listener) {
        this.context = context;
        this.meals = meals;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meals_item, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        MealsFiltered meal = meals.get(position);
        holder.mealName.setText(meal.getStrMeal());

        Glide.with(context)
                .load(meal.getStrMealThumb())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.mealImage);

        holder.mealCard.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMealClick(meal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImage;
        TextView mealName;
        CardView mealCard;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.mealImage);
            mealName = itemView.findViewById(R.id.mealName);
            mealCard = itemView.findViewById(R.id.mealCard);
        }
    }
}