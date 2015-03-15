package com.shemeshapps.drexelstudybuddies.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.shemeshapps.drexelstudybuddies.Helpers.Utils;
import com.shemeshapps.drexelstudybuddies.Models.Group;
import com.shemeshapps.drexelstudybuddies.R;

public class ViewGroupActivity extends ActionBarActivity {

    /*
        Edited by Kelly on 3/14/15
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Group g = (Group) getIntent().getSerializableExtra("group");

        TextView txtClass = (TextView) findViewById(R.id.txtClass);
        txtClass.append(" " + g.course);
        TextView txtName = (TextView) findViewById(R.id.txtName);
        txtName.append(" " + g.groupName);
        TextView txtLocation  = (TextView) findViewById(R.id.txtLocation);
        txtLocation.append(" " + g.location);
        TextView txtCreator = (TextView) findViewById(R.id.txtCreator);
        txtCreator.append(" " + g.creator);
        TextView txtDate = (TextView) findViewById(R.id.txtDate);
        txtDate.append(" " + Utils.formatDate(g.startTime));
        TextView txtTime = (TextView) findViewById(R.id.txtTime);
        txtTime.append(" " + Utils.formatTime(g.startTime) + "-" + Utils.formatTime(g.endTime));
        TextView txtAttending = (TextView) findViewById(R.id.txtAttending);
        for(int i = 0; i < g.attendingUsers.length; i++) {
            txtAttending.append("\n" + g.attendingUsers[i]);
        }
        TextView txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtDescription.append(" " + g.description);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

}
