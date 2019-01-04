package com.spring.boot.exception;

/**
 * Created by ace on 2017/9/8.
 */
public class SysException extends RuntimeException {
    private int status = 200;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public SysException() {
        super("系统异常，请联系系统管理员进行处理，谢谢！！");
    }

    public SysException(String message, int status) {
        super(message);
        this.status = status;
    }

    public SysException(String message) {
        super(message);
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysException(Throwable cause) {
        super(cause);
    }

    public SysException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
