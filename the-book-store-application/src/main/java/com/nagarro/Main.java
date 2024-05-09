package com.nagarro;

import com.nagarro.io.AdminInput;
import com.nagarro.io.UserInput;
import com.nagarro.util.InputUtil;

public class Main {
    public static void main(String[] args) {
    	String decision = "y";
        do {
            try{
                System.out.println("Are you Admin or Normal User?");
                System.out.print("Type Admin for Admin and User for Normal User and Exit for exiting: ");
                String user = InputUtil.readInput();

                if(user.equalsIgnoreCase("Admin")){
                    do {
                        AdminInput.handleAdminInput();
                        System.out.print("Do you want to continue as Admin? (y/n): ");
                        decision = InputUtil.readInput();
                        while(!decision.equalsIgnoreCase("y") && !decision.equalsIgnoreCase("n")){
                            System.out.println("Invalid Input. Please enter 'y' or 'n'.");
                            System.out.print("Do you want to continue as Admin? (y/n): ");
                            decision = InputUtil.readInput();
                        }
                    } while (decision.equalsIgnoreCase("y"));
                } else if (user.equalsIgnoreCase("User")) {
                    do {
                        UserInput.handleUserInput();
                        System.out.print("Do you want to continue as User? (y/n): ");
                        decision = InputUtil.readInput();
                        while(!decision.equalsIgnoreCase("y") && !decision.equalsIgnoreCase("n")){
                            System.out.println("Invalid Input. Please enter 'y' or 'n'.");
                            System.out.print("Do you want to continue as User? (y/n): ");
                            decision = InputUtil.readInput();
                        }
                    } while (decision.equalsIgnoreCase("y"));
                } else if (user.equalsIgnoreCase("Exit")) {
                    System.exit(0);
                } else{
                    throw new Exception("Enter Admin or User");
                }
                if(!decision.equalsIgnoreCase("y")){
                    decision = "y";
                }
            } catch (Exception e){
                System.out.println("Please enter correct value: " + e.getMessage());
            }
        } while (decision.equalsIgnoreCase("y"));
    }
}