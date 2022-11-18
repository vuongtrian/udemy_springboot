package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
//		create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
//		create session
		Session session = factory.getCurrentSession();
		
		try {			
			// create a student object
			Student tempStudent = new Student("Hieu", "Phan", "hieu.phan@gmail.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the student...");
			session.save(tempStudent);
			
			// commit transaction
			session.getTransaction().commit();
			
			// find out student's id: primary key
			System.out.println("Saved student. Generated id: " + tempStudent.getId());
			
			// now get the new session
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student base on the id: primary key
			System.out.println("\nGetting student id with id: " + tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get complete: " + myStudent);
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}  
		finally {
			session.close();
		}

	}

}
