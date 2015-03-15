package com.shemeshapps.drexelstudybuddies.Models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bret on 3/11/15.
 */
public class Group implements Serializable{
    public String creator;
    public String groupName;
    public String description;
    public Date startTime;
    public Date endTime;
    public String location;
    public String course;
    public String id;
    public String[]  attendingUsers;
    public Group(){}
}
