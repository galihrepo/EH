package net.eazyhealth.id.app.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by GALIH ADITYO on 3/22/2016.
 */
public class CustomScrollView extends ScrollView {
    public CustomScrollView(Context context) {
        super(context);
        init(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        // show view child of this scrollview
        this.setFillViewport(true);

        // not drawing thumb scrollbar (hide scrollbar thumb)
        this.setVerticalScrollBarEnabled(false);
    }
}
