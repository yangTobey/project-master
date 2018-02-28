package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.UserRole;
import com.spring.boot.dao.web.master.UserRoleDao;
import com.spring.boot.service.web.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/6.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<UserRole> findRoleByUserId(long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        return userRoleDao.findRoleByUserId(map);
    }
}
