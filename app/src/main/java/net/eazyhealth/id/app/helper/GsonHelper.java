package net.eazyhealth.id.app.helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.eazyhealth.id.app.model.response.backendless.Mcu;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by GALIH ADITYO on 4/16/2016.
 */
public class GsonHelper {

    public static List<Mcu> getListObjects(String stringObjects) {
        Type type = new TypeToken<List<Mcu>>() {
        }.getType();
        return new Gson().fromJson(stringObjects, type);
    }

    public static String getString(List<Mcu> listObjects) {
        Type type = new TypeToken<List<Mcu>>() {
        }.getType();
        return new Gson().toJson(listObjects, type);
    }

    public static Mcu getObjects(String stringObjects) {
        Type type = new TypeToken<Mcu>() {
        }.getType();
        return new Gson().fromJson(stringObjects, type);
    }

    public static String getString(Mcu mcu) {
        Type type = new TypeToken<Mcu>() {
        }.getType();
        return new Gson().toJson(mcu, type);
    }
}
