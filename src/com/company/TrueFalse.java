package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class TrueFalse extends Question {
    boolean correctAnswer;

    public TrueFalse() { super(); }

    public TrueFalse(String prompt) {
        super(prompt);
    }

    public TrueFalse(String prompt, boolean correctAnswer) {
        super (prompt);
        this.correctAnswer = correctAnswer;
    }

    boolean isCorrectAnswer() {
        return correctAnswer;
    }

    void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    boolean valid(String answer) {
        ArrayList<String> validResponses = new ArrayList<>(Arrays.asList("t", "f", "true", "false"));
        if (validResponses.contains(answer.toLowerCase())) {
            return true;
        }
        return false;
    }

    void check() {
        boolean answer = (this.getResponse().toLowerCase().contains("t"));
        this.setCorrect(answer == correctAnswer);
    }
}
