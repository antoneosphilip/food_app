package com.example.foodproj.data.favorite.datasource;

import android.content.SharedPreferences;
import android.util.Log;
import com.example.foodproj.data.auth.datasource.LogOutNetworkResponse;
import com.example.foodproj.data.calendar.model.MealPlan;
import com.example.foodproj.data.home.model.Meal;
import com.example.foodproj.prefs.UserPrefs;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.List;

public class FirebaseRemoteDataSource {

    private final FirebaseAuth mAuth;
    private final FirebaseFirestore firestore;
    private static final String TAG = "FirebaseRemoteDataSourc";

    private final String token ;
    public FirebaseRemoteDataSource() {
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        this.token= UserPrefs.getToken();
    }



    public void uploadFavoriteData(Meal meal) {

        firestore.collection("users")
                .document(token)
                .collection("meals")
                .document(meal.getIdMeal())
                .set(meal);
    }

    public void uploadPlansData(MealPlan mealPlan) {

        firestore.collection("users")
                .document(token)
                .collection("plansMeals")
                .document(mealPlan.getMealId())
                .set(mealPlan);
    }

    public Task<List<Meal>> getFavoriteMeals() {


        return firestore.collection("users")
                .document(token)
                .collection("meals")
                .get()
                .continueWith(task -> task.getResult().toObjects(Meal.class));
    }

    public Task<List<MealPlan>> getCalendarMeals() {


        return firestore.collection("users")
                .document(token)
                .collection("plansMeals")
                .get()
                .continueWith(task -> task.getResult().toObjects(MealPlan.class));
    }

    public Task<Void> deleteCalendarMeal(String mealId) {


        return firestore.collection("users")
                .document(token)
                .collection("plansMeals")
                .document(mealId)
                .delete();
    }

    public Task<Void> deleteFavoriteMeal(String mealId) {

        return firestore.collection("users")
                .document(token)
                .collection("meals")
                .document(mealId)
                .delete();
    }

    public void signOut(LogOutNetworkResponse callback) {
        try {
            mAuth.signOut();
            callback.onSuccess();
        } catch (Exception e) {
            callback.onError(e.getMessage());
        }
    }
}
