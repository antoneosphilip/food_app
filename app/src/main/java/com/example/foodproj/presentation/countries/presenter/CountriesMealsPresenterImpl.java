package com.example.foodproj.presentation.countries.presenter;

import com.example.foodproj.data.countries.datasource.CountriesNetworkResponse;
import com.example.foodproj.data.countries.model.CountriesMeals;
import com.example.foodproj.data.countries.repo.CountriesMealsRepo;
import com.example.foodproj.data.countries.repo.CountriesRepoImpl;
import com.example.foodproj.presentation.countries.view.CountriesMealsView;

import java.util.List;

public class CountriesMealsPresenterImpl implements CountriesPresenter{
  private final CountriesMealsRepo countriesMealsRepo;
  private final CountriesMealsView countriesMealsView;
    public CountriesMealsPresenterImpl(CountriesMealsView countriesMealsView) {
        this.countriesMealsRepo = new CountriesRepoImpl();
        this.countriesMealsView=countriesMealsView;
    }

    @Override
    public void getCountriesMeals() {
        countriesMealsRepo.getCountriesMeals(new CountriesNetworkResponse() {
            @Override
            public void onCountriesMealsSuccess(List<CountriesMeals> countriesResponses) {
                countriesMealsView.getCountriesMealsSucess(countriesResponses);
            }

            @Override
            public void onCountriesMealsError(String message) {
                countriesMealsView.getCountriesMealsError();
            }
        });
    }
}
