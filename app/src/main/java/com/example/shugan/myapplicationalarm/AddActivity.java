package com.example.shugan.myapplicationalarm;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.Format;
import java.util.Calendar;

public class AddActivity extends MainActivity {

    EditText author_input;
    TextView title_input;
    Button add_button;
    int mHour,mMinute;
    Calendar c;
    String format;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);

        add_button = findViewById(R.id.add_button);


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
                                        startAlarm(c);
                                        addItem(dd,author_input.getText().toString());


                                    }

                                }, mHour, mMinute, false);
                        timePickerDialog.show();


                    }
                });

            }

    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

    }
}
