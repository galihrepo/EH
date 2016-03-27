package net.eazyhealth.id.app.custom;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.widget.DatePicker;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.helper.DateHelper;

/**
 * Created by GALIH ADITYO on 3/24/2016.
 */
public class CustomDatePicker {
    public static void showDate(Context context, final CustomEditText btnDatePicker) {
        DatePickerDialog dialogDate = new DatePickerDialog(context, R.style.custom_date_picker_dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                int addedMonth = month + 1;
                String strMonth = "";
                String strDay = "";
                if (addedMonth < 10) {
                    strMonth = "0"+addedMonth;
                } else {
                    strMonth = ""+addedMonth;
                }
                if (day < 10) {
                    strDay = "0"+day;
                } else {
                    strDay = ""+day;
                }
                strMonth = DateHelper.getMonthName(addedMonth);
                btnDatePicker.setText(strDay+"-"+strMonth+"-"+year);
            }
        }, 1990, 0, 1);

        dialogDate.getDatePicker().setCalendarViewShown(false);
    }
}
