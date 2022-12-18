package com.increff.employee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;
    public static void configure()
    {
//        configure hibernate properties
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

//        adding a mapping from java claas (pojo) to database table
        config.addAnnotatedClass(EmployeePojo.class);
//        create hibernate session factory
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(config.getProperties())
                .build();

        sessionFactory = config.buildSessionFactory(serviceRegistryObj);
    }

    public void createSession()
    {
        if(session!=null && session.isOpen())
            return ;
        session = sessionFactory.openSession();
        return ;
    }

    public Session getSession()
    {
        return session;
    }

    public void closeSession()
    {
        if(session!=null)
            session.close();
    }

    public void beginTransaction()
    {
        transaction = session.beginTransaction();
    }

    public void commitTransaction()
    {
        if(transaction!=null)
            transaction.commit();
    }
}
