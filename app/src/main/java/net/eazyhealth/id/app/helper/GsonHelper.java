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
//
//    public static CityToEntity getObjectCityToEntity(String data) {
//        Type type = new TypeToken<CityToEntity>(){}.getType();
//        return new Gson().fromJson(data, type);
//    }
//
//    public static String getStringCityToEntity(CityToEntity data) {
//        Type type = new TypeToken<CityToEntity>(){}.getType();
//        return new Gson().toJson(data, type);
//    }
//
//    public static ArrayList<BraScheduleResponse> getListObjectBraScheduleResponse(String response) {
//        Type type = new TypeToken<ArrayList<BraScheduleResponse>>(){}.getType();
//        return new Gson().fromJson(response, type);
//    }
//
//    public static String getStringListObjectFilterTypesOp(ArrayList<ScheduleActivity.FilterTypesOp> list) {
//        Type type = new TypeToken<ArrayList<ScheduleActivity.FilterTypesOp>>(){}.getType();
//        return new Gson().toJson(list, type);
//    }
//

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
}
