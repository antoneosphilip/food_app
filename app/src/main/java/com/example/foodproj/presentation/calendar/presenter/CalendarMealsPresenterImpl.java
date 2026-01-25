package com.example.foodproj.presentation.calendar.presenter;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.calendar.model.MealPlan;
import com.example.foodproj.data.calendar.repo.MealPlanRepo;
import com.example.foodproj.data.calendar.repo.MealPlanRepoImpl;
import com.example.foodproj.presentation.calendar.view.CalendarMealsView;

import java.util.List;

public class CalendarMealsPresenterImpl implements CalendarMealsPresenter{
    private final MealPlanRepo mealPlanRepo;
    private final CalendarMealsView calendarMealsView;

    public CalendarMealsPresenterImpl(Context context,CalendarMealsView calendarMealsView) {
        this.mealPlanRepo = new MealPlanRepoImpl(context);
        this.calendarMealsView=calendarMealsView;
    }



    @Override
    public LiveData<List<MealPlan>> getMeals() {
        calendarMealsView.getCalendarDataSuccess();
        return mealPlanRepo.getMeals();
    }

    @Override
    public void deleteMeal(MealPlan mealPlan) {
        calendarMealsView.deleteCalendarDataSuccess();
        mealPlanRepo.deleteMeal(mealPlan);
    }
}
