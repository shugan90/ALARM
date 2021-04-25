package com.example.shugan.myapplicationalarm;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.Format;
import java.util.Calendar;

import static android.provider.CalendarContract.CalendarCache.URI;

public class AddActivity extends MainActivity {

    EditText author_input;
    TextView title_input;
    Button add_button;
    int mHour,mMinute;
    Calendar c;
    String format;
    private Object ContentValues;
    int week;
    private ProgressBar spinner;
    TimePicker simpleTimePicker;
    String dd;
    CheckBox check,check1,check2,check3,check4,check5,check6;
    RecyclerView.ViewHolder viewHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        add_button = findViewById(R.id.add_button);
        check=findViewById(R.id.checkBox);
        check1=findViewById(R.id.checkBox1);
        check2=findViewById(R.id.checkBox2);
        check3=findViewById(R.id.checkBox3);
        check4=findViewById(R.id.checkBox4);
        check5=findViewById(R.id.checkBox5);
        check6=findViewById(R.id.checkBox6);
        simpleTimePicker = (TimePicker) findViewById(R.id.simpleTimePicker);
        simpleTimePicker.setIs24HourView(false);




        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {


                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                c = Calendar.getInstance();
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.set(Calendar.SECOND, 0);

                                // textv.setText(hourOfDay + ":" + minute);
                                //insertItem(hourOfDay,minute);
                                //showTime(hourOfDay,minute);
                                //check selected time for AM ,PM

                                if (hourOfDay == 0) {
                                    hourOfDay += 12;
                                    format = "AM";
                                } else if (hourOfDay == 12) {
                                    format = "PM";
                                } else if (hourOfDay > 12) {
                                    hourOfDay -= 12;
                                    format = "PM";
                                } else {
                                    format = "AM";
                                }
                                String dd = hourOfDay + ":" + minute + "" + format;
                                // Toast.makeText(this,"alarm:"+hour + min + format,Toast.LENGTH_LONG).show();
                                //textv.setText(hour + min + format);
                                //startAlarm(c);
                                // title_input.setText(dd);
                                title_input.setText("Time is :: " + hourOfDay + " : " + minute);



                                //startAlarm(c);
                                week(c);
                                addItem(dd,author_input.getText().toString());

                                //week(c);

                            }

                        }, mHour, mMinute, false);
                timePickerDialog.show();


            }
        });

    }
            public void week(Calendar c){

                if (check1.isChecked()) {
                    c.set(Calendar.DAY_OF_WEEK, 2);
                    startAlarm(c,2);
                } else if (check2.isChecked()) {
                    startAlarm(c,3);
                } else if (check3.isChecked()) {
                  startAlarm(c,4);
                } else if (check4.isChecked()) {
                    startAlarm(c,5);
                } else if (check5.isChecked()) {
                    startAlarm(c,6);
                } else if (check6.isChecked()) {
                    startAlarm(c,7);
                } else if (check.isChecked()) {
                   startAlarm(c,1);
                }
                else   {
                    startAlarm(c);
                }

            }
/*
            public  void time(){

                simpleTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                                c = Calendar.getInstance();

                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.set(Calendar.SECOND, 0);

                                // textv.setText(hourOfDay + ":" + minute);
                                //insertItem(hourOfDay,minute);
                                //showTime(hourOfDay,minute);
                                //check selected time for AM ,PM

                                if (hourOfDay == 0) {
                                    hourOfDay += 12;
                                    format = "AM";
                                } else if (hourOfDay == 12) {
                                    format = "PM";
                                } else if (hourOfDay > 12) {
                                    hourOfDay -= 12;
                                    format = "PM";
                                } else {
                                    format = "AM";
                                }
                                String dd = hourOfDay + ":" + minute + "" + format;
                                // Toast.makeText(this,"alarm:"+hour + min + format,Toast.LENGTH_LONG).show();
                                //textv.setText(hour + min + format);
                                //startAlarm(c);
                                // title_input.setText(dd);
                        title_input.setText("Time is :: " + hourOfDay + " : " + minute);
                                week(c,week);
                                addItem(dd,author_input.getText().toString());

                            }

                        });



            }*/


}
