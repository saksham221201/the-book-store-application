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
        session.saveOrUpdate(book);
        transaction.commit();
        session.close();
    }

    public List<Book> getAllBooks(){
        Session session = sessionFactory.openSession();
        return session.createQuery(" from Book", Book.class).getResultList();
    }
}
