package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
//		create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
//		create session
		Session session = factory.getCurrentSession();
		
		try {			
		
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			// query student: lastName = "Nguyen"
			theStudents = session.createQuery("from Student s where s.lastName = 'Nguyen'").getResultList();
			
			// display the students
			System.out.println("\n\nStudents who has lastName is Nguyen");
			displayStudents(theStudents);
			
			// query students: lastName = "Nguyen" OR firstName = "Hung"
			theStudents = session.createQuery("from Student s where s.lastName = 'Nguyen'"
					+ " OR s.firstName = 'Hung'").getResultList();
			
			// display the students
			System.out.println("\n\nStudents who has lastName is Nguyen or firstName is Hung");
			displayStudents(theStudents);
			
			// query students where email LIKE '%vuong@gmail.com'
			theStudents = session.createQuery("from Student s where s.email"
					+ " LIKE '%vuong@gmail.com'").getResultList();
			
			// display the students
			System.out.println("\n\nStudents who has email end with 'vuong@gmail.com'");
			displayStudents(theStudents);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}  
		finally {
			session.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student temStudent : theStudents) {
			System.out.println(temStudent);
		}
	}

}
