package com.rss.model;

import java.util.Date;

/**
 * Created by nassim_a on 22-Jan-17.
 */
public class FeedArticle {
    String title;
    String description;
    String img_url;
    Date date;

    public FeedArticle(String img_url, String title, String description, Date date) {
        this.title = title;
        this.img_url = img_url;
        this.date = date;
        this.description = description;
    }

    public String getImgUrl() {
        return img_url;
    }
    public String getTitle() {
        return title;
    }
}
