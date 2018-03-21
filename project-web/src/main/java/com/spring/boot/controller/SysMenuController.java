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
 * Created by Administrator on 2018/1/25.
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
        Map<String, Object> map = sysMenuService.getSysMenu(ShiroUtils.getUserEntity().getUserId());
        return R.ok().put(200, map,"获取成功！");
    }
    /**
     * 查询公司信息
     *
     * @param limit  每页限制条数
     * @param offset 页码
     * @return
     */
    @RequestMapping(value = "/getSysCompanyList", method = RequestMethod.GET)
    public R getSysCompanyList(@RequestParam(value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset) {
        if (!UtilHelper.isNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字！");
        }
        if (!UtilHelper.isNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字！");
        }
        Map<String, Object> map = sysMenuService.getSysCompanyList(Integer.valueOf(limit), Integer.valueOf(offset));
        return R.ok().put(200, map,"获取成功！");
    }

    /**
     * 新增公司信息
     *
     * @param companyName
     * @param companyPhone
     * @param companyAddress
     * @return
     */
    @RequestMapping(value = "/addSysCompany", method = RequestMethod.GET)
    public R addSysCompany(@RequestParam(value = "companyName", required = false)String companyName, @RequestParam(value = "companyPhone", required = false)String companyPhone
            , @RequestParam(value = "companyAddress", required = false)String companyAddress) {
        int count = sysMenuService.addSysCompany(companyName, companyPhone, companyAddress);
        if (count > 0) {
            return R.ok(200, "新增成功！");
        }
        return R.error(500, "新增失败，服务器异常，请联系系统管理员！");
    }

    /**
     * 更新公司信息
     *
     * @param companyId      公司id
     * @param companyName    公司名称
     * @param companyPhone   公司电话
     * @param companyAddress 公司地址
     * @return
     */
    @RequestMapping(value = "/updateSysCompanyInfo", method = RequestMethod.GET)
    public R updateSysCompanyInfo(@RequestParam(value = "companyId", required = false)String companyId, @RequestParam(value = "companyName", required = false)String companyName
            , @RequestParam(value = "companyPhone", required = false)String companyPhone,@RequestParam(value = "companyAddress", required = false) String companyAddress) {
        logger.info("公司信息更新操作！");
        if(!UtilHelper.isNumer(companyId)){
            return R.error(400, "公司编号格式不正确，请联系系统管理员！");
        }
        if (UtilHelper.isEmpty(companyName)) {
            return R.error(400, "新密码不能为空！");
        }
        int count = sysMenuService.updateSysCompanyInfo(companyId, companyName, companyPhone, companyAddress);
        if (count > 0) {
            return R.ok(200, "更新成功！");
        }
        return R.error(500, "更新失败，服务器异常，请联系系统管理员！");

    }

    /**
     * 根据公司id删除公司信息（只更新公司状态，不作删除处理）
     *
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/deleteSysCompanyById", method = RequestMethod.GET)
    public R deleteSysCompanyById(@RequestParam(value = "companyId", required = false)String companyId) {
         if(!UtilHelper.isNumer(companyId)){
            return R.error(400, "公司编号格式不正确，请联系系统管理员！");
        }
        int count = sysMenuService.deleteSysCompanyById(companyId);
        if (count > 0) {
            return R.ok(200, "更新成功！");
        }
        return R.error(500, "删除失败，系统异常，请联系系统管理员！");
    }
    /**
     * 根据公司id获取信息
     *
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/findSysCompanyByCompanyId", method = RequestMethod.GET)
    public R findSysCompanyByCompanyId(@RequestParam(value = "companyId", required = false)String companyId){
        if(!UtilHelper.isNumer(companyId)){
            return R.error(400, "公司编号格式不正确，请联系系统管理员！");
        }
        Map<String, Object> map = sysMenuService.findSysCompanyByCompanyId(Long.valueOf(companyId));
        return R.ok().put(200, map,"获取成功！");
    }
}
