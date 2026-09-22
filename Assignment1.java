/*
COP3330 Fall 2025
Programming Assignment 1
Student Name: Yolanda Exalus
File Name: Assignment1.java
NOTE: I hereby certify that this submission is my original work.
It was completed independently by me without unauthorized assistance.
I affirm that all sources consulted have been properly acknowledged.
No part of this work was copied or plagiarized from any other source/s.
*/

import java.util.Scanner;

public class Assignment1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Header
        System.out.println("==== RECEIPT ====");

        // Read input from cashier
        System.out.print("Coffee price: ");
        int coffeePrice = scanner.nextInt();

        System.out.print("Number of coffees: ");
        int numCoffees = scanner.nextInt();

        System.out.print("Pastry price: ");
        int pastryPrice = scanner.nextInt();

        System.out.print("Number of pastries: ");
        int numPastries = scanner.nextInt();

        System.out.print("Party size: ");
        int partySize = scanner.nextInt();

        // Add a gap line
        System.out.println();

        // Calculate totals
        int totalBeforeTax = coffeePrice * numCoffees + pastryPrice * numPastries;
        double taxAmount = totalBeforeTax * 0.06;
        double totalWithTax = totalBeforeTax + taxAmount;
        double eachPays = totalWithTax / partySize;

        // Output using printf safely
        System.out.printf("Total before tax: $ %d\n", totalBeforeTax);
        System.out.printf("Florida tax (6): $ %.2f\n", taxAmount);
        System.out.printf("Total with tax: $ %.2f\n", totalWithTax);
        System.out.printf("For a party size of %d, each person pays: $ %.2f\n", partySize, eachPays);

        // Footer
        System.out.println("=== THANK YOU ===");

        scanner.close();
    }
}
