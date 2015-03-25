package com.example.admin.pdd.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.pdd.R;
import com.example.admin.pdd.entity.Question;
import com.example.admin.pdd.entity.Tests;
import com.example.admin.pdd.entity.User;
import com.example.admin.pdd.fragment.MainSelectionFragment;
import com.example.admin.pdd.module.ConnectWithDatabase;
import com.example.admin.pdd.pattern.Singleton;

public class MainSelectionActivity extends FragmentActivity {
    private MainSelectionFragment selectionFragment;
    private Singleton singleton = Singleton.getInstance();
    private User user = User.getInstance();
    private Tests tests = Tests.getInstance();
    private Question question = Question.getInstance();
    private Button buttonRegistration;
    private TextView textHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_selection);
        new ConnectWithDatabase(this);
        selectionFragment = (MainSelectionFragment) getSupportFragmentManager().
                findFragmentById(R.id.fragment_main_selection);
        buttonRegistration = (Button) selectionFragment.getView().findViewById(R.id.
                button_registration);
        textHello = (TextView) selectionFragment.getView().findViewById(R.id.text_hello);
    }

    @Override
    public void onResume() {
        super.onResume();
        singleton.setEnd(false);
        singleton.setTimerRotation(false);
        tests.setIdQuestions(null);
        tests.setIdQuestionAndAnswersButtonsInOrder(null);
        tests.setIdQuestionAndOneAnswer(null);
        tests.setAnswerNotSkipInOrder(null);
        user.setTrueAnswers(null);
        user.setFalseAnswers(null);
        question.setIdPressedButton(0);
        question.setAnswers(null);
        question.setNumberOfButtonAnswer(null);
        singleton.count = 0;
        if (!user.getLoginUserName().equals("")) {
            textHello.setText("Привет, " + user.getLoginUserName());
            textHello.setVisibility(View.VISIBLE);
            buttonRegistration.setText(getResources().getString(R.string.registration_change));
        }
    }
}