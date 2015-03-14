package com.shemeshapps.drexelstudybuddies.Activities;

import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import com.parse.ParseObject;
import com.shemeshapps.drexelstudybuddies.Helpers.ListStudyGroupAdapter;
import com.shemeshapps.drexelstudybuddies.R;

import java.util.ArrayList;

public class GroupCalendar extends Fragment {

    View parentView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        parentView = inflater.inflate(R.layout.activity_group_calendar, container, false);
        super.onCreate(savedInstanceState);

        final SearchView searchBox = (SearchView)parentView.findViewById(R.id.browseGroupSearchBox);
        SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout)parentView.findViewById(R.id.browseGroupsRefresher);
        final ListStudyGroupAdapter adapter = new ListStudyGroupAdapter(getActivity(),new ArrayList<ParseObject>(),refreshLayout,"");

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.loadGroupFromBackend(searchBox.getQuery().toString(),false);
            }
        });
        ExpandableListView suggestedGroupsList = (ExpandableListView)parentView.findViewById(R.id.list_study_groups);
        suggestedGroupsList.setAdapter(adapter);

        searchBox.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.loadGroupFromBackend(query,true);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty())
                {
                    adapter.loadGroupFromBackend(newText,false);
                }
                return false;
            }
        });
        return parentView;
    }
}
