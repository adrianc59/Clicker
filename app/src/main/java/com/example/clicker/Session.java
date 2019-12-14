package com.example.clicker;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    private SharedPreferences preferences;

    public Session(Context context){
         preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    //Getters
    public String getLogin(){ return preferences.getString("loginKey", ""); }
    public String getUsername(){ return preferences.getString("usernameKey", ""); }
    public int getCurrCount(){ return preferences.getInt("currCountKey", 0); }
    public int getMultiplier(){ return preferences.getInt("multiplierKey", 1); }

    //Setters
    public void setLogin(String login){
        preferences.edit().putString("loginKey", login).apply();
    }
    public void setUsername(String username){ preferences.edit().putString("usernameKey", username).apply(); }
    public void setCurrCount(int currCount){ preferences.edit().putInt("currCountKey", currCount).apply(); }
    public void setMultiplier(int multiplier){ preferences.edit().putInt("multiplierKey", multiplier).apply(); }
}
