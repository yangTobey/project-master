package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.User;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface UserDao {

    User findByUserId(Map<String, Object> map);

    /**
     * 根据用户账号查找用户信息你
     * @param map
     * @return
     */
    User findByUserAccount(Map<String, Object> map);

    /**
     * 更新账号密码
     * @param map
     * @return
     */
    int updatePassword(Map<String, Object> map);
}
