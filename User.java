package com.example.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.example.project.HandleFile.appendStrToFile;

public class User {
    String username;
    String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return username + "," + password;
    }

    public static String getUserData(String[] text){
        String str1 = text[1];
        String[] splitStr1 = str1.split(" ");
        String str2 = text[2];
        String[] splitStr2 = str2.split(" ");
        String userName = splitStr1[1];
        String passWord = splitStr2[1];
        User user = new User(userName, passWord);
        String userData = user.toString();
        return userData;
    }

    public static int userAlreadyExists(String userData){
        int ok = 0;
        File file = new File("users.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (userData.equals(line)) {
                    ok++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("exception occurred" + e);
        }
        return ok;
    }

    public static void createUser(String[] s){
        if (s.length == 1) {
            System.out.println("{'status':'error','message':'Please provide username'}");
        } else if (s.length == 2) {
            System.out.println("{'status' : 'error', 'message' : 'Please provide password'}");
        } else {

            String userData = User.getUserData(s);
            int ok =User.userAlreadyExists(userData);

            if (ok != 0)
                System.out.println("{ 'status' : 'error', 'message' : 'User already exists'}");
            else {
                appendStrToFile("users.txt", userData + "\n");
                System.out.println("{ 'status' : 'ok', 'message' : 'User created successfully'}");

            }

        }
    }



}
