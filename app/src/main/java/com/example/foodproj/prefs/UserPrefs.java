package com.example.foodproj.prefs;

import android.content.Context;
import android.content.SharedPreferences;

 public abstract class UserPrefs {

    private static final String PREF_NAME = "user_prefs";
    private static final String KEY_TOKEN = "token";

    private static SharedPreferences prefs;

     public static void init(Context context) {
         if (prefs == null) {
             prefs = context.getApplicationContext()
                     .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
         }
     }

    public static void saveToken(String token) {
            prefs.edit().putString(KEY_TOKEN, token).apply();
    }

    public static String getToken() {
        return prefs.getString(KEY_TOKEN, null);
    }

    public static void clear() {
        prefs.edit().clear().apply();
    }
}
