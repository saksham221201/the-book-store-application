package com.nagarro.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String isbn;
    private LocalDateTime orderTimestamp;

    @OneToMany
    @JoinTable(name = "book_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "isbn"))
    private List<Book> books;

    public Order(){
        super();
    }


    public Order(String isbn, LocalDateTime orderTimestamp) {
        this.isbn = isbn;
        this.orderTimestamp = orderTimestamp;
    }

    public Order(Long orderId, String isbn, LocalDateTime orderTimestamp) {
        this.orderId = orderId;
        this.isbn = isbn;
        this.orderTimestamp = orderTimestamp;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public LocalDateTime getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setOrderTimestamp(LocalDateTime orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }
}
