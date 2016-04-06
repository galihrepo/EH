package net.eazyhealth.id.app.model.response.mcu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.eazyhealth.id.app.model.response.mcu.ClinicModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GALIH ADITYO on 4/6/2016.
 */
public class DatumMcu {
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("clinic")
    @Expose
    private ClinicModel clinic;
    @SerializedName("detail")
    @Expose
    private List<Object> detail = new ArrayList<Object>();
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("objectId")
    @Expose
    private String objectId;

    /**
     *
     * @return
     * The price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The clinic
     */
    public ClinicModel getClinic() {
        return clinic;
    }

    /**
     *
     * @param clinic
     * The clinic
     */
    public void setClinic(ClinicModel clinic) {
        this.clinic = clinic;
    }

    /**
     *
     * @return
     * The detail
     */
    public List<Object> getDetail() {
        return detail;
    }

    /**
     *
     * @param detail
     * The detail
     */
    public void setDetail(List<Object> detail) {
        this.detail = detail;
    }

    /**
     *
     * @return
     * The productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     *
     * @param productName
     * The product_name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     *
     * @return
     * The objectId
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     *
     * @param objectId
     * The objectId
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
