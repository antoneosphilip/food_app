package com.example.foodproj.presentation.calendar.presenter;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodproj.data.calendar.model.MealPlan;
import com.example.foodproj.data.calendar.repo.MealPlanRepo;
import com.example.foodproj.data.calendar.repo.MealPlanRepoImpl;
import com.example.foodproj.data.home.model.Meal;
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
    public LiveData<List<MealPlan>> getMeals(String date) {
        return mealPlanRepo.getMeals(date);
    }

    @Override
    public void deleteMeal(MealPlan mealPlan) {
        calendarMealsView.deleteCalendarDataSuccess();
        deletePlanMeal(mealPlan.getMealId());

        mealPlanRepo.deleteMeal(mealPlan);
    }

    @Override
    public void getRemoteCalendar() {

        mealPlanRepo.getRemotePlans()
                .addOnSuccessListener(plans -> {

                    if (plans != null && !plans.isEmpty()) {
                        for (MealPlan plan : plans) {
                            mealPlanRepo.InsertMeal(plan);
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
}
