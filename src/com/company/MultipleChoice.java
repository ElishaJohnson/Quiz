package com.company;

import java.util.ArrayList;

public class MultipleChoice extends Question {
    private int responseInt;
    private int correctChoice;

    public MultipleChoice() { super(); }

    public MultipleChoice(String prompt) {
        super(prompt);
    }

    public MultipleChoice(String prompt, ArrayList<String> choices) {
        super(prompt, choices);
    }

    public MultipleChoice(String prompt, ArrayList<String> choices, int correctChoice) {
        super(prompt, choices);
        this.correctChoice = correctChoice;
    }

    boolean valid(String answer) {
        try {
            this.responseInt = Integer.parseInt(answer);
        } catch (Exception ignored) {
            return false;
        }
        return this.responseInt > 0 && this.responseInt <= this.getChoices().size();
    }

    public int getCorrectChoice() {
        return correctChoice;
    }

    public void setCorrectChoice(int correctChoice) {
        this.correctChoice = correctChoice;
    }

    void check() {
        this.setCorrect(this.responseInt == this.correctChoice);
    }
}
