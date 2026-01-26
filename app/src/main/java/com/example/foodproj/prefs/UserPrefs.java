package com.example.foodproj.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPrefs {

    private static final String PREF_NAME = "user_prefs";
    private static final String KEY_TOKEN = "token";

    private SharedPreferences prefs;

    public UserPrefs(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        prefs.edit().putString(KEY_TOKEN, token).apply();
    }

    public String getToken() {
        return prefs.getString(KEY_TOKEN, null);
    }

    public void clear() {
        prefs.edit().clear().apply();
    }
}
