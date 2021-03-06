package com.spring.boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.spring.boot.bean.PageInfoBean;
import com.spring.boot.bean.master.*;
import com.spring.boot.bean.master.entity.SysUserRoleEntity;
import com.spring.boot.service.SysUserService;
import com.spring.boot.service.web.*;
import com.spring.boot.util.R;
import com.spring.boot.util.ShiroUtils;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private SysCompanyBusinessService sysCompanyBusinessService;
    @Autowired
    private SysRoleMenuBusinessService sysRoleMenuBusinessService;
    @Autowired
    private SysMenuBusinessService sysMenuBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Map<String, Object> sysUserList(Integer limit, Integer offset,String account,String userName,Long companyId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map  = new HashMap<String, Object>();
        try {
            map.put("limit", limit);
            map.put("offset", offset);
            map.put("account", account);
            map.put("userName", userName);
            map.put("companyId", companyId);

            //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
            PageHelper.startPage((offset/limit)+1,limit);
            List<SysUser> list=sysUserBusinessService.sysUserList(map);
            PageInfoBean result = new PageInfoBean(list);
            resultMap.put("total", result.getTotalOfData());

            //resultMap.put("total", sysUserBusinessService.sysUserTotal(map));
            List<SysUser> sysUserList=result.getList();
            for(SysUser sysUser:sysUserList){
                //根据用户id利用sql查询用户多个角色名称，用逗号，隔开
                SysUserRoleEntity sysUserRoleEntity=sysUserRoleBusinessService.findUserRoleNameByRoleId(sysUser.getUserId());
                if(null!=sysUserRoleEntity){
                    sysUser.setRoleName(sysUserRoleEntity.getRoleNames());
                }

            }
            resultMap.put("list", sysUserList);
            return R.ok().putData(200, resultMap, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取用户列表出错：" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
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
            if (ShiroUtils.getUserEntity() == null) {
                return R.error(500, "请登录系统再进行操作功能！！！");
            }
            if (ShiroUtils.getUserEntity().getUserId() == null) {
                return R.error(500, "请登录系统再进行操作功能o！");
            }
            Long userId = ShiroUtils.getUserEntity().getUserId();
            SysUser sysUser = sysUserBusinessService.sysUserInfo(userId);
            return R.ok().putData(200, sysUser, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取用户信息出错：" + e.getMessage());
            return R.error(500, "获取用户信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public SysUser findByUserAccount(String account) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("account", account);
        return sysUserBusinessService.findByUserAccount(map);
    }

    @Override
    public Map<String, Object> updatePassword( String password, String newPassword) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (ShiroUtils.getUserEntity() == null) {
            return R.error(500, "请登录系统再进行操作功能！");
        }
        if (ShiroUtils.getUserEntity().getAccount() == null) {
            return R.error(500, "请登录系统再进行操作功能！");
        }
        Long userId=ShiroUtils.getUserEntity().getUserId();

        //根据用户提交的密码，利用md5加密得到加密后的原密码
        password = new SimpleHash("md5", password, null, 2).toHex();
        //根据用户提交的新密码，利用md5加密得到加密后的新密码
        newPassword = new SimpleHash("md5", newPassword, null, 2).toHex();
        SysUser sysUser = sysUserBusinessService.sysUserInfo(userId);
        if(null!=sysUser){
            if(!password.equals(sysUser.getPassword())){
                return R.error(500, "原密码错误，修改密码失败！");
            }
        }else{
            return R.error(500, "修改失败，用户不存在，请联系管理员！");
        }
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        try {
            int count = sysUserBusinessService.updatePassword(map);
            if (count > 0) {
                return R.ok(200, "更新成功！");
            } else {
                return R.error(500, "更新失败，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("更新密码信息出错：" + e.getMessage());
            return R.error(500, "更新密码信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> resetSysUserPassword(Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            //新密码，利用md5加密得到加密后的新密码,重置密码后默认为000000
            String newPassword = new SimpleHash("md5", "000000", null, 2).toHex();
            map.put("userId", userId);
            map.put("newPassword", newPassword);
            int count = sysUserBusinessService.resetSysUserPassword(map);
            if (count > 0) {
                return R.ok(200, "重置密码成功！");
            } else {
                return R.error(500, "重置密码失败，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("重置密码信息出错：" + e.getMessage());
            return R.error(500, "重置密码信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addUser(String userAccount, String password, Long companyId, String roleIds, Long departmentId, String userName, String permsCompanyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        //根据用户提交的密码，利用md5加密得到加密后的原密码new SimpleHash("md5", password, ByteSource.Util.bytes(userAccount), 2).toHex();
        password = new SimpleHash("md5", password, null, 2).toHex();
        map.put("account", userAccount);
        map.put("password", password);
        map.put("companyId", companyId);
        map.put("departmentId", departmentId);
        SysUser sysUser = new SysUser();
        sysUser.setAccount(userAccount);
        sysUser.setPassword(password);
        sysUser.setUserName(userName);
        sysUser.setCompanyId(companyId);
        sysUser.setDepartmentId(departmentId);
        sysUser.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
        try {
            Map<String, Object> userMap = new HashMap<String, Object>();
            userMap.put("account", userAccount);
            SysUser user = sysUserBusinessService.findByUserAccount(userMap);
            if (user != null) {
                return R.error(400, "该账号已存在，不能再次添加！！");
            }
            //将权限公司的id组合字符串转换为list
            List<String> permsCompanyList= Arrays.asList(permsCompanyId.split(","));
            if(permsCompanyList.size()>0){
                if(!permsCompanyList.contains(String.valueOf(companyId))){
                    return R.error(400, "必须选择与用户所在公司对应的权限公司！！");
                }
            }
            int count = sysUserBusinessService.addUser(sysUser);
            if (count > 0) {
                //用户与角色为多对多关系，一个用户可以拥有多个角色
                String[] roleIdArray=roleIds.substring(0, roleIds.length()).split(",");
                SysUserRole sysUserRole =null;
                for (String roleId : roleIdArray) {
                    sysUserRole = new SysUserRole();
                    sysUserRole.setRoleId(Long.valueOf(roleId));
                    sysUserRole.setUserId(sysUser.getUserId());
                    sysUserRoleBusinessService.addUserRole(sysUserRole);
                }
                //当选择的权限公司信息id不为空时，更新用户的权限公司信息
                if(!UtilHelper.isEmpty(permsCompanyId)){
                    String[] permsCompanyIdArray;
                    //去掉最后那个逗号，在进行获取数据
                    permsCompanyIdArray = permsCompanyId.substring(0, permsCompanyId.length()).split(",");
                    SysUserCompany sysUserCompany = null;
                    for (String id : permsCompanyIdArray) {
                        sysUserCompany = new SysUserCompany();
                        sysUserCompany.setCompanyId(Long.valueOf(id));
                        sysUserCompany.setUserId(sysUser.getUserId());
                        sysUserCompanyBusinessService.saveSysUserCompany(sysUserCompany);
                    }
                }
                return R.ok(200, "新增成功！");
            } else {
                return R.error(500, "新增失败，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("新增用户信息出错：" + e.getMessage());
            throw new RuntimeException();
            //return R.error(500,"新增用户信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> updateUserInfo(Long userId, Long companyId, String roleIds, Long departmentId, String userName, String permsCompanyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("companyId", companyId);
        //map.put("roleIds", roleId);
        map.put("departmentId", departmentId);
        map.put("userName", userName);
        try {
            //将权限公司的id组合字符串转换为list
            List<String> permsCompanyList= Arrays.asList(permsCompanyId.split(","));
            if(permsCompanyList.size()>0){
                if(!permsCompanyList.contains(String.valueOf(companyId))){
                    return R.error(400, "必须选择与用户所在公司对应的权限公司！！");
                }
            }
            int count = sysUserBusinessService.updateUserInfo(map);
            if (count > 0) {
                sysUserRoleBusinessService.deleteUserRoleByUserId(userId);
                //用户与角色为多对多关系，一个用户可以拥有多个角色
                String[] roleIdArray=roleIds.substring(0, roleIds.length()).split(",");
                SysUserRole sysUserRole =null;
                for (String roleId : roleIdArray) {
                    sysUserRole = new SysUserRole();
                    sysUserRole.setRoleId(Long.valueOf(roleId));
                    sysUserRole.setUserId(userId);
                    sysUserRoleBusinessService.addUserRole(sysUserRole);
                }

                sysUserCompanyBusinessService.deleteSysUserCompany(userId);
                //当选择的权限公司信息id不为空时，更新用户的权限公司信息
                if(!UtilHelper.isEmpty(permsCompanyId)){
                    String[] permsCompanyIdArray;
                    //去掉最后那个逗号，在进行获取数据
                    permsCompanyIdArray = permsCompanyId.substring(0, permsCompanyId.length()).split(",");
                    SysUserCompany sysUserCompany = null;
                    for (String id : permsCompanyIdArray) {
                        sysUserCompany = new SysUserCompany();
                        sysUserCompany.setCompanyId(Long.valueOf(id));
                        sysUserCompany.setUserId(userId);
                        sysUserCompanyBusinessService.saveSysUserCompany(sysUserCompany);
                    }
                }
                return R.ok(200, "更新成功！");
            } else {
                return R.error(500, "更新失败，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("更新用户信息出错：" + e.getMessage());
            throw new RuntimeException();
            //return R.error(500,"更新部门信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> deleteUser(String userId, String type) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        if ("delete".equals(type)) {
            //status_code为3表示删除
            map.put("statusCode", 3);
        } else if("close".equals(type)){
            //status_code为2表示停用
            map.put("statusCode", 2);
        }else{
            //status_code为2表示停用
            map.put("statusCode", 1);
        }
        try {
            int count = sysUserBusinessService.deleteUser(map);
            if (count > 0) {
                return R.ok(200, "操作成功！");
            } else {
                return R.error(500, "操作失败，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("操作信息出错：" + e.getMessage());
            return R.error(500, "操作信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> sysUserCompany() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            if (ShiroUtils.getUserEntity() == null) {
                return R.error(500, "请登录系统再进行操作功能！！！");
            }
            if (ShiroUtils.getUserEntity().getUserId() == null) {
                return R.error(500, "请登录系统再进行操作功能！");
            }
            Long userId = ShiroUtils.getUserEntity().getUserId();
            List<Long> sysCompanyList = sysCompanyBusinessService.getAllSysCompanyId();
            /*for(SysCompany sysCompany:sysCompanyList){
                sysCompany.getCompanyId()
            }*/
            List<SysUserCompany> list = sysUserCompanyBusinessService.sysUserCompany(userId);
            //2.提取出list对象中的一个属性并去重(jdk1.8)
            List<Long> sysUserCompanyIdList = list.stream().map(SysUserCompany::getCompanyId).distinct().collect(Collectors.toList());
            boolean isAllCompany=true;
            //判断该用户是否拥有全国公司的权限
            for(Long companyId:sysCompanyList){
                if(!sysUserCompanyIdList.contains(companyId)){
                    isAllCompany=false;
                    break;
                }
            }
            //如果包含全国公司权限，list追加一条信息
            if(isAllCompany){
                SysUserCompany sysUserCompany=new SysUserCompany();
                sysUserCompany.setCompanyId(0L);
                sysUserCompany.setCompanyName("全国");
                list.add(0,sysUserCompany);
            }
            resultMap.put("list", list);
            return R.ok().putData(200, resultMap, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取用户权限公司信息出错：" + e.getMessage());
            return R.error(500, "获取用户权限公司信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> sysUserCompanyAuthority(Long userId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            /*if (ShiroUtils.getUserEntity() == null) {
                return R.error(500, "请登录系统再进行操作功能！");
            }
            if (ShiroUtils.getUserEntity().getUserId() == null) {
                return R.error(500, "请登录系统再进行操作功能！");
            }
            Long userId = ShiroUtils.getUserEntity().getUserId();*/
            List<SysCompany> sysCompanyList = sysCompanyBusinessService.getAllSysCompany();
            if (null != sysCompanyList) {
                for (SysCompany sysCompany : sysCompanyList) {
                    SysUserCompany sysUserCompany = sysUserCompanyBusinessService.sysUserCompanyAuthority(userId, sysCompany.getCompanyId());
                    if (null != sysUserCompany) {
                        sysCompany.setAuthority(true);
                    } else {
                        sysCompany.setAuthority(false);
                    }
                }
            }
            resultMap.put("list", sysCompanyList);
            return R.ok().putData(200, resultMap, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取用户权限具体信息出错：" + e.getMessage());
            return R.error(500, "获取用户权限公司具体信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> getUserRole() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            if (ShiroUtils.getUserEntity() == null) {
                return R.error(500, "请登录系统再进行操作功能！！！");
            }
            if (ShiroUtils.getUserEntity().getUserId() == null) {
                return R.error(500, "请登录系统再进行操作功能！");
            }
            Long userId = ShiroUtils.getUserEntity().getUserId();
            //用户权限列表
            //Set<String> permsSet = new HashSet<String>();
            Map<String,Boolean> map=new HashMap<String,Boolean>();

            boolean isSuperadmin=false;
            //根据用户id利用sql查询用户多个角色名称、角色编码、角色id，用逗号，隔开
            SysUserRoleEntity sysUserRoleEntity=sysUserRoleBusinessService.findUserRoleNameByRoleId(userId);
            if(null!=sysUserRoleEntity){
                //获取用户全部角色的角色编码
                List<String> roleCodeList= Arrays.asList(sysUserRoleEntity.getRoleCodes().split(","));
                //角色编码为superadmin的系统超级管理员
                if(roleCodeList.contains("superadmin")){
                    isSuperadmin=true;
                }
            }
            //超级管理员,不需要进行权限校验，直接赋予系统全部权限（最高权限）
            if(isSuperadmin){
                //获取系统全部菜单信息
                List<SysMenu> menuList=sysMenuBusinessService.getSysMenuList(new HashMap<String,Object>());
                for(SysMenu sysMenu:menuList){
                    if(!UtilHelper.isEmpty(sysMenu.getPerms())){
                        map.put(sysMenu.getPerms(),true);
                    }
                }
            }else{
                //获得所有的权限
                List<SysUserRole> permissionList = sysUserRoleBusinessService.findRoleByUserId(userId);
                for (SysUserRole userRole : permissionList) {
                    List<SysRoleMenu> menuRoleList = sysRoleMenuBusinessService.findRoleMenuInfoByRoleId(userRole.getRoleId());
                    for (SysRoleMenu menuRole : menuRoleList) {
                        SysMenu sysMenu = sysMenuBusinessService.findSysMenuInfoByMenuId(menuRole.getMenuId());
                        if (sysMenu != null&&sysMenu.getPerms()!=null&&sysMenu.getPerms()!="") {
                            //permsSet.add(sysMenu.getPerms());
                            map.put(sysMenu.getPerms(),true);
                        }
                    }
                }
            }
            return R.ok().putData(200, map, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取用户操作权限信息出错：" + e.getMessage());
            return R.error(500, "获取用户操作权限信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> findSysUserInfoById(Long userId) {
        try {
            SysUser sysUser = sysUserBusinessService.findSysUserInfoById(userId);
            if (null != sysUser) {
                //根据用户id利用sql查询用户多个角色名称，用逗号，隔开
                SysUserRoleEntity sysUserRoleEntity=sysUserRoleBusinessService.findUserRoleNameByRoleId(sysUser.getUserId());
                if(null!=sysUserRoleEntity){
                    //获取多个角色id，逗号隔开
                    sysUser.setRoleIds(sysUserRoleEntity.getRoleIds());
                }
                return R.ok().putData(200, sysUser, "获取成功！！");
            } else {
                return R.error(500, "获取失败，服务器异常，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("根据用户id获取用户信息出错：" + e.getMessage());
            return R.error(500, "根据用户id获取用户信息失败，服务器异常，请联系管理员！");
        }
    }
}
