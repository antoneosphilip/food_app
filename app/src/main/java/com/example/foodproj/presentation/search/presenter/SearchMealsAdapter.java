package com.example.foodproj.presentation.search.presenter;

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
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.presentation.search.view.SearchOnClickListener;

import java.util.List;

public class SearchMealsAdapter extends RecyclerView.Adapter<SearchMealsAdapter.SearchViewHolder> {

    private Context context;
    private List<Meal> meals;
    private SearchOnClickListener searchOnClickListener;

    public SearchMealsAdapter(Context context, List<Meal> meals, SearchOnClickListener searchOnClickListener) {
        this.context = context;
        this.meals = meals;
        this.searchOnClickListener = searchOnClickListener;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_name_card, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Meal meal = meals.get(position);
        if (meal != null) {
            holder.mealName.setText(meal.getStrMeal());

            Glide.with(context)
                    .load(meal.getStrMealThumb())
                    .placeholder(R.drawable.ic_empty_favorite)
                    .centerCrop()
                    .into(holder.mealImage);
        }
        holder.bind(meal);
    }

    @Override
    public int getItemCount() {
        return meals != null ? meals.size() : 0;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImage;
        TextView mealName;
        CardView cardView;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.mealImage);
            mealName = itemView.findViewById(R.id.mealName);
            cardView = itemView.findViewById(R.id.searchNameCard);
        }

        void bind(Meal meal) {
            cardView.setOnClickListener(v -> searchOnClickListener.searchOnClickListener(meal));
        }
    }
}