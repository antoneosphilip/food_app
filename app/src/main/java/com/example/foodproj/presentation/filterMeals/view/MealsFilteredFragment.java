package com.example.foodproj.presentation.filterMeals.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodproj.R;
import com.example.foodproj.data.mealsfilterd.model.MealsFiltered;
import com.example.foodproj.presentation.filterMeals.presenter.MealsFilteredPresenter;
import com.example.foodproj.presentation.filterMeals.presenter.MealsFilteredPresenterImpl;
import com.example.foodproj.presentation.mealsdetails.view.MealDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealsFilteredFragment extends Fragment implements MealsFilteredView,OnMealClickListener {

    private RecyclerView mealsRecyclerView;
    private ImageView backButton;
    private TextView titleText;
    private ProgressBar progressBar;
    private MealsFilteredAdapter mealsAdapter;
    private List<MealsFiltered> mealsList;
    private String filterType;
    private String filterValue;
    private MealsFilteredPresenter presenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filtered_meals, container, false);

        initViews(view);
        getArgumentsData();
        setupRecyclerView();
        loadMeals();

        backButton.setOnClickListener(v -> requireActivity().onBackPressed());

        return view;
    }

    private void initViews(View view) {
        mealsRecyclerView = view.findViewById(R.id.mealsRecyclerView);
        backButton = view.findViewById(R.id.backButton);
        titleText = view.findViewById(R.id.titleText);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

    }

    private void getArgumentsData() {
        if (getArguments() != null) {
            filterType = getArguments().getString("filter_type");
            filterValue = getArguments().getString("filter_value");
            titleText.setText(filterValue);
        }
    }

    private void setupRecyclerView() {
        mealsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mealsList = new ArrayList<>();
        mealsAdapter = new MealsFilteredAdapter(getContext(), mealsList, this);
        mealsRecyclerView.setAdapter(mealsAdapter);
    }

    private void loadMeals() {
        presenter = new MealsFilteredPresenterImpl(this);

        Map<String, String> filter = new HashMap<>();
        filter.put(filterType, filterValue);

        presenter.getMealsFiltered(filter);
    }

    @Override
    public void getMealsFilteredSuccess(List<MealsFiltered> mealsFiltereds) {
        progressBar.setVisibility(View.GONE);
        mealsList.clear();
        mealsList.addAll(mealsFiltereds);
        mealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void getMealsFilteredError() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), "Failed to fetch meals", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealClick(MealsFiltered meal) {
        Bundle bundle = new Bundle();
        bundle.putString("meal_id", "i");
        bundle.putString("meal_id",meal.getIdMeal());

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