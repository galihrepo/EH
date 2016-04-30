package net.eazyhealth.id.app.custom;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by GALIH ADITYO on 5/1/2016.
 */
public class CustomProgressDialog extends ProgressDialog {
    public CustomProgressDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        super.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setMessage("Loading");
        setCanceledOnTouchOutside(false);
    }
}
