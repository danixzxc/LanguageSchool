package com.grishel.languageschool.server.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
        sessionFactory = null;
    }

    public static Session openSession() {
    	return sessionFactory.getCurrentSession();
    }
    
    public static Session getCurrentSession() {
    	return sessionFactory.getCurrentSession();
    }
    
    
}
