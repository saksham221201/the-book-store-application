package com.nagarro.service;

import com.nagarro.entity.Book;
import com.nagarro.util.InputUtil;

import java.time.LocalDateTime;
import java.util.*;

public class BookService {
	private static final Map<String, Book> bookInventory = new TreeMap<>();

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
		for (Book book : books) {
			System.out.println("BookName: " + book.getBookName() + " BookAuthor: " + book.getAuthorName() + " Time of Arrival: " + book.getArrivalTime() + " Quantity: " + book.getQuantity());
		}
	}

	public static void findABook() {
		System.out.print("Enter ISBN: ");
		String isbn = InputUtil.readInput();
		Book book = bookInventory.get(isbn);

		System.out.println("Book Name: " + book.getBookName() + " Author: " + book.getAuthorName());
	}
}
