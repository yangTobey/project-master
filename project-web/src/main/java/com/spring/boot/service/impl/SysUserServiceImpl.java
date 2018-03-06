package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysUser;
import com.spring.boot.dao.web.master.SysUserDao;
import com.spring.boot.service.SysUserService;
import com.spring.boot.service.web.SysUserBusinessService;
import com.spring.boot.util.ShiroUtils;
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
    public int updatePassword(long userId, String password, String newPassword) {
        Map<String, Object> map = new HashMap<String, Object>();
        //根据用户提交的密码，利用md5加密得到加密后的原密码
        password = new SimpleHash("md5", password, ByteSource.Util.bytes(ShiroUtils.getUserEntity().getAccount()), 2).toHex();
        //根据用户提交的新密码，利用md5加密得到加密后的新密码
        newPassword = new SimpleHash("md5", newPassword, ByteSource.Util.bytes(ShiroUtils.getUserEntity().getAccount()), 2).toHex();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        return sysUserBusinessService.updatePassword(map);
    }

    @Override
    public int addUser(String userAccount, String password, String companyId, String roleId, String departmentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        //根据用户提交的密码，利用md5加密得到加密后的原密码
        password = new SimpleHash("md5", password, ByteSource.Util.bytes(userAccount), 2).toHex();
        map.put("userAccount", userAccount);
        map.put("password", password);
        map.put("companyId", companyId);
        map.put("roleId", roleId);
        map.put("departmentId", departmentId);
        return sysUserBusinessService.addUser(map);
    }

    @Override
    public int updateUserInfo(String userId, String companyId, String roleId, String departmentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("companyId", companyId);
        map.put("roleId", roleId);
        map.put("departmentId", departmentId);
        return sysUserBusinessService.updateUserInfo(map);
    }

    @Override
    public int deleteUser(String userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        return sysUserBusinessService.deleteUser(map);
    }
}
