package com.example.admin.pdd.interfaces;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public interface IDataBasePdd {
    public int setQuestion(Context context, TextView textQuestion, ImageView imageQuestion,
                           int idQuestionAlreadyExist, boolean isAlreadyExist);
    public Drawable loadImageFromAsset(int index, Context context);
    public ArrayList<String> setAnswers(Context context, int idQuestion);
    public String getTrueAnswer(Context context, int idQuestion);
}
