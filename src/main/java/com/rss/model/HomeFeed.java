package com.rss.model;

/**
 * Created by nassim_a on 22-Jan-17.
 */
public class HomeFeed {
    private String  title;
    private String  url;

    public HomeFeed(String url, String title) {
        this.title = title;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
    public String getTitle() {
        return title;
    }
}
