package ugurayavuz.hibernate.demo;

import com.ugurayavuz.hibernate.demo.entity.Course;
import com.ugurayavuz.hibernate.demo.entity.Instructor;
import com.ugurayavuz.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

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

            // create the objects

			Instructor tempInstructor = 
					new Instructor("Pismaniye", "Yavuz", "pismaniye.yavuz@gmail.com");
			
			InstructorDetail tempInstructorDetail =
					new InstructorDetail(
							"http://www.pismaniyethecat.com/youtube",
							"Sleeping");


//            Instructor tempInstructor =
//                    new Instructor("Madhu", "Patel", "madhu@luv2code.com");
//
//            InstructorDetail tempInstructorDetail =
//                    new InstructorDetail(
//                            "http://www.youtube.com",
//                            "Guitar");

            // associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // start a transaction
            session.beginTransaction();

            // save the instructor
            //
            // Note: this will ALSO save the details object
            // because of CascadeType.ALL
            //
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            //add clean up code
            session.close();

            factory.close();
        }
    }

}