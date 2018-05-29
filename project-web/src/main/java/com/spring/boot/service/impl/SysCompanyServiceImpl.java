package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysUser;
import com.spring.boot.controller.SysCompanyController;
import com.spring.boot.service.SysCompanyService;
import com.spring.boot.service.SysUserService;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.service.web.SysUserBusinessService;
import com.spring.boot.util.R;
import com.spring.boot.util.ShiroUtils;
import com.spring.boot.util.UtilHelper;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysCompanyServiceImpl implements SysCompanyService {
    private static final Logger logger = Logger.getLogger(SysCompanyServiceImpl.class);
    @Autowired
    private SysCompanyBusinessService sysCompanyBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Map<String, Object> getSysCompanyList(Integer limit, Integer offset) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("limit", limit);
        map.put("offset", offset);
        try {
            resultMap.put("total", sysCompanyBusinessService.getSysCompanyListTotal(map));
            resultMap.put("list", sysCompanyBusinessService.getSysCompanyList(map));
            return R.ok().putData(200,resultMap,"获取成功！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取公司列表出错："+e.getMessage());
            return R.error(500,"服务器异常，请联系管理员！");
        }

    }

    @Override
    public Map<String,Object> addSysCompany(String companyName, String companyPhone, String companyAddress) {
        Map<String, Object> map = new HashMap<String, Object>();
        //公司编码（服务识别号）
        String companyCode = "C" + RandomUtils.nextInt(10) + RandomUtils.nextInt(10) + String.valueOf(System.currentTimeMillis()).substring(5, 12) + UtilHelper.chars.charAt((int) (Math.random() * 52));
        map.put("companyName", companyName);
        map.put("companyPhone", companyPhone);
        map.put("parentId", 1);
        map.put("companyAddress", companyAddress);
        map.put("companyCode", companyCode);
        map.put("createTime", UtilHelper.getNowTimeStr());
        try {
            int count=sysCompanyBusinessService.addSysCompany(map);
            if(count>0){
                return R.ok(200,"新增成功！");
            }else{
                return R.error(500,"新增失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("新增公司出错："+e.getMessage());
            return R.error(500,"添加失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String,Object> updateSysCompanyInfo(String companyId, String companyName, String companyPhone, String companyAddress) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        map.put("companyName", companyName);
        map.put("companyPhone", companyPhone);
        map.put("parentId", 1);
        map.put("companyAddress", companyAddress);
        try {
            int count=sysCompanyBusinessService.updateSysCompanyInfo(map);
            if(count>0){
                return R.ok(200,"更新成功！");
            }else{
                return R.error(500,"更新失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("更新公司信息出错："+e.getMessage());
            return R.error(500,"更新信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String,Object> deleteSysCompanyById(String companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        try {
            int count=sysCompanyBusinessService.deleteSysCompanyById(map);
            if(count>0){
                return R.ok(200,"删除成功！");
            }else{
                return R.error(500,"删除失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除公司信息出错："+e.getMessage());
            return R.error(500,"删除信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> findSysCompanyByCompanyId(long companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object>  resultMap = new HashMap<String, Object>();
        map.put("companyId", companyId);
        SysCompany sysCompany = sysCompanyBusinessService.findSysCompanyByCompanyId(map);
        resultMap.put("data", sysCompanyBusinessService.findSysCompanyByCompanyId(map));
        return resultMap;
    }

    @Override
    public Map<String, Object> getAllSysCompany() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            List<SysCompany> sysCompanyList = sysCompanyBusinessService.getAllSysCompany();
            resultMap.put("list", sysCompanyList);
            return R.ok().putData(200,resultMap,"获取成功！");
        }catch (Exception e){
            logger.info("获取全部公司信息出错："+e.getMessage());
            return R.error(500,"删除信息失败，服务器异常，请联系管理员！");
        }
    }
}
