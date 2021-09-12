package ugurayavuz.hibernate.demo;

import com.ugurayavuz.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            int studentId = 1;

            session=factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("\nGetting student with id: " + studentId);

            Student myStudent = session.get(Student.class, studentId);

            System.out.println("Updating student...");
            myStudent.setFirstName("Scooby");

            System.out.println("Done!");
            /////////
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();

            //update email for all students
            System.out.println("\n\nUpdate email for all students");

            session.createQuery("update Student set email='foo@gmail.com'")
                    .executeUpdate();

            session.getTransaction().commit();
            System.out.println("Done!");

        }
        finally {
            factory.close();
        }

    }
}
