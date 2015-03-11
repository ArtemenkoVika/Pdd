package com.example.admin.pdd.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.pdd.R;

public class TestsOfPDDFragmentPartTwo extends BaseFragment implements View.OnClickListener {
    private TextView textCountTrue;
    private TextView textCountFalse;
    private Button buttonPrevious;
    private Button buttonNext;
    private Button buttonAnswer;
    private ClickSomeButtonListener clickSomeButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tests_of_pdd_part_two, container, false);
        textCountTrue = (TextView) view.findViewById(R.id.text_count_true);
        textCountFalse = (TextView) view.findViewById(R.id.text_count_false);
        buttonPrevious = (Button) view.findViewById(R.id.button_previous);
        buttonNext = (Button) view.findViewById(R.id.button_next);
        buttonAnswer = (Button) view.findViewById(R.id.button_answer);

        buttonPrevious.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
        buttonAnswer.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
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
