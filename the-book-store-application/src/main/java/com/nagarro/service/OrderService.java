package com.nagarro.service;

import com.nagarro.dao.BookDao;
import com.nagarro.dao.OrderDao;
import com.nagarro.entity.Book;
import com.nagarro.entity.Order;
import com.nagarro.util.InputUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public static void addOrder(){
        System.out.print("Enter ISBN of book you want to order: ");
        String inputs = InputUtil.readInput();
        String[] isbn = inputs.split(" ");
        BookDao bookDao = new BookDao();
        List<Book> books = new ArrayList<>();
        try {
            for (String eachIsbn : isbn) {
                Book book = bookDao.getBookByIsbn(eachIsbn);
                if (book.getQuantity() > 0) {
                    books.add(book);
                    book.setQuantity(book.getQuantity() - 1);
                }
            }
            Order order = new Order(inputs, LocalDateTime.now());
            order.setBooks(books);
            OrderDao orderDao = new OrderDao();
            orderDao.saveOrderToDatabase(order);
            System.out.println("Order Saved Successfully in the database!!");
        } catch (Exception e) {
            System.out.println("ISBN not found in the database!! " + e);
        }
    }

    public static void listAllOrders(){
        OrderDao orderDao = new OrderDao();
        List<Order> orders = orderDao.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("There are no Orders in the database!");
        } else{
            for (Order order : orders){
                System.out.println("OrderId: " + order.getOrderId() + "ISBN: " + order.getIsbn() + "Timestamp: " + order.getOrderTimestamp());
            }
        }
    }
}
