package com.example.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Question {
    String text;
    String type; //single or multiple
    String[] answers;

    public Question(String type, String text) {
        this.type = type;
        // this.answers = answers;
        this.text = text;
        // this.indicators = indicators;
    }

    @Override
    public String toString() {
        return text + ',' + type; //+ ',' + Arrays.toString(answers) +',' + Arrays.toString(indicators);
    }

    public static int textAlreadyExists(String qtext) {
        int ok = 0;
        File file = new File("questiontext.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (qtext.equals(line)) {
                    ok++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("exception occurred" + e);
        }
        return ok;
    }

    public static String findid(String qtext) {
        String idx = new String();
        File file = new File("questionid.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ,");

                if (qtext.equals(line[0])) {
                    idx = line[1];
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("exception occurred" + e);
        }
        return idx;
    }

    public static void getQuestionID(String[] args) {
        if (args.length == 1 || args.length == 2) {
            System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
        } else {
            String userData = User.getUserData(args);
            int ok = User.userAlreadyExists(userData);
            if (ok == 0) {
                System.out.println("{ 'status' : 'error', 'message' : 'Login failed'}");
            }
            if (ok != 0) {
                String[] split = args[3].split("'");
                int sameText = Question.textAlreadyExists(split[1]);
                if (sameText == 0)
                    System.out.println("{ 'status' : 'error', 'message' : 'Question does not exist'}");
                if (sameText != 0) {
                    String idx = findid(split[1]);
                    System.out.println("{ 'status' : 'ok', 'message' : '" + idx + "'}");
                }

            }
        }

    }

    public static int countLines(String fileName) {
        int k = 0;
        File file = new File("questiontext.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                k++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("exception occurred" + e);
        }
        return k;

    }

    public static void createQuestion(String[] args) {
        if (args.length == 1 || args.length == 2) {
            System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
        } else if (args.length == 5) {
            System.out.println("{ 'status' : 'error', 'message' : 'No answer provided'}");
        } else if (args.length == 7) {
            System.out.println("{ 'status' : 'error', 'message' : 'Only one answer provided'}");
        } else if (args.length > 15) {
            System.out.println("More than 5 answers were submitted");
        } else {
            String userData = User.getUserData(args);
            int ok = User.userAlreadyExists(userData);
            if (ok == 0) {
                System.out.println("{ 'status' : 'error', 'message' : 'Login failed'}");
            }
            if (ok != 0) {
                String[] split = args[3].split("'");
                String textIndicator = split[0];
                String text = split[1];
                int sameText = Question.textAlreadyExists(text);
                if (sameText != 0) {
                    System.out.println("{ 'status' : 'error', 'message' : 'Question already exists'}");
                }
                //append the question
                if (sameText == 0) {
                    String[] split5 = args[5].split(" ");
                    String ans1 = split5[1];
                    String[] split7 = args[7].split(" ");
                    String ans2 = split7[1];
                    String txt = new String("-text ");
                    String[] split6 = args[6].split(" ");
                    String correct1 = split6[0];
                    String firstCorrect = split6[1];

                    if (args.length == 8) {

                        System.out.println("{ 'status' : 'error', 'message' : 'No question text provided'}");
                    }

                    if (args.length == 9) {

                        String[] split8 = args[8].split(" ");
                        String correct2 = split8[0];
                        String secondCorrect = split8[1];
                        if (correct1.equals("-answer-1-is-correct") && correct2.equals("-answer-2-is-correct")) {
                            if (ans1.equals(ans2)) {
                                System.out.println("{ 'status' : 'error', 'message' : 'Same answer provided more than once'}");
                            } else {
                                if (firstCorrect.equals(secondCorrect)) {
                                    System.out.println("{ 'status' : 'error', 'message' : 'Single correct answer question has more than one correct answer'}");
                                } else {
                                    int qID = countLines("questiontext.txt") + 1;
                                    HandleFile.appendStrToFile("questiontext.txt", text + "\n");
                                    //citesc cate randuri sunt si incrementez id ul inainte sa append
                                    HandleFile.appendStrToFile("questionid.txt", text + " " + "," + qID + "\n");
                                    System.out.println("{ 'status' : 'ok', 'message' : 'Question added successfully'}");

                                }
                            }
                        }

                    }
                }
            }


        }
    }

    public static void getAllq(String[] args) {
        if (args.length == 1 || args.length == 2) {
            System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
        } else {
            String userData = User.getUserData(args);
            int ok = User.userAlreadyExists(userData);
            if (ok == 0) {
                System.out.println("{ 'status' : 'error', 'message' : 'Login failed'}");
            }
            if (ok != 0) {
                String[] ids = new String[0];
                //get q info
                String idx = new String();
                String q = new String();
                File file = new File("questionid.txt");
                try {
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {
                        String[] line = scanner.nextLine().split(" ,");
                        q = line[0];
                        idx = line[1];
                        String element = "{question_id : " + idx + " ," + "question_name : " + q;

                        List<String> l = new ArrayList<String>(
                                Arrays.asList(ids)); // Convert Array to ArrayList
                        l.add("{" + '"' + "question_id" + '"' + " : " + '"' + idx + '"');
                        l.add('"' + "question_name" + '"' + " : " + '"' + q + '"' + "}");// Add new element in ArrayList l
                        ids = l.toArray(ids); // Revert Conversion from ArrayList to Array
                        // printing the new Array
                        // String out =

                    }
                } catch (FileNotFoundException e) {
                    System.out.println("exception occurred" + e);
                }
                System.out.println("{ 'status' : 'ok', 'message' : '" + Arrays.toString(ids) + "'}");
                //  return idx;
            }
        }
    }


}
