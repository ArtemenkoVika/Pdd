package com.example.admin.pdd.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.pdd.R;
import com.example.admin.pdd.entity.Question;
import com.example.admin.pdd.entity.Tests;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ResultsFragment extends BaseFragment {
    private Question question = Question.getInstance();
    private Tests tests = Tests.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        System.out.println(tests.getIdQuestionAndOneAnswer());
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy z", Locale.getDefault());
        String strDate = simpleDateFormat.format(calendar.getTime());
        strDate = strDate.substring(0, 10);
        System.out.println(strDate);
        return view;
    }
}
