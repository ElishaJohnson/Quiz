package com.company;

import java.util.ArrayList;
import java.util.Scanner;

abstract class Question {
    Scanner input = new Scanner(System.in);
    private String prompt;
    private ArrayList<String> choices;
    private String response;
    private boolean correct = false;

    Question() {
        this.prompt = "Question Missing";
        this.correct = true;
    }

    Question(String prompt) {
        this.prompt = prompt;
    }

    Question(String prompt, ArrayList<String> choices) {
        this(prompt);
        this.choices = choices;
    }

    String getPrompt() {
        return prompt;
    }

    void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    ArrayList<String> getChoices() {
        return choices;
    }

    void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    String getResponse() { return response; }

    boolean isCorrect() {
        return correct;
    }

    void setCorrect(boolean correct) {
        this.correct = correct;
    }

    abstract boolean valid(String answer);
    abstract void check();

    void ask() {
        System.out.print(this.prompt);
        if (this.prompt.equals("Question Missing")) return;
        if (this instanceof TrueFalse) {
            System.out.print(" ");
        } else if (this.choices == null || this.choices.isEmpty()) {
            System.out.println("Choices Missing");
            this.correct = true;
            return;
        } else {
            for (int i = 0; i < this.choices.size(); i++) {
                System.out.print("\n" + (i + 1) + ") " + this.choices.get(i));
            }
            System.out.println();
        }
        do {
            if (this instanceof CheckBox) {
                System.out.print("Enter the numbers of all that apply, separated by spaces: ");
            } else if (this instanceof MultipleChoice){
                System.out.print("Choose one (1-" + this.getChoices().size() + "): ");
            } else {
                System.out.print("(T/F): ");
            }
            this.response = input.nextLine();
        } while (!valid(this.getResponse()));
        this.check();
    }
}
