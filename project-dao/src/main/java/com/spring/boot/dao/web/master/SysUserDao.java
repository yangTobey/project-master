package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysUser;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysUserDao {

    SysUser findByUserId(Map<String, Object> map);

    /**
     * 根据用户账号查找用户信息你
     * @param map
     * @return
     */
    SysUser findByUserAccount(Map<String, Object> map);

    /**
     * 更新账号密码
     * @param map
     * @return
     */
    int updatePassword(Map<String, Object> map);
    /**
     * 新增用户信息
     *
     * @param map
     * @return
     */
    int addUser(Map<String, Object> map);

    /**
     * 更新用户信息
     *
     * @param map
     * @return
     */
    int updateUserInfo(Map<String, Object> map);

    /**
     * 删除用户信息（只更新用户账号状态，不作删除处理）
     *
     * @param map
     * @return
     */
    int deleteUser(Map<String, Object> map);
}
