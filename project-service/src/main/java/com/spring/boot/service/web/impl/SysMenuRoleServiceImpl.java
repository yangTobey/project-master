package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysMenuRole;
import com.spring.boot.service.web.SysMenuRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 */
@Service
public class SysMenuRoleServiceImpl implements SysMenuRoleService {
    @Override
    public List<SysMenuRole> findMenuRoleInfoByRoleId(long roleId) {
        return null;
    }

}
