package com.spring.boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.spring.boot.bean.PageInfoBean;
import com.spring.boot.bean.master.SysMenu;
import com.spring.boot.bean.master.SysQualityManage;
import com.spring.boot.bean.master.SysRoleMenu;
import com.spring.boot.bean.master.entity.SysUserRoleEntity;
import com.spring.boot.service.SysMenuService;
import com.spring.boot.service.web.SysMenuBusinessService;
import com.spring.boot.service.web.SysRoleMenuBusinessService;
import com.spring.boot.service.web.SysUserBusinessService;
import com.spring.boot.service.web.SysUserRoleBusinessService;
import com.spring.boot.util.Constant;
import com.spring.boot.util.R;
import com.spring.boot.util.ShiroUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private SysUserRoleBusinessService sysUserRoleBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

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
        List<Long> menuIdList = null;
        //根据用户id利用sql查询用户多个角色名称，用逗号，隔开
        SysUserRoleEntity sysUserRoleEntity=sysUserRoleBusinessService.findUserRoleNameByRoleId(userId);
        if(null!=sysUserRoleEntity){
            //获取用户全部角色的角色编码
            List<String> roleCodeList= Arrays.asList(sysUserRoleEntity.getRoleCodes().split(","));
            //角色编码为superadmin的系统超级管理员
            if(!roleCodeList.contains("superadmin")){
                //用户菜单列表
                menuIdList = sysUserBusinessService.queryUserAllMenuId(userId, 1);
            }
        }
        /*//非超级管理员需要查询权限菜单，超级管理员的userId为1
        if(userId!=1){
            //用户菜单列表
            menuIdList = sysUserBusinessService.queryUserAllMenuId(userId, 1);
        }*/
        List<SysMenu> menuList = getAllMenuList(menuIdList, 1, null);
        if (null != menuList && menuList.size() > 0) {
            return R.ok().putData(200, menuList, "获取成功！");
        } else {
            return R.error(500, "获取失败，没找到数据！");
        }

    }

    @Override
    public Map<String, Object> queryCatalogAndMenu(Integer menuType) {
       try {
           //查询根菜单列表
           List<SysMenu> menuList=null;
           //假如添加的是菜单或者目录，需要特别追加最外层菜单（添加目录或者和目录同级别的菜单时，需要获取添加的目录或者菜单的上级id）
           if(menuType==0){
               menuList=new ArrayList<SysMenu>();
               SysMenu sysMenu=new SysMenu();
               sysMenu.setMenuName("系统最外层目录");
               sysMenu.setMenuId(0L);
               menuList.add(sysMenu);
           }else{
               //查询根菜单列表
               menuList = sysMenuBusinessService.queryCatalogAndMenu(menuType);
               if(null!=menuList&&menuList.size()>0){
                   //假如添加的是菜单或者目录，需要特别追加最外层菜单（添加目录或者和目录同级别的菜单时，需要获取添加的目录或者菜单的上级id）
                   if(menuType==1){
                       SysMenu sysMenu=new SysMenu();
                       sysMenu.setMenuName("系统最外层菜单");
                       sysMenu.setMenuId(0L);
                       menuList.add(sysMenu);
                   }
               }else{
                   return R.error(500, "获取出错,服务器异常，请联系管理员！");
               }
           }
           return R.ok().putData(200, menuList, "获取成功！");

       }catch (Exception e){
           e.printStackTrace();
           logger.info("获取出错：" + e.getMessage());
           return R.error(500, "获取出错,服务器异常，请联系管理员！");
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
        List<SysMenu> menuList = getAllMenuList(menuIdList, 2, roleId);


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
     * @param roleId     角色id，注意：在角色管理的更新操作时，才有效，在获取系统左边菜单和角色添加操作时，无效
     * @return
     */
    private List<SysMenu> getAllMenuList(List<Long> menuIdList, Integer selectType, Long roleId) {
        //查询根菜单列表
        List<SysMenu> menuList = null;
        if (selectType == 1) {
            menuList = queryListParentId(0L, menuIdList, selectType);
            //递归获取子菜单
            menuList = getMenuTreeList(menuList, menuIdList, selectType);
        } else if (selectType == 2) {
            menuList = sysMenuBusinessService.findMenuAndModule();
            //递归获取子菜单、功能模块（如：新增、修改等）
            menuList = getMenuModuleTreeList(menuList, menuIdList, selectType, roleId);
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
            if (sysMenu.getMenuType() == Constant.MenuType.CATALOG.getValue()) {
                //两级菜单，需要判断时候有下级菜单，如果有，组装第一级菜单，如果没有，不组装
                List<SysMenu> list = getMenuTreeList(queryListParentId(sysMenu.getMenuId(), menuIdList, selectType), menuIdList, selectType);
                if (list != null && list.size() > 0) {
                    sysMenu.setList(list);
                    subMenuList.add(sysMenu);
                }
            } else if (sysMenu.getMenuType() == Constant.MenuType.MENU.getValue()) {
                //一级菜单，直接判断权限信息内有没有数据，如果有，直接组装
                if (null!=menuIdList&&menuIdList.contains(sysMenu.getMenuId())) {
                    subMenuList.add(sysMenu);
                }else{//当超级管理员时，不需要判断权限信息
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
     * @param roleId     角色id，注意：在角色管理的更新操作时，才有效，在获取系统左边菜单和角色添加操作时，无效
     * @return
     */
    private List<SysMenu> getMenuModuleTreeList(List<SysMenu> menuList, List<Long> menuIdList, Integer selectType, Long roleId) {
        List<SysMenu> subMenuList = new ArrayList<SysMenu>();
        for (SysMenu sysMenu : menuList) {
            List<SysMenu> list = queryListParentId(sysMenu.getMenuId(), menuIdList, selectType);
            //将菜单本身，设置为查看功能按钮，返回到前端界面，在授权时，直接将菜单本身写入到数据库记录中
            SysMenu sysMenuAdd = new SysMenu();
            sysMenuAdd.setMenuId(sysMenu.getMenuId());
            sysMenuAdd.setParentId(sysMenu.getParentId());
            sysMenuAdd.setPerms(sysMenu.getPerms());
            sysMenuAdd.setIsUse(sysMenu.getIsUse());
            sysMenuAdd.setMenuType(sysMenu.getMenuType());
            sysMenuAdd.setMenuName("查看列表或者详情");
            //在更新操作中，假如该角色已授权该菜单查看功能，则给前端返回true值
            if (null != roleId && menuIdList != null) {
                SysRoleMenu sysRoleMenu = sysRoleMenuBusinessService.findRoleMenuByMenuId(sysMenu.getMenuId(),roleId);
                if (null != sysRoleMenu) {
                    sysMenuAdd.setAuth(true);
                }
            }
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
                if (menuIdList.contains(menu.getMenuId()) || menu.getMenuType() == 0) {
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
    public Map<String, Object> getSysMenuList(Integer limit, Integer offset,String menuName,String menuUrl) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object>  map = new HashMap<String, Object>();
        map.put("limit", limit);
        map.put("offset", offset);
        map.put("menuName", menuName);
        map.put("menuUrl", menuUrl);
        try {
            //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
            PageHelper.startPage((offset/limit)+1,limit);
            List<SysMenu> list=sysMenuBusinessService.getSysMenuList(map);
            PageInfoBean result = new PageInfoBean(list);
            resultMap.put("total", result.getTotalOfData());
            resultMap.put("list", result.getList());

            //resultMap.put("total", sysMenuBusinessService.getSysMenuListTotal(map));
            //resultMap.put("list", sysMenuBusinessService.getSysMenuList(map));
            return R.ok().putData(200, resultMap, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取菜单列表出错：" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> addSysMenu(String menuName, String menuUrl, String menuPerms, String icon, Long parentId, String sort, Integer menuType, String remark) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("menuName", menuName);
        map.put("menuUrl", menuUrl);
        map.put("menuPerms", menuPerms);
        map.put("icon", icon);
        map.put("parentId", parentId);
        map.put("sort", sort);
        map.put("menuType", menuType);
        map.put("remark", remark);
        try {
            int count = sysMenuBusinessService.addSysMenu(map);
            if (count > 0) {
                return R.ok(200, "新增成功！");
            } else {
                return R.error(500, "新增失败，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("新增菜单出错：" + e.getMessage());
            return R.error(500, "添加失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> updateSysMenu(String menuName, String menuUrl, String menuPerms, String icon, Long parentId, String sort, Integer menuType, String remark, Long menuId) {
        Map<String, Object> map = new HashMap<String, Object>();
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
            int count = sysMenuBusinessService.updateSysMenu(map);
            if (count > 0) {
                return R.ok(200, "更新成功！");
            } else {
                return R.error(500, "更新失败，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("更新菜单信息出错：" + e.getMessage());
            return R.error(500, "更新信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> deleteSysMenuById(Long menuId) {
        Map<String, Object>  map = new HashMap<String, Object>();
        map.put("menuId", menuId);

        try {
            int count = sysMenuBusinessService.deleteSysMenuById(menuId);
            if (count > 0) {
                return R.ok(200, "删除成功！");
            } else {
                return R.error(500, "删除失败，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除菜单信息出错：" + e.getMessage());
            return R.error(500, "删除信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> findSysMenuById(Long menuId) {
        sysMenuBusinessService.findSysMenuInfoByMenuId(menuId);
        try {
            SysMenu sysMenu = sysMenuBusinessService.findSysMenuInfoByMenuId(menuId);
            if (null!=sysMenu) {
                return R.ok().putData(200,sysMenu, "获取成功！");
            } else {
                return R.error(500, "获取失败，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取菜单信息出错：" + e.getMessage());
            return R.error(500, "获取信息失败，服务器异常，请联系管理员！");
        }
    }
}
