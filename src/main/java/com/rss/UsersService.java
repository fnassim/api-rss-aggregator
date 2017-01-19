package com.rss;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rss.Entities.User;
import com.rss.model.ServiceResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/user")
public class UsersService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceResponse getUser(@QueryParam("username") String _username) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        List<User> result = (List<User>) session.createQuery("from User where username = '" + _username + "'").list();
        tx.commit();
        session.close();
        if (result.size() == 0) {
            return new ServiceResponse(404, "User doesn't exist", true);
        }
        String userJSON = "";
        try {
            userJSON = result.get(0).userToJSON();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ServiceResponse(200, userJSON, false);
    }

    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ServiceResponse newUser(User _user) {


        SessionFactory sessionFactory;
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        session.save(_user);
        try {
            tx.commit();
        } catch (PersistenceException e) {
            return new ServiceResponse(403, "Username already exists", true);
        }
        session.close();

        return new ServiceResponse(200, "mdp", false);
    }

}