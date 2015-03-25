package com.example.admin.pdd.module;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.pdd.R;
import com.example.admin.pdd.activity.ResultsActivity;
import com.example.admin.pdd.entity.Question;
import com.example.admin.pdd.entity.Tests;
import com.example.admin.pdd.fragment.TestsOfPDDFragment;
import com.example.admin.pdd.fragment.TestsOfPDDFragment.ClickSomeButtonListener;
import com.example.admin.pdd.interfaces.ITestsSystem;
import com.example.admin.pdd.pattern.Singleton;

import java.util.ArrayList;
import java.util.Random;

public class TestsSystem implements ITestsSystem {
    private Singleton singleton = Singleton.getInstance();
    private DataBasePdd dataBasePdd = new DataBasePdd();
    private Question question = Question.getInstance();
    private Tests tests = Tests.getInstance();
    private TimerModule timerModule;
    private ClickSomeButtonListener clickSomeButton;
    private Context context;
    private Button buttonAnswerOne;
    private Button buttonAnswerTwo;
    private Button buttonAnswerThree;
    private Button buttonAnswerFour;
    private ImageView imageQuestion;
    private TextView textQuestion;

    public TestsSystem(ClickSomeButtonListener clickSomeButton, Context context, Button
            buttonAnswerOne, Button buttonAnswerTwo, Button buttonAnswerThree, Button
                               buttonAnswerFour, ImageView imageQuestion, TextView textQuestion,
                       TimerModule timerModule) {
        this.clickSomeButton = clickSomeButton;
        this.context = context;
        this.buttonAnswerOne = buttonAnswerOne;
        this.buttonAnswerTwo = buttonAnswerTwo;
        this.buttonAnswerThree = buttonAnswerThree;
        this.buttonAnswerFour = buttonAnswerFour;
        this.imageQuestion = imageQuestion;
        this.textQuestion = textQuestion;
        this.timerModule = timerModule;
    }

    @Override
    public boolean nextQuestion() {
        singleton.count++;
        if ((tests.getIdQuestionAndOneAnswer().size() == TestsOfPDDFragment.QUANTITY_OF_QUESTIONS &&
                !singleton.isEnd() || (singleton.count == TestsOfPDDFragment.QUANTITY_OF_QUESTIONS &&
                singleton.isEnd()))) {
            singleton.count--;
            if (timerModule.getTimer() != null) timerModule.getTimer().cancel();
            Intent intent = new Intent();
            intent.setClass(context, ResultsActivity.class);
            context.startActivity(intent);
            clickSomeButton.buttonAnswerEnabled(false);
            checkOfSelection();
            return true;
        }
        clickSomeButton.buttonPreviousVisibility(View.VISIBLE);
        clickSomeButton.buttonNextVisibility(View.VISIBLE);
        if (singleton.count == TestsOfPDDFragment.QUANTITY_OF_QUESTIONS - 1)
            clickSomeButton.buttonNextVisibility(View.INVISIBLE);
        else clickSomeButton.buttonNextVisibility(View.VISIBLE);
        return false;
    }

    @Override
    public void setOfTestsSystem(int idQuestion) {
        ArrayList<String> textForButtonsAnswer = new ArrayList();
        dataBasePdd.setQuestion(context, textQuestion, imageQuestion, idQuestion, true);
        question.setAnswers(dataBasePdd.getAnswers(context, idQuestion));
        buttonAnswerOne.setText("answer");
        buttonAnswerTwo.setText("answer");
        buttonAnswerThree.setText("answer");
        buttonAnswerFour.setText("answer");
        try {
            textForButtonsAnswer = tests.getIdQuestionAndAnswersButtonsInOrder().get(idQuestion);
        } catch (Exception e) {
        }
        if (!tests.getIdQuestionAndAnswersButtonsInOrder().isEmpty() && textForButtonsAnswer != null) {
            try {
                buttonAnswerOne.setText(textForButtonsAnswer.get(0));
                buttonAnswerTwo.setText(textForButtonsAnswer.get(1));
                buttonAnswerThree.setText(textForButtonsAnswer.get(2));
                buttonAnswerFour.setText(textForButtonsAnswer.get(3));
            } catch (Exception e) {
            }
        } else {
            setButtonAnswer(buttonAnswerOne, idQuestion);
            setButtonAnswer(buttonAnswerTwo, idQuestion);
            setButtonAnswer(buttonAnswerThree, idQuestion);
            setButtonAnswer(buttonAnswerFour, idQuestion);
            question.getNumberOfButtonAnswer().clear();
            singleton.setAnswersForOneQuestionOrder(new ArrayList());
        }
        setButtonVisibility(buttonAnswerOne);
        setButtonVisibility(buttonAnswerTwo);
        setButtonVisibility(buttonAnswerThree);
        setButtonVisibility(buttonAnswerFour);
    }

    @Override
    public void checkOfSelection() {
        if (tests.getIdQuestionAndOneAnswer().containsKey(tests.getIdQuestions().get(singleton.count))) {
            String textOfButton = tests.getIdQuestionAndOneAnswer().get(tests.getIdQuestions().
                    get(singleton.count));
            setEnabledButtonsAnswer(false);
            buttonEqualsWithPressed(buttonAnswerOne, textOfButton);
            buttonEqualsWithPressed(buttonAnswerTwo, textOfButton);
            buttonEqualsWithPressed(buttonAnswerThree, textOfButton);
            buttonEqualsWithPressed(buttonAnswerFour, textOfButton);
        } else {
            setEnabledButtonsAnswer(true);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void buttonEqualsWithPressed(Button button, String textOfButton) {
        if (button.getText().equals(textOfButton)) {
            button.setBackground(context.getResources().getDrawable(R.drawable.
                    button_of_answer_pressed));
            button.setEnabled(true);
        }
    }

    @Override
    public void buttonPrevious() {
        clickSomeButton.buttonPreviousVisibility(View.VISIBLE);
        clickSomeButton.buttonNextVisibility(View.VISIBLE);
        singleton.count--;
        if (singleton.count == -1) {
            singleton.count++;
            checkOfSelection();
            clickSomeButton.buttonPreviousVisibility(View.INVISIBLE);
            return;
        }
        if (singleton.count == 0) clickSomeButton.buttonPreviousVisibility(View.INVISIBLE);
        clickSomeButton.buttonAnswerEnabled(false);
        setOfTestsSystem(tests.getIdQuestions().get(singleton.count));
        setDefaultBackgroundButtonsAnswer();
        checkOfSelection();
    }

    @Override
    public void buttonAnswer() {
        tests.getIdQuestionAndOneAnswer().put(tests.getIdQuestions().get(singleton.count),
                question.getTextAnswer());
        tests.getAnswerNotSkipInOrder().add(singleton.count);
        boolean end = nextQuestion();
        if (end) return;
        setDefaultBackgroundButtonsAnswer();
        clickSomeButton.buttonAnswerEnabled(false);
        if (singleton.count == TestsOfPDDFragment.QUANTITY_OF_QUESTIONS) {
            singleton.count = 0;
        }
        if (tests.getAnswerNotSkipInOrder().contains(singleton.count)) {
            do {
                singleton.count++;
                if (singleton.count == TestsOfPDDFragment.QUANTITY_OF_QUESTIONS) {
                    singleton.count = 0;
                }
            } while (tests.getAnswerNotSkipInOrder().contains(singleton.count));
            if (singleton.count != TestsOfPDDFragment.QUANTITY_OF_QUESTIONS - 1)
                clickSomeButton.buttonNextVisibility(View.VISIBLE);
        }
        if (singleton.count == 0) clickSomeButton.buttonPreviousVisibility(View.INVISIBLE);
        else clickSomeButton.buttonPreviousVisibility(View.VISIBLE);
        setOfTestsSystem(tests.getIdQuestions().get(singleton.count));
        checkOfSelection();
    }

    @Override
    public void buttonNext() {
        nextQuestion();
        if (singleton.count == TestsOfPDDFragment.QUANTITY_OF_QUESTIONS &&
                tests.getIdQuestionAndOneAnswer().size() != TestsOfPDDFragment.QUANTITY_OF_QUESTIONS) {
            singleton.count--;
            checkOfSelection();
            clickSomeButton.buttonNextVisibility(View.INVISIBLE);
            return;
        }
        setOfTestsSystem(tests.getIdQuestions().get(singleton.count));
        setDefaultBackgroundButtonsAnswer();
        clickSomeButton.buttonAnswerEnabled(false);
        checkOfSelection();
    }

    @Override
    public void setButtonAnswer(Button button, int idQuestion) {
        Random random = new Random();
        int numberOfButton = random.nextInt(question.getAnswers().size());
        for (int i = 0; i < question.getAnswers().size(); i++) {
            if (button.getTag().equals(i)) {
                while (true) {
                    if (question.getNumberOfButtonAnswer().contains(numberOfButton))
                        numberOfButton = random.nextInt(question.getAnswers().size());
                    else break;
                }
                singleton.getAnswersForOneQuestionOrder().add(question.getAnswers().get(numberOfButton));
                button.setText(question.getAnswers().get(numberOfButton));
                question.getNumberOfButtonAnswer().add(numberOfButton);
            }
        }
        tests.getIdQuestionAndAnswersButtonsInOrder().put(idQuestion, singleton.getAnswersForOneQuestionOrder());
    }

    @Override
    public void setButtonVisibility(Button button) {
        if (button.getText().equals("answer")) button.setVisibility(View.GONE);
        else button.setVisibility(View.VISIBLE);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void setDefaultBackgroundButtonsAnswer() {
        buttonAnswerOne.setBackground(context.getResources().getDrawable(R.drawable.button_of_answer));
        buttonAnswerTwo.setBackground(context.getResources().getDrawable(R.drawable.button_of_answer));
        buttonAnswerThree.setBackground(context.getResources().getDrawable(R.drawable.button_of_answer));
        buttonAnswerFour.setBackground(context.getResources().getDrawable(R.drawable.button_of_answer));
    }

    @Override
    public void setEnabledButtonsAnswer(boolean flag) {
        buttonAnswerOne.setEnabled(flag);
        buttonAnswerTwo.setEnabled(flag);
        buttonAnswerThree.setEnabled(flag);
        buttonAnswerFour.setEnabled(flag);
    }
}
