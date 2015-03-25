package com.example.admin.pdd.module;

import android.content.Context;
import android.widget.TextView;

import com.example.admin.pdd.async_task.TimerAsyncTask;
import com.example.admin.pdd.entity.TimerEntity;
import com.example.admin.pdd.fragment.TestsOfPDDFragment;
import com.example.admin.pdd.pattern.Singleton;

import java.util.Timer;
import java.util.TimerTask;

public class TimerModule {
    private Timer timer = new Timer();
    private TaskLimitTime taskLimitTime = new TaskLimitTime();
    private Singleton singleton = Singleton.getInstance();
    private Context context;
    private TextView textViewTimer;
    private TimerEntity timerEntity = TimerEntity.getInstance();
    private TimerAsyncTask timerAsyncTask;

    public TimerModule(Context context, TextView textViewTimer) {
        this.context = context;
        this.textViewTimer = textViewTimer;
        setMinutesAndSeconds();
        singleton.setFlagMinute(false);
        timer.schedule(taskLimitTime, 2500, 1000);
    }

    public Timer getTimer() {
        return timer;
    }

    public void setMinutesAndSeconds() {
        if (!singleton.isTimerRotation()) {
            timerEntity.setMinutes(TestsOfPDDFragment.QUANTITY_OF_QUESTIONS - 1);
            timerEntity.setSeconds(0);
        }
    }

    class TaskLimitTime extends TimerTask {

        @Override
        public void run() {
            singleton.setTimerRotation(true);
            timerAsyncTask = new TimerAsyncTask(context, timer, textViewTimer);
            timerAsyncTask.execute();
        }
    }
}
