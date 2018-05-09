package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysMenu;
import com.spring.boot.bean.master.SysUserRole;
import com.spring.boot.service.SysCompanyService;
import com.spring.boot.service.SysMenuService;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.service.web.SysMenuBusinessService;
import com.spring.boot.service.web.SysRoleMenuBusinessService;
import com.spring.boot.service.web.SysUserBusinessService;
import com.spring.boot.util.Constant;
import com.spring.boot.util.R;
import com.spring.boot.util.ShiroUtils;
import com.spring.boot.util.UtilHelper;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    private static final Logger logger = Logger.getLogger(SysMenuServiceImpl.class);
    @Autowired
    private SysMenuBusinessService sysMenuBusinessService;
    @Autowired
    private SysUserBusinessService sysUserBusinessService;
    @Autowired
    private SysRoleMenuBusinessService sysRoleMenuBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;
    Map<String, Object> resultMap = null;
    Map<String, Object> map = null;

    @Override
    public Map<String, Object> getSysMenu() {
        if (ShiroUtils.getUserEntity() == null) {
            return R.error(500, "请登录系统再进行操作功能！");
        }
        if (ShiroUtils.getUserEntity().getUserId() == null) {
            return R.error(500, "请登录系统再进行操作功能！");
        }
        Long userId = ShiroUtils.getUserEntity().getUserId();
        //用户菜单列表
        List<Long> menuIdList = sysUserBusinessService.queryUserAllMenuId(userId, 1);
        List<SysMenu> menuList = getAllMenuList(menuIdList, 1);
        if (null != menuList && menuList.size() > 0) {
            return R.ok().putData(200, menuList, "获取成功！");
        } else {
            return R.error(500, "获取失败，没找到数据！");
        }

    }

    @Override
    public Map<String, Object> getSysModule(String type, Long roleId) {
        //用户菜单列表
        List<Long> menuIdList = null;
        if ("add".equals(type)) {
            //新增不需要获取登录用户已授权的功能菜单和按钮
            menuIdList = null;
        } else if ("update".equals(type)) {
            /*if (ShiroUtils.getUserEntity() == null) {
                return R.error(500, "请登录系统再进行操作功能！");
            }
            if (ShiroUtils.getUserEntity().getUserId() == null) {
                return R.error(500, "请登录系统再进行操作功能！");
            }
            Long userId = ShiroUtils.getUserEntity().getUserId();*/
            //用户菜单列表
            //menuIdList = sysUserBusinessService.queryUserAllMenuId(userId, 2);
            menuIdList = sysRoleMenuBusinessService.getMenuIdByRoleId(roleId);
        }
        List<SysMenu> menuList = getAllMenuList(menuIdList, 2);


        if (null != menuList && menuList.size() > 0) {
            return R.ok().putData(200, menuList, "获取成功！");
        } else {
            return R.error(500, "获取失败，没找到数据！");
        }
    }

    /**
     * 获取所有菜单列表
     *
     * @param menuIdList 用户权限内所有菜单信息列表
     * @param selectType 查找类型 1:菜单，2：菜单、功能模块（如：新增、修改）
     * @return
     */
    private List<SysMenu> getAllMenuList(List<Long> menuIdList, Integer selectType) {
        //查询根菜单列表
        List<SysMenu> menuList = null;
        if (selectType == 1) {
            menuList = queryListParentId(0L, menuIdList, selectType);
            //递归获取子菜单
            menuList = getMenuTreeList(menuList, menuIdList, selectType);
        } else {
            menuList = sysMenuBusinessService.findMenuAndModule();
            //递归获取子菜单、功能模块（如：新增、修改等）
            menuList = getMenuModuleTreeList(menuList, menuIdList, selectType);
        }
        return menuList;
    }

    /**
     * 递归生成子菜单(不包含子功能模块，如：添加、更新)
     *
     * @param menuList   根菜单列表
     * @param menuIdList 用户权限内所有菜单信息列表
     * @return
     */
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIdList, Integer selectType) {
        List<SysMenu> subMenuList = new ArrayList<SysMenu>();
        for (SysMenu sysMenu : menuList) {
            //*************************组装菜单列表**********************************/
            if (sysMenu.getType() == Constant.MenuType.CATALOG.getValue()) {
                //两级菜单，需要判断时候有下级菜单，如果有，组装第一级菜单，如果没有，不组装
                List<SysMenu> list = getMenuTreeList(queryListParentId(sysMenu.getMenuId(), menuIdList, selectType), menuIdList, selectType);
                if (list != null && list.size() > 0) {
                    sysMenu.setList(list);
                    subMenuList.add(sysMenu);
                }
            } else if (sysMenu.getType() == Constant.MenuType.MENU.getValue()) {
                //一级菜单，直接判断权限信息内有没有数据，如果有，直接组装
                if (menuIdList.contains(sysMenu.getMenuId())) {
                    subMenuList.add(sysMenu);
                }
            }
        }
        return subMenuList;
    }

    /**
     * 递归生成子菜单和功能模块(包含子功能模块，如：添加、更新)
     *
     * @param menuList   根菜单列表
     * @param menuIdList 用户权限内所有菜单信息列表
     * @return
     */
    private List<SysMenu> getMenuModuleTreeList(List<SysMenu> menuList, List<Long> menuIdList, Integer selectType) {
        List<SysMenu> subMenuList = new ArrayList<SysMenu>();
        for (SysMenu sysMenu : menuList) {
            List<SysMenu> list = queryListParentId(sysMenu.getMenuId(), menuIdList, selectType);
            //将菜单本身，设置为查看功能按钮，返回到前端界面，在授权时，直接将菜单本身写入到数据库记录中
            SysMenu sysMenuAdd = new SysMenu();
            sysMenuAdd.setMenuId(sysMenu.getMenuId());
            sysMenuAdd.setParentId(sysMenu.getParentId());
            sysMenuAdd.setPerms(sysMenu.getPerms());
            sysMenuAdd.setIsUse(sysMenu.getIsUse());
            sysMenuAdd.setType(sysMenu.getType());
            sysMenuAdd.setMenuName("查看");
            sysMenuAdd.setAuth(true);
            list.add(sysMenuAdd);
            sysMenu.setList(list);
            subMenuList.add(sysMenu);
        }
        return subMenuList;
    }

    /**
     * 根据上级菜单id，获取下级菜单id信息
     *
     * @param parentId
     * @param menuIdList 用户权限内所有菜单信息列表
     * @return
     */
    public List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList, Integer selectType) {
        //查询根菜单列表
        List<SysMenu> menuList = sysMenuBusinessService.findMenuByParentId(parentId, selectType);
        /*List<SysMenu> menuList = sysMenuDao.queryListParentId(parentId);*/
        if (menuIdList == null) {
            return menuList;
        }
        List<SysMenu> userMenuList = new ArrayList<>();
        for (SysMenu menu : menuList) {
            //权限内的菜单，或者目录菜单，将进行添加（目录菜单在授权时，不保存到数据库记录）
            if (selectType == 1) {
                if (menuIdList.contains(menu.getMenuId()) || menu.getType() == 0) {
                    userMenuList.add(menu);
                }
            } else if (selectType == 2) {
                if (menuIdList.contains(menu.getMenuId())) {
                    menu.setAuth(true);
                }
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public Map<String, Object> getSysMenuList(Integer limit, Integer offset) {
        resultMap = new HashMap<String, Object>();
        map = new HashMap<String, Object>();
        map.put("limit", limit);
        map.put("offset", offset);
        try {
            resultMap.put("total", null);
            resultMap.put("list", sysMenuBusinessService.getSysMenuList(map));
            return R.ok().putData(200,resultMap,"获取成功！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取菜单列表出错："+e.getMessage());
            return R.error(500,"服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> addSysMenu(String menuName, String menuUrl, String menuPerms, String icon, Long parentId, String sort, Integer menuType, String remark) {
        map = new HashMap<String, Object>();
        //公司编码（服务识别号）
        map.put("menuName", menuName);
        map.put("menuUrl", menuUrl);
        map.put("menuPerms", menuPerms);
        map.put("icon", icon);
        map.put("parentId", parentId);
        map.put("sort", sort);
        map.put("menuType", menuType);
        map.put("remark", remark);
        try {
            int count=sysMenuBusinessService.addSysMenu(map);
            if(count>0){
                return R.ok(200,"新增成功！");
            }else{
                return R.error(500,"新增失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("新增菜单出错："+e.getMessage());
            return R.error(500,"添加失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> updateSysMenu(String menuName, String menuUrl, String menuPerms, String icon, Long parentId, String sort, Integer menuType, String remark, Long menuId) {
        map = new HashMap<String, Object>();
        map.put("menuName", menuName);
        map.put("menuUrl", menuUrl);
        map.put("menuPerms", menuPerms);
        map.put("icon", icon);
        map.put("parentId", parentId);
        map.put("sort", sort);
        map.put("menuType", menuType);
        map.put("remark", remark);
        map.put("menuId", menuId);
        try {
            int count=sysMenuBusinessService.updateSysMenu(map);
            if(count>0){
                return R.ok(200,"更新成功！");
            }else{
                return R.error(500,"更新失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("更新菜单信息出错："+e.getMessage());
            return R.error(500,"更新信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> deleteSysMenuById(Long menuId) {
        map = new HashMap<String, Object>();
        map.put("menuId", menuId);

        try {
            int count=sysMenuBusinessService.deleteSysMenuById(menuId);
            if(count>0){
                return R.ok(200,"删除成功！");
            }else{
                return R.error(500,"删除失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除菜单信息出错："+e.getMessage());
            return R.error(500,"删除信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> findSysMenuById(Long menuId) {
        map = new HashMap<String, Object>();
        resultMap = new HashMap<String, Object>();
        map.put("menuId", menuId);
        sysMenuBusinessService.findSysMenuById(menuId);
        return resultMap;
    }
}
