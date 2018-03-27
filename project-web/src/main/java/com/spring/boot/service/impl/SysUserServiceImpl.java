package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysUser;
import com.spring.boot.dao.web.master.SysUserDao;
import com.spring.boot.service.SysUserService;
import com.spring.boot.service.web.SysUserBusinessService;
import com.spring.boot.util.R;
import com.spring.boot.util.ShiroUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    private StringRedisTemplate redisTemplate;

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
    public SysUser findByUserAccount(String account) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("account", account);
        return sysUserBusinessService.findByUserAccount(map);
    }

    @Override
    public Map<String, Object> updatePassword(long userId, String password, String newPassword) {
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
            logger.info("更新部门信息出错："+e.getMessage());
            return R.error(500,"更新信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object>  addUser(String userAccount, String password, String companyId, String roleId, String departmentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        //根据用户提交的密码，利用md5加密得到加密后的原密码
        password = new SimpleHash("md5", password, ByteSource.Util.bytes(userAccount), 2).toHex();
        map.put("userAccount", userAccount);
        map.put("password", password);
        map.put("companyId", companyId);
        map.put("roleId", roleId);
        map.put("departmentId", departmentId);
        try {
            int count=sysUserBusinessService.addUser(map);
            if(count>0){
                return R.ok(200,"新增成功！");
            }else{
                return R.error(500,"新增失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("新增用户信息出错："+e.getMessage());
            return R.error(500,"新增用户信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object>  updateUserInfo(String userId, String companyId, String roleId, String departmentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("companyId", companyId);
        map.put("roleId", roleId);
        map.put("departmentId", departmentId);
        try {
            int count=sysUserBusinessService.updateUserInfo(map);
            if(count>0){
                return R.ok(200,"更新成功！");
            }else{
                return R.error(500,"更新失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("更新用户信息出错："+e.getMessage());
            return R.error(500,"更新部门信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object>  deleteUser(String userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        try {
            int count=sysUserBusinessService.deleteUser(map);
            if(count>0){
                return R.ok(200,"删除成功！");
            }else{
                return R.error(500,"删除失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除信息出错："+e.getMessage());
            return R.error(500,"删除信息失败，服务器异常，请联系管理员！");
        }
    }
}
