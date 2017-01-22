package com.rss;

import com.google.gson.Gson;
import com.rss.Entities.Feed;
import com.rss.model.ServiceResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by root on 21/01/17.
 */

@Path("/feeds")
public class FeedsService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceResponse getFeeds(@QueryParam("user_id") int user_id) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        List<Feed> result = (List<Feed>) session.createQuery("from Feed where user_id = '" + user_id + "'").list();
        tx.commit();
        session.close();
        if (result.size() == 0) {
            return new ServiceResponse(404, "User doesn't have any feed", true);
        }
        String json = new Gson().toJson(result);
        return new ServiceResponse(200, json, false);
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ServiceResponse newFeed(Feed _feed) {


        SessionFactory sessionFactory;
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        session.save(_feed);
        try {
            tx.commit();
        } catch (PersistenceException e) {
            return new ServiceResponse(403, "Username already exists", true);
        }
        session.close();

        return new ServiceResponse(200, "new feed", false);
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ServiceResponse deleteFeed(@QueryParam("feed_id") int feed_id) {


        SessionFactory sessionFactory;
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete Feed where id = :ID");
        query.setParameter("ID", new Long(feed_id));

        int result = query.executeUpdate();
        session.close();

        if (result == 1) {
            return new ServiceResponse(200, "Feed deleted", false);
        }
        else {
            return new ServiceResponse(404, "Feed doesn't exists", false);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceResponse getArticles(@QueryParam("feed_url") String feed_url) {

        return new ServiceResponse(200, feed_url, false);
    }


}
