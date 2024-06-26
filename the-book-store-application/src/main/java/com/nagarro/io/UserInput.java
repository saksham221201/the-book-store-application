package com.nagarro.io;

import com.nagarro.service.BookService;
import com.nagarro.service.OrderService;
import com.nagarro.util.InputUtil;

public class UserInput {
    public static boolean handleUserInput(){
        System.out.println("User Options");
        System.out.println("1. List all Books");
        System.out.println("2. Order a Book");
        System.out.println("3. List all Orders");
        System.out.println("4. Track Order by Order ID");
        System.out.println("5. Search for a Book");
        System.out.println("6. Exit to Main Menu");

        System.out.print("Enter your option: ");
        int option = InputUtil.readIntInput();

        // Switch case using JAVA 17 Constructs
        switch (option){
            case 1 -> BookService.listAllBooks();
            case 2 -> OrderService.addOrder();
            case 3 -> OrderService.listAllOrders();
            case 4 -> OrderService.trackOrder();
            case 5 -> BookService.searchABook();
            case 6 -> {
                return false;
            }
            default -> System.out.println("Invalid choice");
        }
        return true;
    }
}
