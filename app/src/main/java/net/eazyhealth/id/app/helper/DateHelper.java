package net.eazyhealth.id.app.helper;

import java.text.DateFormatSymbols;
import java.util.Calendar;

/**
 * Created by GALIH ADITYO on 3/24/2016.
 */
public class DateHelper {
    public static String getMonthName(int month) {
        return new DateFormatSymbols().getMonths()[month - 1];
    }
}
