package com.example.admin.pdd.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.pdd.R;
import com.example.admin.pdd.entity.Question;
import com.example.admin.pdd.entity.Tests;
import com.example.admin.pdd.module.DataBasePdd;
import com.example.admin.pdd.module.TestsSystem;
import com.example.admin.pdd.module.TimerModule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class TestsOfPDDFragment extends BaseFragment implements View.OnClickListener {
    public final static int QUANTITY_OF_QUESTIONS = 20;  //not more 200
    public final static int QUANTITY_OF_ALL_QUESTIONS = 200;
    private ImageView imageQuestion;
    private TextView textQuestion;
    private TextView textViewTimer;
    private Button buttonAnswerOne;
    private Button buttonAnswerTwo;
    private Button buttonAnswerThree;
    private Button buttonAnswerFour;
    private DataBasePdd dataBasePdd = new DataBasePdd();
    private Question question = Question.getInstance();
    private Tests tests = Tests.getInstance();
    private HashSet<Integer> idQuestions = new HashSet();
    private TimerModule timerModule;
    private ClickSomeButtonListener clickSomeButton;
    private View view;
    private TestsSystem testsSystem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tests_of_pdd, container, false);

        imageQuestion = (ImageView) view.findViewById(R.id.image_question);
        textQuestion = (TextView) view.findViewById(R.id.text_question);
        textViewTimer = (TextView) view.findViewById(R.id.text_timer);
        buttonAnswerOne = (Button) view.findViewById(R.id.button_answer_one);
        buttonAnswerTwo = (Button) view.findViewById(R.id.button_answer_two);
        buttonAnswerThree = (Button) view.findViewById(R.id.button_answer_three);
        buttonAnswerFour = (Button) view.findViewById(R.id.button_answer_four);

        buttonAnswerOne.setOnClickListener(this);
        buttonAnswerTwo.setOnClickListener(this);
        buttonAnswerThree.setOnClickListener(this);
        buttonAnswerFour.setOnClickListener(this);

        buttonAnswerOne.setTag(0);
        buttonAnswerTwo.setTag(1);
        buttonAnswerThree.setTag(2);
        buttonAnswerFour.setTag(3);

        if (tests.getIdQuestions() == null) {
            tests.setIdQuestions(new ArrayList());
            tests.setIdQuestionAndOneAnswer(new LinkedHashMap());
            tests.setAnswerNotSkipInOrder(new ArrayList());
            tests.setIdQuestionAndAnswersButtonsInOrder(new LinkedHashMap());
            singleton.setAnswersForOneQuestionOrder(new ArrayList());
            question.setNumberOfButtonAnswer(new ArrayList());
            while (idQuestions.size() < QUANTITY_OF_QUESTIONS) {
                idQuestions.add(dataBasePdd.setQuestion(getActivity(), textQuestion,
                        imageQuestion, 0, false));
            }
            tests.getIdQuestions().addAll(idQuestions);
        }
        if (!singleton.isEnd()) timerModule = new TimerModule(getActivity(), textViewTimer);
        testsSystem = new TestsSystem(clickSomeButton, getActivity(), buttonAnswerOne,
                buttonAnswerTwo, buttonAnswerThree, buttonAnswerFour, imageQuestion, textQuestion,
                timerModule);

        testsSystem.setOfTestsSystem(tests.getIdQuestions().get(0));
        System.out.println(tests.getIdQuestions());
        return view;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        if (tests.getIdQuestionAndOneAnswer().containsKey(tests.getIdQuestions().get(singleton.count))) {
            clickSomeButton.buttonAnswerEnabled(false);
        } else clickSomeButton.buttonAnswerEnabled(true);
        Button button = (Button) view.findViewById(view.getId());

        question.setTextAnswer(button.getText().toString());
        question.setIdPressedButton(view.getId());

        testsSystem.setDefaultBackgroundButtonsAnswer();
        button.setBackground(getResources().getDrawable(R.drawable.button_of_answer_pressed));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void orPreviousOrAnswerOrNext(int idOfButton) {
        switch (idOfButton) {

            case R.id.button_previous:
                testsSystem.buttonPrevious();
                break;

            case R.id.button_answer:
                testsSystem.buttonAnswer();
                break;

            case R.id.button_next:
                testsSystem.buttonNext();
                break;

            default:
                break;
        }
    }

    public interface ClickSomeButtonListener {
        public void buttonAnswerEnabled(boolean enableButtonAnswer);

        public void buttonPreviousVisibility(int visibilityButtonPrevious);

        public void buttonNextVisibility(int visibilityButtonNext);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            clickSomeButton = (ClickSomeButtonListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ClickSomeButtonListener");
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onResume() {
        super.onResume();
        testsSystem.checkOfSelection();
        if (question.getIdPressedButton() != 0) {
            Button button = (Button) view.findViewById(question.getIdPressedButton());
            button.setBackground(getResources().getDrawable(R.drawable.button_of_answer_pressed));
            question.setIdPressedButton(0);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (timerModule.getTimer() != null) timerModule.getTimer().cancel();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickSomeButton = null;
    }
}
