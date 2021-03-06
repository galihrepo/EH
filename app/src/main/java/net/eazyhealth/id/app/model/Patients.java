package net.eazyhealth.id.app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Patients {

    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();
    @SerializedName("nextPage")
    @Expose
    private String nextPage;
    @SerializedName("totalObjects")
    @Expose
    private Integer totalObjects;

    /**
     * @return The offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * @param offset The offset
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * @return The data
     */
    public List<Datum> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<Datum> data) {
        this.data = data;
    }

    /**
     * @return The nextPage
     */
    public String getNextPage() {
        return nextPage;
    }

    /**
     * @param nextPage The nextPage
     */
    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * @return The totalObjects
     */
    public Integer getTotalObjects() {
        return totalObjects;
    }

    /**
     * @param totalObjects The totalObjects
     */
    public void setTotalObjects(Integer totalObjects) {
        this.totalObjects = totalObjects;
    }
}
