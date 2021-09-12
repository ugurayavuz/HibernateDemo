package ugurayavuz.hibernate.demo;

import com.ugurayavuz.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class QueryStudentDemo {

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

            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();

            // display the students
            displayStudents(theStudents);

            // query students: lastName = "Darko"
            theStudents = session.createQuery("from Student s where s.lastName='Darko'").getResultList();
            
            // display the students
            System.out.println("\n\nStudent who have last name of Darko");
            displayStudents(theStudents);

            //query students: lastName = 'Darko' OR firstName 'Donnie'
            theStudents = session.createQuery("from Student s where"
                    + " s.lastName='Darko' or  s.firstName='Michy'").getResultList();
            // display the students
            System.out.println("\n\nStudent who have last name of Darko or first name of Michy");
            displayStudents(theStudents);

            //query students email LIKE '%gmail.com'
            theStudents = session.createQuery("from Student s where"
            + " s.email LIKE '%gmail.com'").getResultList();

            //display the students
            System.out.println("\n\nStudents who have gmail.com mail");
            displayStudents(theStudents);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        }
        finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for(Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }
}
