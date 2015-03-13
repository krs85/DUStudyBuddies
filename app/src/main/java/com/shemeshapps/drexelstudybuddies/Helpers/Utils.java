package com.shemeshapps.drexelstudybuddies.Helpers;

import com.parse.ParseObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Tomer on 3/12/15.
 */
public class Utils {

    public static String formatTime(int hour, int minute)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,hour);
        cal.set(Calendar.MINUTE,minute);
        return formatTime(cal.getTime());
    }
    public static String formatTime(Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat("h:mm a");
        return format.format(date);
    }


    public static String formatDate(int month, int day,int year)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.DAY_OF_MONTH,day);
        cal.set(Calendar.YEAR,year);
        return formatDate(cal.getTime());
    }

    public static String formatDate(Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat("MMMM d yyyy");
        return format.format(date);
    }

    public static void sortGroups(List<ParseObject> allGroups, List<List<ParseObject>> sortedGroups, List<Date> groupDates )
    {
        Calendar tempcal;
        int curDate = -1;

        for(ParseObject group:allGroups)
        {
            tempcal = new GregorianCalendar();
            tempcal.setTime(group.getDate("StartTime"));
            int groupDate = tempcal.get(Calendar.DAY_OF_YEAR);

            if(groupDate == curDate)
            {
                sortedGroups.get(sortedGroups.size()-1).add(group);
            }
            else
            {
                curDate = groupDate;
                groupDates.add(group.getDate("StartTime"));
                sortedGroups.add(new ArrayList<ParseObject>());
                sortedGroups.get(sortedGroups.size()-1).add(group);
            }
        }
    }
}
