package ugurayavuz.hibernate.demo;

import com.ugurayavuz.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create a session
        Session session = factory.getCurrentSession();

        //use the session object to save Java object
        try {
            // create a student object
            System.out.println("Creating a new student object...");
            Student tempStudent = new Student("Deli", "Coban", "delicoban96@gmail.com");
            // start a transaction
            session.beginTransaction();
            // save the student object
            System.out.println("Saving the student...");
            session.save(tempStudent);
            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
