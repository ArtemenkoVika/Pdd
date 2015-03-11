package com.example.admin.pdd.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.pdd.R;
import com.example.admin.pdd.activity.ResultsActivity;
import com.example.admin.pdd.java_classes.DataBasePdd;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Random;

public class TestsOfPDDFragment extends BaseFragment implements View.OnClickListener {
    private ImageView imageQuestion;
    private TextView textQuestion;
    private TextView textTimer;
    private Button buttonAnswerOne;
    private Button buttonAnswerTwo;
    private Button buttonAnswerThree;
    private Button buttonAnswerFour;
    private DataBasePdd dataBasePdd;
    private ArrayList<String> answers;
    private ArrayList<Integer> idQuestionsArrayList;
    private ArrayList<Integer> numberOfButtonAnswer;
    private ArrayList<String> setButtonAnswers;
    private ClickSomeButtonListener clickSomeButton;
    private int quantityOfAnswers = 5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tests_of_pdd, container, false);

        imageQuestion = (ImageView) view.findViewById(R.id.image_question);
        textQuestion = (TextView) view.findViewById(R.id.text_question);
        textTimer = (TextView) view.findViewById(R.id.text_timer);
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

        dataBasePdd = new DataBasePdd();
        dialogMessage = getActivity().getResources().getString(R.string.dialog_info_not_all_question);

        if (singleton.getIdQuestions() == null) {
            singleton.setIdQuestions(new HashSet());
            singleton.setAnswersMap(new LinkedHashMap());
            singleton.setAnswersArrayList(new ArrayList());
            singleton.setAnswerOrSkip(new LinkedHashMap());
            while (singleton.getIdQuestions().size() < quantityOfAnswers) {
                singleton.getIdQuestions().add(dataBasePdd.setQuestion(getActivity(), textQuestion,
                        imageQuestion, 0, false));
            }
        }
        numberOfButtonAnswer = new ArrayList();
        idQuestionsArrayList = new ArrayList();
        idQuestionsArrayList.addAll(singleton.getIdQuestions());
        setOfTestsSystem(idQuestionsArrayList.get(0));
        return view;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        if (singleton.getAnswerOrSkip().containsKey(idQuestionsArrayList.get(singleton.count))) {
            clickSomeButton.clickOnSomeOfButtonsAnswer(false);
        } else clickSomeButton.clickOnSomeOfButtonsAnswer(true);
        Button button = (Button) view.findViewById(view.getId());
        singleton.setTextAnswer(button.getText().toString());
        setBackgroundButton(buttonAnswerOne);
        setBackgroundButton(buttonAnswerTwo);
        setBackgroundButton(buttonAnswerThree);
        setBackgroundButton(buttonAnswerFour);
        button.setBackground(getResources().getDrawable(R.drawable.button_of_answer_pressed));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setBackgroundButton(Button button) {
        button.setBackground(getResources().getDrawable(R.drawable.button_of_answer));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        checkOfSelection();
    }

    public void orPreviousOrAnswerOrNext(int idOfButton) {
        switch (idOfButton) {

            case R.id.button_previous:
                setBackgroundButton(buttonAnswerOne);
                setBackgroundButton(buttonAnswerTwo);
                setBackgroundButton(buttonAnswerThree);
                setBackgroundButton(buttonAnswerFour);
                singleton.count--;
                if (singleton.count == -1) {
                    singleton.count++;
                    return;
                }
                clickSomeButton.clickOnSomeOfButtonsAnswer(false);
                setOfTestsSystem(idQuestionsArrayList.get(singleton.count));
                checkOfSelection();
                break;

            case R.id.button_answer:
                singleton.getAnswerOrSkip().put(idQuestionsArrayList.get(singleton.count),
                        singleton.getTextAnswer());
                nextQuestion();
                break;

            case R.id.button_next:
                checkOfSelection();
                nextQuestion();
                break;

            default:
                break;
        }
    }

    public void checkOfSelection(){
        if (singleton.getAnswerOrSkip().containsKey(idQuestionsArrayList.get(singleton.count))) {
            String textOfButton = singleton.getAnswerOrSkip().get(idQuestionsArrayList.
                    get(singleton.count));
            buttonEqualsWithTrueAnswer(buttonAnswerOne, textOfButton);
            buttonEqualsWithTrueAnswer(buttonAnswerTwo, textOfButton);
            buttonEqualsWithTrueAnswer(buttonAnswerThree, textOfButton);
            buttonEqualsWithTrueAnswer(buttonAnswerFour, textOfButton);
        } else clickSomeButton.clickOnSomeOfButtonsAnswer(true);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void buttonEqualsWithTrueAnswer(Button button, String textOfButton){
        if (button.getText().equals(textOfButton)) button.setBackground(getResources().
                getDrawable(R.drawable.button_of_answer_pressed));
    }

    public void setButtonAnswer(Button button, int idQuestion) {
        Random random = new Random();
        int numberOfButton = random.nextInt(answers.size());
        for (int i = 0; i < answers.size(); i++) {
            if (button.getTag().equals(i)) {
                while (true) {
                    if (numberOfButtonAnswer.contains(numberOfButton))
                        numberOfButton = random.nextInt(answers.size());
                    else break;
                }
                singleton.getAnswersArrayList().add(answers.get(numberOfButton));
                button.setText(answers.get(numberOfButton));
                numberOfButtonAnswer.add(numberOfButton);
            }
        }
        singleton.getAnswersMap().put(idQuestion, singleton.getAnswersArrayList());
    }

    public void setOfTestsSystem(int idQuestion) {
        dataBasePdd.setQuestion(getActivity(), textQuestion, imageQuestion, idQuestion, true);
        answers = dataBasePdd.setAnswers(getActivity(), idQuestion);

        buttonAnswerOne.setText("answer");
        buttonAnswerTwo.setText("answer");
        buttonAnswerThree.setText("answer");
        buttonAnswerFour.setText("answer");
        try {
            setButtonAnswers = singleton.getAnswersMap().get(idQuestion);
        } catch (Exception e) {
        }
        if (!singleton.getAnswersMap().isEmpty() && setButtonAnswers != null) {
            try {
                buttonAnswerOne.setText(setButtonAnswers.get(0));
                buttonAnswerTwo.setText(setButtonAnswers.get(1));
                buttonAnswerThree.setText(setButtonAnswers.get(2));
                buttonAnswerFour.setText(setButtonAnswers.get(3));
            } catch (Exception e) {
            }
        } else {
            setButtonAnswer(buttonAnswerOne, idQuestion);
            setButtonAnswer(buttonAnswerTwo, idQuestion);
            setButtonAnswer(buttonAnswerThree, idQuestion);
            setButtonAnswer(buttonAnswerFour, idQuestion);

            numberOfButtonAnswer.clear();
            singleton.setAnswersArrayList(new ArrayList());

        }
        setButtonVisibility(buttonAnswerOne);
        setButtonVisibility(buttonAnswerTwo);
        setButtonVisibility(buttonAnswerThree);
        setButtonVisibility(buttonAnswerFour);
    }

    public void setButtonVisibility(Button button) {
        if (button.getText().equals("answer")) button.setVisibility(View.GONE);
        else button.setVisibility(View.VISIBLE);
    }

    public void nextQuestion() {
        setBackgroundButton(buttonAnswerOne);
        setBackgroundButton(buttonAnswerTwo);
        setBackgroundButton(buttonAnswerThree);
        setBackgroundButton(buttonAnswerFour);
        singleton.count++;
        if (singleton.count == quantityOfAnswers && singleton.getAnswerOrSkip().size() ==
                quantityOfAnswers) {
            singleton.count = 0;
            Intent intent = new Intent();
            intent.setClass(getActivity(), ResultsActivity.class);
            getActivity().startActivity(intent);
            return;
        }
        if (singleton.count == quantityOfAnswers && singleton.getAnswerOrSkip().size() !=
                quantityOfAnswers) {
            showDialogInfo(dialogTitle, dialogMessage);
            singleton.count--;
            return;
        }
        clickSomeButton.clickOnSomeOfButtonsAnswer(false);
        setOfTestsSystem(idQuestionsArrayList.get(singleton.count));
    }

    public interface ClickSomeButtonListener {
        public void clickOnSomeOfButtonsAnswer(Boolean enableButtonAnswer);
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
