package com.example.foodproj.presentation.favorite.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.foodproj.R;
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.network.NetworkMonitor;
import com.example.foodproj.presentation.favorite.presenter.FavoritePresenter;
import com.example.foodproj.presentation.favorite.presenter.FavoritePresenterImpl;
import com.example.foodproj.presentation.mealsdetails.view.MealDetails;

import java.util.List;

public class Favorite extends Fragment implements FavoriteView, FavoriteOnClickListener {

    private RecyclerView favoritesRecyclerView;
    private TextView titleText;
    private LinearLayout emptyStateLayout;
    private ProgressBar progressBar;
    private FavoritesAdapter adapter;
    private FavoritePresenter presenter;
    private NetworkMonitor networkMonitor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FavoritePresenterImpl(getContext(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_faveorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favoritesRecyclerView = view.findViewById(R.id.favoritesRecyclerView);
        titleText = view.findViewById(R.id.titleText);
        setupNetworkMonitor();
        emptyStateLayout = view.findViewById(R.id.emptyStateLayout);
        progressBar = view.findViewById(R.id.progressBar);
        adapter = new FavoritesAdapter(getContext(), this);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        favoritesRecyclerView.setAdapter(adapter);

        progressBar.setVisibility(View.VISIBLE);
        presenter.getMeals();
        presenter.getRemoteFavorites();
    }

    private void updateTitle(int count) {
        String text = count + " meal" + (count > 1 ? "s" : "") + " saved";
        titleText.setText(text);
    }
    private void setupNetworkMonitor() {
        networkMonitor = new NetworkMonitor(requireContext(), new NetworkMonitor.NetworkCallback() {
            @Override
            public void onNetworkAvailable() {

            }

            @Override
            public void onNetworkUnavailable() {

            }
        });

        networkMonitor.register();
    }

    private void showContent() {
        favoritesRecyclerView.setVisibility(View.VISIBLE);
        emptyStateLayout.setVisibility(View.GONE);
    }

    private void showEmptyState() {
        favoritesRecyclerView.setVisibility(View.GONE);
        emptyStateLayout.setVisibility(View.VISIBLE);
        titleText.setText("0 meals saved");
    }

    private void showDeleteConfirmationDialog(Meal meal) {
        new AlertDialog.Builder(getContext())
                .setTitle("Delete Meal")
                .setMessage("Are you sure you want to remove \"" + meal.getStrMeal() + "\" from favorites?")
                .setPositiveButton("Delete", (dialog, which) -> presenter.deleteMeal(meal))
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public void getFavoriteDataSuccess(List<Meal> meals) {
        progressBar.setVisibility(View.GONE);
        if (meals != null && !meals.isEmpty()) {
            adapter.updateMeals(meals);
            updateTitle(meals.size());
            showContent();
        } else {
            showEmptyState();
        }
    }

    @Override
    public void getDataError() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), "Error loading favorites", Toast.LENGTH_SHORT).show();
        showEmptyState();
    }

    @Override
    public void insertDataError() {
        Toast.makeText(getContext(), "Error adding to favorites", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void insertDataSuccess() {}

    @Override
    public void deleteDataSuccess() {
        Toast.makeText(getContext(), "Meal removed from favorites", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteDataError() {
        Toast.makeText(getContext(), "Error removing from favorites", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getFavoriteRemoteSuccess() {

    }

    @Override
    public void getFavoriteRemoteError(String error) {
        Toast.makeText(getContext(), "Error getting favorites", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealClick(Meal meal) {
        if (networkMonitor.isNetworkAvailable()) {
            Bundle bundle = new Bundle();
            bundle.putString("meal_id", meal.getIdMeal());

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

    @Override
    public void onDeleteClick(Meal meal) {
        showDeleteConfirmationDialog(meal);
    }
}
