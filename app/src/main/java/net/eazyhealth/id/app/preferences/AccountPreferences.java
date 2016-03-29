package net.eazyhealth.id.app.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by GALIH ADITYO on 3/29/2016.
 */
public class AccountPreferences {
    private final static String SHARED_PREFERENCE_NAME = "accountPreferences";
    private final static String USERNAME = "accountPreferencesUsername";
    private final static String PASSWORD = "accountPreferencesPassword";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public AccountPreferences(Context context) {
        prefs = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setUsername(String username) {
        prefs.edit().putString(USERNAME, username).apply();
    }

    public void setPassword(String password) {
        prefs.edit().putString(PASSWORD, password).apply();
    }

    public String getUsername() {
        return prefs.getString(USERNAME, null);
    }

    public String getPassword() {
        return prefs.getString(PASSWORD, null);
    }
}
