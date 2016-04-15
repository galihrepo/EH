package net.eazyhealth.id.app.helper;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

import com.thinkcool.circletextimageview.CircleTextImageView;

import net.eazyhealth.id.app.application.MyApplication;
import net.eazyhealth.id.app.custom.CustomAutoCompleteTextView;
import net.eazyhealth.id.app.custom.CustomToast;

import java.util.ArrayList;

/**
 * Created by GALIH ADITYO on 3/22/2016.
 */
public class WidgetHelper {
    public static void setAutocompleteAdapter(Context context, CustomAutoCompleteTextView view, ArrayList<String> listData) {
        try {
            int len = listData.size();
            String[] data = new String[len];
            for (int i = 0; i < len; i++) {
                data[i] = listData.get(i);
            }

            ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, data);
            view.setAdapter(adapter);
            view.setThreshold(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setNotificationNumber(final Context context, CircleTextImageView circleTextImageView, View shoppingCartButton) {
        if (MyApplication.getInstance().getDataPreferences().getMedicalChoosen() == null) {
            circleTextImageView.setVisibility(View.GONE);
            circleTextImageView.setText(0 + "");
        } else {
            int len = MyApplication.getInstance().getDataPreferences().getMedicalChoosen().size();
            if (len == 0) {
                circleTextImageView.setVisibility(View.GONE);
                circleTextImageView.setText(0 + "");
            } else {
                circleTextImageView.setVisibility(View.VISIBLE);
                circleTextImageView.setText(len + "");
            }
        }

        circleTextImageView.invalidate();

        shoppingCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomToast.setMessage(context, "tekan");
            }
        });
    }
}
