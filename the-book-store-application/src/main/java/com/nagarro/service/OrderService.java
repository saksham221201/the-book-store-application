package com.nagarro.service;

import com.nagarro.entity.Order;
import com.nagarro.io.Output;
import com.nagarro.util.InputUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private static final Map<Long, Order> orderInventory = new HashMap<>();
    private static Long nextOrderId = 1L;

    public static void addOrder(){
        System.out.print("Enter ISBN of book you want to order: ");
        String isbn = InputUtil.readInput();
        BookService.getBookByISBN(isbn);
        Order order = new Order(nextOrderId, isbn, "PLACED", LocalDateTime.now());
        orderInventory.put(nextOrderId, order);
        BookService.updateQuantity(isbn);
        nextOrderId++;
        System.out.println("Order Placed Successfully");
    }

    public static void listAllOrders(){
        List<Order> orders = new ArrayList<>(orderInventory.values());
        if (orders.isEmpty()) {
            System.out.println("There are no Orders in the database!");
        } else{
            Output.displayOrders(orders);
        }
    }

    public static void updateOrderStatus() {
        System.out.print("Enter Order Id: ");
        String orderId = InputUtil.readInput();
        Long id = Long.parseLong(orderId);
        System.out.print("Enter the order Status: ");
        String updatedOrderStatus = InputUtil.readInput();
        Order order = orderInventory.get(id);
        order.setOrderStatus(updatedOrderStatus);
        System.out.println("Order Status updated!!");
    }

    public static void trackOrder() {
        System.out.print("Enter Order ID: ");
        String orderId = InputUtil.readInput();
        Long id = Long.parseLong(orderId);
        Order order = orderInventory.get(id);
        System.out.println("The Order Status is " + order.getOrderStatus());
    }
}
