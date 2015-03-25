package com.example.admin.pdd.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.pdd.R;
import com.example.admin.pdd.fragment.TestsOfPDDFragment;
import com.example.admin.pdd.fragment.TestsOfPDDFragmentPartTwo;

public class TestsOfPDDActivity extends BaseActivity implements TestsOfPDDFragmentPartTwo.
        ClickSomeButtonListener, TestsOfPDDFragment.ClickSomeButtonListener {
    private TestsOfPDDFragment testsOfPDDFragment;
    private TestsOfPDDFragmentPartTwo testsOfPDDFragmentPartTwo;
    private ImageView imageQuestion;
    private TextView textQuestion;
    private TextView textCountTrue;
    private TextView textCountFalse;
    private TextView textCountAllQuestions;
    private TextView textTimer;
    private Button buttonAnswerOne;
    private Button buttonAnswerTwo;
    private Button buttonAnswerThree;
    private Button buttonAnswerFour;
    private Button buttonAnswer;
    private Button buttonPrevious;
    private Button buttonNext;
    private MenuItem menuResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests_of_pdd);
        testsOfPDDFragment = (TestsOfPDDFragment) getSupportFragmentManager().
                findFragmentById(R.id.fragment_tests_of_pdd);
        testsOfPDDFragmentPartTwo = (TestsOfPDDFragmentPartTwo) getSupportFragmentManager().
                findFragmentById(R.id.fragment_tests_of_pdd_part_two);

        imageQuestion = (ImageView) testsOfPDDFragment.getView().findViewById(R.id.image_question);
        textQuestion = (TextView) testsOfPDDFragment.getView().findViewById(R.id.text_question);
        textCountTrue = (TextView) testsOfPDDFragmentPartTwo.getView().findViewById(R.id.text_count_false);
        textCountFalse = (TextView) testsOfPDDFragmentPartTwo.getView().findViewById(R.id.text_count_true);
        textCountAllQuestions = (TextView) testsOfPDDFragmentPartTwo.getView().findViewById(R.id.
                text_count_all_questions);
        textTimer = (TextView) testsOfPDDFragment.getView().findViewById(R.id.text_timer);
        buttonAnswerOne = (Button) testsOfPDDFragment.getView().findViewById(R.id.button_answer_one);
        buttonAnswerTwo = (Button) testsOfPDDFragment.getView().findViewById(R.id.button_answer_two);
        buttonAnswerThree = (Button) testsOfPDDFragment.getView().findViewById(R.id.button_answer_three);
        buttonAnswerFour = (Button) testsOfPDDFragment.getView().findViewById(R.id.button_answer_four);
        buttonAnswer = (Button) testsOfPDDFragmentPartTwo.getView().findViewById(R.id.button_answer);
        buttonPrevious = (Button) testsOfPDDFragmentPartTwo.getView().findViewById(R.id.button_previous);
        buttonNext = (Button) testsOfPDDFragmentPartTwo.getView().findViewById(R.id.button_next);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tests_of_pdd, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menuResults = menu.findItem(R.id.results);
        menuResults.setVisible(false);
        if (singleton.isEnd()) menuResults.setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.back_to_main:
                onBackPressed();
                return true;

            case R.id.results:
                Intent intent = new Intent();
                intent.setClass(this, ResultsActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        try {
            outState.putByteArray("bytes", getByteArrayFromBitmap(((BitmapDrawable) imageQuestion.
                    getDrawable()).getBitmap()));
        } catch (NullPointerException e) {
        }

        outState.putString("textQuestion", textQuestion.getText().toString());
        outState.putString("textCountTrue", textCountTrue.getText().toString());
        outState.putString("textCountFalse", textCountFalse.getText().toString());
        outState.putString("textTimer", textTimer.getText().toString());
        outState.putString("textCountAllQuestions", textCountAllQuestions.getText().toString());

        outState.putString("buttonAnswerOneText", buttonAnswerOne.getText().toString());
        outState.putString("buttonAnswerTwoText", buttonAnswerTwo.getText().toString());
        outState.putString("buttonAnswerThreeText", buttonAnswerThree.getText().toString());
        outState.putString("buttonAnswerFourText", buttonAnswerFour.getText().toString());

        outState.putInt("buttonAnswerOneVisibility", buttonAnswerOne.getVisibility());
        outState.putInt("buttonAnswerTwoVisibility", buttonAnswerTwo.getVisibility());
        outState.putInt("buttonAnswerThreeVisibility", buttonAnswerThree.getVisibility());
        outState.putInt("buttonAnswerFourVisibility", buttonAnswerFour.getVisibility());
        outState.putInt("buttonPreviousVisibility", buttonPrevious.getVisibility());
        outState.putInt("buttonNextVisibility", buttonNext.getVisibility());

        outState.putBoolean("buttonAnswerEnabled", buttonAnswer.isEnabled());
        outState.putBoolean("buttonAnswerOneEnabled", buttonAnswerOne.isEnabled());
        outState.putBoolean("buttonAnswerTwoEnabled", buttonAnswerTwo.isEnabled());
        outState.putBoolean("buttonAnswerThreeEnabled", buttonAnswerThree.isEnabled());
        outState.putBoolean("buttonAnswerFourEnabled", buttonAnswerFour.isEnabled());
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        try {
            imageQuestion.setImageBitmap(getBitmapFromByteArray(savedInstanceState.getByteArray
                    ("bytes")));
        } catch (NullPointerException e) {
            System.out.println(e + " - i  the TestsOfPDDActivity");
        }

        textQuestion.setText(savedInstanceState.getString("textQuestion"));
        textCountTrue.setText(savedInstanceState.getString("textCountTrue"));
        textCountFalse.setText(savedInstanceState.getString("textCountFalse"));
        textTimer.setText(savedInstanceState.getString("textTimer"));
        textCountAllQuestions.setText(savedInstanceState.getString("textCountAllQuestions"));

        buttonAnswerOne.setText(savedInstanceState.getString("buttonAnswerOneText"));
        buttonAnswerTwo.setText(savedInstanceState.getString("buttonAnswerTwoText"));
        buttonAnswerThree.setText(savedInstanceState.getString("buttonAnswerThreeText"));
        buttonAnswerFour.setText(savedInstanceState.getString("buttonAnswerFourText"));

        setViewVisibility(savedInstanceState, "buttonAnswerOneVisibility", buttonAnswerOne);
        setViewVisibility(savedInstanceState, "buttonAnswerTwoVisibility", buttonAnswerTwo);
        setViewVisibility(savedInstanceState, "buttonAnswerThreeVisibility", buttonAnswerThree);
        setViewVisibility(savedInstanceState, "buttonAnswerFourVisibility", buttonAnswerFour);
        setViewVisibility(savedInstanceState, "buttonPreviousVisibility", buttonPrevious);
        setViewVisibility(savedInstanceState, "buttonNextVisibility", buttonNext);

        buttonAnswer.setEnabled(savedInstanceState.getBoolean("buttonAnswerEnabled"));
        buttonAnswerOne.setEnabled(savedInstanceState.getBoolean("buttonAnswerOneEnabled"));
        buttonAnswerTwo.setEnabled(savedInstanceState.getBoolean("buttonAnswerTwoEnabled"));
        buttonAnswerThree.setEnabled(savedInstanceState.getBoolean("buttonAnswerThreeEnabled"));
        buttonAnswerFour.setEnabled(savedInstanceState.getBoolean("buttonAnswerFourEnabled"));
    }

    @Override
    public void clickOnButton(int idOfButton) {
        testsOfPDDFragment.orPreviousOrAnswerOrNext(idOfButton);
    }

    @Override
    public void buttonAnswerEnabled(boolean enableButtonAnswer) {
        buttonAnswer.setEnabled(enableButtonAnswer);
    }

    @Override
    public void buttonPreviousVisibility(int visibilityButtonPrevious) {
        buttonPrevious.setVisibility(visibilityButtonPrevious);
    }

    @Override
    public void buttonNextVisibility(int visibilityButtonNext) {
        buttonNext.setVisibility(visibilityButtonNext);
    }
}
