package net.eazyhealth.id.app.model.response.backendless;

/**
 * Created by GALIH ADITYO on 4/10/2016.
 */
public abstract class BackendlessObject {
    private String objectId;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
