package com.nagarro.dao;

import com.nagarro.config.HibernateConfiguration;
import com.nagarro.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDao {
    private final SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();

    public void saveOrderToDatabase(Order order){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(order);
        //catch duplicate entry - org.hibernate.exception.ConstraintViolationException.
        transaction.commit();
        session.close();
    }

    public List<Order> getAllOrders(){
        Session session = sessionFactory.openSession();
        System.out.println("Hii");
        return session.createQuery(" from Order", Order.class).getResultList();
    }
}
