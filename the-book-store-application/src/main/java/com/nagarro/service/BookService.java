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

	public static void fillData() {
		Book book1 = new Book("a1", "Harry Potter", "DumbbellDore", "This is Harry Potter Series.", LocalDateTime.now());
		Book book2 = new Book("b2", "Two States", "Chetan Bhagat", "This is Love Story from two different states.", LocalDateTime.now());
		Book book3 = new Book("c3", "Harry Potter 2", "DumbbellDore", "This is Harry Potter Series.", LocalDateTime.now());
		bookInventory.put("a1", book1);
		bookInventory.put("b2", book2);
		bookInventory.put("c3", book3);
	}

	public static void addBook() {
		try {
			System.out.print("Enter ISBN: ");
			String isbn = InputUtil.readInput();
			if(bookInventory.containsKey(isbn)) {
				throw new ResourceAlreadyExistsException("This isbn: "+isbn+ " already exists", 400);
			}
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
		} catch (ResourceAlreadyExistsException e) {
			System.out.println("Error - "+e.getMessage()+"\n"+"Status code - "+ e.getCode());
		}
	}

	public static void updateBook() {
		try {
			System.out.print("Enter ISBN: ");
			String isbn = InputUtil.readInput();
			if(!bookInventory.containsKey(isbn)) {
				throw new ResourceNotFoundException("This isbn: "+isbn+ " does not exists", 404);
			}
			System.out.print("Enter Book Name: ");
			String bookName = InputUtil.readInput();
			System.out.print("Enter Book Author: ");
			String bookAuthor = InputUtil.readInput();
			System.out.print("Enter Description: ");
			String description = InputUtil.readInput();
			Book book = new Book(isbn, bookName, bookAuthor, description, LocalDateTime.now());
			bookInventory.put(isbn, book);
			System.out.println("Book Updated Successfully!!");
		} catch (ResourceNotFoundException e) {
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
		Book book = bookInventory.get(isbn);

		System.out.println("Book Name: " + book.getBookName() + " Author: " + book.getAuthorName());
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