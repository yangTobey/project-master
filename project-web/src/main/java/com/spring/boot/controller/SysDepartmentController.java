package com.spring.boot.controller;

import com.spring.boot.service.SysDepartmentService;
import com.spring.boot.service.SysUserService;
import com.spring.boot.util.R;
import com.spring.boot.util.ShiroUtils;
import com.spring.boot.util.UtilHelper;
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
    @RequestMapping(value = "/getSysDepartmentInfo", method = RequestMethod.POST)
    @ResponseBody
    public R getSysDepartmentInfo(@RequestParam(value = "limit", required = false)String limit,@RequestParam(value = "offset", required = false) String offset){
        if (!UtilHelper.isNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字！");
        }else if (!UtilHelper.isNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字！");
        }
        Map<String, Object> map=sysDepartmentService.getSysDepartmentInfo(Integer.valueOf(limit),Integer.valueOf(offset));
        return R.ok(map);
    }
    /**
     * 获取系统全部部门
     * @return
     */
    @RequestMapping(value = "/getAllSysDepartment", method = RequestMethod.POST)
    @ResponseBody
    public R getAllSysDepartment(){
        Map<String, Object> map=sysDepartmentService.getAllSysDepartment();
        return R.ok(map);
    }

    /**
     * 添加部门
     * @return
     */
    @RequestMapping(value = "/addSysDepartment", method = RequestMethod.POST)
    @ResponseBody
    public R addSysDepartment(@RequestParam(value = "departmentName", required = false)String departmentName,@RequestParam(value = "companyId", required = false)String companyId) {
        if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不正确！");
        }else if (UtilHelper.isEmpty(departmentName)) {
            return R.error(400, "部门名称不能为空！");
        }
        Map<String, Object> map =sysDepartmentService.addSysDepartment(departmentName,Long.valueOf(companyId));
        return R.ok(map);
    }
    /**
     * 更新部门信息
     * @return
     */
    @RequestMapping(value = "/updateSysDepartmentInfo", method = RequestMethod.POST)
    @ResponseBody
    public R updateSysDepartmentInfo(@RequestParam(value = "departmentId", required = false)String departmentId, @RequestParam(value = "departmentName", required = false)String departmentName
            ,@RequestParam(value = "companyId", required = false)String companyId) {
        if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不正确！");
        }else if (UtilHelper.isEmpty(departmentName)) {
            return R.error(400, "部门名称不能为空！");
        }else if (!UtilHelper.isNumer(departmentId)) {
            return R.error(400, "部门id格式不正确！");
        }
        Map<String, Object> map =sysDepartmentService.updateSysDepartmentInfo(Long.valueOf(departmentId),departmentName,Long.valueOf(companyId));
        return R.ok(map);
    }
    /**
     * 添加部门
     * @return
     */
    @RequestMapping(value = "/deleteSysDepartment", method = RequestMethod.POST)
    @ResponseBody
    public R deleteSysDepartment(@RequestParam(value = "departmentId", required = false)String departmentId) {
        if (!UtilHelper.isNumer(departmentId)) {
            return R.error(400, "部门id格式不正确！");
        }
        Map<String, Object> map =sysDepartmentService.deleteSysDepartment(Long.valueOf(departmentId));
        return R.ok(map);
    }
}
