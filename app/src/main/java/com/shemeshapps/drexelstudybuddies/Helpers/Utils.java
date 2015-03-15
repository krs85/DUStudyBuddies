package com.shemeshapps.drexelstudybuddies.Helpers;

import com.parse.ParseObject;
import com.shemeshapps.drexelstudybuddies.Models.Group;

import org.json.JSONArray;
import org.json.JSONException;

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

    public static Group ParseObjectToGroup(ParseObject o)
    {
        Group g = new Group();
        g.id = o.getObjectId();
        g.course = o.getString("Study");
        g.creator = o.getString("Creator");
        g.groupName = o.getString("Name");
        g.location = o.getString("Location");
        g.description = o.getString("Description");
        g.startTime = o.getDate("StartTime");
        g.endTime = o.getDate("EndTime");
        JSONArray attending = o.getJSONArray("UsersAttending");
        g.attendingUsers = new String[attending.length()];
        for(int i=0; i < attending.length(); i++)
        {
            try {
                g.attendingUsers[i] = attending.getString(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return g;
    }


    public static ParseObject GroupToParseObject(Group g)
    {
        ParseObject study;
        if(g.id==null)
        {
            study = new ParseObject("StudyGroup");
        }
        else
        {
            study = ParseObject.createWithoutData("StudyGroup", g.id);
        }
        study.put("Class", g.course);
        study.put("Name",g.groupName);
        study.put("Location",g.location);
        study.put("Description",g.description);
        study.put("StartTime",g.startTime);
        study.put("EndTime",g.endTime);
        study.put("Authorization", GenAuthorization.GetTokenHeader());

        return study;
    }
}
