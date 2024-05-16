package com.nagarro.service;

import com.nagarro.entity.Book;
import com.nagarro.io.Output;
import com.nagarro.util.InputUtil;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	private MockedStatic<InputUtil> inputMock;
	private MockedStatic<Output> outputMock;
    
    @BeforeEach
    public void setUp() {
    	inputMock = mockStatic(InputUtil.class);
    	outputMock = mockStatic(Output.class);
    	BookService.preFillData();
    }
    
    @AfterEach
    public void tearDown() {
    	inputMock.close();
    	outputMock.close();
    }

	@Test
    @DisplayName("Add Book")
    void testAddBook() {
		
		inputMock.when(InputUtil :: readInput).thenReturn("h5","Test Book","Test Author","Test desc");
        
        inputMock.when(InputUtil :: readDoubleInput).thenReturn(4000.0);
        
        BookService.addBook();
        
        outputMock.verify(()-> Output.displayBooks(anyList()),times(1));

    }

    @Test
    void updateBook() {   	

		inputMock.when(InputUtil :: readInput).thenReturn("A1","Test Book","Test Author","Test desc");
        
        inputMock.when(InputUtil :: readDoubleInput).thenReturn(4000.0);
        
        BookService.updateBook();
        
        Book updatedBook = BookService.getBookByISBN("A1");
        assertEquals("Test Book", updatedBook.getBookName());
        assertEquals("Test Author", updatedBook.getAuthorName());
        assertEquals("Test desc", updatedBook.getDescription());
        assertEquals(4000.0, updatedBook.getBookPrice(), 0.01);
        
    }

    @Test
    void updateQuantity() {
    	BookService.updateQuantity("B2");
    	Book testBook = BookService.getBookByISBN("B2");
    	
    	assertEquals(4, testBook.getQuantity());
    }

    @Test
    void markOutOfStock() {
    	
    	inputMock.when(InputUtil :: readInput).thenReturn("C3");
    	
    	BookService.markOutOfStock();
    	
    	Book outOfStockBook = BookService.getBookByISBN("C3");
    	
    	assertEquals(0, outOfStockBook.getQuantity());
    }

    @Test
    void listAllBooks() {
    	BookService.listAllBooks();
    	outputMock.verify(()-> Output.displayBooks(anyList()),times(1));
    }

    @Test
    void findABook() {
    	inputMock.when(InputUtil :: readInput).thenReturn("D4");
    	
    }

    @Test
    void getBookByISBN() {
    	
    	inputMock.when(InputUtil :: readInput).thenReturn("E5");
    	
    	Book testBook = BookService.getBookByISBN("E5");
    	
    	assertEquals("ONCE A THIEF", testBook.getBookName());
    	assertEquals("CHRISTOPHER REICH", testBook.getAuthorName());
    	assertEquals("FACING ENEMIES AT EVERY TURN, PRIVATE SPY.", testBook.getDescription());
    	assertEquals(4375.0, testBook.getBookPrice(), 0.01);
    	
    }

    @Test
    void searchABook() {
    	inputMock.when(InputUtil :: readInput).thenReturn("STORY OF MY LIFE");
    	
    	BookService.searchABook();
    	
    	outputMock.verify(()-> Output.displayBooks(anyList()),times(1));
    }

    @Test
    void getTopFiveSelling() {
    	BookService.getTopFiveSelling();
    	
    	outputMock.verify(()-> Output.displayBooks(anyList()),times(1));
    }

    @Test
    void getMaximumProfit() {
    	
    }
}