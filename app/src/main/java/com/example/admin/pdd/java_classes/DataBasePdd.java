package com.example.admin.pdd.java_classes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.pdd.database_helper.PddSqlHelper;
import com.example.admin.pdd.interfaces.IDataBasePdd;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class DataBasePdd implements IDataBasePdd {

    @Override
    public int setQuestion(Context context, TextView textQuestion, ImageView imageQuestion,
                           int idQuestionAlreadyExist, boolean isAlreadyExist) {
        int idQuestionRandom;
        int idQuestion;
        String question;
        Random random = new Random();
        while (true) {
            idQuestionRandom = random.nextInt(20);
            if (idQuestionRandom == 0) idQuestionRandom = random.nextInt(20);
            else break;
        }
        if (isAlreadyExist) {
            idQuestion = idQuestionAlreadyExist;
            SQLiteDatabase database = SQLiteDatabase.openDatabase(PddSqlHelper.DB_PATH, null,
                    SQLiteDatabase.OPEN_READONLY);
            Cursor cursor = database.query(PddSqlHelper.TABLE_QUESTIONS, new String[]{PddSqlHelper
                    .COLUMN_QUESTIONS}, PddSqlHelper._ID + "=" + idQuestion, null, null, null, null);
            cursor.moveToFirst();
            question = cursor.getString(cursor.getColumnIndex(PddSqlHelper.COLUMN_QUESTIONS));
            cursor.close();
            database.close();
            textQuestion.setText(question);
            imageQuestion.setImageDrawable(loadImageFromAsset(idQuestion, context));
        } else idQuestion = idQuestionRandom;
        return idQuestion;
    }

    @Override
    public Drawable loadImageFromAsset(int index, Context context) {
        Drawable drawable = null;
        try {
            InputStream ims = context.getAssets().open("pictures/" + index + ".jpg");
            drawable = Drawable.createFromStream(ims, null);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return drawable;
    }

    @Override
    public ArrayList<String> setAnswers(Context context, int idQuestion) {
        String trueAnswer;
        ArrayList<String> answers = new ArrayList();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(PddSqlHelper.DB_PATH, null,
                SQLiteDatabase.OPEN_READONLY);
        trueAnswer = getTrueAnswer(context, idQuestion);
        answers.add(trueAnswer);
        Cursor cursor = database.query(PddSqlHelper.TABLE_FALSE_ANSWERS, new String[]{PddSqlHelper.
                COLUMN_ANSWER}, PddSqlHelper.ID_FALSE_ANSWER + "=" + idQuestion, null, null, null, null);
        cursor.moveToFirst();
        do {
            answers.add(cursor.getString(cursor.getColumnIndex(PddSqlHelper.COLUMN_ANSWER)));
        } while (cursor.moveToNext());
        cursor.close();
        database.close();
        return answers;
    }

    @Override
    public String getTrueAnswer(Context context, int idQuestion) {
        String trueAnswer;
        SQLiteDatabase database = SQLiteDatabase.openDatabase(PddSqlHelper.DB_PATH, null,
                SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = database.query(PddSqlHelper.TABLE_TRUE_ANSWERS, new String[]{PddSqlHelper
                .COLUMN_ANSWER}, PddSqlHelper._ID + "=" + idQuestion, null, null, null, null);
        cursor.moveToFirst();
        trueAnswer = cursor.getString(cursor.getColumnIndex(PddSqlHelper.COLUMN_ANSWER));
        cursor.close();
        database.close();
        return trueAnswer;
    }
}
