package com.spring.boot.shiro;

import com.spring.boot.bean.master.SysMenu;
import com.spring.boot.bean.master.SysMenuRole;
import com.spring.boot.bean.master.SysUser;
import com.spring.boot.bean.master.SysUserRole;
import com.spring.boot.service.SysUserService;
import com.spring.boot.service.web.SysMenuBusinessService;
import com.spring.boot.service.web.SysMenuRoleService;
import com.spring.boot.service.web.SysUserRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by xiaoyang on 2016/10/21.
 */

public class ShiroRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysMenuBusinessService sysMenuBusinessService;
    @Autowired
    private SysMenuRoleService menuRoleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("doGetAuthorizationInfo+" + principalCollection.toString());
        SysUser user = ((SysUser) principalCollection.getPrimaryPrincipal());
        List<String> permissions = new ArrayList<String>();
        //用户权限列表
        Set<String> permsSet = new HashSet<String>();

        //把principals放session中 key=userId value=principals
        //SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(user.getId()),SecurityUtils.getSubject().getPrincipals());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //获得所有的权限
        List<SysUserRole> permissionList = sysUserRoleService.findRoleByUserId(user.getUserId());
        //赋予角色
        //for(Role userRole:user.getRoles()){
        //  info.addRole(userRole.getName());
        // }
        //赋予权限
        //for(Permission permission:permissionService.getByUserId(user.getId())){
//            if(StringUtils.isNotBlank(permission.getPermCode()))
        //  info.addStringPermission(permission.getName());
        // }
        for (SysUserRole userRole : permissionList) {
            List<SysMenuRole> menuRoleList = menuRoleService.findMenuRoleInfoByRoleId(userRole.getRoleId());
            for (SysMenuRole menuRole : menuRoleList) {
                SysMenu sysMenu = sysMenuBusinessService.findSysMenuInfoByMenuId(menuRole.getMenuId());
                if (sysMenu != null) {
                    permsSet.add(sysMenu.getPerms());
                }
            }
        }
        info.addStringPermissions(permsSet);

        //设置登录次数、时间
//        userService.updateUserLogin(user);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("doGetAuthenticationInfo +" + authenticationToken.toString());

       /* UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName=token.getUsername();
        logger.info(userName+token.getPassword());

       // User user = userService.getByUserName(token.getUsername());
        //if (user != null) {
//            byte[] salt = Encodes.decodeHex(user.getSalt());
//            ShiroUser shiroUser=new ShiroUser(user.getId(), user.getLoginName(), user.getName());
            //设置用户session
            Session session = SecurityUtils.getSubject().getSession();
            //session.setAttribute("user", user);
            //return new SimpleAuthenticationInfo(userName,user.getPassword(),getName());
        //} else {
            return null;
       // }
//        return null;*/
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        //UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //String userName=token.getUsername();
        //logger.info(userName+token.getPassword());
        SysUser u = sysUserService.findByUserId("1");
        System.out.println("测试账号：" + u.getAccount());
        //查询用户信息
        SysUser user = sysUserService.findByUserAccount(username);
        if (user != null) {
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("user", user);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(username), getName());
            return info;
        } else {
            //账号不存在
            if (user == null) {
                System.out.println("EE");
                throw new UnknownAccountException("账号或密码不正确");
            }

            //密码错误
            if (!password.equals(user.getPassword())) {
                System.out.println("FF");
                throw new IncorrectCredentialsException("账号或密码不正确");
            }

            //账号锁定
            if (user.getStatus() == 0) {
                System.out.println("GG");
                throw new LockedAccountException("账号已被锁定,请联系管理员");
            }
            return null;
        }
    }

}
