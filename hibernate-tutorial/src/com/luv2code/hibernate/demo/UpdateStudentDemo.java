package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
//		create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
//		create session
		Session session = factory.getCurrentSession();
		
		try {			
			int studentId = 1;
			
			// now get the new session
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student base on the id: primary key
			System.out.println("\nGetting student id with id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating student...");
			myStudent.setFirstName("Boong");
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
			// UPDATE WITH QUERY
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// update email for all stuents
			System.out.println("Update email for all students");
			
			session.createQuery("update Student set email = 'foo@gmail.com'")
							.executeUpdate();
			
			// commit the transaction
			session.getTransaction().commit();
		}  
		finally {
			session.close();
		}

	}

}
