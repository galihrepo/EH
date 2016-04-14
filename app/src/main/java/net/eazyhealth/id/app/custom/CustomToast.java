package net.eazyhealth.id.app.custom;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by GALIH ADITYO on 3/23/2016.
 */
public class CustomToast {
    public static void setMessage(Context context, String message) {
        try {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSnackbar(View coordinatorLayoutView, String alert) {
        try {
            final Snackbar snackbar = Snackbar.make(coordinatorLayoutView, alert, Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(Color.WHITE);
            snackbar.setAction("Close", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            });
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
