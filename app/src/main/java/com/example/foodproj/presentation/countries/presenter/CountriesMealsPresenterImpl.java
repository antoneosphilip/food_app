package com.example.foodproj.presentation.countries.presenter;

import com.example.foodproj.data.countries.repo.CountriesMealsRepo;
import com.example.foodproj.data.countries.repo.CountriesRepoImpl;
import com.example.foodproj.presentation.countries.view.CountriesMealsView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CountriesMealsPresenterImpl implements CountriesPresenter{
  private final CountriesMealsRepo countriesMealsRepo;
  private final CountriesMealsView countriesMealsView;
    public CountriesMealsPresenterImpl(CountriesMealsView countriesMealsView) {
        this.countriesMealsRepo = new CountriesRepoImpl();
        this.countriesMealsView=countriesMealsView;
    }

    @Override
    public void getCountriesMeals() {
        countriesMealsRepo.getCountriesMeals().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> countriesMealsView.getCountriesMealsSucess(meals.getCountriesMeals()),
                        throwable -> countriesMealsView.getCountriesMealsError()
                );
    }
}
