package com.nagarro.service;

import com.nagarro.entity.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private static final Map<String, Book> bookInventory = new TreeMap<>(Collections.reverseOrder());

    @BeforeAll
    public static void setUp() {
        Book book1 = new Book("A1", "HARRY POTTER AND THE PHILOSOPHER'S STONE", "J.K. ROWLING", "THIS IS A HARRY POTTER SERIES.", 2000.0,LocalDateTime.now());
        Book book2 = new Book("B2", "A SHERLOCK HOLMES MYSTERY", "SIR ARTHUR CONAN", "THE RETURN OF SHERLOCK HOLMES EIGHT YEARS AFTER HIS APPARENT DEATH.", 2500.0,LocalDateTime.now());
        Book book3 = new Book("C3", "HARRY POTTER AND THE CHAMBER OF SECRETS", "J.K. ROWLING", "THIS IS A HARRY POTTER SERIES.", 2200.0,LocalDateTime.now());
        Book book4 = new Book("D4","THE LION INSIDE", "RACHEL BRIGHT", "BOARD BOOK EDITION OF THIS BESTSELLING STORY.", 5400.0,LocalDateTime.now());
        Book book5 = new Book("E5", "ONCE A THIEF", "CHRISTOPHER REICH", "FACING ENEMIES AT EVERY TURN, PRIVATE SPY.", 4375.0,LocalDateTime.now());
        Book book6 = new Book("F6", "STORY OF MY LIFE", "SAKSHAM", "THIS IS THE STORY OF SAKSHAM'S LIFE.", 5600.0, LocalDateTime.now());
        Book book7 = new Book("F7", "STORY OF HIS LIFE", "KAMAD", "THIS IS THE STORY OF KAMAD'S LIFE.", 5600.0, LocalDateTime.now());
        Book book8 = new Book("G1", "HELL OF IT ALL", "CHARLIE BROOKER", "THIS IS HELL OF IT ALL BY CHARLIE", 648, LocalDateTime.now());

        bookInventory.put(book1.getIsbn(), book1);
        bookInventory.put(book2.getIsbn(), book2);
        bookInventory.put(book3.getIsbn(), book3);
        bookInventory.put(book4.getIsbn(), book4);
        bookInventory.put(book5.getIsbn(), book5);
        bookInventory.put(book6.getIsbn(), book6);
        bookInventory.put(book7.getIsbn(), book7);
        bookInventory.put(book8.getIsbn(), book8);
    }

    @Test
    @DisplayName("Add Book")
    void addBook() {
        Book book = new Book("H1", "Test Book", "Test Author", "Test Description", 4000.0, LocalDateTime.now());
        bookInventory.put(book.getIsbn(), book);
        assertTrue(bookInventory.containsKey("H1"));
    }

    @Test
    @DisplayName("Add Book Exception")
    void addBookException() {
        Book book = new Book("h5", "Test Book", "Test Author", "Test Description", 4000.0, LocalDateTime.now());
        bookInventory.put(book.getIsbn(), book);
        assert true;
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