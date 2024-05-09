package com.nagarro.io;

import com.nagarro.entity.Book;

import java.util.Iterator;
import java.util.List;

public class Output {
    public static void outputBooks(List<Book> books) {
        Iterator<Book> iterator = books.iterator();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-25s %-15s %-50s %-30s %s\n", "Book Name", "Book Author", "Description", "Time Of Arrival", "Quantity");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        while(iterator.hasNext()) {
            Book book = iterator.next();
            System.out.printf("%-25s %-15s %-50s %-30s %s\n", book.getBookName(), book.getAuthorName(), book.getDescription(), book.getArrivalTime(), book.getQuantity());
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
    }
}
