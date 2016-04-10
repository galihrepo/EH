package net.eazyhealth.id.app.model.response.backendless;

/**
 * Created by GALIH ADITYO on 4/10/2016.
 */
public class McuItem extends BackendlessObject {

    private String specificCheckupName;
    private String detail;
    private Clinic fromClinic;

    public String getSpecificCheckupName() {
        return specificCheckupName;
    }

    public void setSpecificCheckupName(String specificCheckupName) {
        this.specificCheckupName = specificCheckupName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Clinic getFromClinic() {
        return fromClinic;
    }

    public void setFromClinic(Clinic fromClinic) {
        this.fromClinic = fromClinic;
    }

    public McuItem() {
    }
}
