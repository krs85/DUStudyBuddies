package com.shemeshapps.drexelstudybuddies.Activities;

/***
 *    Application Name : MessageBox
 *    Author : Vimal Rughani
 *    Website : http://pulse7.net
 *    Android SDK : 2.2
 *    For more details visit http://pulse7.net/android/date-picker-dialog-time-picker-dialog-android/
 */

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.method.TextKeyListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.shemeshapps.drexelstudybuddies.R;

public class CreateGroupActivity extends Activity {

    // Widget GUI
    EditText txtDate, txtStartTime, txtEndTime;

    // Variable for storing current date and time
    private int mYear, mMonth, mDay, mHour, mMinute;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        txtDate = (EditText) findViewById(R.id.date_txt);
        txtStartTime = (EditText) findViewById(R.id.start_time_txt);
        txtEndTime = (EditText) findViewById(R.id.end_time_txt);



        txtDate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                    TextKeyListener.clear((txtDate).getText());
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    // Launch Date Picker Dialog
                    DatePickerDialog dpd = new DatePickerDialog(CreateGroupActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    // Display Selected date in textbox
                                    txtDate.setText(dayOfMonth + "-"
                                            + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    dpd.show();



            }

        });

        txtStartTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                    TextKeyListener.clear((txtStartTime).getText());
                    final Calendar c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog tpd = new TimePickerDialog(CreateGroupActivity.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {
                                    // Display Selected time in textbox
                                    txtStartTime.setText(hourOfDay + ":" + minute);
                                }
                            }, mHour, mMinute, false);
                    tpd.show();

                }

        });

        txtEndTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                    TextKeyListener.clear((txtEndTime).getText());
                    final Calendar c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog tpd = new TimePickerDialog(CreateGroupActivity.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {
                                    // Display Selected time in textbox
                                    txtEndTime.setText(hourOfDay + ":" + minute);
                                }
                            }, mHour, mMinute, false);
                    tpd.show();

                }

        });
    }



//    @Override
//    public void onFocusChange(View v, boolean hasFocus) {
//
//        if (v == txtDate) {
//
//            // Process to get Current Date

//        }
//        if (v == txtStartTime) {
//
//            // Process to get Current Time

//        }
//
//        if (v == txtEndTime) {
//
//            // Process to get Current Time
//            final Calendar c = Calendar.getInstance();
//            mHour = c.get(Calendar.HOUR_OF_DAY);
//            mMinute = c.get(Calendar.MINUTE);
//
//            // Launch Time Picker Dialog
//            TimePickerDialog tpd = new TimePickerDialog(this,
//                    new TimePickerDialog.OnTimeSetListener() {
//
//                        @Override
//                        public void onTimeSet(TimePicker view, int hourOfDay,
//                                              int minute) {
//                            // Display Selected time in textbox
//                            txtEndTime.setText(hourOfDay + ":" + minute);
//                        }
//                    }, mHour, mMinute, false);
//            tpd.show();
//        }
//    }
}