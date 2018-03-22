package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysMenu;
import com.spring.boot.service.SysCompanyService;
import com.spring.boot.service.SysMenuService;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.service.web.SysMenuBusinessService;
import com.spring.boot.service.web.SysUserBusinessService;
import com.spring.boot.util.Constant;
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
        List<Long> menuIdList = sysUserBusinessService.queryUserAllMenuId(userId);
        List<SysMenu> menuList = getAllMenuList(menuIdList);
        resultMap = new HashMap<String, Object>();
        resultMap.put("data", menuList);
        return resultMap;
    }

    /**
     * 获取所有菜单列表
     *
     * @param menuIdList 用户权限内所有菜单信息列表
     * @return
     */
    private List<SysMenu> getAllMenuList(List<Long> menuIdList) {
        //查询根菜单列表
        List<SysMenu> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);
        return menuList;
    }

    /**
     * 递归生成子菜单
     *
     * @param menuList   根菜单列表
     * @param menuIdList 用户权限内所有菜单信息列表
     * @return
     */
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIdList) {
        List<SysMenu> subMenuList = new ArrayList<SysMenu>();
        for (SysMenu sysMenu : menuList) {
            //菜单列表
            if (sysMenu.getType() == Constant.MenuType.MENU.getValue()) {
                List<SysMenu> list = getMenuTreeList(queryListParentId(sysMenu.getMenuId(), menuIdList), menuIdList);
                if (list != null && list.size() > 0) {
                    sysMenu.setList(list);
                }
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
    public List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList) {
        //查询根菜单列表
        List<SysMenu> menuList = sysMenuBusinessService.findMenuByParentId(parentId);
        /*List<SysMenu> menuList = sysMenuDao.queryListParentId(parentId);*/
        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenu> userMenuList = new ArrayList<>();
        for (SysMenu menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
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