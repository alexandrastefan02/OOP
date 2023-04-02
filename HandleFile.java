package com.example.project;

import java.io.*;

// Main class
public class HandleFile {

    // Method 1
    // TO append string into a file
    public static void appendStrToFile(String fileName,
                                 String str) {
        // Try block to check for exceptions
        try {

            // Open given file in append mode by creating an
            // object of BufferedWriter class
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fileName, true));

            // Writing on output stream
            out.write(str);
            // Closing the connection
            out.close();
        }

        // Catch block to handle the exceptions
        catch (IOException e) {

            // Display message when exception occurs
            System.out.println("exception occurred" + e);
        }
    }

    public static void clearTheUsersFile()  {
        try {
            PrintWriter out = new PrintWriter("users.txt");
            out.print("");
            out.close();
        } catch (IOException e) {
//exception handling left as an exercise for the reader
        }

    }
    public static void clearQuizzFile()  {
        try {
            PrintWriter out = new PrintWriter("quizzNames.txt");
            out.print("");
            out.close();
        } catch (IOException e) {
//exception handling left as an exercise for the reader
        }

    }

    public static void clearTheTextFile()  {
        try {
            PrintWriter out = new PrintWriter("questiontext.txt");
            out.print("");
            out.close();
        } catch (IOException e) {
//exception handling left as an exercise for the reader
        }

    }

    public static void clearTheIDFile()  {
        try {
            PrintWriter out = new PrintWriter("questionid.txt");
            out.print("");
            out.close();
        } catch (IOException e) {
//exception handling left as an exercise for the reader
        }

    }

    public static void clearTheNamesFile()  {
        try {
            PrintWriter out = new PrintWriter("quiznames.txt");
            out.print("");
            out.close();
        } catch (IOException e) {
//exception handling left as an exercise for the reader
        }

    }

    public static void clearTheQuizIDFile()  {
        try {
            PrintWriter out = new PrintWriter("quizid.txt");
            out.print("");
            out.close();
        } catch (IOException e) {
//exception handling left as an exercise for the reader
        }

    }

}