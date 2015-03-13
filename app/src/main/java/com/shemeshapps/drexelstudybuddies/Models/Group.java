package com.shemeshapps.drexelstudybuddies.Models;

/**
 * Created by bret on 3/11/15.
 */
public class Group {
    public String groupName;
    public String date;
    public String startTime;
    public String endTime;
    public String location;
    public String course;

    public Group(){}
    public Group(String groupName, String date, String startTime, String endTime, String location, String course) {
        this.groupName = groupName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.course = course;
    }
}
