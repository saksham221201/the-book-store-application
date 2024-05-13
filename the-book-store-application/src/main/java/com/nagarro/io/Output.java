package com.nagarro.io;

import com.nagarro.entity.Book;
import com.nagarro.entity.Order;

import java.util.Iterator;
import java.util.List;

public class Output {
    public static void displayBooks(List<Book> books) {
        Iterator<Book> iterator = books.iterator();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-45s %-25s %-15s %-70s %-30s %s\n", "ISBN","Book Name", "Book Author", "Book Price", "Description","Time Of Arrival", "Quantity");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        while(iterator.hasNext()) {
            Book book = iterator.next();
            System.out.printf("%-10s %-45s %-25s %-15s %-70s %-30s %s\n", book.getIsbn(),book.getBookName(), book.getAuthorName(), book.getBookPrice(),book.getDescription(), book.getArrivalTime(), book.getQuantity());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void displayOrders(List<Order> orders) {
        Iterator<Order> iterator = orders.iterator();
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %s\n", "Order ID", "ISBN", "Order Status", "Time Of Order");
        System.out.println("----------------------------------------------------------------------------------");
        while(iterator.hasNext()) {
            Order order = iterator.next();
            System.out.printf("%-15s %-15s %-15s %s\n", order.getOrderId(), order.getIsbn(), order.getOrderStatus(), order.getOrderTimestamp());
        }
        System.out.println("----------------------------------------------------------------------------------");

    }
}
