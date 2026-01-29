package com.example.foodproj.data.home.datasource;

import com.example.foodproj.data.home.model.Category;

import java.util.List;

public interface CategoryNetworkResponse extends HomeNetworkResponse{
    void onCategorySuccess(List<Category> categories);
    void onCategoryError(String message);
}
