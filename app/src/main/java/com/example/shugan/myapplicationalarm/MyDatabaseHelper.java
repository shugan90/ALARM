package com.example.shugan.myapplicationalarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import com.example.shugan.myapplicationalarm.Datamodel.*;
import androidx.annotation.Nullable;

import static com.example.shugan.myapplicationalarm.Datamodel.*;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;


    MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DataEntry.TABLE_NAME +
                " (" + DataEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DataEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                DataEntry. COLUMN_AUTHOR + " TEXT NOT NULL) ;";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DataEntry.TABLE_NAME);
        onCreate(db);
    }

/*
    void addBook(String title, String author){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);

        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String title, String author){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);


        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

public boolean deleteone( String id){
    Toast.makeText(context, "row:"+id, Toast.LENGTH_SHORT).show();
    SQLiteDatabase db = this.getWritableDatabase();
    String queryString="DELETE FROM" + TABLE_NAME + "WHERE" + COLUMN_ID + "=" +id;
    Cursor cursor = db.rawQuery(queryString, null);

    if(cursor.moveToFirst()){
        return true;

    }else{
        return false;
    }
    }

    boolean deleteOneRow(String row_id){
        Toast.makeText(context, "row"+row_id, Toast.LENGTH_SHORT).show();
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();

        }
        return true;
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.close();
    }


    public void delete(String tableName, String s, Object o) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString="DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " +s;
        Cursor cursor = db.rawQuery(queryString, null);


    }

 */
}
