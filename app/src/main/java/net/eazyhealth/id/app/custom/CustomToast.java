package net.eazyhealth.id.app.custom;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by GALIH ADITYO on 3/23/2016.
 */
public class CustomToast {
    public static void setMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
