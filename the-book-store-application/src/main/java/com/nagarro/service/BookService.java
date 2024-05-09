package com.nagarro.service;

import com.nagarro.entity.Book;
import com.nagarro.io.Output;
import com.nagarro.util.InputUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookService {
	private static final Map<String, Book> bookInventory = new HashMap<>();

	public static void fillData() {
		Book book1 = new Book("a1", "Harry Potter", "DumbbellDore", "This is Harry Potter Series.", LocalDateTime.now());
		Book book2 = new Book("b2", "Two States", "Chetan Bhagat", "This is Love Story from two different states.", LocalDateTime.now());
		Book book3 = new Book("c3", "Harry Potter 2", "DumbbellDore", "This is Harry Potter Series.", LocalDateTime.now());
		bookInventory.put("a1", book1);
		bookInventory.put("b2", book2);
		bookInventory.put("c3", book3);
	}

	public static void addBook() {
		System.out.print("Enter ISBN: ");
		String isbn = InputUtil.readInput();
		System.out.print("Enter Book Name: ");
		String bookName = InputUtil.readInput();
		System.out.print("Enter Book Author: ");
		String bookAuthor = InputUtil.readInput();
		System.out.print("Enter Description: ");
		String description = InputUtil.readInput();
		Book book = new Book(isbn, bookName, bookAuthor, description, LocalDateTime.now());
		bookInventory.put(isbn, book);
		System.out.println("Book Saved Successfully in the database!!");
		listAllBooks();
	}

	public static void updateBook() {
		System.out.print("Enter ISBN: ");
		String isbn = InputUtil.readInput();
		System.out.print("Enter Book Name: ");
		String bookName = InputUtil.readInput();
		System.out.print("Enter Book Author: ");
		String bookAuthor = InputUtil.readInput();
		System.out.print("Enter Description: ");
		String description = InputUtil.readInput();
		Book book = new Book(isbn, bookName, bookAuthor, description, LocalDateTime.now());
		bookInventory.put(isbn, book);
		System.out.println("Book Updated Successfully!!");
	}

	public static void listAllBooks() {
		List<Book> books = new ArrayList<>(bookInventory.values());
		Output.outputBooks(books);
	}

	public static void findABook() {
		System.out.print("Enter ISBN: ");
		String isbn = InputUtil.readInput();
		Book book = bookInventory.get(isbn);

		System.out.println("Book Name: " + book.getBookName() + " Author: " + book.getAuthorName());
	}
}
