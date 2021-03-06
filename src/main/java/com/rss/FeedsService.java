package com.rss;

import com.google.gson.Gson;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.rss.Entities.Feed;
import com.rss.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 21/01/17.
 */

@Path("/feeds")
public class FeedsService {
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public FeedResponse getFeeds(@QueryParam("user_id") int user_id) {
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
            return new FeedResponse(404, "User doesn't have any feed", true, null);
        }

        ArrayList<HomeFeed> feed = new ArrayList<>();
        SyndFeed rss;
        for (int i = 0; i < result.size(); i++){

            try {
                rss = new SyndFeedInput().build(new XmlReader(new URL(result.get(i).getUrl())));
                String url = result.get(i).getUrl() == null ? "" : result.get(i).getUrl();
                String title = rss.getTitle() == null ? "" : rss.getTitle();
                String description = rss.getDescription() == null ? "" : rss.getDescription();
                feed.add(new HomeFeed(result.get(i).getId(), url, title, description));
            } catch (FeedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FeedResponse resp = new FeedResponse(200, "ok",false, feed);
        return resp;
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public FeedResponse newFeed(Feed _feed) {


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
            return new FeedResponse(403, "Feed already exists", true, null);
        }
        session.close();

        ArrayList<HomeFeed> newFeed = new ArrayList<>();
        SyndFeed rss;
        try {
            rss = new SyndFeedInput().build(new XmlReader(new URL(_feed.getUrl())));
            newFeed.add(new HomeFeed(_feed.getId(), _feed.getUrl(), rss.getTitle(), rss.getDescription()));
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (newFeed.size() < 1)
            return new FeedResponse(500, "Bad feed format", true, null);
        else
            return new FeedResponse(200, "Success", false, newFeed);
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
            return new ServiceResponse(404, "Feed doesn't exists", true);
        }
    }

    @GET
    @Path("/articles")
    @Produces(MediaType.APPLICATION_JSON)
    public ArticlesResponse getArticles(@QueryParam("feed_url") String feed_url) {

        SyndFeed feed;
        String img = null;
        try {
            feed = new SyndFeedInput().build(new XmlReader(new URL(feed_url)));
            ArrayList<FeedArticle> articles = new ArrayList<>();
            for (int i = 0; i < feed.getEntries().size(); i++){
               SyndEntry entry = feed.getEntries().get(i);
               if (entry.getEnclosures() != null)
                   if (entry.getEnclosures().size() > 0) {
                        img = entry.getEnclosures().get(0).getUrl();
                   }
                   else {
                       img = "";
                   }
                String image = img == null ? "" : img;
                String title = entry.getTitle() == null ? "" : entry.getTitle();
                String description = entry.getDescription() == null ? "" : entry.getDescription().getValue();
                String date = entry.getPublishedDate() == null ? "" : entry.getPublishedDate().toString();
                String url = entry.getLink() == null ? "" : entry.getLink();
//                articles.add(new FeedArticle(img, entry.getTitle(), entry.getDescription().getValue(), entry.getPublishedDate().toString(), entry.getLink()));
                articles.add(new FeedArticle(image, title, description, date, url));

            }
            return new ArticlesResponse(200, "OK", false, articles);
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArticlesResponse(500, "something went wrong", true, null);
    }
}
