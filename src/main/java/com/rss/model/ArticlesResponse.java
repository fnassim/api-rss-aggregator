package com.rss.model;

import java.util.ArrayList;

/**
 * Created by nassim_a on 22-Jan-17.
 */
public class ArticlesResponse {
    private int     code;
    private String  message;
    private ArrayList<FeedArticle> articles;
    private boolean error;

    public ArticlesResponse(int code, String message, boolean error, ArrayList<FeedArticle> articles) {
        this.code = code;
        this.message = message;
        this.articles = articles;
        this.error = error;
    }

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public ArrayList<FeedArticle> getFeed() { return articles; }
    public boolean isError() { return error; }
}
