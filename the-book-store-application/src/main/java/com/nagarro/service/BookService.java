package com.nagarro.service;

import com.nagarro.dao.BookDao;
import com.nagarro.entity.Book;
import com.nagarro.util.InputUtil;

import java.time.LocalDateTime;
import java.util.List;

public class BookService {
    public static void addBook(){
        System.out.print("Enter ISBN: ");
        String isbn = InputUtil.readInput();
        System.out.print("Enter Book Name: ");
        String bookName = InputUtil.readInput();
        System.out.print("Enter Book Author: ");
        String bookAuthor = InputUtil.readInput();
        System.out.print("Enter Description: ");
        String description = InputUtil.readInput();
        Book book = new Book(isbn, bookName, bookAuthor, description, LocalDateTime.now());
        BookDao bookDao = new BookDao();
        bookDao.saveBookToDatabase(book);
        System.out.println("Book Saved Successfully in the database!!");
    }

    public static void updateBook(){

    }

    public static void listAllBooks(){
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.getAllBooks();
        for (Book book : books){
            System.out.println(book.getBookName());
        }
    }

    public static void findABook(){

    }
}
