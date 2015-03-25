package com.example.admin.pdd.async_task;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.admin.pdd.activity.ResultsActivity;
import com.example.admin.pdd.entity.TimerEntity;
import com.example.admin.pdd.pattern.Singleton;

import java.util.Timer;

public class TimerAsyncTask extends AsyncTask<Void, Void, String> {
    private Singleton singleton = Singleton.getInstance();
    private TimerEntity timerEntity = TimerEntity.getInstance();
    private String message;
    private Context context;
    private Timer timer;
    private TextView textViewTimer;

    public TimerAsyncTask(Context context, Timer timer, TextView textViewTimer) {
        this.context = context;
        this.timer = timer;
        this.textViewTimer = textViewTimer;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (timerEntity.getSeconds() == 0) timerEntity.setSeconds(60);

        timerEntity.seconds--;

        if (timerEntity.getSeconds() == 59 && singleton.isFlagMinute()) {
            timerEntity.minutes--;
            singleton.setFlagMinute(false);
        }

        singleton.setFlagMinute(true);

        if (timerEntity.getMinutes() < 10)
            message = "0" + timerEntity.getMinutes() + ":" + timerEntity.
                    getSeconds();

        if (timerEntity.getSeconds() < 10)
            message = timerEntity.getMinutes() + ":" + 0 + timerEntity.
                    getSeconds();

        if (timerEntity.getSeconds() < 10 && timerEntity.getMinutes() < 10)
            message = "0" + timerEntity.getMinutes() + ":" + 0 + timerEntity.
                    getSeconds();

        if (timerEntity.getSeconds() > 9 && timerEntity.getMinutes() > 9)
            message = timerEntity.getMinutes() + ":" + timerEntity.
                    getSeconds();

        if (timerEntity.getMinutes() == 0 && timerEntity.getSeconds() == 0) {
            timer.cancel();
            Intent intent = new Intent();
            intent.setClass(context, ResultsActivity.class);
            context.startActivity(intent);
        }
        return message;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        textViewTimer.setText(result);
    }
}
