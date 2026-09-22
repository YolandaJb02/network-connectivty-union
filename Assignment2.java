/*
COP3330 Fall 2025
Programming Assignment 2
Student Name: Yolanda Exalus
File Name: Assignment2.java
NOTE: I hereby certify that this submission is my original work.
It was completed independently by me without unauthorized assistance.
I affirm that all sources consulted have been properly acknowledged.
No part of this work was copied or plagiarized from any other source/s.
*/
import java.util.Scanner;

public class Assignment2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user to enter a number
        System.out.print("Enter Number: ");
        String input = scanner.nextLine();

        // Validate input: must contain only digits
        if (isDigit(input) != true) {  // call the isDigit method
            System.out.println("Invalid Input"); // error message for invalid input
            scanner.close();
            return; // exit the program
        }

        // Convert the string input to an integer
        int number = Integer.parseInt(input);

        // Check if the number is non-negative
        if (number < 0) {
            System.out.println("Invalid Input"); // error message for negative numbers
            scanner.close();
            return; // exit the program
        }

        scanner.close(); // close the scanner to free resources

        // Check if the input string is a palindrome
        boolean isPalindrome = isPalindrome(input);

        // Convert each digit to a corresponding letter
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char digitChar = input.charAt(i); // get the character at index i
            int digit = digitChar - '0'; // convert char to integer (0-9)
            char letter = (char) ('A' + digit); // map 0->A, 1->B, ..., 9->J

            // If not a palindrome, convert the letter to lowercase
            if (!isPalindrome) {
                letter = Character.toLowerCase(letter);
            }

            result.append(letter); // append the letter to the result string
        }

        // Output the final result
        System.out.println(result.toString());
    }

    // Method to check if a string contains only digits
    private static boolean isDigit(String s) {
        if (s.length() == 0) return false; // empty string is invalid
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') return false; // any non-digit character invalidates input
        }
        return true; // all characters are digits
    }

    // Method to check if a string is a palindrome
    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) { // mismatch found
                return false;
            }
            left++;
            right--;
        }
        return true; // string reads the same forwards and backwards
    }
}

