package com.spring.boot.exception;

/**
 * Created by ace on 2017/9/8.
 */
public class XssException extends RuntimeException {
    private int status = 200;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public XssException() {
        super("您输入的内容含有非法字符，请重新输入或者联系系统管理员进行处理，谢谢！！");
    }

    public XssException(String message, int status) {
        super(message);
        this.status = status;
    }

    public XssException(String message) {
        super(message);
    }

    public XssException(String message, Throwable cause) {
        super(message, cause);
    }

    public XssException(Throwable cause) {
        super(cause);
    }

    public XssException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
