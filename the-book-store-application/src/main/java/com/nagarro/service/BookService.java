package com.nagarro.service;

import com.nagarro.entity.Book;
import com.nagarro.io.Output;
import com.nagarro.exception.ResourceAlreadyExistsException;
import com.nagarro.exception.ResourceNotFoundException;
import com.nagarro.util.InputUtil;

import java.time.LocalDateTime;
import java.util.*;

public class BookService {
	private static final Map<String, Book> bookInventory = new TreeMap<>(Collections.reverseOrder());

	public static void preFillData() {
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
		List<Book> books = new ArrayList<>(bookInventory.values()).stream()
				.sorted((s1,s2)-> s2.getArrivalTime().compareTo(s1.getArrivalTime())).toList();
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

	public static void getTopFiveSelling() {
		List<Book> books = bookInventory.values().stream().sorted(Comparator.comparingInt(Book::getQuantity))
				.limit(5).toList();
		Output.displayBooks(books);
	}

	public static void getMaximumProfit() {
		List<Book> maximumProfit = bookInventory.values().stream().sorted(Comparator.comparingInt(Book::getQuantity))
				.limit(1).toList();

		System.out.println("Maximum Profit");
		for (Book book : maximumProfit) {
			System.out.println(book.getIsbn() + " " + book.getBookName() + " " + book.getBookPrice() * (5 - book.getQuantity()));
		}
	}

	public static Book getBookByName(String bookName) {
		for (Map.Entry<String, Book> entry : bookInventory.entrySet()) {
			Book book = entry.getValue();
			if (book.getBookName().equalsIgnoreCase(bookName)) {
				return book;
			}
		} throw new ResourceNotFoundException("Book not found in the Inventory", 404);
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
}