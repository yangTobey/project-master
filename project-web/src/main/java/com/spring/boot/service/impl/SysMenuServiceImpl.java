package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysMenu;
import com.spring.boot.service.SysCompanyService;
import com.spring.boot.service.SysMenuService;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.service.web.SysMenuBusinessService;
import com.spring.boot.service.web.SysUserBusinessService;
import com.spring.boot.util.Constant;
import com.spring.boot.util.R;
import com.spring.boot.util.ShiroUtils;
import com.spring.boot.util.UtilHelper;
import org.apache.commons.lang.math.RandomUtils;
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
    @Autowired
    private SysMenuBusinessService sysMenuBusinessService;
    @Autowired
    private SysUserBusinessService sysUserBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;
    Map<String, Object> resultMap = null;
    Map<String, Object> map = null;

    @Override
    public Map<String, Object> getSysMenu(long userId) {
        //用户菜单列表
        List<Long> menuIdList = sysUserBusinessService.queryUserAllMenuId(userId,1);
        List<SysMenu> menuList = getAllMenuList(menuIdList,1);
        resultMap = new HashMap<String, Object>();
        resultMap.put("data", menuList);
        return resultMap;
    }

    @Override
    public Map<String, Object> getSysModule() {
        if(ShiroUtils.getUserEntity()==null){
            return R.error(500,"请登录系统再进行操作功能！");
        }
        if(ShiroUtils.getUserEntity().getUserId()==null){
            return R.error(500,"请登录系统再进行操作功能！");
        }
        Long userId=ShiroUtils.getUserEntity().getUserId();
        //用户菜单列表
        List<Long> menuIdList = sysUserBusinessService.queryUserAllMenuId(userId,2);
        List<SysMenu> menuList = getAllMenuList(menuIdList,2);
        resultMap = new HashMap<String, Object>();
        resultMap.put("data", menuList);
        return resultMap;
    }

    /**
     * 获取所有菜单列表
     *
     * @param menuIdList 用户权限内所有菜单信息列表
     * @param selectType 查找类型 1:菜单，2：菜单、功能模块（如：新增、修改）
     * @return
     */
    private List<SysMenu> getAllMenuList(List<Long> menuIdList,Integer selectType) {
        //查询根菜单列表
        List<SysMenu> menuList = null;
        if(selectType==1){
            menuList = queryListParentId(0L, menuIdList,selectType);
            //递归获取子菜单
            menuList=getMenuTreeList(menuList, menuIdList,selectType);
        }else{
            menuList = sysMenuBusinessService.findMenuAndModule();
            //递归获取子菜单、功能模块（如：新增、修改等）
            menuList=getMenuModuleTreeList(menuList, menuIdList,selectType);
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
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIdList,Integer selectType) {
        List<SysMenu> subMenuList = new ArrayList<SysMenu>();
        for (SysMenu sysMenu : menuList) {
            //*************************组装菜单列表**********************************/
            if (sysMenu.getType() == Constant.MenuType.CATALOG.getValue()) {
                //两级菜单，需要判断时候有下级菜单，如果有，组装第一级菜单，如果没有，不组装
                List<SysMenu> list = getMenuTreeList(queryListParentId(sysMenu.getMenuId(), menuIdList,selectType), menuIdList,selectType);
                if (list != null && list.size() > 0) {
                    sysMenu.setList(list);
                    subMenuList.add(sysMenu);
                }
            }else if(sysMenu.getType() == Constant.MenuType.MENU.getValue()){
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
    private List<SysMenu> getMenuModuleTreeList(List<SysMenu> menuList, List<Long> menuIdList,Integer selectType) {
        List<SysMenu> subMenuList = new ArrayList<SysMenu>();
        for (SysMenu sysMenu : menuList) {
                List<SysMenu> list = getMenuModuleTreeList(queryListParentId(sysMenu.getMenuId(), menuIdList,selectType), menuIdList,selectType);
                if (list != null && list.size() > 0) {

                            //将菜单本身，设置为查看功能按钮，返回到前端界面，在授权时，直接将菜单本身写入到数据库记录中
                            SysMenu sysMenuAdd=new SysMenu();
                            sysMenuAdd=sysMenu;
                            sysMenuAdd.setMenuName("查看");
                            list.add(sysMenuAdd);
                            sysMenu.setList(list);
                }
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
    public List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList,Integer selectType) {
        //查询根菜单列表
        List<SysMenu> menuList = sysMenuBusinessService.findMenuByParentId(parentId,Integer.valueOf(selectType));
        /*List<SysMenu> menuList = sysMenuDao.queryListParentId(parentId);*/
        if (menuIdList == null) {
            return menuList;
        }
        List<SysMenu> userMenuList = new ArrayList<>();
        for (SysMenu menu : menuList) {
            //权限内的菜单，或者目录菜单，将进行添加（目录菜单在授权时，不保存到数据库记录）
            if (menuIdList.contains(menu.getMenuId())||menu.getType()==0) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public Map<String, Object> getSysCompanyList(int limit, int offset) {
        resultMap = new HashMap<String, Object>();
        map = new HashMap<String, Object>();
        map.put("limit", limit);
        map.put("offset", offset);
        //resultMap.put("total", sysMenuBusinessService.getSysCompanyListTotal(map));
        //resultMap.put("list", sysMenuBusinessService.getSysCompanyList(map));
        return resultMap;
    }

    @Override
    public int addSysCompany(String companyName, String companyPhone, String companyAddress) {
        map = new HashMap<String, Object>();
        //公司编码（服务识别号）
        String companyCode = "C" + RandomUtils.nextInt(10) + RandomUtils.nextInt(10) + String.valueOf(System.currentTimeMillis()).substring(5, 12) + UtilHelper.chars.charAt((int) (Math.random() * 52));
        map.put("companyName", companyName);
        map.put("companyPhone", companyPhone);
        map.put("parentId", 1);
        map.put("companyAddress", companyAddress);
        map.put("companyCode", companyCode);
        return 0;
    }

    @Override
    public int updateSysCompanyInfo(String companyId, String companyName, String companyPhone, String companyAddress) {
        map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        map.put("companyName", companyName);
        map.put("companyPhone", companyPhone);
        map.put("parentId", 1);
        map.put("companyAddress", companyAddress);
        return 0;
    }

    @Override
    public int deleteSysCompanyById(String companyId) {
        map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        return 0;
    }

    @Override
    public Map<String, Object> findSysCompanyByCompanyId(long companyId) {
        map = new HashMap<String, Object>();
        resultMap = new HashMap<String, Object>();
        map.put("companyId", companyId);
        //SysCompany sysCompany=sysMenuBusinessService.findSysCompanyByCompanyId(map);
        //resultMap.put("data", sysMenuBusinessService.findSysCompanyByCompanyId(map));
        return resultMap;
    }
}
