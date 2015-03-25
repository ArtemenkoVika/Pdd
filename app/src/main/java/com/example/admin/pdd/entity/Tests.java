package com.example.admin.pdd.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Tests {
    public static final Tests instance = new Tests();
    private ArrayList<Integer> idQuestions;
    private LinkedHashMap<Integer, String> idQuestionAndOneAnswer;
    private ArrayList<Integer> answerNotSkipInOrder;
    private LinkedHashMap<Integer, ArrayList> idQuestionAndAnswersButtonsInOrder;

    private Tests() {
    }

    public static final Tests getInstance() {
        return instance;
    }

    public LinkedHashMap<Integer, String> getIdQuestionAndOneAnswer() {
        return idQuestionAndOneAnswer;
    }

    public void setIdQuestionAndOneAnswer(LinkedHashMap<Integer, String> idQuestionAndOneAnswer) {
        this.idQuestionAndOneAnswer = idQuestionAndOneAnswer;
    }

    public void setIdQuestions(ArrayList<Integer> idQuestions) {
        this.idQuestions = idQuestions;
    }

    public ArrayList<Integer> getIdQuestions() {
        return idQuestions;
    }

    public ArrayList<Integer> getAnswerNotSkipInOrder() {
        return answerNotSkipInOrder;
    }

    public void setAnswerNotSkipInOrder(ArrayList<Integer> answerNotSkipInOrder) {
        this.answerNotSkipInOrder = answerNotSkipInOrder;
    }

    public LinkedHashMap<Integer, ArrayList> getIdQuestionAndAnswersButtonsInOrder() {
        return idQuestionAndAnswersButtonsInOrder;
    }

    public void setIdQuestionAndAnswersButtonsInOrder(LinkedHashMap<Integer, ArrayList> idQuestionAndAnswersButtonsInOrder) {
        this.idQuestionAndAnswersButtonsInOrder = idQuestionAndAnswersButtonsInOrder;
    }
}
