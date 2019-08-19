package com.cosine.cosplan.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
    String createTableClause;

    Context context;
    public MySQLiteHelper(Context context, String dbname, int version, String createTableClause){
        super(context, dbname, null, version);
        this.context = context;
        this.createTableClause = createTableClause;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        try {
            System.out.println(createTableClause);
            db.execSQL(createTableClause);
        } catch (Exception e) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
