package com.rss;

import com.rss.Entities.User;
import com.rss.model.Address;
import com.rss.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/user")
public class UsersService {

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee Hello() {
        Address a = new Address();
        Employee e = new Employee();
        e.setAddress(a);
        e.setId(42);
        e.setName("loLzar");
        e.setPermanent(true);
        e.setPhoneNumbers(new long[]{0625252525, 062545654});
        e.setRole("LELOL");

        SessionFactory sessionFactory;
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        User task = new User();
        task.setId(new Long(3));
        session.save(task);
        tx.commit();
        session.close();

        //String result = "HELLO!";
        // return Response.status(200).entity(result).build();
        return e;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Employee HelloWorld() {

        Address a = new Address();
        Employee e = new Employee();
        e.setAddress(a);
        e.setId(42);
        e.setName("loLzar");
        e.setPermanent(true);
        e.setPhoneNumbers(new long[]{0625252525, 062545654});
        e.setRole("LELOL");

        SessionFactory sessionFactory;
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        User task = new User();
        task.setId(new Long(2));
        session.save(task);
        tx.commit();
        session.close();

        //String result = "HELLO!";
       // return Response.status(200).entity(result).build();
        return e;
    }

}