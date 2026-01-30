package com.example.foodproj.presentation.calendar.view;

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
import com.example.foodproj.data.calendar.model.MealPlan;

import java.util.List;

public class CalendarMealAdapter extends RecyclerView.Adapter<CalendarMealAdapter.PlanMealViewHolder> {

    private Context context;
    private List<MealPlan> mealPlans;
    private OnCalendarMealClickListener listener;


    public CalendarMealAdapter(Context context, List<MealPlan> mealPlans, OnCalendarMealClickListener listener) {
        this.context = context;
        this.mealPlans = mealPlans;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlanMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_cander_item, parent, false);
        return new PlanMealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanMealViewHolder holder, int position) {
        MealPlan mealPlan = mealPlans.get(position);

        holder.mealName.setText(mealPlan.getMealName());
        holder.mealDate.setText(mealPlan.getPlanDate());

        Glide.with(context)
                .load(mealPlan.getMealThumb())
                .centerCrop()
                .placeholder(R.drawable.ic_empty_favorite)
                .into(holder.mealImage);

        holder.deleteButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.deleteCalendarMeal(mealPlan);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealPlans.size();
    }

     static class PlanMealViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImage;
        TextView mealName;
        TextView mealDate;
        ImageView deleteButton;

        public PlanMealViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.mealImage);
            mealName = itemView.findViewById(R.id.mealName);
            mealDate = itemView.findViewById(R.id.mealDate);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}