package net.eazyhealth.id.app.model.response.backendless;

/**
 * Created by GALIH ADITYO on 4/10/2016.
 */
public class Mcu extends BackendlessObject {
    private Integer price;
    private String productName;
    private McuDetail mcuDetail;
    private Clinic clinic;

    public McuDetail getMcuDetail() {
        return mcuDetail;
    }

    public void setMcuDetail(McuDetail mcuDetail) {
        this.mcuDetail = mcuDetail;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Mcu() {
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
