package com.example.admin.pdd.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.admin.pdd.R;
import com.example.admin.pdd.activity.ReadingActivity;
import com.example.admin.pdd.activity.RegistrationActivity;
import com.example.admin.pdd.activity.ResultsActivity;
import com.example.admin.pdd.activity.SignActivity;
import com.example.admin.pdd.activity.TestsOfPDDActivity;

import java.util.Map;

public class MainSelectionFragment extends BaseFragment implements View.OnClickListener {
    private Button buttonRegistration;
    private Button buttonSign;
    private Button buttonReading;
    private Button buttonTests;
    private Button buttonResults;
    private SharedPreferences userNamePreferences;
    private Map<String, ?> allPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_selection, container, false);

        buttonRegistration = (Button) view.findViewById(R.id.button_registration);
        buttonSign = (Button) view.findViewById(R.id.button_sign);
        buttonReading = (Button) view.findViewById(R.id.button_reading);
        buttonTests = (Button) view.findViewById(R.id.button_tests);
        buttonResults = (Button) view.findViewById(R.id.button_results);

        buttonRegistration.setOnClickListener(this);
        buttonSign.setOnClickListener(this);
        buttonReading.setOnClickListener(this);
        buttonTests.setOnClickListener(this);
        buttonResults.setOnClickListener(this);

        dialogMessage = getActivity().getResources().getString(R.string.dialog_info_must_registration);

        userNamePreferences = getActivity().getSharedPreferences("userName", Context.MODE_PRIVATE);
        allPreferences = userNamePreferences.getAll();
        if (allPreferences.size() == 1) {
            user.setLoginUserName((String) allPreferences.
                    get(new RegistrationFragment().getKeyPreferencesUserName()));
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_registration:
                goToActivity(RegistrationActivity.class);
                break;

            case R.id.button_sign:
                goToActivity(SignActivity.class);
                break;

            case R.id.button_reading:
                goToActivity(ReadingActivity.class);
                break;

            case R.id.button_tests:
                if (user.getLoginUserName().equals("")) {
                    showDialogInfo(dialogTitle, dialogMessage);
                } else
                    goToActivity(TestsOfPDDActivity.class);
                break;

            case R.id.button_results:
                if (user.getLoginUserName().equals("")) {
                    showDialogInfo(dialogTitle, dialogMessage);
                } else
                    goToActivity(ResultsActivity.class);
                break;
        }
    }

    public void goToActivity(Class activity) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), activity);
        getActivity().startActivity(intent);
    }
}
