package com.example.foodproj.presentation.home.view.mealDetils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodproj.R;
import com.example.foodproj.data.home.model.Ingredient;

import java.util.List;

public class IngredientsAdapter extends BaseAdapter {

    private Context context;
    private List<Ingredient> ingredients;

    public IngredientsAdapter(Context context, List<Ingredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public Object getItem(int position) {
        return ingredients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ingredient_item, parent, false);
        }

        Ingredient ingredient = ingredients.get(position);

        ImageView ingredientImage = convertView.findViewById(R.id.ingredientImage);
        TextView ingredientName = convertView.findViewById(R.id.ingredientName);
        TextView ingredientQuantity = convertView.findViewById(R.id.ingredientQuantity);

        ingredientName.setText(ingredient.getName());
        ingredientQuantity.setText(ingredient.getMeasure());

        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredient.getName() + ".png";
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(ingredientImage);

        return convertView;
    }
}