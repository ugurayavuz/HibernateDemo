package ugurayavuz.hibernate.demo;

import com.ugurayavuz.hibernate.demo.entity.Course;
import com.ugurayavuz.hibernate.demo.entity.Instructor;
import com.ugurayavuz.hibernate.demo.entity.InstructorDetail;
import com.ugurayavuz.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCourseAndReviewsDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // get course from the db
            int theId = 10;
            Course tempCourse=session.get(Course.class, theId);

            // print the course
            System.out.println("Course: " + tempCourse);

            // print the course reviews
            System.out.println(tempCourse.getReviews());

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {

            // add clean up code
            session.close();

            factory.close();
        }
    }

}