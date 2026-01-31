package com.example.foodproj.presentation.search.presenter;

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
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.data.search.datasource.SearchResponse;
import com.example.foodproj.presentation.filterMeals.view.MealsFilteredFragment;

import com.example.foodproj.presentation.mealsdetails.view.MealDetails;
import com.example.foodproj.presentation.search.presenter.SearchMealsAdapter;
import com.example.foodproj.presentation.search.presenter.SearchPresenter;
import com.example.foodproj.presentation.search.view.SearchMealView;
import com.example.foodproj.presentation.search.view.SearchOnClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NameSearch extends Fragment implements SearchMealView, SearchOnClickListener {

    private RecyclerView searchRecyclerView;
    private SearchMealsAdapter searchAdapter;
    private List<Meal> mealsList;
    private List<Meal> filteredList;
    private SearchPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_name_search, container, false);

        searchRecyclerView = view.findViewById(R.id.namesRecyclerView);
        searchRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        mealsList = new ArrayList<>();
        filteredList = new ArrayList<>();

        searchAdapter = new SearchMealsAdapter(getContext(), filteredList, this);
        searchRecyclerView.setAdapter(searchAdapter);

        presenter = new SearchNamePresenterImpl(this);

        return view;
    }

    public void searchMeals(String query) {
        if (query.isEmpty()) {
            filteredList.clear();
            searchAdapter.notifyDataSetChanged();
        } else {
            Map<String, String> filter = new HashMap<>();
            filter.put("s", query);
            presenter.getSearchesMeals(filter);
        }
    }



    @Override
    public void getSearchMealsSuccess(List<Meal> meals) {
        mealsList.clear();
        if (meals != null) {
            mealsList.addAll(meals);
            filteredList.clear();
            filteredList.addAll(mealsList);
            searchAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(), "No meals found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getSearchMealsError() {
        Toast.makeText(getContext(), "Failed to fetch meals", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void searchOnClickListener(Meal meal) {
        Bundle bundle = new Bundle();
        bundle.putString("meal_id", meal.getIdMeal());

        MealDetails fragment = new MealDetails();
        fragment.setArguments(bundle);

        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}