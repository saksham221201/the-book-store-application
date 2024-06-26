package com.nagarro.main;

import com.nagarro.constant.Constant;
import com.nagarro.io.AdminInput;
import com.nagarro.io.UserInput;
import com.nagarro.service.BookService;
import com.nagarro.util.InputUtil;

public class Main {
    public static void main(String[] args) {
        BookService.preFillData();
    	String decision = "Y";
        do {
            System.out.println("Are you Admin or Normal User?");
            System.out.print("Type Admin for Admin and User for Normal User and Exit for exiting: ");
            try{
                String user = InputUtil.readInput();
                if(user.equalsIgnoreCase(Constant.ADMIN.name())){
                    do {
                        boolean continueAdmin = AdminInput.handleAdminInput();
                        if (!continueAdmin) {
                            decision = "N";
                        }
                    } while (decision.equalsIgnoreCase("Y"));
                } else if (user.equalsIgnoreCase(Constant.USER.name())) {
                    do {
                        boolean continueUser = UserInput.handleUserInput();
                        if (!continueUser) {
                            decision = "N";
                        }
                    } while (decision.equalsIgnoreCase("Y"));
                } else if (user.equalsIgnoreCase(Constant.EXIT.name())) {
                    InputUtil.close();
                    System.exit(0);
                } else{
                    throw new Exception("Enter Admin or User");
                }
                decision = "Y";
            } catch (Exception e){
                System.out.println("Please enter correct value: " + e.getMessage());
            }
        } while (decision.equalsIgnoreCase("Y"));
    }
}