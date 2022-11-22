package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndReviewDemo {

	public static void main(String[] args) {
//		create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.buildSessionFactory();
		
//		create session
		Session session = factory.getCurrentSession();
		
		try {	
			
			// start a transaction
			session.beginTransaction();
			
			// create course
			Course temCourse = new Course("Springboot - From Zero to Hero");
			
			// add some reviews
			temCourse.addReview(new Review("Great course...love it"));
			temCourse.addReview(new Review("Cool course, job well done"));
			temCourse.addReview(new Review("What is dumb course"));
			
			// save the course ... and leverage the cascade All
			System.out.println("Saving the course");
			System.out.println(temCourse);
			System.out.println(temCourse.getReviews());
			session.save(temCourse);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}  
		finally {
			session.close();
			factory.close();
		}

	}

}
