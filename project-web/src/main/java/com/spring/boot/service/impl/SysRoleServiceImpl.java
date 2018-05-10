package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysRole;
import com.spring.boot.service.SysRoleService;
import com.spring.boot.service.web.SysRoleBusinessService;
import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    private static final Logger logger = Logger.getLogger(SysRoleServiceImpl.class);
    @Autowired
    private SysRoleBusinessService sysRoleBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Map<String, Object> getSysRoleList(Integer limit, Integer offset) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        map.put("limit", limit);
        map.put("offset", offset);
        try {
            resultMap.put("total", sysRoleBusinessService.getSysRoleListTotal());
            resultMap.put("list", sysRoleBusinessService.getSysRoleList(map));
            return R.ok().putData(200, resultMap, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取部门信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> getAllSysRole() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        map.put("limit", null);
        map.put("offset", null);
        try {
            resultMap.put("list", sysRoleBusinessService.getSysRoleList(map));
            return R.ok().putData(200, resultMap, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取部门信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addSysRole(String roleName, String moduleIds, String remark) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleName", roleName);
        map.put("remark", remark);
        map.put("createTime", UtilHelper.getNowTimeStr());
        SysRole sysRole = new SysRole();
        sysRole.setRoleName(roleName);
        sysRole.setRemark(remark);
        sysRole.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
        int count = sysRoleBusinessService.addSysRole(sysRole);
        if (count > 0) {
            //当选择的权限不为空时
            if(!UtilHelper.isEmpty(moduleIds)){
                String[] moduleIdArray;
                //去掉最后那个逗号，在进行获取数据
                moduleIdArray = moduleIds.substring(0, moduleIds.length()).split(",");
                for (String moduleId : moduleIdArray) {
                    sysRoleBusinessService.addSysRoleMenu(Long.valueOf(moduleId), sysRole.getRoleId());
                }
            }
            return R.ok(200, "新增成功！");
        } else {
            return R.error(500, "新增失败，请联系管理员！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> updateSysRole(Long roleId, String roleName, String remark, String moduleIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleId", roleId);
        map.put("roleName", roleName);
        map.put("remark", remark);
        int count = sysRoleBusinessService.updateSysRole(map);
        if (count > 0) {
            int deleteCount = sysRoleBusinessService.deleteSysRoleMenu(roleId);
            //当选择的权限不为空时
            if(!UtilHelper.isEmpty(moduleIds)){
                String[] moduleIdArray;
                //去掉最后那个逗号，在进行获取数据
                moduleIdArray = moduleIds.substring(0, moduleIds.length()).split(",");
                for (String moduleId : moduleIdArray) {
                    sysRoleBusinessService.addSysRoleMenu(Long.valueOf(moduleId), roleId);
                }
            }
            return R.ok(200, "更新成功！");
        }
        return R.error(500, "更新失败，请联系管理员！");
    }

    @Override
    public Map<String, Object> deleteSysRole(String roleId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleId", roleId);
        try {
            int count = sysRoleBusinessService.deleteSysRole(map);
            if (count > 0) {
                return R.ok(200, "删除成功！");
            } else {
                return R.error(500, "删除失败，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除角色信息出错：" + e.getMessage());
            return R.error(500, "删除角色信息失败，服务器异常，请联系管理员！");
        }

    }
}
