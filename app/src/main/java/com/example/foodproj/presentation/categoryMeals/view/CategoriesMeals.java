package com.example.foodproj.presentation.categoryMeals.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodproj.R;
import com.example.foodproj.data.categories.model.CategoryMeals;
import com.example.foodproj.presentation.categoryMeals.presenter.CategoriesMealsPresenter;
import com.example.foodproj.presentation.categoryMeals.presenter.CategoriesMealsPresenterImpl;
import com.example.foodproj.presentation.categoryMeals.view.CategoriesMealsAdapter;
import com.example.foodproj.presentation.categoryMeals.view.CategoriesMealsView;
import com.example.foodproj.presentation.home.view.category.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class CategoriesMeals extends Fragment implements CategoriesMealsView {

    private RecyclerView categoriesRecyclerView;
    private CategoriesMealsAdapter categoriesAdapter;
    private List<CategoryMeals> categoriesList;
    private List<CategoryMeals> filteredList;
    private CategoriesMealsPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categroeis_meals, container, false);

        categoriesRecyclerView = view.findViewById(R.id.categoriesRecyclerView);
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        categoriesList = new ArrayList<>();
        filteredList = new ArrayList<>();
        categoriesAdapter = new CategoriesMealsAdapter(getContext(), filteredList);
        categoriesRecyclerView.setAdapter(categoriesAdapter);

        presenter = new CategoriesMealsPresenterImpl(this);
        presenter.getCategoriesMeals();

        return view;
    }

    public void searchCategories(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(categoriesList);
        } else {
            for (CategoryMeals category : categoriesList) {
                if (category.getStrCategory().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(category);
                }
            }
        }
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCategoryMealsSuccess(List<CategoryMeals> categoryMeals) {
        categoriesList.clear();
        categoriesList.addAll(categoryMeals);
        filteredList.clear();
        filteredList.addAll(categoryMeals);
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCategoryMealsError() {
        Toast.makeText(getContext(), "Failed to fetch categories", Toast.LENGTH_SHORT).show();
    }
}