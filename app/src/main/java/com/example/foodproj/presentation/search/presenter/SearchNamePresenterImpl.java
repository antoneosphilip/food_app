package com.example.foodproj.presentation.search.presenter;

import com.example.foodproj.data.categories.repo.CategoryMealsRepo;
import com.example.foodproj.data.categories.repo.CategoryMealsRepoImpl;
import com.example.foodproj.data.search.datasource.SearchResponse;
import com.example.foodproj.data.search.repo.SearchRepo;
import com.example.foodproj.data.search.repo.SearchRepoImpl;
import com.example.foodproj.presentation.categoryMeals.view.CategoriesMealsView;
import com.example.foodproj.presentation.search.view.SearchMealView;

import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchNamePresenterImpl implements SearchPresenter {
    private final SearchRepo searchRepo;
    private final SearchMealView searchMealView;

    public SearchNamePresenterImpl(SearchMealView searchMealView) {
        this.searchRepo = new SearchRepoImpl();
        this.searchMealView=searchMealView;
    }
    @Override
    public void getSearchesMeals(Map<String, String> filter) {
        searchRepo.getSearchedMeals(filter).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> searchMealView.getSearchMealsSuccess(meals.getMeals()),
                        throwable -> searchMealView.getSearchMealsError()
                );;
    }
}

