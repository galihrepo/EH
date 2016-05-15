package net.eazyhealth.id.app.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import net.eazyhealth.id.app.helper.GsonHelper;
import net.eazyhealth.id.app.model.response.backendless.Mcu;

import java.util.List;

/**
 * Created by GALIH ADITYO on 3/29/2016.
 */
public class DataPreferences {
    private final static String SHARED_PREFERENCE_NAME = DataPreferences.class.getSimpleName() + ".name";
    private final static String USERNAME = SHARED_PREFERENCE_NAME + ".username";
    private final static String PASSWORD = SHARED_PREFERENCE_NAME + ".password";
    private final static String MEDICAL_CHOOSEN = SHARED_PREFERENCE_NAME + ".medical.choosen";
    private final static String MCU_CHOOSEN = SHARED_PREFERENCE_NAME + ".mcu.choosen";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public DataPreferences(Context context) {
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

    public void setMedicalChoosen(List<Mcu> listObjects) {
        String data = null;
        if (listObjects != null) {
            data = GsonHelper.getString(listObjects);
        }
        prefs.edit().putString(MEDICAL_CHOOSEN, data).apply();
    }

    public List<Mcu> getMedicalChoosen() {
        String data = prefs.getString(MEDICAL_CHOOSEN, "");
        if (data == null) {
            return null;
        } else {
            return GsonHelper.getListObjects(data);
        }
    }

    public void setMcuChoosen(Mcu mcu) {
        prefs.edit().putString(MCU_CHOOSEN, GsonHelper.getString(mcu)).apply();
    }

    public Mcu getMcuChoosen() {
        return GsonHelper.getObjects(prefs.getString(MCU_CHOOSEN, ""));
    }

}
