package com.example.foodproj.presentation.countries.view;

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
import com.example.foodproj.data.countries.model.CountriesMeals;
import com.example.foodproj.presentation.countries.presenter.CountriesMealsPresenterImpl;
import com.example.foodproj.presentation.countries.presenter.CountriesPresenter;
import com.example.foodproj.presentation.countries.view.CountriesMealsView;

import java.util.ArrayList;
import java.util.List;

public class CountriesMealsFragment extends Fragment implements CountriesMealsView {

    private RecyclerView countriesRecyclerView;
    private CountriesAdapter countriesAdapter;
    private List<CountriesMeals> countriesList;
    private List<CountriesMeals> filteredList;
    private CountriesPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_countries_meals, container, false);

        countriesRecyclerView = view.findViewById(R.id.countriesRecyclerView);
        countriesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        countriesList = new ArrayList<>();
        filteredList = new ArrayList<>();
        countriesAdapter = new CountriesAdapter(getContext(), filteredList);
        countriesRecyclerView.setAdapter(countriesAdapter);

        presenter = new CountriesMealsPresenterImpl(this);
        presenter.getCountriesMeals();

        return view;
    }

    public void searchCountries(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(countriesList);
        } else {
            for (CountriesMeals country : countriesList) {
                if (country.getStrArea().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(country);
                }
            }
        }
        countriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCountriesMealsSucess(List<CountriesMeals> countriesResponses) {
        countriesList.clear();
        countriesList.addAll(countriesResponses);
        filteredList.clear();
        filteredList.addAll(countriesResponses);
        countriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCountriesMealsError() {
        Toast.makeText(getContext(), "Failed to fetch countries", Toast.LENGTH_SHORT).show();
    }
}