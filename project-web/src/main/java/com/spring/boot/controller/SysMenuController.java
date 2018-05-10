package com.spring.boot.controller;

import com.spring.boot.service.SysCompanyService;
import com.spring.boot.service.SysMenuService;
import com.spring.boot.util.R;
import com.spring.boot.util.ShiroUtils;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by xiaoyang on 2018/1/25.
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {
    private static final Logger logger = Logger.getLogger(SysMenuController.class);
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 获取系统菜单
     *
     * @return
     */
    @RequestMapping(value = "/getSysMenu", method = RequestMethod.GET)
    public R getSysMenu() {
        Map<String, Object> map = sysMenuService.getSysMenu();
        return R.ok(map);
    }

    /**
     * 获取系统目录和菜单，用于新增菜单或者更新菜单信息
     *
     * @return
     */
    @RequestMapping(value = "/queryCatalogAndMenu", method = RequestMethod.GET)
    public R queryCatalogAndMenu(@RequestParam(value = "type", required = false) String type) {
        if (UtilHelper.isEmpty(type)) {
            return R.error(400, "操作类型不能为空！！");
        }
        Map<String, Object> map = sysMenuService.queryCatalogAndMenu(type);
        return R.ok(map);
    }

    /**
     * 获取系统菜单和功能按钮
     *
     * @return
     */
    @RequestMapping(value = "/getSysModule", method = RequestMethod.GET)
    public R getSysModule(@RequestParam(value = "type", required = false) String type, @RequestParam(value = "roleId", required = false) String roleId) {
        if (UtilHelper.isEmpty(type)) {
            return R.error(400, "操作类型不能为空！！");
        } else {
            if ("update".equals(type)) {
                if (!UtilHelper.isNumer(roleId)) {
                    return R.error(400, "角色id格式不正确！！");
                }
            } else {
                //赋值roleId为0,避免类型转化出错，不实际查询操作
                roleId = "0";
            }
        }
        Map<String, Object> map = sysMenuService.getSysModule(type, Long.valueOf(roleId));
        return R.ok(map);
    }

    /**
     * 查询公司信息
     *
     * @param limit  每页限制条数
     * @param offset 页码
     * @return
     */
    @RequestMapping(value = "/getSysMenuList", method = RequestMethod.GET)
    public R getSysMenuList(@RequestParam(value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset) {
        if (!UtilHelper.isNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字！");
        }
        if (!UtilHelper.isNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字！");
        }
        Map<String, Object> map = sysMenuService.getSysMenuList(Integer.valueOf(limit), Integer.valueOf(offset));
        return R.ok().put(200, map, "获取成功！");
    }

    /**
     * 新增菜单信息
     *
     * @param menuName  菜单名称
     * @param menuUrl   菜单地址
     * @param menuPerms 菜单标识权限名称
     * @param icon      菜单图标
     * @param parentId  上级id
     * @param sort      菜单排序号
     * @param menuType  菜单类型（1：c菜单，2：按钮）
     * @param remark    备注信息
     * @return
     */
    @RequestMapping(value = "/addSysMenu", method = RequestMethod.GET)
    public R addSysCompany(@RequestParam(value = "menuName", required = false) String menuName, @RequestParam(value = "menuUrl", required = false) String menuUrl
            , @RequestParam(value = "menuPerms", required = false) String menuPerms, @RequestParam(value = "icon", required = false) String icon
            , @RequestParam(value = "parentId", required = false) String parentId, @RequestParam(value = "sort", required = false) String sort
            , @RequestParam(value = "menuType", required = false) String menuType, @RequestParam(value = "remark", required = false) String remark) {
        Map<String, Object> map = sysMenuService.addSysMenu(menuName, menuUrl, menuPerms, icon, Long.valueOf(parentId), sort, Integer.valueOf(menuType), remark);
        return R.ok(map);

    }

    /**
     * @param menuName
     * @param menuUrl
     * @param menuPerms
     * @param icon
     * @param parentId
     * @param sort
     * @param menuType
     * @param remark
     * @param menuId    菜单id
     * @return
     */
    @RequestMapping(value = "/updateSysMenu", method = RequestMethod.GET)
    public R updateSysMenu(@RequestParam(value = "menuName", required = false) String menuName, @RequestParam(value = "menuUrl", required = false) String menuUrl
            , @RequestParam(value = "menuPerms", required = false) String menuPerms, @RequestParam(value = "icon", required = false) String icon
            , @RequestParam(value = "parentId", required = false) String parentId, @RequestParam(value = "sort", required = false) String sort
            , @RequestParam(value = "menuType", required = false) String menuType, @RequestParam(value = "remark", required = false) String remark
            , @RequestParam(value = "menuId", required = false) String menuId) {
        if (!UtilHelper.isNumer(menuId)) {
            return R.error(400, "菜单编号格式不正确，请联系系统管理员！");
        }
        Map<String, Object> map = sysMenuService.updateSysMenu(menuName, menuUrl, menuPerms, icon, Long.valueOf(parentId), sort, Integer.valueOf(menuType), remark, Long.valueOf(menuId));
        return R.ok(map);

    }

    /**
     * 根据公司id删除菜单信息（只更新状态，不作删除处理）
     *
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/deleteSysMenuById", method = RequestMethod.GET)
    public R deleteSysMenuById(@RequestParam(value = "menuId", required = false) String menuId) {
        if (!UtilHelper.isNumer(menuId)) {
            return R.error(400, "菜单编号格式不正确，请联系系统管理员！");
        }
        Map<String, Object> map = sysMenuService.deleteSysMenuById(Long.valueOf(menuId));
        return R.ok(map);
    }

    /**
     * 根据菜单id获取信息
     *
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/findSysMenuById", method = RequestMethod.GET)
    public R findSysMenuById(@RequestParam(value = "menuId", required = false) String menuId) {
        if (!UtilHelper.isNumer(menuId)) {
            return R.error(400, "菜单编号格式不正确，请联系系统管理员！");
        }
        Map<String, Object> map = sysMenuService.findSysMenuById(Long.valueOf(menuId));
        return R.ok().put(200, map, "获取成功！");
    }
}
