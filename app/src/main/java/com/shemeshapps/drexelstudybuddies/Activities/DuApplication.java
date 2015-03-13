package com.shemeshapps.drexelstudybuddies.Activities;

import android.app.Application;

import com.parse.Parse;
import com.shemeshapps.drexelstudybuddies.NetworkingServices.RequestUtil;

/**
 * Created by Tomer on 2/27/2015.
 */
public class DuApplication extends Application {
    @Override
    public void onCreate()
    {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "4iyYxPEbCNZ6h2XXtXJyvB1S2qu9OO7ADvWeGHK0", "SSNW1LMMgnPYNnx5Kq9HrOS2mtrWbJHMoQq6Wac7");
        RequestUtil.init(this);
    }
}
