package com.nagarro.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfiguration {
    private static SessionFactory sessionFactory;

    static {
        try{
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e){
            System.out.println("SessionFactory creation failed " + e.getMessage());
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
