package ugurayavuz.hibernate.demo;

import com.ugurayavuz.hibernate.demo.entity.Instructor;
import com.ugurayavuz.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // get the instructor detail object
            int theId=3;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class,
                    theId);

            // print the instructor detail
            System.out.println("\ntempInstructorDetail: " + tempInstructorDetail);

            // print the associated instructor
            System.out.println("\nthe associated instructor: " +
                    tempInstructorDetail.getInstructor());

            // delete the instructor detail
            System.out.println("Deleting tempInstructorDetail: " + tempInstructorDetail);
            session.delete(tempInstructorDetail);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            // handle connection leak issue
            session.close();
            factory.close();
        }
    }

}