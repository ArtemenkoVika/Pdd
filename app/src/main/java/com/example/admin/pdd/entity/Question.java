package com.example.admin.pdd.entity;

import java.util.ArrayList;

public class Question {
    public static final Question instance = new Question();
    private String textAnswer;
    private int idPressedButton;
    private ArrayList<String> answers;
    private ArrayList<Integer> numberOfButtonAnswer;

    private Question() {
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public static final Question getInstance() {
        return instance;
    }

    public int getIdPressedButton() {
        return idPressedButton;
    }

    public void setIdPressedButton(int idPressedButton) {
        this.idPressedButton = idPressedButton;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }

    public ArrayList<Integer> getNumberOfButtonAnswer() {
        return numberOfButtonAnswer;
    }

    public void setNumberOfButtonAnswer(ArrayList<Integer> numberOfButtonAnswer) {
        this.numberOfButtonAnswer = numberOfButtonAnswer;
    }
}
