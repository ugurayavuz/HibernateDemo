package ugurayavuz.hibernate.demo;

import com.ugurayavuz.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.util.Date;

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

            String theDateOfBirthStr = "06/02/1996";
            Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);

            Student tempStudent = new Student("Ugur", "Yavuz",
                    theDateOfBirth, "ugura.yavuz@gmail.com");
            // start a transaction
            session.beginTransaction();
            // save the student object
            System.out.println("Saving the student...");
            session.save(tempStudent);
            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
