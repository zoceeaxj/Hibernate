package com.java;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Main {

	public static void main(String[] args) {
		
		// loads configuration and mappings
		Configuration configuration = new Configuration().configure();
		ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
		registry.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = registry.buildServiceRegistry();

		// builds a session factory from the service registry
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		// obtains the session
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		// creates a Book entity
		Book newBook = new Book();
		newBook.setTitle(" Hibernate4.2.21");
		newBook.setDescription("good1");
		newBook.setPublishedDate(new Date());		
		newBook.setAuthor(new Author("James", "Cameron@gmail.com"));
		
		// persists the book entity
		Long bookId = (Long) session.save(newBook);
		
		
		  // gets the book entity back
		  
		  Book book = (Book) session.get(Book.class, bookId);
		  System.out.println("Book's Title: " + book.getTitle());
		  System.out.println("Book's Description: " + book.getDescription());
		  
		  Author author = book.getAuthor(); System.out.println("Author's Name: " +
		  author.getName()); System.out.println("Author's Email: " +
		  author.getEmail());
		  
		  Book newBook2 = new Book("Python","basic",new Date(), new
		  Author("Bindhu","prabhat@gmail.com")); session.save(newBook2);
		  //session.save(new Author("BBBindhu","prabhat@gmail.com"));
		 		
		session.getTransaction().commit();
		session.close();		
		
	}

}
