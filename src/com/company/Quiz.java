package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {
    Scanner input = new Scanner(System.in);
    ArrayList<Question> questions = new ArrayList<>();

    public Quiz() {}

    public Quiz(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    void takeQuiz() {
        for (int i = 0; i < this.getQuestions().size(); i++) {
            Question thisQuestion = this.getQuestions().get(i);
            thisQuestion.ask();
            if (thisQuestion.isCorrect()) {
                System.out.println("CORRECT\n");
            } else {
                System.out.println("INCORRECT\n");
            }
        }
    }

    void printScore() {
        int totalCorrect = 0;
        int numberOfQuestions = this.getQuestions().size();
        int percentCorrect;
        for (int i = 0; i < numberOfQuestions; i++) {
            if (this.getQuestions().get(i).isCorrect()) totalCorrect++;
        }
        percentCorrect = (int) (((float) totalCorrect / numberOfQuestions) * 100);
        System.out.println("TOTAL SCORE: " + percentCorrect + "% (" + totalCorrect + " out of " + numberOfQuestions + ")");
    }

    void addQuestion() {
        int selection = 0;
        int questionIndex = questions.size();
        System.out.println("\nPlease select question type:\n1) True/False\n2) Multiple Choice\n3) Check Box");
        do {
            try {
                System.out.print("Choose one: ");
                selection = Integer.parseInt(input.nextLine());
            } catch (Exception ignored) {}
        } while (selection <= 0 || selection > 3);
        if (selection == 1) {
            questions.add(new TrueFalse());
        } else if (selection == 2) {
            questions.add(new MultipleChoice());
        } else {
            questions.add(new CheckBox());
        }
        Question newQuestion = questions.get(questionIndex);
        do {
            try {
                System.out.print("Enter question or statement: ");
                newQuestion.setPrompt(input.nextLine());
            } catch (Exception ignored) {
                newQuestion.setPrompt("");
            }
        } while (newQuestion.getPrompt().length() <= 0);
        if(!(newQuestion instanceof TrueFalse)) {
            System.out.println("Enter choices or \"done\" when finished:");
            int i = 1;
            String choice;
            ArrayList<String> choiceList = new ArrayList<>();
            do {
                choice = "";
                System.out.print(i + ") ");
                try {
                    choice = input.nextLine();
                    if (choice.length() > 0 && !choice.toLowerCase().equals("done")) {
                        choiceList.add(choice);
                        i++;
                    }
                } catch (Exception ignored) {}
            } while (!choice.toLowerCase().equals("done"));
            newQuestion.setChoices(choiceList);
        }
        String answer = "";
        do {
            try {
                if (newQuestion instanceof TrueFalse) System.out.print("Enter correct answer (T/F): ");
                if (newQuestion instanceof MultipleChoice) System.out.print("Enter number of correct choice: ");
                if (newQuestion instanceof CheckBox) System.out.print("Enter numbers of all correct choices separated by spaces: ");
                answer = input.nextLine();
            } catch (Exception ignored) {}
        } while (!newQuestion.valid(answer));
        if (newQuestion instanceof TrueFalse) {
            ((TrueFalse) newQuestion).setCorrectAnswer(answer.toLowerCase().contains("t"));
        } else if (newQuestion instanceof MultipleChoice) {
            ((MultipleChoice) newQuestion).setCorrectChoice(Integer.parseInt(answer));
        } else {
            String[] answers = answer.split(" ");
            ArrayList<Integer> correctAnswers = new ArrayList<>();
            for (int i = 0; i < answers.length; i++) {
                correctAnswers.add(Integer.parseInt(answers[i]));
            }
            ((CheckBox) newQuestion).setCorrectChoices(correctAnswers);
        }
    }
}
