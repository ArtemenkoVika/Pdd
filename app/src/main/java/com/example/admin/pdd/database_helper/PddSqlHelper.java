package com.example.admin.pdd.database_helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class PddSqlHelper extends SQLiteOpenHelper implements BaseColumns {
    public static final String TABLE_QUESTIONS = "questions";
    public static final String TABLE_FALSE_ANSWERS = "false_answers";
    public static final String TABLE_TRUE_ANSWERS = "true_answers";
    public static final String COLUMN_QUESTIONS = "question";
    public static final String COLUMN_ANSWER = "answer";
    public static final String ID_FALSE_ANSWER = "id";
    public static final String DB_NAME = "pdd_database.sqlite";
    public static final String DB_FOLDER = "/data/data/" + "com.example.admin.pdd/databases/";
    public static final String DB_PATH = DB_FOLDER + DB_NAME;
    public static final String DB_ASSETS_PATH = "databases/" + DB_NAME;
    public static final int DB_VERSION = 1;
    public static final int DB_FILES_COPY_BUFFER_SIZE = 8192;

    private PddSqlHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        throw new SQLiteException(
                "Call PddSqlHelper.Initialize first. This method should never be called.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new SQLiteException(
                "Call PddSqlHelper.Initialize first. This method should never be called.");
    }
}
