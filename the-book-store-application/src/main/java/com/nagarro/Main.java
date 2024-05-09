package com.nagarro;

import com.nagarro.io.AdminInput;
import com.nagarro.io.UserInput;
import com.nagarro.service.BookService;
import com.nagarro.util.InputUtil;

public class Main {
    public static void main(String[] args) {
        BookService.fillData();
    	String decision = "y";
        do {
            try{
                System.out.println("Are you Admin or Normal User?");
                System.out.print("Type Admin for Admin and User for Normal User and Exit for exiting: ");
                String user = InputUtil.readInput();

                if(user.equalsIgnoreCase("Admin")){
                    do {
                        boolean continueAdmin = AdminInput.handleAdminInput();
                        if (!continueAdmin) {
                            decision = "n";
                        }
                    } while (decision.equalsIgnoreCase("y"));
                } else if (user.equalsIgnoreCase("User")) {
                    do {
                        boolean continueUser = UserInput.handleUserInput();
                        if (!continueUser) {
                            decision = "n";
                        }
                    } while (decision.equalsIgnoreCase("y"));
                } else if (user.equalsIgnoreCase("Exit")) {
                    System.exit(0);
                } else{
                    throw new Exception("Enter Admin or User");
                }
                decision = "y";
            } catch (Exception e){
                System.out.println("Please enter correct value: " + e.getMessage());
            }
        } while (decision.equalsIgnoreCase("y"));
    }
}