package com.example.foodproj.data.favorite.datasource;

import android.util.Log;

import com.example.foodproj.data.auth.datasource.AuthNetworkResponse;
import com.example.foodproj.data.auth.datasource.LogOutNetworkResponse;
import com.example.foodproj.data.home.model.Meal;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class FirebaseRemoteDataSource {

    private final FirebaseAuth mAuth;
    private final FirebaseFirestore firestore;

    private static final String TAG = "FirebaseRemoteDataSourc";
    public FirebaseRemoteDataSource() {
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    public void uploadUserData(Meal meal) {
        System.out.print("uploadUserData");
        Log.i(TAG, "uploadUserData: "+meal.getStrMeal());
        firestore.collection("meals")
                .document(meal.getIdMeal())
                .set(meal);
//                 .addOnSuccessListener(aVoid -> callback.onSuccess())
//                .addOnFailureListener(e -> callback.onError(e.getMessage()));
    }

    public Task<List<Meal>> getMeals() {
        return firestore.collection("meals")
                .get()
                .continueWith(task ->
                        task.getResult().toObjects(Meal.class)
                );
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
