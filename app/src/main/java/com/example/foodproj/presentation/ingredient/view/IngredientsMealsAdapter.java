package com.example.foodproj.presentation.ingredient.view;

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
import com.example.foodproj.data.ingredient.model.IngredientMeals;

import java.util.List;

public class IngredientsMealsAdapter extends RecyclerView.Adapter<IngredientsMealsAdapter.IngredientViewHolder> {

    private Context context;
    private List<IngredientMeals> ingredients;
    IngredientsOnClickListener ingredientsOnClickListener;

    public IngredientsMealsAdapter(Context context, List<IngredientMeals> ingredients,IngredientsOnClickListener ingredientsOnClickListener) {
        this.context = context;
        this.ingredients = ingredients;
        this.ingredientsOnClickListener=ingredientsOnClickListener;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ingredient_card, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        IngredientMeals ingredient = ingredients.get(position);
        holder.ingredientName.setText(ingredient.getStrIngredient());

        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredient.getStrIngredient() + ".png";
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_empty_favorite)
                .into(holder.ingredientImage);
        holder.Bind(ingredient);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

     class IngredientViewHolder extends RecyclerView.ViewHolder {
        ImageView ingredientImage;
        TextView ingredientName;
        CardView cardView;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientImage = itemView.findViewById(R.id.ingredientImage);
            ingredientName = itemView.findViewById(R.id.ingredientName);
            cardView = itemView.findViewById(R.id.ingredientCard);
        }
        void Bind(IngredientMeals ingredientMeals){
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ingredientsOnClickListener.ingredientsOnClickListener(ingredientMeals);
                }
            });
        }
    }
}