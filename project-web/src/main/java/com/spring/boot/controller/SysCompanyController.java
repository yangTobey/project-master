package com.spring.boot.controller;

import com.spring.boot.service.SysCompanyService;
import com.spring.boot.util.R;
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
@RequestMapping("/sysCompany")
public class SysCompanyController {
    private static final Logger logger = Logger.getLogger(SysCompanyController.class);
    @Autowired
    private SysCompanyService sysCompanyService;

    /**
     * 查询公司信息
     *
     * @param limit 每页限制条数
     * @param offset 页码
     * @return
     */
    @RequestMapping(value = "/getSysCompanyList", method = RequestMethod.GET)
    public R getSysCompanyList(@RequestParam(value = "limit",required=false)String limit,@RequestParam(value = "offset",required=false)String offset) {
        if(!UtilHelper.isNumer(limit)){
            return R.error(400,"分页控制，每页条数limit只能为数字！");
        }
        if(!UtilHelper.isNumer(offset)){
            return R.error(400,"分页控制，页码offset只能为数字！");
        }
        Map<String, Object> map = sysCompanyService.getSysCompanyList(Integer.valueOf(limit),Integer.valueOf(offset));
        return R.ok().put("200", map);
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
    public String addSysCompany(String companyName, String companyPhone, String companyAddress) {
        int count = sysCompanyService.addSysCompany(companyName, companyPhone, companyAddress);
        if (count > 0) {
            System.out.println("新增成功！");
        }
        return "success";
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
    public String updateSysCompanyInfo(String companyId, String companyName, String companyPhone, String companyAddress) {
        logger.info("hello world");
        if (UtilHelper.isEmpty(companyId)) {
            return "公司id不能为空，请联系系统管理员进行修改！";
        }
        if (UtilHelper.isEmpty(companyName)) {
            return "新密码不能为空！";
        }
        int count = sysCompanyService.updateSysCompanyInfo(companyId, companyName, companyPhone, companyAddress);
        if (count > 0) {
            System.out.println("新增成功！");
        }
        return "success";

    }

    /**
     * 根据公司id删除公司信息（只更新公司状态，不作删除处理）
     *
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/deleteSysCompany", method = RequestMethod.GET)
    public R deleteSysCompanyInfo(String companyId) {
        if (UtilHelper.isEmpty(companyId)) {
            return R.error(400, "公司编号不能为空，请联系系统管理员！");
        }
        int count = sysCompanyService.deleteSysCompanyInfo(companyId);
        if (count > 0) {
            return R.ok(200, "公司编号不能为空，请联系系统管理员！");
        }
        return R.error(201, "系统异常，请联系系统管理员！");

    }

}
