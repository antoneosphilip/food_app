package com.example.foodproj.presentation.home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodproj.R;
import com.example.foodproj.data.home.model.Category;
import com.example.foodproj.presentation.home.view.CategoryListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    private Context context;
    private List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item, parent, false);
        }

        ImageView image = convertView.findViewById(R.id.categoryImage);
        TextView name = convertView.findViewById(R.id.categoryTitle);

        Category category = categories.get(position);

        name.setText(category.getStrCategory());

        Glide.with(context)
                .load(category.getStrCategoryThumb())
                .into(image);

        return convertView;
    }
}
