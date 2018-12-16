package com.spring.boot.exception.auth;


import com.spring.boot.constant.CommonConstants;
import com.spring.boot.exception.BaseException;

/**
 * Created by ace on 2017/9/10.
 */
public class ClientInvalidException extends BaseException {
    public ClientInvalidException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
