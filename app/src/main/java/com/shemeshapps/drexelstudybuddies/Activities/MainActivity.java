package com.shemeshapps.drexelstudybuddies.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.parse.FunctionCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.shemeshapps.drexelstudybuddies.Helpers.ListStudyGroupAdapter;
import com.shemeshapps.drexelstudybuddies.Models.Group;
import com.shemeshapps.drexelstudybuddies.NetworkingServices.RequestUtil;
import com.shemeshapps.drexelstudybuddies.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    ExpandableListView suggestedGroupsList;
    SwipeRefreshLayout refreshLayout;
    ListStudyGroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button crt_grp = (Button) findViewById(R.id.create_grp);

        crt_grp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CreateGroupActivity.class);
                startActivity(intent);
            }
        });

        final Button grp_cal = (Button) findViewById(R.id.avail_grp);

        grp_cal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GroupCalendar.class);
                startActivity(intent);
            }
        });

        final SharedPreferences pref = getSharedPreferences("login_data", Context.MODE_PRIVATE);
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.suggestedStudyListRefresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.loadGroupFromBackend(pref.getString("user_classes",""),false);
            }
        });
        suggestedGroupsList = (ExpandableListView)findViewById(R.id.suggestedStudyGroupList);
        adapter = new ListStudyGroupAdapter(getApplicationContext(),new ArrayList<ParseObject>(),refreshLayout,pref.getString("user_classes",""));
        suggestedGroupsList.setAdapter(adapter);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            RequestUtil.logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
