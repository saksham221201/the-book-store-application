package com.nagarro.io;

import com.nagarro.service.BookService;
import com.nagarro.service.OrderService;
import com.nagarro.util.InputUtil;

public class AdminInput {
    public static boolean handleAdminInput() {
        System.out.println("Admin Options");
        System.out.println("1. Add a Book");
        System.out.println("2. Update a Book");
        System.out.println("3. List all Book");
        System.out.println("4. List all Orders");
        System.out.println("5. Find a Book");
        System.out.println("6. Search for a Book");
        System.out.println("7. Update Order Status");
        System.out.println("8. Mark Out Of Stock");
        System.out.println("9. Get Top 5 Selling Books");
        System.out.println("10. Profit");
        System.out.println("11. Exit to Main Menu");

        System.out.print("Enter your option: ");
        int option = InputUtil.readIntInput();

        // Switch case using JAVA 17 Constructs
        switch (option) {
            case 1 -> BookService.addBook();
            case 2 -> BookService.updateBook();
            case 3 -> BookService.listAllBooks();
            case 4 -> OrderService.listAllOrders();
            case 5 -> BookService.findABook();
            case 6 -> BookService.searchABook();
            case 7 -> OrderService.updateOrderStatus();
            case 8 -> BookService.markOutOfStock();
            case 9 -> BookService.getTopFiveSelling();
            case 10 -> BookService.getMaximumProfit();
            case 11 -> {
                return false;
            }
            default -> System.out.println("Invalid choice");
        };
        return true;
    }
}
