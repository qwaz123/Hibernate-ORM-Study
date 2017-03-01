package com.util;

import org.hibernate.SessionFactory;

public class HibernateUtil {

    public static SessionFactory getSessionFactory() {
    	
    	return EntityManagerUtil.getEntityManagerFactory().unwrap(SessionFactory.class);
    }
}
