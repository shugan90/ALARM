package com.example.shugan.myapplicationalarm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.widget.Toast;

import static com.example.shugan.myapplicationalarm.Datamodel.DataEntry.COLUMN_ID;

public class Datamodel {
    private Datamodel(){}
    public static final class DataEntry implements BaseColumns {
        public static final String TABLE_NAME = "my_library";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_TITLE = "alarm_time";
        public static final String COLUMN_AUTHOR = "alarm_name";

        public static String getColumnId() {
            return COLUMN_ID;
        }
    }



}
