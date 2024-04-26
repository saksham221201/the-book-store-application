package com.nagarro.util;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readInput(){
        return scanner.nextLine();
    }

    public static int readIntInput(){
        while (true){
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Invalid Input. Please enter Integer.");
            }
        }
    }
}
