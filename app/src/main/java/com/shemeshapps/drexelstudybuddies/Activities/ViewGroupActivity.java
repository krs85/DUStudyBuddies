package com.shemeshapps.drexelstudybuddies.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.shemeshapps.drexelstudybuddies.Models.Group;
import com.shemeshapps.drexelstudybuddies.R;

public class ViewGroupActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Group g = (Group) getIntent().getSerializableExtra("group");
        /*
           go to activity view group layout put ids in all the text boxes and set them to the information from the group above. dont worry about the button
           feel free to use the Utils.formatTime and formatDate to make the dates into pretty strings. make sure to print out all attending users in the array
         */
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
