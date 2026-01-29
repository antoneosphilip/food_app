package com.example.foodproj.presentation.calendar.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodproj.R;
import com.example.foodproj.data.calendar.model.MealPlan;
import com.example.foodproj.presentation.calendar.presenter.CalendarMealsPresenter;
import com.example.foodproj.presentation.calendar.presenter.CalendarMealsPresenterImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Calendar extends Fragment implements CalendarMealsView,OnCalendarMealClickListener {

    private CalendarView calendarView;
    private TextView selectedDateText;
    private RecyclerView mealsRecyclerView;
    private LinearLayout emptyStateLayout;
    private ProgressBar progressBar;
    private CalendarMealAdapter mealsAdapter;
    private List<MealPlan> mealsList;
    private CalendarMealsPresenter presenter;
    private String selectedDate = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        initViews(view);
        setupRecyclerView();
        return view;
    }

    @SuppressLint("SetTextI18n")
    private void initViews(View view) {
        calendarView = view.findViewById(R.id.calendarView);
        selectedDateText = view.findViewById(R.id.selectedDateText);

        mealsRecyclerView = view.findViewById(R.id.mealsRecyclerView);
        emptyStateLayout = view.findViewById(R.id.emptyStateLayout);
        progressBar = view.findViewById(R.id.progressBar);

        presenter = new CalendarMealsPresenterImpl(getContext(), this);

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
           java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            selectedDate = dateFormat.format(calendar.getTime());
            selectedDateText.setText("Meals for " + selectedDate);
            loadMealsByDate(selectedDate);
        });
    }

    private void setupRecyclerView() {
        mealsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mealsList = new ArrayList<>();
        mealsAdapter = new CalendarMealAdapter(getContext(), mealsList, this);
        mealsRecyclerView.setAdapter(mealsAdapter);
    }


    private void loadMealsByDate(String date) {
        progressBar.setVisibility(View.VISIBLE);
        presenter.getMeals(date).observe(getViewLifecycleOwner(), new Observer<List<MealPlan>>() {
            @Override
            public void onChanged(List<MealPlan> mealPlans) {
                progressBar.setVisibility(View.GONE);
                mealsList.clear();
                if (mealPlans != null && !mealPlans.isEmpty()) {
                    mealsList.addAll(mealPlans);
                    updateUI(mealPlans.size());
                } else {
                    updateUI(0);
                }
                mealsAdapter.notifyDataSetChanged();
            }
        });
    }

    private void updateUI(int mealsCount) {
        if (mealsCount > 0) {
            mealsRecyclerView.setVisibility(View.VISIBLE);
            emptyStateLayout.setVisibility(View.GONE);
        } else {
            mealsRecyclerView.setVisibility(View.GONE);
            emptyStateLayout.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void deleteCalendarDataSuccess() {
        Toast.makeText(getContext(),"delete meal successfully",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteCalendarMeal(MealPlan mealPlan) {
        presenter.deleteMeal(mealPlan);
        loadMealsByDate(selectedDate);
    }
}