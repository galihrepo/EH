package net.eazyhealth.id.app.helper;

import android.content.Context;
import android.widget.ArrayAdapter;

import net.eazyhealth.id.app.custom.CustomAutoCompleteTextView;

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
}
