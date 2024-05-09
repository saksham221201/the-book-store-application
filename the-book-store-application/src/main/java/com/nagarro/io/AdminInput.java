package com.nagarro.io;

import com.nagarro.service.BookService;
import com.nagarro.service.OrderService;
import com.nagarro.util.InputUtil;

public class AdminInput {
    public static void handleAdminInput() {
        System.out.println("Admin Options");
        System.out.println("1. Add a Book");
        System.out.println("2. Update a Book");
        System.out.println("3. List all Book");
        System.out.println("4. List all Orders");
        System.out.println("5. Find a Book");

        System.out.print("Enter your option: ");
        int option = InputUtil.readIntInput();

        switch (option){
            case 1:
                BookService.addBook();
                break;
            case 2:
                BookService.updateBook();
                break;
            case 3:
                BookService.listAllBooks();
                break;
            case 4:
                OrderService.listAllOrders();
                break;
            case 5:
            	BookService.findABook();
            	break;
            default:
                System.out.println("Invalid choice");
        }
    }
}
