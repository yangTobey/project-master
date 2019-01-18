package com.spring.boot.exception;

/**
 * Created by yang on 2019/1/15.
 */
public class ArrayIndexOutOfBoundsException extends RuntimeException {
    private int status = 200;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayIndexOutOfBoundsException() {
        super("数组越界，请检查数据格式的正确性！！");
    }

    public ArrayIndexOutOfBoundsException(String message, int status) {
        super(message);
        this.status = status;
    }

    public ArrayIndexOutOfBoundsException(String message) {
        super(message);
    }

    public ArrayIndexOutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArrayIndexOutOfBoundsException(Throwable cause) {
        super(cause);
    }

    public ArrayIndexOutOfBoundsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
