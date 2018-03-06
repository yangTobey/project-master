
package com.spring.boot.controller;

import com.spring.boot.service.SysContractService;
import com.spring.boot.service.SysDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2018/2/6.
 */
@Controller
@RequestMapping("/contract")
public class SysContractController {

    @Autowired
    private SysContractService sysContractService;

    /**
     * 登录系统
     * @param userName 账号
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/getDepartmentInfo", method = RequestMethod.GET)
    public String login(String userName,String password){

        return "views/login/login";
    }

    /**
     * 添加合同分类
     * @return
     */
    @RequestMapping(value = "addContractType", method = RequestMethod.GET)
    public String addContractType(String contractTypeName,String companyId) {
        sysContractService.addContractType(contractTypeName);
        return "views/login/login";
    }
    /**
     * 更新合同分类信息
     * @return
     */
    @RequestMapping(value = "updateContractType", method = RequestMethod.GET)
    public String updateContractType(String contractTypeId, String contractTypeName) {
        sysContractService.updateContractType(contractTypeId,contractTypeName);
        return "views/login/login";
    }
    /**
     * 删除合同分类
     * @return
     */
    @RequestMapping(value = "deleteContractType", method = RequestMethod.GET)
    public String deleteContractType(String contractTypeId) {
        int count=sysContractService.deleteContractType(contractTypeId);
        System.out.println("删除条数："+count);
        return "views/login/login";
    }


    /**
     * 添加合同
     * @return
     */
    @RequestMapping(value = "addContractInfo", method = RequestMethod.GET)
    public String addContractInfo(String contractTypeName,String companyId) {
        sysContractService.addContractType(contractTypeName);
        return "views/login/login";
    }
    /**
     * 更新合同
     * @return
     */
    @RequestMapping(value = "updateContractInfo", method = RequestMethod.GET)
    public String updateContractInfo(String contractTypeId, String contractTypeName) {
        sysContractService.updateContractType(contractTypeId,contractTypeName);
        return "views/login/login";
    }
    /**
     * 删除合同
     * @return
     */
    @RequestMapping(value = "deleteContractInfo", method = RequestMethod.GET)
    public String deleteContractInfo(String contractTypeId) {
        int count=sysContractService.deleteContractType(contractTypeId);
        System.out.println("删除条数："+count);
        return "views/login/login";
    }
}
