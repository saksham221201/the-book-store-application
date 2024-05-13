package com.nagarro.service;

import com.nagarro.entity.Book;
import com.nagarro.io.Output;
import com.nagarro.exception.ResourceAlreadyExistsException;
import com.nagarro.exception.ResourceNotFoundException;
import com.nagarro.util.InputUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookService {
	private static final Map<String, Book> bookInventory = new HashMap<>();

	public static void preFillData() {
		Book book1 = new Book("A1", "HARRY POTTER", "DUMBBELLDORE", "THIS IS A HARRY POTTER SERIES.", 2000.0,LocalDateTime.now());
		Book book2 = new Book("B2", "TWO STATES", "CHETAN BHAGAT", "THIS IS A LOVE STORY FROM TWO DIFFERENT STATES.", 2500.0,LocalDateTime.now());
		Book book3 = new Book("C3", "HARRY POTTER 2", "DUMBBELLDORE", "THIS IS A HARRY POTTER SERIES.", 2200.0,LocalDateTime.now());
		Book book4 = new Book("D4","THE LION INSIDE", "RACHEL BRIGHT", "BOARD BOOK EDITION OF THIS BESTSELLING STORY.", 5400.0,LocalDateTime.now());
		Book book5 = new Book("E5", "ONCE A THIEF", "CHRISTOPHER REICH", "FACING ENEMIES AT EVERY TURN, PRIVATE SPY.", 4375.0,LocalDateTime.now());

		bookInventory.put("A1", book1);
		bookInventory.put("B2", book2);
		bookInventory.put("C3", book3);
		bookInventory.put("D4", book4);
		bookInventory.put("E5", book5);
	}

	public static void addBook() {
		try {
			System.out.print("Enter ISBN: ");
			String isbn = InputUtil.readInput();
			if(bookInventory.containsKey(isbn)) {
				throw new ResourceAlreadyExistsException("This isbn: " + isbn + " already exists", 400);
			}
			System.out.print("Enter Book Name: ");
			String bookName = InputUtil.readInput();
			System.out.print("Enter Book Author: ");
			String bookAuthor = InputUtil.readInput();
			System.out.print("Enter Description: ");
			String description = InputUtil.readInput();
			System.out.print("Enter the Book Price: ");
			double bookPrice = InputUtil.readDoubleInput();
			Book book = new Book(isbn, bookName, bookAuthor, description, bookPrice,LocalDateTime.now());
			bookInventory.put(isbn, book);
			System.out.println("Book Saved Successfully in the database!!");
			listAllBooks();
		} catch (ResourceAlreadyExistsException e) {
			System.out.println("Error! " + e.getMessage() + "\n"+"Status code: "+ e.getCode());
		}
	}

	public static void updateBook() {
		try {
			System.out.print("Enter ISBN: ");
			String isbn = InputUtil.readInput();
			if(!bookInventory.containsKey(isbn)) {
				throw new ResourceNotFoundException("This isbn: " + isbn + " does not exists", 404);
			}
			System.out.print("Enter Book Name: ");
			String bookName = InputUtil.readInput();
			System.out.print("Enter Book Author: ");
			String bookAuthor = InputUtil.readInput();
			System.out.print("Enter Description: ");
			String description = InputUtil.readInput();
			System.out.print("Enter the Book Price: ");
			double bookPrice = InputUtil.readDoubleInput();
			Book book = new Book(isbn, bookName, bookAuthor, description, bookPrice,LocalDateTime.now());
			bookInventory.put(isbn, book);
			System.out.println("Book Updated Successfully!!");
		} catch (ResourceNotFoundException e) {
			System.out.println("Error! "+e.getMessage() + "\n" + "Status code: "+ e.getCode());
		}
	}

	public static void updateQuantity(String isbn) {
		Book book = bookInventory.get(isbn);
		if (book != null) {
			book.setQuantity(Math.max(0, book.getQuantity() - 1));
		} else System.out.println("Problem in updating quantity!!");
	}

	public static void markOutOfStock() {
		System.out.print("Enter ISBN: ");
		String isbn = InputUtil.readInput();
		Book book = bookInventory.get(isbn.toUpperCase());
		book.setQuantity(0);
	}

	public static void listAllBooks() {
		List<Book> books = new ArrayList<>(bookInventory.values());
		Output.displayBooks(books);
	}

	public static void findABook() {
		System.out.print("Enter ISBN: ");
		String isbn = InputUtil.readInput();
		Book book = bookInventory.get(isbn);

		System.out.println("Book Name: " + book.getBookName() + " Author: " + book.getAuthorName() + " Price: " + book.getBookPrice());
	}

	public static Book getBookByISBN(String isbn) {
		Book book = bookInventory.get(isbn);
		if (book == null) {
			throw new ResourceNotFoundException("This isbn: " + isbn + " does not exists", 404);
		}
		return book;
	}

	public static void searchABook() {
		System.out.print("Search: ");
		String search = InputUtil.readInput();
		List<Book> searchResult = new ArrayList<>();
		try {
			for (Map.Entry<String, Book> entry : bookInventory.entrySet()) {
				if (entry.getValue().getBookName().contains(search) ||
						entry.getValue().getAuthorName().contains(search) ||
						entry.getValue().getDescription().contains(search)) {
					searchResult.add(entry.getValue());
				}
			}
			if (searchResult.isEmpty()) {
				throw new ResourceNotFoundException("No Results found!", 404);
			}else {
				Output.displayBooks(searchResult);
			}
		} catch (ResourceNotFoundException e) {
			System.out.println("Error! "+e.getMessage() + "\n" + "Status code: "+ e.getCode());
		}
	}
}