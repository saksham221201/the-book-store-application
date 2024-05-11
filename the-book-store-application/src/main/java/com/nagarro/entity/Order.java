package com.nagarro.entity;

import java.time.LocalDateTime;


public class Order {
    private Long orderId;
    private String isbn;
    private String orderStatus;
    private LocalDateTime orderTimestamp;

    public Order(){
        super();
    }

    public Order(Long orderId, String isbn, String orderStatus, LocalDateTime orderTimestamp) {
        this.orderId = orderId;
        this.isbn = isbn;
        this.orderStatus = orderStatus;
        this.orderTimestamp = orderTimestamp;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setOrderTimestamp(LocalDateTime orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }
}
