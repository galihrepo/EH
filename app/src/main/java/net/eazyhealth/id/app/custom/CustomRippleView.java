package net.eazyhealth.id.app.custom;

import android.content.Context;
import android.util.AttributeSet;

import net.eazyhealth.id.app.R;

/**
 * Created by GALIH ADITYO on 4/3/2016.
 */
public class CustomRippleView extends RippleViewAndroidM {
    public CustomRippleView(Context context) {
        super(context);
        init(context);
    }

    public CustomRippleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomRippleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.setCentered(true);
        this.setRippleDuration(100);
        this.setRippleColor(R.color.green_1);
    }
}
