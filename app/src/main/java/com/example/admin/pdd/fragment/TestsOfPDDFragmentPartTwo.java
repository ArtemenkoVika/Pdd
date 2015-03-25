package com.example.admin.pdd.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.pdd.R;
import com.example.admin.pdd.entity.Question;
import com.example.admin.pdd.entity.Tests;
import com.example.admin.pdd.module.DataBasePdd;

import java.util.ArrayList;

public class TestsOfPDDFragmentPartTwo extends BaseFragment implements View.OnClickListener {
    private TextView textCountTrue;
    private TextView textCountFalse;
    private TextView textCountAllQuestions;
    private Button buttonPrevious;
    private Button buttonNext;
    private Button buttonAnswer;
    private Question question = Question.getInstance();
    private Tests tests = Tests.getInstance();
    private DataBasePdd dataBasePdd = new DataBasePdd();
    private ClickSomeButtonListener clickSomeButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tests_of_pdd_part_two, container, false);
        textCountTrue = (TextView) view.findViewById(R.id.text_count_true);
        textCountFalse = (TextView) view.findViewById(R.id.text_count_false);
        textCountAllQuestions = (TextView) view.findViewById(R.id.text_count_all_questions);
        buttonPrevious = (Button) view.findViewById(R.id.button_previous);
        buttonNext = (Button) view.findViewById(R.id.button_next);
        buttonAnswer = (Button) view.findViewById(R.id.button_answer);

        buttonPrevious.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
        buttonAnswer.setOnClickListener(this);

        if (user.getTrueAnswers() == null) {
            user.setTrueAnswers(new ArrayList());
            user.setFalseAnswers(new ArrayList());
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_answer) {
            if (question.getTextAnswer().equals(dataBasePdd.getTrueAnswer(getActivity(),
                    tests.getIdQuestions().get(singleton.count)))) {
                user.getTrueAnswers().add(1);
                textCountTrue.setText("" + user.getTrueAnswers().size());
            } else {
                user.getFalseAnswers().add(1);
                textCountFalse.setText("" + user.getFalseAnswers().size());
            }
            textCountAllQuestions.setText("" + (TestsOfPDDFragment.QUANTITY_OF_QUESTIONS -
                    tests.getAnswerNotSkipInOrder().size() - 1));
        }
        clickSomeButton.clickOnButton(view.getId());
    }

    public interface ClickSomeButtonListener {
        public void clickOnButton(int idOfButton);
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

    @Override
    public void onDetach() {
        super.onDetach();
        clickSomeButton = null;
    }
}
