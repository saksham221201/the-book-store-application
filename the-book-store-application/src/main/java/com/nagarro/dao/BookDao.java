package com.nagarro.dao;

import com.nagarro.config.HibernateConfiguration;
import com.nagarro.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class BookDao {
    private final SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();

    public void saveBookToDatabase(Book book){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(book);
        //catch duplicate entry - org.hibernate.exception.ConstraintViolationException.
        transaction.commit();
        session.close();
    }

    public void updateBook(Book book){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(book);
        //catch duplicate entry - org.hibernate.exception.ConstraintViolationException.
        transaction.commit();
        session.close();
    }

    public List<Book> getAllBooks(){
        Session session = sessionFactory.openSession();
        return session.createQuery(" from Book", Book.class).getResultList();
    }
    
    public Book getBookByIsbn(String isbn){
    	Session session = sessionFactory.openSession();
    	return session.createQuery(" from Book where isbn="+"\'"+isbn+"\'",Book.class).getSingleResult();
    }
    
    public List<Book> getBooksBySearch(String search){
        Session session = sessionFactory.openSession();
        return session.createQuery(" from Book where authorName="+"\'"+search+"\'"+" or bookName="+"\'"+search+"\'"+" or description="+"\'"+search+"\'", Book.class).getResultList();
    }
}
