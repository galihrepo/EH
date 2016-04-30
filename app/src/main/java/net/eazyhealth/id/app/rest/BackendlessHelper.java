package net.eazyhealth.id.app.rest;

import com.backendless.persistence.local.UserTokenStorageFactory;

/**
 * Created by GALIH ADITYO on 5/1/2016.
 */
public class BackendlessHelper {

    public static String getAccessToken() {
        return UserTokenStorageFactory.instance().getStorage().get();
    }
}
