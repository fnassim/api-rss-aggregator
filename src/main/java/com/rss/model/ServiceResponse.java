package com.rss.model;

/**
 * Created by root on 07/01/17.
 */
public class ServiceResponse {

    private int     code;
    private String  message;
    private boolean error;

    public ServiceResponse(int code, String message, boolean error) {
        this.code = code;
        this.message = message;
        this.error = error;
    }



    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() { return error; }
}
