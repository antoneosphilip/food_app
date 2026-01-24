package com.example.foodproj.presentation.ingredient.view;

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
import com.example.foodproj.data.ingredient.model.IngredientMeals;
import com.example.foodproj.presentation.filterMeals.view.MealsFilteredFragment;
import com.example.foodproj.presentation.ingredient.presenter.IngredientsMealsPresenter;
import com.example.foodproj.presentation.ingredient.presenter.IngredientsMealsPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class IngredientsFragment extends Fragment implements IngredientsMealsView,IngredientsOnClickListener {

    private RecyclerView ingredientsRecyclerView;
    private IngredientsMealsAdapter ingredientsAdapter;
    private List<IngredientMeals> ingredientsList;
    private List<IngredientMeals> filteredList;
    private IngredientsMealsPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient_meals, container, false);

        ingredientsRecyclerView = view.findViewById(R.id.ingredientsRecyclerView);
        ingredientsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        ingredientsList = new ArrayList<>();
        filteredList = new ArrayList<>();
        ingredientsAdapter = new IngredientsMealsAdapter(getContext(),filteredList,this);
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);

        presenter = new IngredientsMealsPresenterImpl(this);
        presenter.getIngredientsMeals();

        return view;
    }

    public void searchIngredients(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(ingredientsList);
        } else {
            for (IngredientMeals ingredient : ingredientsList) {
                if (ingredient.getStrIngredient().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(ingredient);
                }
            }
        }
        ingredientsAdapter.notifyDataSetChanged();
    }

    @Override
    public void getIngredientsMealsSuccess(List<IngredientMeals> ingredientsMealsList) {
        ingredientsList.clear();
        ingredientsList.addAll(ingredientsMealsList);
        filteredList.clear();
        filteredList.addAll(ingredientsMealsList);
        ingredientsAdapter.notifyDataSetChanged();
    }

    @Override
    public void getIngredientsMealsError() {
        Toast.makeText(getContext(), "Failed to fetch ingredients", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ingredientsOnClickListener(IngredientMeals ingredientMeals) {
        Bundle bundle = new Bundle();
        bundle.putString("filter_type", "i");
        bundle.putString("filter_value", ingredientMeals.getStrIngredient());

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