package com.example.foodproj.presentation.calendar.view;

import com.example.foodproj.data.calendar.model.MealPlan;
import com.example.foodproj.data.home.model.Meal;

import java.util.List;

public interface CalendarMealsView {
    void deleteCalendarDataSuccess();

    void getRemoteCalendarSuccess();
    void getRemoteCalendarError(String error);

    void getLocalCalendarSuccess(List<MealPlan> mealPlans);

    void getLocalCalendarError(String message);


}
