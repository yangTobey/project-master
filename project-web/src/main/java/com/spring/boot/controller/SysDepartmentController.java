package com.spring.boot.controller;

import com.spring.boot.service.SysDepartmentService;
import com.spring.boot.service.SysUserService;
import com.spring.boot.util.R;
import com.spring.boot.util.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/6.
 */
@Controller
@RequestMapping("/sysDepartment")
public class SysDepartmentController {

    @Autowired
    private SysDepartmentService sysDepartmentService;

    /**
     * 获取部门列表
     * @return
     */
    @RequestMapping(value = "/getSysDepartmentInfo", method = RequestMethod.GET)
    @ResponseBody
    public R getSysDepartmentInfo(){
        Map<String, Object> map=sysDepartmentService.getSysDepartmentInfo();
        System.out.println(map.get("list"));
        return R.ok().put("200",map);
    }

    /**
     * 添加部门
     * @return
     */
    @RequestMapping(value = "addSysDepartment", method = RequestMethod.GET)
    public String addSysDepartment(String departmentName,String companyId) {
        sysDepartmentService.addSysDepartment(departmentName,companyId);
        return "views/login/login";
    }
    /**
     * 更新部门信息
     * @return
     */
    @RequestMapping(value = "updateSysDepartmentInfo", method = RequestMethod.GET)
    public String updateSysDepartmentInfo(String departmentId, String departmentName,String companyId) {
        sysDepartmentService.updateSysDepartmentInfo(departmentId,departmentName,companyId);
        return "views/login/login";
    }
    /**
     * 添加部门
     * @return
     */
    @RequestMapping(value = "deleteSysDepartment", method = RequestMethod.GET)
    @ResponseBody
    public String deleteSysDepartment(String departmentId) {
        int count=sysDepartmentService.deleteSysDepartment(departmentId);
        System.out.println("删除条数："+count);
        return "views/login/login";
    }
}
