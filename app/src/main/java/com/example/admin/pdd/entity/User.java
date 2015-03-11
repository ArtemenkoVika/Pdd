package com.example.admin.pdd.entity;

import java.util.ArrayList;

public class User {
    public static final User instance = new User();
    private String loginUserName = "";
    private ArrayList<Integer> trueAnswers;
    private ArrayList<Integer> falseAnswers;

    private User(){
    }

    public static final User getInstance() {
        return instance;
    }

    public ArrayList<Integer> getTrueAnswers() {
        return trueAnswers;
    }

    public void setTrueAnswers(ArrayList<Integer> trueAnswers) {
        this.trueAnswers = trueAnswers;
    }

    public ArrayList<Integer> getFalseAnswers() {
        return falseAnswers;
    }

    public void setFalseAnswers(ArrayList<Integer> falseAnswers) {
        this.falseAnswers = falseAnswers;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }
}
