package net.eazyhealth.id.app.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by GALIH ADITYO on 3/27/2016.
 */
public class PatientsScheduleResponse {

    @SerializedName("clinic_name")
    @Expose
    private String clinicName;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("package_medis")
    @Expose
    private String packageMedical;
    @SerializedName("date")
    @Expose
    private String date;

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPackageMedical() {
        return packageMedical;
    }

    public void setPackageMedical(String packageMedical) {
        this.packageMedical = packageMedical;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
