package com.spring.boot.service.web.impl;

import com.spring.boot.bean.MenuRole;
import com.spring.boot.service.web.MenuRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 */
@Service
public class MenuRoleServiceImpl implements MenuRoleService{
    @Override
    public List<MenuRole> findMenuRoleInfoByRoleId(long roleId){
        return null;
    }

}
