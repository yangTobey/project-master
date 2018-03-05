package com.spring.boot.service;

import com.spring.boot.bean.master.User;

/**
 * Created by Administrator on 2018/1/25.
 *
 */
public interface UserService {
    /**
     * 根据用户id查找用户信息
     *
     * @param userId
     * @return
     */
    User findByUserId(String userId);

    /**
     * 根据用户账号查找用户信息你
     * @param account
     * @return
     */
    User findByUserAccount(String account);

    int updatePassword(long userId, String password, String newPassword);

    int addUser(String userAccount, String password, String companyId,String roldId,String departmentId);

    int updateUser(String userId , String companyId,String roldId,String departmentId);

    int deleteUser(String userId);
}
