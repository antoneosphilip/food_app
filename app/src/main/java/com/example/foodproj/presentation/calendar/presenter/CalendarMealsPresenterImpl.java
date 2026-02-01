package com.example.foodproj.presentation.calendar.presenter;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.calendar.model.MealPlan;
import com.example.foodproj.data.calendar.repo.MealPlanRepo;
import com.example.foodproj.data.calendar.repo.MealPlanRepoImpl;
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.presentation.calendar.view.CalendarMealsView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalendarMealsPresenterImpl implements CalendarMealsPresenter {
    private final MealPlanRepo mealPlanRepo;
    private final CalendarMealsView calendarMealsView;

    public CalendarMealsPresenterImpl(Context context, CalendarMealsView calendarMealsView) {
        this.mealPlanRepo = new MealPlanRepoImpl(context);
        this.calendarMealsView = calendarMealsView;
    }


    @Override
    public void getMeals(String date) {
        mealPlanRepo.getMeals(date).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> calendarMealsView.getLocalCalendarSuccess(meals),
                        throwable -> calendarMealsView.getRemoteCalendarError(throwable.getMessage())
                );
    }

    @Override
    public void deleteMeal(MealPlan mealPlan) {
        mealPlanRepo.deleteMeal(mealPlan).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            deletePlanMeal(mealPlan.getMealId());
                            calendarMealsView.deleteCalendarDataSuccess();

                        }
                );


    }

    @Override
    public void getRemoteCalendar() {

        mealPlanRepo.getRemotePlans()
                .addOnSuccessListener(plans -> {

                    if (plans != null && !plans.isEmpty()) {
                        for (MealPlan plan : plans) {
                            mealPlanRepo.InsertMeal(plan).subscribeOn(Schedulers.io())
                                    .subscribe();
                        }
                    }

                    calendarMealsView.getRemoteCalendarSuccess();
                })
                .addOnFailureListener(e ->
                        calendarMealsView.getRemoteCalendarError(e.getMessage())
                );
    }


    @Override
    public void uploadRemoteCalendar(MealPlan mealPlan) {
        mealPlanRepo.uploadPlansData(mealPlan);
    }

    @Override
    public void deletePlanMeal(String id) {
        mealPlanRepo.deletePlanMeal(id);
    }

    @Override
    public void deleteAllPlans() {
        mealPlanRepo.deleteAllPlans().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                );

    }
}