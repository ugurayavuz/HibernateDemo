package ugurayavuz.hibernate.demo;

import com.ugurayavuz.hibernate.demo.entity.Course;
import com.ugurayavuz.hibernate.demo.entity.Instructor;
import com.ugurayavuz.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {

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

            // option2: Hibernate query with HQL

            // get the instructor from db
            int theId=1;
            Query<Instructor> query = session.createQuery("select i from Instructor i "
                    + "JOIN FETCH i.courses "
                    + "where i.id=:theInstructorId",
                    Instructor.class);
            // set parameter on query
            query.setParameter("theInstructorId", theId);

            // execute query and get instructor
            Instructor tempInstructor = query.getSingleResult();

            System.out.println("Debug: Instructor: " + tempInstructor);


            // commit transaction
            session.getTransaction().commit();

            //close the session
            session.close();

            System.out.println("\nDebug: The session is now closed!\n");

            System.out.println("Debug: Courses: " + tempInstructor.getCourses());

            System.out.println("Debug: Done!");
        }
        finally {
            //add clean up code
            session.close();

            factory.close();
        }
    }

}