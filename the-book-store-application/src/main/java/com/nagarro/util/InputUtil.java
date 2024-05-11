package com.nagarro.util;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readInput(){
        return scanner.nextLine().toUpperCase();
    }

    public static int readIntInput(){
        while (true){
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Invalid Input. Please enter Integer.");
                System.out.print("Enter your option: ");
            }
        }
    }

    public static double readDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter Double/Integer.");
                System.out.print("Enter the Book Price: ");
            }
        }
    }
}
