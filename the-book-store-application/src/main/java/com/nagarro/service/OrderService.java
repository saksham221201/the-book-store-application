package com.nagarro.service;

import com.nagarro.dao.BookDao;
import com.nagarro.dao.OrderDao;
import com.nagarro.entity.Book;
import com.nagarro.entity.Order;
import com.nagarro.util.InputUtil;

import java.time.LocalDateTime;
import java.util.List;

public class OrderService {

    public static void addOrder(){
        System.out.print("Enter ISBN of book you want to order: ");
        String isbn = InputUtil.readInput();
        BookDao bookDao = new BookDao();
        try {
            Book book = bookDao.getBookByIsbn(isbn);
            Order order = new Order(isbn, LocalDateTime.now());
            OrderDao orderDao = new OrderDao();
            orderDao.saveOrderToDatabase(order);
            System.out.println("Order Saved Successfully in the database!!");
        } catch (Exception e) {
            System.out.println("ISBN not found in the database!!");
        }
    }

    public static void listAllOrders(){
        OrderDao orderDao = new OrderDao();
        List<Order> orders = orderDao.getAllOrders();
        for (Order order : orders){
            System.out.println("OrderId: " + order.getOrderId() + "ISBN: " + order.getIsbn() + "Timestamp: " + order.getOrderTimestamp());
        }
    }
}
