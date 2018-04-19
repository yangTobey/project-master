package com.spring.boot.util;

/**
 * Created by Administrator on 2018/4/19.
 */
public class RuntimeException extends  Exception{
    public RuntimeException() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public RuntimeException(String message) {
        super(message);
    }

    public RuntimeException(Throwable cause) {
        super(cause);
    }

    public RuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
