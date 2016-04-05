package net.eazyhealth.id.app.rest;

import net.eazyhealth.id.app.model.Patients;

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
}
