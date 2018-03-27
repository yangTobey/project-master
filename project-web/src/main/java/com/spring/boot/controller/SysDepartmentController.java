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
import org.springframework.web.bind.annotation.*;

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
    public R getSysDepartmentInfo(@RequestParam(value = "limit", required = false)String limit,@RequestParam(value = "offset", required = false) String offset){
        Map<String, Object> map=sysDepartmentService.getSysDepartmentInfo(limit,offset);
        return R.ok(map);
    }

    /**
     * 添加部门
     * @return
     */
    @RequestMapping(value = "addSysDepartment", method = RequestMethod.GET)
    public R addSysDepartment(String departmentName,String companyId) {
        Map<String, Object> map =sysDepartmentService.addSysDepartment(departmentName,companyId);
        return R.ok(map);
    }
    /**
     * 更新部门信息
     * @return
     */
    @RequestMapping(value = "updateSysDepartmentInfo", method = RequestMethod.GET)
    public R updateSysDepartmentInfo(String departmentId, String departmentName,String companyId) {
        Map<String, Object> map =sysDepartmentService.updateSysDepartmentInfo(departmentId,departmentName,companyId);
        return R.ok(map);
    }
    /**
     * 添加部门
     * @return
     */
    @RequestMapping(value = "deleteSysDepartment", method = RequestMethod.GET)
    @ResponseBody
    public R deleteSysDepartment(String departmentId) {
        Map<String, Object> map =sysDepartmentService.deleteSysDepartment(departmentId);
        return R.ok(map);
    }
}
