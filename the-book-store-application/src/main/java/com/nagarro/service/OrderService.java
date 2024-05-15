package com.nagarro.service;

import com.nagarro.constant.Constant;
import com.nagarro.entity.Book;
import com.nagarro.entity.Order;
import com.nagarro.exception.ResourceNotFoundException;
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
        BookService.listAllBooks();
        System.out.print("Enter the book name you want to order: ");
        String bookName = InputUtil.readInput();
        try {
            Book book = BookService.getBookByName(bookName);
            if (book.getQuantity() == 0) {
                System.out.println("Cannot order book because it is not in the Inventory");
            } else {
                Order order = new Order(nextOrderId, book.getIsbn(), Constant.PLACED.name(), LocalDateTime.now());
                orderInventory.put(nextOrderId, order);
                BookService.updateQuantity(book.getIsbn());
                nextOrderId++;
                System.out.println("Order Placed Successfully");
            }
        } catch (ResourceNotFoundException e) {
            System.out.println("Error! "+e.getMessage() + "\n" + "Status code: "+ e.getCode());
        }
    }

    public static void listAllOrders(){
        List<Order> orders = new ArrayList<>(orderInventory.values());
        if (orders.isEmpty()) {
            System.out.println("There are no Orders in the database!");
        } else{
            Output.displayOrders(orders);
        }
    }

    public static Order getOrderByOrderId(Long orderId) {
        Order order = orderInventory.get(orderId);
        try {
            if (order == null) {
                throw new ResourceNotFoundException("OrderId not found in the Inventory", 404);
            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage(), e.getCode());
        }
        return order;
    }

    public static void updateOrderStatus() {
        System.out.print("Enter Order Id: ");
        Long orderId = InputUtil.readLongInput();
        try {
            Order order = getOrderByOrderId(orderId);
            System.out.print("Enter the order Status: ");
            String updatedOrderStatus = InputUtil.readInput();
            order.setOrderStatus(updatedOrderStatus);
            System.out.println("Order Status updated!!");
        } catch (ResourceNotFoundException e) {
            System.out.println("Error! "+e.getMessage() + "\n" + "Status code: "+ e.getCode());
        }
    }

    public static void trackOrder() {
        System.out.print("Enter Order ID: ");
        Long orderId = InputUtil.readLongInput();
        try {
            Order order = getOrderByOrderId(orderId);
            System.out.println("The Order Status is " + order.getOrderStatus());
        } catch (ResourceNotFoundException e) {
            System.out.println("Error! "+e.getMessage() + "\n" + "Status code: "+ e.getCode());
        }
    }
}
