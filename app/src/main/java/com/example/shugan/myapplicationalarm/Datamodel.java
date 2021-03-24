package com.example.shugan.myapplicationalarm;

import android.provider.BaseColumns;

public class Datamodel {
    private Datamodel(){}
    public static final class DataEntry implements BaseColumns {
        public static final String TABLE_NAME = "my_library";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_TITLE = "alarm_time";
        public static final String COLUMN_AUTHOR = "alarm_name";

    }
}
