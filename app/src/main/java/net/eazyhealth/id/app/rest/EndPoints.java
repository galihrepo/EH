package net.eazyhealth.id.app.rest;

import net.eazyhealth.id.app.model.Patients;
import net.eazyhealth.id.app.model.response.mcu.McuModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by GALIH ADITYO on 4/3/2016.
 */
public interface EndPoints {
    @GET("Patients")
    Call<Patients> getPatients();

    @GET
    Call<Patients> getPatients(@Url String nextPage);

//    @GET("mcu?loadRelations=clinic%2Cdetail&props=product_name%2Cprice%2CobjectId")
    @GET("mcu?loadRelations=clinic")
    Call<McuModel> getMcuList();

    @GET
    Call<McuModel> getMcuList(@Url String nextPage);
}
