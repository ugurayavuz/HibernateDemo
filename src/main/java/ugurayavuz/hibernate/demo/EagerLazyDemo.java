package ugurayavuz.hibernate.demo;

import com.ugurayavuz.hibernate.demo.entity.Course;
import com.ugurayavuz.hibernate.demo.entity.Instructor;
import com.ugurayavuz.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // get the instructor from db
            int theId=1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            System.out.println("Debug: Instructor: " + tempInstructor);
            // get course for the instructor
            System.out.println("Debug: Courses: " + tempInstructor.getCourses());

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Debug: Done!");
        }
        finally {
            //add clean up code
            session.close();

            factory.close();
        }
    }

}