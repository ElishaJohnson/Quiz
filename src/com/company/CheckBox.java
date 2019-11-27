package com.company;

import java.util.ArrayList;

public class CheckBox extends Question {
    private ArrayList<Integer> correctChoices = new ArrayList<>();
    private ArrayList<Integer> responseInts = new ArrayList<>();

    public CheckBox() { super(); }

    public CheckBox(String prompt) {
        super(prompt);
    }

    public CheckBox(String prompt, ArrayList<String> choices) { super(prompt, choices); }

    public CheckBox(String prompt, ArrayList<String> choices, ArrayList<Integer> correctChoices) {
        super(prompt, choices);
        this.correctChoices = correctChoices;
    }

    ArrayList<Integer> getCorrectChoices() {
        return correctChoices;
    }

    void setCorrectChoices(ArrayList<Integer> correctChoices) {
        this.correctChoices = correctChoices;
    }

    boolean valid(String answer) {
        try {
            this.responseInts.clear();
            String[] responses = answer.split(" ");
            for (int i = 0; i < responses.length; i++) {
                this.responseInts.add(Integer.parseInt(responses[i]));
            }
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }

    void check() {
        if (responseInts.size() != correctChoices.size()) {
            this.setCorrect(false);
            return;
        }
        for (int i = 0; i < responseInts.size(); i++) {
            if (!correctChoices.contains(responseInts.get(i)) || !responseInts.contains(correctChoices.get(i))) {
                this.setCorrect(false);
                return;
            }
        }
        this.setCorrect(true);
    }
}
