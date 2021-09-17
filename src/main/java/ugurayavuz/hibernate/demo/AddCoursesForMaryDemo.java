package ugurayavuz.hibernate.demo;

import com.ugurayavuz.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForMaryDemo {

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

            // get the student "Mary" from the database
            int theId=1;
            Student tempStudent = session.get(Student.class, theId);
            System.out.println("\nLoaded student: " + tempStudent);
            System.out.println("Courses: " + tempStudent.getCourses());

            // create more courses
            Course tempCourse1 = new Course("Rubik's Cube - How to speed cube");
            Course tempCourse2 = new Course("Atari 2600 - Game Developement");

            // add student to the courses
            tempCourse1.addStudent(tempStudent);
            tempCourse2.addStudent(tempStudent);

            // save the courses
            System.out.println("\nSaving the new courses...");
            session.save(tempCourse1);
            session.save(tempCourse2);
            System.out.println("\nSaved courses: " + tempCourse1 + "\n"+ tempCourse2);
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