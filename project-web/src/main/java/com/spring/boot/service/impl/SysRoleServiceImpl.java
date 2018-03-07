package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysRole;
import com.spring.boot.bean.master.SysUser;
import com.spring.boot.service.SysRoleService;
import com.spring.boot.service.SysUserService;
import com.spring.boot.service.web.SysRoleBusinessService;
import com.spring.boot.service.web.SysUserBusinessService;
import com.spring.boot.util.ShiroUtils;
import com.spring.boot.util.UtilHelper;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleBusinessService sysRoleBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Map<String, Object> getSysRoleList() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total",sysRoleBusinessService.getSysRoleList().size());
        map.put("list",sysRoleBusinessService.getSysRoleList());
        return map;
    }

    @Override
    public int addSysRole(String roleName, String remark) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleName", roleName);
        map.put("remark", remark);
        map.put("createTime", UtilHelper.getNowTimeStr());
        SysRole sysRole = new SysRole();
        sysRole.setRoleName(roleName);
        sysRole.setRemark(remark);
        sysRole.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));

        sysRoleBusinessService.addSysRole(sysRole);
        System.out.println("保存信息：" + sysRole.getRoleId());
        return 1;
    }

    @Override
    public int updateSysRole(String roleId, String roleName, String remark) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleId", roleId);
        map.put("roleName", roleName);
        map.put("remark", remark);
        return sysRoleBusinessService.updateSysRole(map);
    }

    @Override
    public int deleteSysRole(String roleId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleId", roleId);
        return sysRoleBusinessService.deleteSysRole(map);
    }
}
