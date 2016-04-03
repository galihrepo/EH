package net.eazyhealth.id.app.rest;

import net.eazyhealth.id.app.model.Patients;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by GALIH ADITYO on 4/3/2016.
 */
public interface EndPoints {
    @GET("Patients")
    Call<Patients> getPatients();
}
