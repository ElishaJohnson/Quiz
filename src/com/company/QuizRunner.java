package com.company;

import java.util.Scanner;

public class QuizRunner {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Quiz myQuiz = new Quiz();
        char another;

        do {
            myQuiz.addQuestion();
            another = getYN("Add another?");
        } while (another == 'y');

        char takeAgain;
        do {
            for (int i = 0; i < 40; i ++) { System.out.println(); }
            myQuiz.takeQuiz();
            myQuiz.printScore();
            takeAgain = getYN("Take quiz again?");
        } while (takeAgain == 'y');
    }

    static char getYN(String prompt) {
        Scanner input = new Scanner(System.in);
        String yn;
        do {
            System.out.print(prompt + " (Y/N): ");
            yn = input.nextLine().toLowerCase();
        } while (!yn.equals("y") && !yn.equals("n") && !yn.equals("yes") && !yn.equals("no"));
        return yn.charAt(0);
    }
}
