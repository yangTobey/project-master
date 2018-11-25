package com.spring.boot.aspect;

/**
 * Created by Yang on 2018/11/24.
 */
public class UserMessage {
    @NotNull
    private String userName;
    @NotNull
    private Integer num;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
