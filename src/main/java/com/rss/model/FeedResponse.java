package com.rss.model;

import java.util.ArrayList;

/**
 * Created by nassim_a on 22-Jan-17.
 */
public class FeedResponse {
    private int     code;
    private String  message;
    private ArrayList<HomeFeed> feed;
    private boolean error;

    public FeedResponse(int code, String message, boolean error, ArrayList<HomeFeed> feed) {
        this.code = code;
        this.message = message;
        this.feed = feed;
        this.error = error;
    }

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public ArrayList<HomeFeed> getFeed() { return feed; }
    public boolean isError() { return error; }
}
