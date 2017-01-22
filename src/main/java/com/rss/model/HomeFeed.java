package com.rss.model;

/**
 * Created by nassim_a on 22-Jan-17.
 */
public class HomeFeed {
    private String  title;
    private String  url;
    private String  description;

    public HomeFeed(String url, String title, String description) {
        this.title = title;
        this.url = url;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() { return description; }
}
