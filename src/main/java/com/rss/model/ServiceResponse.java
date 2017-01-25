package com.rss.model;

import com.rss.Entities.User;

/**
 * Created by root on 07/01/17.
 */
public class ServiceResponse {
    private int     code;
    private String  message;
    private User    user;
    private boolean error;

    public ServiceResponse(int code, String message, boolean error) {
        this.code = code;
        this.message = message;
        this.error = error;
    }

    public ServiceResponse(int code, User user, boolean error) {
        this.code = code;
        this.user = user;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() { return error; }

    public User getUser() { return user; }
}
