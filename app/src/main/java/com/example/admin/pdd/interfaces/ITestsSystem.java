package com.example.admin.pdd.interfaces;

import android.widget.Button;

public interface ITestsSystem {
    public boolean nextQuestion();

    public void setOfTestsSystem(int idQuestion);

    public void checkOfSelection();

    public void buttonPrevious();

    public void buttonAnswer();

    public void buttonNext();

    public void buttonEqualsWithPressed(Button button, String textOfButton);

    public void setButtonAnswer(Button button, int idQuestion);

    public void setButtonVisibility(Button button);

    public void setDefaultBackgroundButtonsAnswer();

    public void setEnabledButtonsAnswer(boolean flag);
}
