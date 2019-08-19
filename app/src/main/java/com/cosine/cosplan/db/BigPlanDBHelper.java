package com.cosine.cosplan.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cosine.cosplan.record.BigPlan;

public class BigPlanDBHelper {
    private static final String DATABASE_NAME = "myDatabase";
    private static final int DATABASE_Version = 1;
    private static final String TITLE = "title";
    private static final String TYPE = "type";
    private static final String CONTENT = "content";
    private static final String TABLE_NAME = "bigPlan";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + TITLE + " VARCHAR(63) PRIMARY KEY, " + TYPE + " VARCHAR(63) ,"
            + CONTENT + " TEXT);";

    MySQLiteHelper dbHelper;
    public BigPlanDBHelper(Context context){
        dbHelper = new MySQLiteHelper(context, DATABASE_NAME, DATABASE_Version, CREATE_TABLE);
    }

    public long insertData(String title, String type, String content){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, title);
        cv.put(TYPE, type);
        cv.put(CONTENT, content);
        long id = db.insert(TABLE_NAME, null, cv);
        return id;
    }

    public long insertData(BigPlan bigPlan){
        return insertData(bigPlan.getTitle(), bigPlan.getType(), bigPlan.getContent());
    }

    public String getData(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = {TITLE,TYPE,CONTENT};
        Cursor cursor =db.query(TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(TITLE));
            String type = cursor.getString(cursor.getColumnIndex(TYPE));
            String content = cursor.getString(cursor.getColumnIndex(CONTENT));
            buffer.append(title + " " + type + " " + content + "\n");
        }
        return buffer.toString();
    }


}
