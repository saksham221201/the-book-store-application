package com.nagarro.io;

import com.nagarro.service.BookService;
import com.nagarro.service.OrderService;
import com.nagarro.util.InputUtil;

public class UserInput {
    public static void handleUserInput(){
        System.out.println("User Options");
        System.out.println("1. List all Books");
        System.out.println("2. Order a Book");

        System.out.print("Enter your option: ");
        int option = InputUtil.readIntInput();

        switch (option){
            case 1:
                BookService.listAllBooks();
                break;
            case 2:
                OrderService.addOrder();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}
