package com.example.admin.pdd.database_helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ResultsSqlHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "result.db";
    public static final String TABLE_NAME = "results";
    public static final String COLUMN_RESULT = "result";
    public static final String COLUMN_USER_NAME = "user";
    public static final int DATABASE_VERSION = 1;
    private static ResultsSqlHelper instance;
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME +
            " (" + COLUMN_USER_NAME + " VARCHAR(75), " + COLUMN_RESULT +
            "  VARCHAR(75));";

    private ResultsSqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final ResultsSqlHelper getInstance(Context context) {
        if (instance == null) instance = new ResultsSqlHelper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
