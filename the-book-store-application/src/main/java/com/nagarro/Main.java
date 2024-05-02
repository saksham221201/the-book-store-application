package com.nagarro;

import com.nagarro.io.AdminInput;
import com.nagarro.io.UserInput;
import com.nagarro.util.InputUtil;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Are you Admin or Normal User?");
            System.out.print("Type Admin for Admin and User for Normal User: ");
            String user = InputUtil.readInput();
            if (user.equalsIgnoreCase("Admin")) {
                AdminInput.handleAdminInput();
                return;
            }
            if (user.equalsIgnoreCase("User")) {
                UserInput.handleUserInput();
            } else System.out.println("Please enter correct value.");
        } catch (Exception e) {
            System.out.println("Please enter correct value: " + e.getMessage());
        }
    }
}