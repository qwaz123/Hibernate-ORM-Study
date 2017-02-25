package com.util;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;

/*
 * Note:
 * 		Tomcat is a servlet container, not a Java EE server.
 * 		No @PersistenceContext injection of a container managed persistence unit is available.  
 * */
public class EntityManagerUtil {

	private static EntityManagerFactory emf = null;

	static{
		emf = Persistence.createEntityManagerFactory( "CRM" );
	}
	
	public static EntityManagerFactory getEntityManagerFactory(){
		return emf;
	}
	public static SessionFactory getSessionFactory(){
		return emf.unwrap( SessionFactory.class );
	}
}
