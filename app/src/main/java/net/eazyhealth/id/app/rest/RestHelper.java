package net.eazyhealth.id.app.rest;

/**
 * Created by GALIH ADITYO on 4/6/2016.
 */
public class RestHelper {

    public static String removeBaseUrl(String url) {
        return url.replace(ServiceAddress.BASE_URL, "");
    }
}
