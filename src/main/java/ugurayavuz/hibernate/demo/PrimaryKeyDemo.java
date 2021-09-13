package ugurayavuz.hibernate.demo;

import com.ugurayavuz.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            System.out.println("Creating 3 student objects...");

            Student tempStudent1 = new Student("Rachid", "Ghezzal", "rachidghezzal@gmail.com");
            Student tempStudent2 = new Student("Michy", "Batshuayi", "michy1903@gmail.com");
            Student tempStudent3 = new Student("Marek", "Hamsik", "Hamsik61@gmail.com");

            session.beginTransaction();

            System.out.println("Saving the student...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {

        }
    }
}
