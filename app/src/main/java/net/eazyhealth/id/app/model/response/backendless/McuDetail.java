package net.eazyhealth.id.app.model.response.backendless;

import com.backendless.Backendless;

import java.util.List;

/**
 * Created by GALIH ADITYO on 4/10/2016.
 */
public class McuDetail extends BackendlessObject {

    private List<McuVisitTime> visitTime;
    private List<McuItem> mcuItem;

    public List<McuVisitTime> getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(List<McuVisitTime> visitTime) {
        this.visitTime = visitTime;
    }

    public List<McuItem> getMcuItem() {
        return mcuItem;
    }

    public void setMcuItem(List<McuItem> mcuItem) {
        this.mcuItem = mcuItem;
    }

    public McuDetail() {
    }
}
