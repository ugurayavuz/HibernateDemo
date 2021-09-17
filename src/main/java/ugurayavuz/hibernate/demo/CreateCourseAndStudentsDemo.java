package ugurayavuz.hibernate.demo;

import com.ugurayavuz.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {

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

            // create a course
            Course tempCourse = new Course("Pacman - How to score one million points?");

            // create the students
            Student tempStudent1= new Student("John", "Doe", "johndoe@gmail.com");
            Student tempStudent2= new Student("Mary", "Public", "marypublic@gmail.com");

            // add students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            // save the students
            System.out.println("Saving course and students...");
            session.persist(tempCourse);
            System.out.println("Saved course: " +tempCourse + "Saved students: " + tempCourse.getStudents());

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
