package com.example.project;


import java.io.*;
import java.util.Scanner;

import static com.example.project.HandleFile.appendStrToFile;

public class Tema1 {
    public static void main(final String[] args) {

        if (args == null) {
            System.out.println("Hello world!");
        } else {

            String command = args[0];
            String case1 = new String("-create-user");
            String case2 = new String("-cleanup-all");
            String case3 = new String("-create-question");
            String case4 = new String("-get-question-id-by-text");
            String case5 = new String("-get-all-questions");
            String case6 = new String("-create-quizz");
            String case7 = new String("-get-quizz-by-name");
            String case8 = new String("-get-all-quizzes");
            String case9 = new String("-get-quizz-details-by-id");
            String case10 = new String("-submit-quizz");
            String case11 = new String("-delete-quizz-by-id");
            String case12 = new String("-get-my-solutions");

            if (command.equals(case1)) {
                User.createUser(args);
            }
            if (command.equals(case2)) {
                HandleFile.clearTheUsersFile();
                HandleFile.clearTheTextFile();
                HandleFile.clearTheIDFile();
                HandleFile.clearTheNamesFile();
                HandleFile.clearTheQuizIDFile();
            }
            if (command.equals(case3)) {
                Question.createQuestion(args);
            }
            if (command.equals(case4)) {
                Question.getQuestionID(args);
            }
            if (command.equals(case5)) {
                Question.getAllq(args);
            }
            if (command.equals(case6)) {
                Quiz.createQuiz(args);
            }
            if (command.equals(case7)) {
                Quiz.getQuizID(args);
            }
            if (command.equals(case8)) {
                Quiz.getAllQuizes(args);
            }
            if (command.equals(case9)) {
                Quiz.getQuizDetails(args);
            }
            if (command.equals(case10)) {
                Quiz.submit(args);
            }
            if (command.equals(case11)) {
                Quiz.delete(args);
            }
            if (command.equals(case12)) {
                Quiz.getSol(args);
            }


        }
    }
}







