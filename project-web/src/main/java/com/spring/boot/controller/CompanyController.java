package com.spring.boot.controller;

import com.spring.boot.bean.master.User;
import com.spring.boot.service.CompanyService;
import com.spring.boot.service.UserService;
import com.spring.boot.util.ShiroUtils;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/1/25.
 */
@RestController
@RequestMapping("/company")
public class CompanyController {
    private static final Logger logger = Logger.getLogger(CompanyController.class);
    @Autowired
    private CompanyService companyService;

    /**
     * 新增公司信息
     * @param companyName
     * @param companyPhone
     * @param companyAddress
     * @return
     */
    @RequestMapping(value = "/addCompany", method = RequestMethod.GET)
    public String addCompany(String companyName, String companyPhone, String companyAddress) {
        int count= companyService.addCompany(companyName,companyPhone,companyAddress);
        if(count>0){
            System.out.println("新增成功！");
        }
        return "success";
    }

    /**
     * 更新公司信息
     * @param companyId 公司id
     * @param companyName 公司名称
     * @param companyPhone 公司电话
     * @param companyAddress 公司地址
     * @return
     */
    @RequestMapping(value = "/updateCompanyInfo", method = RequestMethod.GET)
    public String updateCompanyInfo(String companyId, String companyName,String companyPhone,String companyAddress) {
        logger.info("hello world");
        if(UtilHelper.isEmpty(companyId)){
           return "公司id不能为空，请联系系统管理员进行修改！";
        }
        if(UtilHelper.isEmpty(companyName)){
            return "新密码不能为空！";
        }
        int count= companyService.updateCompanyInfo(companyId,companyName,companyPhone,companyAddress);
        if(count>0){
            System.out.println("新增成功！");
        }
        return "success";

    }

}
