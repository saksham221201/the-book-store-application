package com.nagarro.service;

import com.nagarro.entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private static final Map<String, Book> bookInventory = new TreeMap<>(Collections.reverseOrder());
    private BookService bookService;



    @Test
    @DisplayName("Add Book")
    void addBook() {
        Book book = new Book("h5", "Test Book", "Test Author", "Test Description", 4000.0, LocalDateTime.now());
        bookInventory.put(book.getIsbn(), book);
        assertTrue(bookInventory.containsKey("h5"));
    }

    @Test
    void updateBook() {
    }

    @Test
    void updateQuantity() {
    }

    @Test
    void markOutOfStock() {
    }

    @Test
    void listAllBooks() {
    }

    @Test
    void findABook() {
    }

    @Test
    void getBookByISBN() {
    }

    @Test
    void searchABook() {
    }

    @Test
    void getTopFiveSelling() {
    }

    @Test
    void getMaximumProfit() {
    }
}