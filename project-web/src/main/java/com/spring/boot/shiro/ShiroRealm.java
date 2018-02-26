package com.spring.boot.shiro;

import com.spring.boot.bean.MenuRole;
import com.spring.boot.bean.SysMenu;
import com.spring.boot.bean.User;
import com.spring.boot.bean.UserRole;
import com.spring.boot.dao.web.SysMenuDao;
import com.spring.boot.service.web.MenuRoleService;
import com.spring.boot.service.web.SysMenuService;
import com.spring.boot.service.web.UserRoleService;
import com.spring.boot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  on 2016/10/21.
 */

public class ShiroRealm extends AuthorizingRealm {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;
   @Autowired
   private UserRoleService userRoleService;
   @Autowired
   private SysMenuService sysMenuService;
   @Autowired
   private MenuRoleService menuRoleService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("doGetAuthorizationInfo+"+principalCollection.toString());
        User user = ((User) principalCollection.getPrimaryPrincipal());
        List<String> permissions = new ArrayList<String>();

        //把principals放session中 key=userId value=principals
        //SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(user.getId()),SecurityUtils.getSubject().getPrincipals());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //获得所有的权限
        List<UserRole> permissionList = userRoleService.findRoleByUserId(user.getUserId());
        //赋予角色
        //for(Role userRole:user.getRoles()){
          //  info.addRole(userRole.getName());
       // }
        //赋予权限
        //for(Permission permission:permissionService.getByUserId(user.getId())){
//            if(StringUtils.isNotBlank(permission.getPermCode()))
              //  info.addStringPermission(permission.getName());
       // }
        for(UserRole userRole:permissionList){
            List<MenuRole> menuRoleList=menuRoleService.findMenuRoleInfoByRoleId(userRole.getRoleId());
            for(MenuRole menuRole:menuRoleList){
                SysMenu sysMenu=sysMenuService.findSysMenuInfoByMenuId(menuRole.getMenuId());
                if(sysMenu!=null){
                    permissions.add(sysMenu.getPerms());
                }
            }
        }
        info.addStringPermissions(permissions);

        //设置登录次数、时间
//        userService.updateUserLogin(user);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("doGetAuthenticationInfo +"  + token.toString());

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
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        User u=userService.findByUserId("1");
        System.out.println("测试账号："+u.getAccount());
        //查询用户信息
        User user = userService.findByUserAccount(username);

        if(user!=null){
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("user", user);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
            return info;
        }else{
            //账号不存在
            if(user == null) {
                throw new UnknownAccountException("账号或密码不正确");
            }

            //密码错误
            if(!password.equals(user.getPassword())) {
                throw new IncorrectCredentialsException("账号或密码不正确");
            }

            //账号锁定
            if(user.getStatus() == 0){
                throw new LockedAccountException("账号已被锁定,请联系管理员");
            }
            return null;
        }





    }

}
