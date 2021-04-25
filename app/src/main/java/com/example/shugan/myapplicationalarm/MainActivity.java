package com.example.shugan.myapplicationalarm;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity  {
    private SQLiteDatabase mDatabase;
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    Context context;
    Button del;
    MyDatabaseHelper myDB;
    Datamodel madapter;
    ArrayList<String> alarmids, alarmtimes, alarmnames;
    RecyclerViewAdapter customAdapter;
    //String id, title, author;
    Calendar c;
    int mHour, mMinute;
    AdapterView.OnItemClickListener listener;
    EditText author_input;
    TextView title_input;
    private ProgressBar spinner;
    RecyclerView.ViewHolder viewHolder;
   // final int tim= (int) System.currentTimeMillis();
    String format;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB=new MyDatabaseHelper(this);
        mDatabase=myDB.getWritableDatabase();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customAdapter = new RecyclerViewAdapter(this, getAllItems());
        recyclerView.setAdapter(customAdapter);

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        add_button = findViewById(R.id.add_button);
        del = findViewById(R.id.del);
        //AppCompatDelegate.setDefaultNightMode(
        //        AppCompatDelegate.MODE_NIGHT_YES);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                removeItem((long) viewHolder.itemView.getTag());

            }
        }).attachToRecyclerView(recyclerView);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                Intent myIntent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(myIntent);

            }
        });

    }

    public void addItem( String dd, String author_input) {

        ContentValues cv = new ContentValues();
        cv.put(Datamodel.DataEntry.COLUMN_TITLE, dd);
        cv.put(Datamodel.DataEntry.COLUMN_AUTHOR, author_input);
        mDatabase.insert(Datamodel.DataEntry.TABLE_NAME, null, cv);
        customAdapter.swapCursor(getAllItems());
        this.author_input.getText().clear();
    }

    private void removeItem(long id) {

        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, Math.toIntExact(id), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();
        //if (pendingIntent!=null)
         //   alarmManager.cancel(pendingIntent);
       // alarmManager.cancel(pendingIntent);
        mDatabase.delete(Datamodel.DataEntry.TABLE_NAME,
                Datamodel.DataEntry._ID + "=" + id, null);
        customAdapter.swapCursor(getAllItems());
        Toast.makeText(this,"id" + id,Toast.LENGTH_SHORT).show();
    }

    public void startAlarm(Calendar cal, int wk) {

        cal.set(Calendar.DAY_OF_WEEK, wk);

        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent,0);
        // if (c.before(Calendar.getInstance())) {
        //   c.add(Calendar.DATE, 0);
        // }
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

    }

    public void startAlarm(Calendar cal) {

        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent,0);
        // if (c.before(Calendar.getInstance())) {
        //   c.add(Calendar.DATE, 0);
        // }
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }


    private Cursor getAllItems() {
        return mDatabase.query(
                Datamodel.DataEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                Datamodel.DataEntry.COLUMN_ID + " DESC"
        );

    }

}
/*
    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");


        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + alarmids.toString() + " ?");
        builder.setMessage("Are you sure you want to delete " + alarmids.toString() + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                // myDB.deleteOneRow("23");
                //  myDB.deleteEntry(Long.parseLong(alarmids.toString()));
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            //empty_imageview.setVisibility(View.VISIBLE);
            //no_data.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                alarmids.add(cursor.getString(0));
                alarmtimes.add(cursor.getString(1));
                alarmnames.add(cursor.getString(2));

            }
            // empty_imageview.setVisibility(View.GONE);
            // no_data.setVisibility(View.GONE);
        }
    }

*/


