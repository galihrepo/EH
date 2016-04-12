package net.eazyhealth.id.app.custom;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.DatePicker;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.helper.DateHelper;

import org.joda.time.DateTime;

/**
 * Created by GALIH ADITYO on 3/24/2016.
 */
public class CustomDatePicker extends MaterialCalendarView {

    public CustomDatePicker(Context context) {
        super(context);
        init(context);
    }

    public CustomDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setSelectionColor(R.color.colorPrimaryDark);
        setShowOtherDates(SHOW_ALL);
        setMinimumDate(CalendarDay.today());
        setMaximumDate(CalendarDay.from(new DateTime().plusDays(10).toCalendar(null)));
    }
}
