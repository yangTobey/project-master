package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysUser;
import com.spring.boot.bean.master.SysUserCompany;
import com.spring.boot.bean.master.SysUserRole;
import com.spring.boot.dao.web.master.SysUserDao;
import com.spring.boot.service.SysUserService;
import com.spring.boot.service.web.SysUserBusinessService;
import com.spring.boot.service.web.SysUserCompanyBusinessService;
import com.spring.boot.service.web.SysUserRoleBusinessService;
import com.spring.boot.util.R;
import com.spring.boot.util.ShiroUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    private static final Logger logger = Logger.getLogger(SysUserServiceImpl.class);
    @Autowired
    private SysUserBusinessService sysUserBusinessService;
    @Autowired
    private SysUserRoleBusinessService sysUserRoleBusinessService;
    @Autowired
    private SysUserCompanyBusinessService sysUserCompanyBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Map<String, Object> sysUserList(Integer limit, Integer offset) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = map = new HashMap<String, Object>();
        try {
            map.put("limit", limit);
            map.put("offset", offset);
            resultMap.put("total", sysUserBusinessService.sysUserTotal());
            resultMap.put("list", sysUserBusinessService.sysUserList(map));
            return R.ok().putData(200,resultMap,"获取成功！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取用户列表出错："+e.getMessage());
            return R.error(500,"服务器异常，请联系管理员！");
        }
    }

    @Override
    public SysUser findByUserId(String userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        //springboot 集成redis操作缓存
        /*ValueOperations<String, String> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey("admin");
        if(hasKey){
            String value=operations.get("admin");
            System.out.println("redis-admin:"+value);
        }else{
            operations.set("admin","hello world everybody!");
            System.out.println("set-admin-value:"+operations.get("admin"));
        }*/
        return sysUserBusinessService.findByUserId(map);
    }

    @Override
    public Map<String, Object> sysUserInfo() {
        try {
            if(ShiroUtils.getUserEntity()==null){
                return R.error(500,"请登录系统再进行操作功能！");
            }
            if(ShiroUtils.getUserEntity().getUserId()==null){
                return R.error(500,"请登录系统再进行操作功能！");
            }
            Long userId=ShiroUtils.getUserEntity().getUserId();
            SysUser sysUser=sysUserBusinessService.sysUserInfo(userId);
            return R.ok().putData(200,sysUser,"获取成功！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取用户信息出错："+e.getMessage());
            return R.error(500,"获取用户信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public SysUser findByUserAccount(String account) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("account", account);
        return sysUserBusinessService.findByUserAccount(map);
    }

    @Override
    public Map<String, Object> updatePassword(Long userId, String password, String newPassword) {
        Map<String, Object> map = new HashMap<String, Object>();
        //根据用户提交的密码，利用md5加密得到加密后的原密码
        password = new SimpleHash("md5", password, ByteSource.Util.bytes(ShiroUtils.getUserEntity().getAccount()), 2).toHex();
        //根据用户提交的新密码，利用md5加密得到加密后的新密码
        newPassword = new SimpleHash("md5", newPassword, ByteSource.Util.bytes(ShiroUtils.getUserEntity().getAccount()), 2).toHex();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        try {
            int count=sysUserBusinessService.updatePassword(map);
            if(count>0){
                return R.ok(200,"更新成功！");
            }else{
                return R.error(500,"更新失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("更新密码信息出错："+e.getMessage());
            return R.error(500,"更新密码信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> resetSysUserPassword(Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //新密码，利用md5加密得到加密后的新密码,重置密码后默认为111
            String newPassword = new SimpleHash("md5", "111", ByteSource.Util.bytes(ShiroUtils.getUserEntity().getAccount()), 2).toHex();
            map.put("userId", userId);
            map.put("newPassword", newPassword);
            int count=sysUserBusinessService.resetSysUserPassword(map);
            if(count>0){
                return R.ok(200,"重置密码成功！");
            }else{
                return R.error(500,"重置密码失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("重置密码信息出错："+e.getMessage());
            return R.error(500,"重置密码信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object>  addUser(String userAccount, String password, Long companyId, Long roleId, Long departmentId,String userName,String permsCompanyId ) {
        Map<String, Object> map = new HashMap<String, Object>();
        //根据用户提交的密码，利用md5加密得到加密后的原密码
        password = new SimpleHash("md5", password, ByteSource.Util.bytes(userAccount), 2).toHex();
        map.put("account", userAccount);
        map.put("password", password);
        map.put("companyId", companyId);
        map.put("departmentId", departmentId);
        SysUser sysUser=new SysUser();
        sysUser.setAccount(userAccount);
        sysUser.setPassword(password);
        sysUser.setUserName(userName);
        sysUser.setCompanyId(companyId);
        sysUser.setDepartmentId(departmentId);
        try {
            Map<String, Object> userMap = new HashMap<String, Object>();
            userMap.put("account", userAccount);
            SysUser user = sysUserBusinessService.findByUserAccount(userMap);
            if(user!=null){
                return R.error(400, "该账号已存在，不能再次添加！！");
            }
            int count=sysUserBusinessService.addUser(sysUser);
            if(count>0){
                SysUserRole sysUserRole=new SysUserRole();
                sysUserRole.setRoleId(roleId);
                sysUserRole.setUserId(sysUser.getUserId());
                sysUserRoleBusinessService.addUserRole(sysUserRole);

                String[] permsCompanyIdArray;
                //去掉最后那个逗号，在进行获取数据
                permsCompanyIdArray = permsCompanyId.substring(0, permsCompanyId.length() - 1).split(",");
                SysUserCompany sysUserCompany=null;
                for(String id:permsCompanyIdArray){
                    sysUserCompany=new SysUserCompany();
                    sysUserCompany.setCompanyId(Long.valueOf(id));
                    sysUserCompany.setUserId(sysUser.getUserId());
                    sysUserCompanyBusinessService.saveSysUserCompany(sysUserCompany);
                }
                return R.ok(200,"新增成功！");
            }else{
                return R.error(500,"新增失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("新增用户信息出错："+e.getMessage());
            throw new RuntimeException();
            //return R.error(500,"新增用户信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object>  updateUserInfo(Long userId, Long companyId, Long roleId, Long departmentId,String userName,String permsCompanyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("companyId", companyId);
        map.put("roleId", roleId);
        map.put("departmentId", departmentId);
        try {
            int count=sysUserBusinessService.updateUserInfo(map);
            if(count>0){
                sysUserRoleBusinessService.deleteUserRoleByUserId(userId);
                SysUserRole sysUserRole=new SysUserRole();
                sysUserRole.setRoleId(roleId);
                sysUserRole.setUserId(userId);
                sysUserRoleBusinessService.addUserRole(sysUserRole);

                sysUserCompanyBusinessService.deleteSysUserCompany(userId);
                String[] permsCompanyIdArray;
                //去掉最后那个逗号，在进行获取数据
                permsCompanyIdArray = permsCompanyId.substring(0, permsCompanyId.length() - 1).split(",");
                SysUserCompany sysUserCompany=null;
                for(String id:permsCompanyIdArray){
                    sysUserCompany=new SysUserCompany();
                    sysUserCompany.setCompanyId(Long.valueOf(id));
                    sysUserCompany.setUserId(userId);
                    sysUserCompanyBusinessService.saveSysUserCompany(sysUserCompany);
                }
                return R.ok(200,"更新成功！");
            }else{
                return R.error(500,"更新失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("更新用户信息出错："+e.getMessage());
            throw new RuntimeException();
            //return R.error(500,"更新部门信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object>  deleteUser(String userId,String type) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        if("delete".equals(type)){
            //status_code为3表示删除
            map.put("statusCode", 3);
        }else{
            //status_code为2表示停用
            map.put("statusCode", 2);
        }

        try {
            int count=sysUserBusinessService.deleteUser(map);
            if(count>0){
                return R.ok(200,"操作成功！");
            }else{
                return R.error(500,"操作失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("操作信息出错："+e.getMessage());
            return R.error(500,"操作信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> sysUserCompany() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {

            if(ShiroUtils.getUserEntity()==null){
                return R.error(500,"请登录系统再进行操作功能！");
            }
            if(ShiroUtils.getUserEntity().getUserId()==null){
                return R.error(500,"请登录系统再进行操作功能！");
            }
            Long userId=ShiroUtils.getUserEntity().getUserId();
            List<SysUserCompany> list=sysUserCompanyBusinessService.sysUserCompany(userId);
            resultMap.put("list", list);
            return R.ok().putData(200,resultMap,"获取成功！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取用户权限小区信息出错："+e.getMessage());
            return R.error(500,"获取用户权限小区信息失败，服务器异常，请联系管理员！");
        }
    }
}
