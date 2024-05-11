package com.nagarro.entity;

import java.time.LocalDateTime;

public class Book {

    private String isbn;
    private String bookName;
    private String authorName;
    private String description;
    private double bookPrice;
    private LocalDateTime arrivalTime;
    private int quantity;

    public Book() {
        super();
    }

    public Book(String isbn, String bookName, String authorName, String description, double bookPrice, LocalDateTime arrivalTime) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;
        this.bookPrice = bookPrice;
        this.arrivalTime = arrivalTime;
        this.quantity = 5;
    }

	public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBookPrice() { return bookPrice; }

    public void setBookPrice(double bookPrice) { this.bookPrice = bookPrice; }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
