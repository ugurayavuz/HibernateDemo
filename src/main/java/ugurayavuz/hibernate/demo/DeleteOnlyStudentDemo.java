package ugurayavuz.hibernate.demo;

import com.ugurayavuz.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteOnlyStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // get the Student from the db
            int studentId=2;
            Student tempStudent = session.get(Student.class, studentId);
            System.out.println("\nLoaded student: " +tempStudent);
            System.out.println("Courses: " + tempStudent.getCourses());

            // delete the student
            System.out.println("\nDeleting the student: " + tempStudent);
            session.delete(tempStudent);
            System.out.println("\nStudent deleted.");

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