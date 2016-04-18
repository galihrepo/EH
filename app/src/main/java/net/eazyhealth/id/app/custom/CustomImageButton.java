package net.eazyhealth.id.app.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * Created by GALIH ADITYO on 4/18/2016.
 */
public class CustomImageButton extends ImageButton {
    public CustomImageButton(Context context) {
        super(context);
        init(context);
    }

    public CustomImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomImageButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.setBackgroundColor(Color.TRANSPARENT);
    }
}
