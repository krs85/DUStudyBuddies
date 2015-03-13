package com.shemeshapps.drexelstudybuddies.Helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Tomer on 3/12/15.
 */
public class Utils {

    public static String formatDate(int hour, int minute)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,hour);
        cal.set(Calendar.MINUTE,minute);

        SimpleDateFormat format = new SimpleDateFormat("h:mm a");

        return format.format(cal.getTime());
    }
}
