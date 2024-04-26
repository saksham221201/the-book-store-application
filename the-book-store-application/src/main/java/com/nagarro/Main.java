package com.nagarro;

import com.nagarro.io.AdminInput;
import com.nagarro.io.UserInput;
import com.nagarro.util.InputUtil;

public class Main {
    public static void main(String[] args) {
        String decision = "yes";
        do {
            try{
                System.out.println("Are you Admin or Normal User?");
                System.out.print("Type Admin for Admin and User for Normal User and Exit for exiting: ");
                String user = InputUtil.readInput();

                if(user.equalsIgnoreCase("Admin")){
                    do {
                        AdminInput.handleAdminInput();
                        System.out.print("Do you want to continue as Admin? (yes/no): ");
                        decision = InputUtil.readInput();
                        while(!decision.equalsIgnoreCase("yes") && !decision.equalsIgnoreCase("no")){
                            System.out.println("Invalid Input. Please enter 'yes' or 'no'.");
                            System.out.print("Do you want to continue as Admin? (yes/no): ");
                            decision = InputUtil.readInput();
                        }
                    } while (decision.equalsIgnoreCase("yes"));
                } else if (user.equalsIgnoreCase("User")) {
                    do {
                        UserInput.handleUserInput();
                        System.out.print("Do you want to continue as User? (yes/no): ");
                        decision = InputUtil.readInput();
                        while(!decision.equalsIgnoreCase("yes") && !decision.equalsIgnoreCase("no")){
                            System.out.println("Invalid Input. Please enter 'yes' or 'no'.");
                            System.out.print("Do you want to continue as User? (yes/no): ");
                            decision = InputUtil.readInput();
                        }
                    } while (decision.equalsIgnoreCase("yes"));
                } else if (user.equalsIgnoreCase("Exit")) {
                    System.exit(0);
                } else{
                    throw new Exception("Enter Admin or User");
                }
                if(!decision.equalsIgnoreCase("yes")){
                    decision = "yes";
                }
            } catch (Exception e){
                System.out.println("Please enter correct value: " + e.getMessage());
            }
        } while (decision.equalsIgnoreCase("yes"));
    }
}