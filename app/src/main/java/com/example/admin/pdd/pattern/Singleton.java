package com.example.admin.pdd.pattern;

import java.util.ArrayList;

public class Singleton {
    public static final Singleton instance = new Singleton();
    public int count;
    private boolean showDialog = false;
    private boolean end = false;
    private boolean flagMinute = false;
    private boolean timerRotation = false;
    private ArrayList<String> answersForOneQuestionOrder;

    private Singleton() {
    }

    public boolean isTimerRotation() {
        return timerRotation;
    }

    public void setTimerRotation(boolean timerRotation) {
        this.timerRotation = timerRotation;
    }

    public boolean isFlagMinute() {
        return flagMinute;
    }

    public void setFlagMinute(boolean flagMinute) {
        this.flagMinute = flagMinute;
    }

    public static final Singleton getInstance() {
        return instance;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public ArrayList<String> getAnswersForOneQuestionOrder() {
        return answersForOneQuestionOrder;
    }

    public void setAnswersForOneQuestionOrder(ArrayList<String> answersForOneQuestionOrder) {
        this.answersForOneQuestionOrder = answersForOneQuestionOrder;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
