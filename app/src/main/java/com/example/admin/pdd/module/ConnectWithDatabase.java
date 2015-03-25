package com.example.admin.pdd.module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.admin.pdd.database_helper.PddTestsSqlHelper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConnectWithDatabase {
    private Context context;

    public ConnectWithDatabase(Context context) {
        this.context = context;
        initialize();
    }

    public void initialize() {
        if (isInitialized() == false) {
            copyInitialDBFromAssets();
        }
    }

    public boolean isInitialized() {
        SQLiteDatabase checkDB = null;
        Boolean correctVersion = false;
        try {
            checkDB = SQLiteDatabase.openDatabase(PddTestsSqlHelper.DB_PATH, null,
                    SQLiteDatabase.OPEN_READWRITE);
            checkDB.setVersion(1);
            correctVersion = checkDB.getVersion() == PddTestsSqlHelper.DB_VERSION;
        } catch (SQLiteException e) {
            System.out.println(e + " - in the ConnectWithDatabase.class");
        } finally {
            if (checkDB != null)
                checkDB.close();
        }
        return checkDB != null && correctVersion;
    }

    private void copyInitialDBFromAssets() {
        InputStream inStream;
        OutputStream outStream;
        try {
            inStream = new BufferedInputStream(context.getAssets().open(
                    PddTestsSqlHelper.DB_ASSETS_PATH), PddTestsSqlHelper.DB_FILES_COPY_BUFFER_SIZE);
            File dbDir = new File(PddTestsSqlHelper.DB_FOLDER);
            if (dbDir.exists() == false)
                dbDir.mkdir();
            outStream = new BufferedOutputStream(new FileOutputStream(PddTestsSqlHelper.DB_PATH),
                    PddTestsSqlHelper.DB_FILES_COPY_BUFFER_SIZE);
            byte[] buffer = new byte[PddTestsSqlHelper.DB_FILES_COPY_BUFFER_SIZE];
            int length;
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            outStream.flush();
            outStream.close();
            inStream.close();
        } catch (IOException e) {
            System.out.println(e + " - in the ConnectWithDatabase.class");
        }
    }
}
