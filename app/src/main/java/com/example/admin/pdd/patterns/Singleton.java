package com.example.admin.pdd.patterns;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class Singleton {
    public static final Singleton instance = new Singleton();
    public int count;
    private boolean showDialog;
    private HashSet<Integer> idQuestions;
    private LinkedHashMap<Integer, ArrayList> answersMap;
    private ArrayList<String> answersArrayList;
    private LinkedHashMap<Integer, String> answerOrSkip;
    private String textAnswer;

    private Singleton() {
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }

    public LinkedHashMap<Integer, String> getAnswerOrSkip() {
        return answerOrSkip;
    }

    public void setAnswerOrSkip(LinkedHashMap<Integer, String> answerOrSkip) {
        this.answerOrSkip = answerOrSkip;
    }

    public static final Singleton getInstance() {
        return instance;
    }

    public ArrayList<String> getAnswersArrayList() {
        return answersArrayList;
    }

    public void setAnswersArrayList(ArrayList<String> answersArrayList) {
        this.answersArrayList = answersArrayList;
    }

    public LinkedHashMap<Integer, ArrayList> getAnswersMap() {
        return answersMap;
    }

    public void setAnswersMap(LinkedHashMap<Integer, ArrayList> answersMap) {
        this.answersMap = answersMap;
    }

    public void setIdQuestions(HashSet<Integer> idQuestions) {
        this.idQuestions = idQuestions;
    }

    public HashSet<Integer> getIdQuestions() {
        return idQuestions;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
