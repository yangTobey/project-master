package com.spring.boot.exception.auth;


import com.spring.boot.constant.CommonConstants;
import com.spring.boot.exception.BaseException;

/**
 * Created by ace on 2017/9/8.
 */
public class UserTokenException extends BaseException {
    public UserTokenException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
