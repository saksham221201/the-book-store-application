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
        bookDao.updateBook(book);
        System.out.println("Book Updated Successfully!!");
    }

    public static void listAllBooks(){
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.getAllBooks();
        for (Book book : books){
            System.out.println(book.getBookName());
        }
    }

    public static void findABook(){
    	System.out.print("Enter ISBN: ");
        String isbn = InputUtil.readInput();
        
    	BookDao bookDao = new BookDao();
    	Book book = bookDao.getBookByIsbn(isbn);
    	
    	System.out.println("Book Name: " + book.getBookName() + " Author: " + book.getAuthorName());

    }
    
    public static void listAllBooksBySearch() throws Exception {
    	System.out.print("Search: ");
    	String search = InputUtil.readInput();
    	
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.getBooksBySearch(search);
        if(books.isEmpty()) {
            throw new Exception("Book does not exist");
        }
        for (Book book : books){
            System.out.println("Book Name: " + book.getBookName() + " Author: " + book.getAuthorName());
        }
    }
}
