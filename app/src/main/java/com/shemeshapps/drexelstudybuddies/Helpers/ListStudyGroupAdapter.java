package com.shemeshapps.drexelstudybuddies.Helpers;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.parse.FunctionCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.shemeshapps.drexelstudybuddies.NetworkingServices.RequestUtil;
import com.shemeshapps.drexelstudybuddies.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tomer on 3/12/2015.
 */
public class ListStudyGroupAdapter extends BaseExpandableListAdapter {
    List<List<ParseObject>> sortedStudyGroups = new ArrayList<>();
    List<Date> groupDates = new ArrayList<>();
    Context context;
    LayoutInflater mInflater;
    SwipeRefreshLayout refreshLayout;

    public ListStudyGroupAdapter(Context c, List<ParseObject> studyGroups, final SwipeRefreshLayout refreshLayout, String initialQuery)
    {
        Utils.sortGroups(studyGroups,sortedStudyGroups,groupDates);
        this.context = c;
        mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        this.refreshLayout = refreshLayout;

        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        if(initialQuery != null)
        {
            refreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setRefreshing(true);
                }
            });
            loadGroupFromBackend(initialQuery,false);
        }

    }


    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.study_group_row_template, null);
        }
        ParseObject g = sortedStudyGroups.get(groupPosition).get(childPosition);
        TextView title = (TextView)convertView.findViewById(R.id.group_list_title);
        TextView time = (TextView)convertView.findViewById(R.id.group_list_time);
        TextView location = (TextView)convertView.findViewById(R.id.group_list_location);
        TextView desc = (TextView)convertView.findViewById(R.id.group_list_desc);

        title.setText(g.get("Class") + " - " + g.get("Name"));
        location.setText(g.getString("Location"));
        time.setText(Utils.formatTime(g.getDate("StartTime"))  + " - " + Utils.formatTime(g.getDate("EndTime")));
        desc.setText(g.getString("Description"));
        return convertView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.date_header, null);
        }
        TextView date = (TextView)convertView.findViewById(R.id.header_group_date);
        date.setText(Utils.formatDate(groupDates.get(groupPosition)));

        return convertView;
    }

        @Override
    public int getChildrenCount(int groupPosition) {
        return sortedStudyGroups.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupDates.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groupDates.size();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupDates.get(groupPosition).getTime();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return sortedStudyGroups.get(groupPosition).get(childPosition).getObjectId().hashCode();
    }


    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return sortedStudyGroups.get(groupPosition).get(childPosititon);
    }

    public void loadGroupFromBackend(String query,boolean showRefresh)
    {
        if(showRefresh)
        {
            refreshLayout.setRefreshing(true);
        }

        RequestUtil.getStudyGroups(query, new FunctionCallback<List<ParseObject>>() {
            public void done(List<ParseObject> groups, ParseException e) {
                if (e == null) {
                    resetList(groups);
                    int count = getGroupCount();
                    for (int position = 1; position <= count; position++)
                        ((ExpandableListView)refreshLayout.getChildAt(0)).expandGroup(position - 1);

                    refreshLayout.setRefreshing(false);

                }
            }
        });
    }

    public void resetList(List<ParseObject> newGroups)
    {
        sortedStudyGroups.clear();
        groupDates.clear();
        Utils.sortGroups(newGroups,sortedStudyGroups,groupDates);
        notifyDataSetChanged();
    }
}
