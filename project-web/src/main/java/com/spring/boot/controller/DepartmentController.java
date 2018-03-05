package com.spring.boot.controller;

import com.spring.boot.service.DepartmentService;
import com.spring.boot.service.UserService;
import com.spring.boot.util.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2018/2/6.
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

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
     * 添加部门
     * @return
     */
    @RequestMapping(value = "addDepartment", method = RequestMethod.GET)
    public String addDepartment(String departmentName,String companyId) {
        departmentService.addDepartment(departmentName,companyId);
        return "views/login/login";
    }
    /**
     * 更新部门信息
     * @return
     */
    @RequestMapping(value = "updateDepartmentInfo", method = RequestMethod.GET)
    public String updateDepartmentInfo(String departmentId, String departmentName,String companyId) {
        departmentService.updateDepartmentInfo(departmentId,departmentName,companyId);
        return "views/login/login";
    }
    /**
     * 添加部门
     * @return
     */
    @RequestMapping(value = "deleteDepartment", method = RequestMethod.GET)
    public String deleteDepartment(String departmentId) {
        int count=departmentService.deleteDepartment(departmentId);
        System.out.println("删除条数："+count);
        return "views/login/login";
    }
}
