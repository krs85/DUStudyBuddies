package com.shemeshapps.drexelstudybuddies.Activities;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.shemeshapps.drexelstudybuddies.Helpers.Utils;
import com.shemeshapps.drexelstudybuddies.Models.Group;
import com.shemeshapps.drexelstudybuddies.NetworkingServices.RequestUtil;
import com.shemeshapps.drexelstudybuddies.R;

public class CreateGroupActivity extends Fragment {

    // Widget GUI
    EditText txtDate, txtStartTime, txtEndTime, txtGroupName, txtLocation, txtCourse, txtDescr;
    private int mYear, mMonth, mDay, mStartHour, mStartMinute, mEndHour,mEndMinute;
    View parentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        parentView = inflater.inflate(R.layout.activity_create_group, container, false);

        txtDate = (EditText)parentView.findViewById(R.id.date_txt);
        txtStartTime = (EditText)parentView.findViewById(R.id.start_time_txt);
        txtEndTime = (EditText) parentView.findViewById(R.id.end_time_txt);
        txtGroupName = (EditText) parentView.findViewById(R.id.grp_name);
        txtCourse = (EditText)parentView.findViewById(R.id.course_txt);
        txtLocation = (EditText)parentView.findViewById(R.id.location_txt);
        txtDescr = (EditText)parentView.findViewById(R.id.desc_txt);

        final Calendar c = Calendar.getInstance();
        mStartHour = c.get(Calendar.HOUR_OF_DAY);
        mStartMinute = c.get(Calendar.MINUTE);
        mEndHour = c.get(Calendar.HOUR_OF_DAY);
        mEndMinute = c.get(Calendar.MINUTE);
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        txtDate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                    DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    mYear = year;
                                    mMonth = monthOfYear;
                                    mDay = dayOfMonth;
                                    txtDate.setText(Utils.formatDate(monthOfYear ,dayOfMonth,year));
                                    txtDate.setError(null);
                                }
                            }, mYear, mMonth, mDay);
                    dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                    dpd.show();
            }

        });


        txtStartTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                    TimePickerDialog tpd = new TimePickerDialog(getActivity(),
                            new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,int minute) {
                                    mStartHour = hourOfDay;
                                    mStartMinute = minute;
                                    txtStartTime.setText(Utils.formatTime(mStartHour, mStartMinute));
                                    txtStartTime.setError(null);
                                }
                            }, mStartHour, mStartMinute, false);

                    tpd.show();

                }

        });

        txtEndTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                    TimePickerDialog tpd = new TimePickerDialog(getActivity(),
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {
                                    mEndHour = hourOfDay;
                                    mEndMinute = minute;
                                    txtEndTime.setText(Utils.formatTime(mEndHour, mEndMinute));
                                    txtEndTime.setError(null);
                                }
                            }, mEndHour, mEndMinute, false);
                    tpd.show();

                }

        });

        Button create = (Button)parentView.findViewById(R.id.submit_grp_btn);
        create.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                createGroup();
            }
        });
        return parentView;
    }

    private void createGroup()
    {
        LinearLayout root = (LinearLayout)parentView.findViewById(R.id.createGroupRoot);
        int childcount = root.getChildCount();
        boolean missingField = false;
        for (int i=0; i < childcount; i++){
            View v = root.getChildAt(i);
            if(v instanceof EditText)
            {
                if(((EditText)v).getText().toString().isEmpty())
                {
                    ((EditText)v).setError("Required Field");
                    missingField = true;
                }
                else
                {
                    ((EditText)v).setError(null);
                }
            }
        }

        /*
        HERE TO CHECK FOR IF END TIME IS BEFORE START,
        START TIME IS BEFORE RIGHT NOW,
        NOT MORE THAN 2 WEEKS AHEAD OF TIME

        if so do ex: txtEndTime.setError("Start time before end");
                     missingField = true;
         */

        if(!missingField)
        {
            Group g= new Group();
            g.course = txtCourse.getText().toString();
            g.description = txtDescr.getText().toString();
            g.groupName = txtGroupName.getText().toString();
            g.location = txtLocation.getText().toString();
            Calendar startCal = Calendar.getInstance();
            startCal.set(Calendar.HOUR_OF_DAY,mStartHour);
            startCal.set(Calendar.MINUTE,mStartMinute);
            startCal.set(Calendar.DAY_OF_MONTH,mDay);
            startCal.set(Calendar.MONTH,mMonth);
            startCal.set(Calendar.YEAR,mYear);
            g.startTime = startCal.getTime();


            Calendar endCal = Calendar.getInstance();
            endCal.set(Calendar.HOUR_OF_DAY,mEndHour);
            endCal.set(Calendar.MINUTE,mEndMinute);
            endCal.set(Calendar.DAY_OF_MONTH,mDay);
            endCal.set(Calendar.MONTH,mMonth);
            endCal.set(Calendar.YEAR,mYear);
            g.endTime = endCal.getTime();

            RequestUtil.postStudyGroup(g);
            ((MainActivity)getActivity()).loadScreen(MainActivity.fragments.MINE);
        }
    }
}