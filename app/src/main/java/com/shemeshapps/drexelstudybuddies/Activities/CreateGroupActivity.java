package com.shemeshapps.drexelstudybuddies.Activities;

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
    private int mYear, mMonth, mDay, mStartHour, mStartMinute, mEndHour,mEndMinute;

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
                    mStartHour = c.get(Calendar.HOUR_OF_DAY);
                    mStartMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog tpd = new TimePickerDialog(CreateGroupActivity.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {
                                    // Display Selected time in textbox
                                    txtStartTime.setText(hourOfDay + ":" + minute);
                                }
                            }, mStartHour, mStartMinute, false);
                    tpd.show();

                }

        });

        txtEndTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                    TextKeyListener.clear((txtEndTime).getText());
                    final Calendar c = Calendar.getInstance();
                    mEndHour = c.get(Calendar.HOUR_OF_DAY);
                    mEndMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog tpd = new TimePickerDialog(CreateGroupActivity.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {
                                    // Display Selected time in textbox
                                    txtEndTime.setText(hourOfDay + ":" + minute);
                                }
                            }, mEndHour, mEndMinute, false);
                    tpd.show();

                }

        });
    }
}