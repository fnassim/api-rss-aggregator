package com.rss.model;

import java.util.Date;

/**
 * Created by nassim_a on 22-Jan-17.
 */
public class FeedArticle {
    String title;
    String description;
    String url;
    String img_url;
    String date;

    public FeedArticle(String img_url, String title, String description, String date, String url) {
        this.title = title;
        this.img_url = img_url;
        this.date = date;
        this.description = description;
        this.url = url;
    }

    public String getImgUrl() {
        return img_url;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getUrl() { return url; }
}
