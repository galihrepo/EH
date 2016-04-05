package net.eazyhealth.id.app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by GALIH ADITYO on 4/3/2016.
 */
public class Datum {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("___class")
    @Expose
    private String Class;
    @SerializedName("__meta")
    @Expose
    private String Meta;

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The Class
     */
    public String getClass_() {
        return Class;
    }

    /**
     *
     * @param Class
     * The ___class
     */
    public void setClass_(String Class) {
        this.Class = Class;
    }

    /**
     *
     * @return
     * The Meta
     */
    public String getMeta() {
        return Meta;
    }

    /**
     *
     * @param Meta
     * The __meta
     */
    public void setMeta(String Meta) {
        this.Meta = Meta;
    }

}
