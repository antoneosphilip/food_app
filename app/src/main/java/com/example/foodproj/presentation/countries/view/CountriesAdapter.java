package com.example.foodproj.presentation.countries.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodproj.R;
import com.example.foodproj.data.countries.model.CountriesMeals;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountryViewHolder> {

    private Context context;
    private List<CountriesMeals> countries;
    private CountriesOnClickListener countriesOnClickListener;

    public CountriesAdapter(Context context, List<CountriesMeals> countries, CountriesOnClickListener countriesOnClickListener) {
        this.context = context;
        this.countries = countries;
        this.countriesOnClickListener = countriesOnClickListener;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_card, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        CountriesMeals country = countries.get(position);

        holder.countryName.setText(country.getStrArea());

        Glide.with(context)
                .load(country.getFlagUrl())
                .placeholder(R.drawable.ic_empty_favorite)
                .centerCrop()
                .into(holder.countryFlag);

        holder.bind(country);
    }

    @Override
    public int getItemCount() {
        return countries != null ? countries.size() : 0;
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        ImageView countryFlag;
        TextView countryName;
        CardView cardView;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            countryFlag = itemView.findViewById(R.id.countryFlag);
            countryName = itemView.findViewById(R.id.countryName);
            cardView = itemView.findViewById(R.id.countryCard);
        }

        public void bind(CountriesMeals country) {
            cardView.setOnClickListener(v -> countriesOnClickListener.countriesOnClickListener(country));
        }
    }
}