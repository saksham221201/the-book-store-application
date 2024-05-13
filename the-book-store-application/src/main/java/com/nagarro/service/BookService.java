package com.nagarro.service;

import com.nagarro.entity.Book;
import com.nagarro.io.Output;
import com.nagarro.exception.ResourceAlreadyExistsException;
import com.nagarro.exception.ResourceNotFoundException;
import com.nagarro.util.InputUtil;

import java.time.LocalDateTime;
import java.util.*;

public class BookService {
	private static final Map<String, Book> bookInventory = new HashMap<>();

	public static void preFillData() {
		Book book1 = new Book("A1", "HARRY POTTER AND THE PHILOSOPHER'S STONE", "J.K. ROWLING", "THIS IS A HARRY POTTER SERIES.", 2000.0,LocalDateTime.now());
		Book book2 = new Book("B2", "A SHERLOCK HOLMES MYSTERY", "SIR ARTHUR CONAN", "THE RETURN OF SHERLOCK HOLMES EIGHT YEARS AFTER HIS APPARENT DEATH.", 2500.0,LocalDateTime.now());
		Book book3 = new Book("C3", "HARRY POTTER AND THE CHAMBER OF SECRETS", "J.K. ROWLING", "THIS IS A HARRY POTTER SERIES.", 2200.0,LocalDateTime.now());
		Book book4 = new Book("D4","THE LION INSIDE", "RACHEL BRIGHT", "BOARD BOOK EDITION OF THIS BESTSELLING STORY.", 5400.0,LocalDateTime.now());
		Book book5 = new Book("E5", "ONCE A THIEF", "CHRISTOPHER REICH", "FACING ENEMIES AT EVERY TURN, PRIVATE SPY.", 4375.0,LocalDateTime.now());

		bookInventory.put("A1", book1);
		bookInventory.put("B2", book2);
		bookInventory.put("C3", book3);
		bookInventory.put("D4", book4);
		bookInventory.put("E5", book5);
	}

	public static void addBook() {
		System.out.print("Enter ISBN: ");
		String isbn = InputUtil.readInput();
		try {
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
			System.out.println("Error! " + e.getMessage() + "\n" + "Status code: " + e.getCode());
		}
	}

	public static void updateBook() {
		System.out.print("Enter ISBN: ");
		String isbn = InputUtil.readInput();
		try {
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
		book.setQuantity(book.getQuantity() - 1);
	}

	public static void markOutOfStock() {
		System.out.print("Enter ISBN: ");
		String isbn = InputUtil.readInput();
		try {
			Book book = bookInventory.get(isbn.toUpperCase());
			if (book == null) {
				throw new ResourceNotFoundException("Book not found", 404);
			} else {
				book.setQuantity(0);
				System.out.println("The Book " + book.getBookName() +" has been sold out!");
			}
		}catch (ResourceNotFoundException e) {
			System.out.println("Error! "+e.getMessage() + "\n" + "Status code: "+ e.getCode());
		}
	}

	public static void listAllBooks() {
		List<Book> books = new ArrayList<>(bookInventory.values());
		Output.displayBooks(books);
	}

	public static void findABook() {
		System.out.print("Enter ISBN: ");
		String isbn = InputUtil.readInput();
		try {
			Book book = bookInventory.get(isbn);
			if (book == null) {
				throw new ResourceNotFoundException("ISBN not found", 404);
			} else {
				System.out.println("Book Name: " + book.getBookName() + " Author: " + book.getAuthorName() + " Price: " + book.getBookPrice() + " Time of Arrival: " + book.getArrivalTime());
			}
		} catch (ResourceNotFoundException e) {
			System.out.println("Error! "+e.getMessage() + "\n" + "Status code: "+ e.getCode());
		}
	}

	public static Book getBookByISBN(String isbn) {
		Book book = bookInventory.get(isbn);
		try {
			if (book == null) {
				throw new ResourceNotFoundException("Book not found in the Inventory", 404);
			}
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage(), e.getCode());
		}
		return book;
	}

	public static void searchABook() {
		System.out.print("Search: ");
		String search = InputUtil.readInput();
		List<Book> searchResult = new ArrayList<>();
		for (Map.Entry<String, Book> entry : bookInventory.entrySet()) {
			if (entry.getValue().getBookName().contains(search) ||
					entry.getValue().getAuthorName().contains(search) ||
					entry.getValue().getDescription().contains(search)) {
				searchResult.add(entry.getValue());
			}
		}
		if (searchResult.isEmpty()) {
			System.out.println("No Results Found!");
		} else {
			Output.displayBooks(searchResult);
		}
	}
}