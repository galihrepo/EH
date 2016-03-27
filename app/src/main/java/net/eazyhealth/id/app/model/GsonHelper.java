package net.eazyhealth.id.app.model;

import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.eazyhealth.id.app.model.response.PatientsScheduleResponse;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by GALIH ADITYO on 3/27/2016.
 */
public class GsonHelper {
    public static List<PatientsScheduleResponse> getObjectListPatientsScheduleResponse(String response) {
        Type type = new TypeToken<List<PatientsScheduleResponse>>(){}.getType();
        return new Gson().fromJson(response, type);
    }
}
