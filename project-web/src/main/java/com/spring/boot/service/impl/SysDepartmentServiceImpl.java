package com.spring.boot.service.impl;

import com.spring.boot.service.SysDepartmentService;
import com.spring.boot.service.web.SysDepartmentBusinessService;
import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysDepartmentServiceImpl implements SysDepartmentService {
    private static final Logger logger = Logger.getLogger(SysDepartmentServiceImpl.class);
    @Autowired
    private SysDepartmentBusinessService sysDepartmentBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Map<String, Object> getSysDepartmentInfo(Integer limit,Integer offset) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("limit",limit);
        map.put("offset",offset);
        try {
            resultMap.put("total", sysDepartmentBusinessService.getSysDepartmentTotal(map));
            resultMap.put("list", sysDepartmentBusinessService.getSysDepartmentInfo(map));
            return R.ok().putData(200,resultMap,"获取成功！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取部门信息失败！"+e.getMessage());
            return R.error(500,"服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> getAllSysDepartment() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("limit",null);
        map.put("offset",null);
        try {
            resultMap.put("list", sysDepartmentBusinessService.getSysDepartmentInfo(map));
            return R.ok().putData(200,resultMap,"获取成功！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取部门信息失败！"+e.getMessage());
            return R.error(500,"服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> addSysDepartment(String departmentName, Long companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        //公司编码（服务识别号）
        String departmentCode="D"+ RandomUtils.nextInt(10)+RandomUtils.nextInt(10)+String.valueOf(System.currentTimeMillis()).substring(5,12)+ UtilHelper.chars.charAt((int)(Math.random() * 52));
        map.put("departmentName", departmentName);
        map.put("companyId", companyId);
        map.put("departmentCode", departmentCode);
        try {
            int count=sysDepartmentBusinessService.addSysDepartment(map);
            if(count>0){
                return R.ok(200,"新增成功！");
            }else{
                return R.error(500,"新增失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("新增部门信息失败！"+e.getMessage());
            return R.error(500,"服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> updateSysDepartmentInfo(Long departmentId, String departmentName, Long companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("departmentId", departmentId);
        map.put("departmentName", departmentName);
        map.put("companyId", companyId);
        /*map.put("status", 2);*/
        try {
            int count=sysDepartmentBusinessService.updateSysDepartmentInfo(map);
            if(count>0){
                return R.ok(200,"更新成功！");
            }else{
                return R.error(500,"更新失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("更新部门信息出错："+e.getMessage());
            return R.error(500,"更新部门信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> deleteSysDepartment(Long departmentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("departmentId", departmentId);
        try {
            int count=sysDepartmentBusinessService.deleteSysDepartment(map);
            if(count>0){
                return R.ok(200,"删除成功！");
            }else{
                return R.error(500,"删除失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除部门信息出错："+e.getMessage());
            return R.error(500,"删除部门信息失败，服务器异常，请联系管理员！");
        }
    }
}
