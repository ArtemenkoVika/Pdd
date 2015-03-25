package com.example.admin.pdd.entity;

public class TimerEntity {
    public static final TimerEntity instance = new TimerEntity();
    public int minutes;
    public int seconds;

    private TimerEntity() {
    }

    public static final TimerEntity getInstance() {
        return instance;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
