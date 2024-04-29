package com.nagarro.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Book {
    @Id
    private String isbn;
    private String bookName;
    private String authorName;
    private String description;
    private LocalDateTime arrivalTime;

    public Book(String isbn, String bookName, String authorName, String description, LocalDateTime arrivalTime) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;
        this.arrivalTime = arrivalTime;
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

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
