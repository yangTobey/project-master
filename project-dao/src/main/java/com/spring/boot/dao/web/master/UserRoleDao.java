package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.UserRole;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface UserRoleDao {
    /**
     * 根据用户id查找用户权限列表
     * @param map 参数map集合（userId）
     * @return
     */
    List<UserRole> findRoleByUserId(Map<String, Object> map);
}
