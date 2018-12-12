package com.spring.boot.handler;

/**
 * Created by Yang on 2018/12/12.
 */
public class Response {
    private String status;
    //private Result result = new Result();
    private String errorCode;
    private String message;

    protected Response(String message, String code) {
        this.status = "500";
        this.errorCode = code;
        this.message = message;
    }

    public static Response error(String message) {
        return new Response(message, "");
    }
}