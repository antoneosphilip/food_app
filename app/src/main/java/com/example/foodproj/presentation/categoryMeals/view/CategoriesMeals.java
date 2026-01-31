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
import com.example.foodproj.data.categories.model.Category;
import com.example.foodproj.presentation.categoryMeals.presenter.CategoriesMealsPresenter;
import com.example.foodproj.presentation.categoryMeals.presenter.CategoriesMealsPresenterImpl;
import com.example.foodproj.presentation.categoryMeals.view.CategoriesMealsAdapter;
import com.example.foodproj.presentation.categoryMeals.view.CategoriesMealsView;
import com.example.foodproj.presentation.filterMeals.view.MealsFilteredFragment;

import java.util.ArrayList;
import java.util.List;

public class CategoriesMeals extends Fragment implements CategoriesMealsView,CategoryOnClickListener {

    private RecyclerView categoriesRecyclerView;
    private CategoriesMealsAdapter categoriesAdapter;
    private List<Category> categoriesList;
    private List<Category> filteredList;
    private CategoriesMealsPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categroeis_meals, container, false);

        categoriesRecyclerView = view.findViewById(R.id.categoriesRecyclerView);
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        categoriesList = new ArrayList<>();
        filteredList = new ArrayList<>();
        categoriesAdapter = new CategoriesMealsAdapter(getContext(), filteredList,this);
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
            for (Category category : categoriesList) {
                if (category.getStrCategory().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(category);
                }
            }
        }
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCategoryMealsSuccess(List<Category> categoryMeals) {
        categoriesList.clear();
        categoriesList.addAll(categoryMeals);
        filteredList.clear();
        filteredList.addAll(categoryMeals);
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCategoryMealsError(String message) {
        Toast.makeText(getContext(), "Failed to fetch categories", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void categoryOnClickListener(Category categoriesMeals) {
        Bundle bundle = new Bundle();
        bundle.putString("filter_type", "c");
        bundle.putString("filter_value", categoriesMeals.getStrCategory());

        MealsFilteredFragment fragment = new MealsFilteredFragment();
        fragment.setArguments(bundle);

        requireActivity()
                .getSupportFragmentManager()

                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}