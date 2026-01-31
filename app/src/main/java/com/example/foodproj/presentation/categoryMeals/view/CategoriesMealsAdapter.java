package com.example.foodproj.presentation.categoryMeals.view;

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
import com.example.foodproj.data.categories.model.Category;

import java.util.List;

public class CategoriesMealsAdapter extends RecyclerView.Adapter<CategoriesMealsAdapter.CategoryViewHolder> {

    private Context context;
    private List<Category> categories;
    private CategoryOnClickListener categoryOnClickListener;

    public CategoriesMealsAdapter(Context context, List<Category> categories, CategoryOnClickListener categoryOnClickListener) {
        this.context = context;
        this.categories = categories;
        this.categoryOnClickListener = categoryOnClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_card, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        if (category != null) {
            holder.categoryName.setText(category.getStrCategory());

            Glide.with(context)
                    .load(category.getStrCategoryThumb())
                    .placeholder(R.drawable.ic_empty_favorite)
                    .centerCrop()
                    .into(holder.categoryImage);
        }
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return categories != null ? categories.size() : 0;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView categoryName;
        CardView cardView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.categoryImage);
            categoryName = itemView.findViewById(R.id.categoryName);
            cardView = itemView.findViewById(R.id.categoryCard);
        }

        void bind(Category category) {
            cardView.setOnClickListener(v -> categoryOnClickListener.categoryOnClickListener(category));
        }
    }
}