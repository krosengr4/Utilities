package com.pluralsight.utils;
import java.util.Scanner;

public class IOUtils {

    public IOUtils(){}

    static Scanner myScanner = new Scanner(System.in);

    // Prompts user, gets user input, returns user input as a string
    public static String getUserInput(String message) {
        System.out.println(message);
        return myScanner.nextLine().trim();
    }

    // Prompts user to enter an integer, makes sure user input is an int, returns user input as an int
    public static int getUserInputInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(myScanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("ERROR! Invalid Input! Please enter a valid integer!");
            }
        }
    }

    // Prompts user to enter an integer, makes sure user input is an int and between a min and max value, returns user input as an int
    public static int getUserInputIntMinMax(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            try {
                int userInput = Integer.parseInt(myScanner.nextLine());
                if (userInput >= min && userInput <= max) {
                    return userInput;
                } else {
                    System.err.println("ERROR! Please Input a Valid Number!");
                }
            } catch (NumberFormatException e) {
                System.err.println("ERROR! Invalid Input! Please enter a valid integer!");
            }
        }
    }

    // Prompts user to input a double, checks that user input is a double, returns user input as a double
    public static double getUserInputDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(myScanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("ERROR! Invalid Input! Please enter a valid double!");
            }
        }
    }

    //Pauses the app until user hits Enter
    public static void pauseApp() {
        System.out.println("\nPress Enter to Continue...");
        myScanner.nextLine();
    }

    //Capitalizes the first letter in every word
    public static String capitalizeWords(String input) {
        String[] words = input.trim().toLowerCase().split(" ");
        StringBuilder capitalized = new StringBuilder();

        for (String word : words) {

            capitalized.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1))
                    .append(" ");
        }
        return capitalized.toString().trim();
    }
}
