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

    public static void addOrder(){
        System.out.print("Enter ISBN of book you want to order: ");
        String isbn = InputUtil.readInput();

        Order order = new Order(1L, isbn, "PLACED", LocalDateTime.now());
        orderInventory.put(1L, order);
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
}
