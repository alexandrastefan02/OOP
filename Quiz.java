package com.example.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    public static int countLines(String fileName) {
        int k = 0;
        File file = new File("quiznames.txt");
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

    public static int nameAlreadyExists(String name) {
        int ok = 0;
        File file = new File("quiznames.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (name.equals(line)) {
                    ok++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("exception occurred" + e);
        }
        return ok;
    }

    public static void createQuiz(String[] args) {
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
                String name = split[1];
                int sameName = Quiz.nameAlreadyExists(name);
                if (sameName != 0) {
                    System.out.println("{ 'status' : 'error', 'message' : 'Quizz name already exists'}");
                }
                if (sameName == 0) {
                    int quizID = countLines("quiznames.txt") + 1;
                    HandleFile.appendStrToFile("quiznames.txt", name + "\n");
                    HandleFile.appendStrToFile("quizid.txt", name + " " + "," + quizID + "\n");
                    System.out.println("{ 'status' : 'ok', 'message' : 'Quizz added successfully'}");
                }
            }
        }
    }

    public static void getAllQuizes(String[] args) {
        if (args.length == 1 || args.length == 2) {
            System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
        } else if (args.length > 14) {
            System.out.println("{ 'status' : 'error', 'message' : Quizz has more than 10 questions'}");
        } else {
            String userData = User.getUserData(args);
            int ok = User.userAlreadyExists(userData);
            if (ok == 0) {
                System.out.println("{ 'status' : 'error', 'message' : 'Login failed'}");
            }

        }
    }

    public static String findID(String qtext) {
        String idx = new String();
        File file = new File("quizid.txt");
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

    public static void getQuizID(String[] args) {
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
                int sameText = Quiz.nameAlreadyExists(split[1]);
                if (sameText == 0)
                    System.out.println("{ 'status' : 'error', 'message' : 'Quizz does not exist'}");
                if (sameText != 0) {
                    String idx = findID(split[1]);
                    System.out.println("{ 'status' : 'ok', 'message' : '" + idx + "'}");
                }
            }
        }
    }

    public static void getQuizDetails(String[] args) {
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
                File file = new File("quizid.txt");
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
            }
        }

    }

    public static void submit(String[] args) {
        if (args.length == 1 || args.length == 2) {
            System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
        } else {
            String userData = User.getUserData(args);
            int ok = User.userAlreadyExists(userData);
            if (ok == 0) {
                System.out.println("{ 'status' : 'error', 'message' : 'Login failed'}");
            }

        }
    }

    public static void delete(String[] args) {
        if (args.length == 1 || args.length == 2) {
            System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
        } else {
            String userData = User.getUserData(args);
            int ok = User.userAlreadyExists(userData);
            if (ok == 0) {
                System.out.println("{ 'status' : 'error', 'message' : 'Login failed'}");
            }
            if (ok != 0) {

            }
        }
    }

    public static void getSol(String[] args) {
        if (args.length == 1 || args.length == 2) {
            System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
        } else {
            String userData = User.getUserData(args);
            int ok = User.userAlreadyExists(userData);
            if (ok == 0) {
                System.out.println("{ 'status' : 'error', 'message' : 'Login failed'}");
            }
            if (ok != 0) {

            }
        }
    }
}
