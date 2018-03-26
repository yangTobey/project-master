package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysBasicData;
import com.spring.boot.bean.master.entity.SysBasicDataEntity;
import com.spring.boot.service.SysBasicDataService;
import com.spring.boot.service.SysCompanyService;
import com.spring.boot.service.web.SysBasicDataBusinessService;
import com.spring.boot.service.web.SysCompanyBusinessService;
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
public class SysBasicDataServiceImpl implements SysBasicDataService {
    private static final Logger logger = Logger.getLogger(SysBasicDataServiceImpl.class);
    @Autowired
    private SysBasicDataBusinessService sysBasicDataBusinessService;
    @Autowired
    private SysCompanyBusinessService sysCompanyBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    Map<String, Object> map = null;
    Map<String, Object> resultMap = null;

    @Override
    public Map<String, Object> sysBasicDataAnalysisData(long companyId, int year,int month) {
        resultMap = new HashMap<String, Object>();
        map = new HashMap<String, Object>();
        map.put("companyId",companyId);
        map.put("year",year);
        map.put("month",month);
        try {
            SysBasicDataEntity sysBasicDataEntity = sysBasicDataBusinessService.sysBasicDataAnalysisData(map);
            if (sysBasicDataEntity!=null) {
                int querySubsidiaryCount=sysCompanyBusinessService.querySubsidiaryCount(map);
                //分公司总数
                sysBasicDataEntity.setSubsidiaryCount(querySubsidiaryCount);
                //房屋装修率
                sysBasicDataEntity.setDecorateHouseScale(Double.valueOf(UtilHelper.DecimalFormatNumber(sysBasicDataEntity.getDecorateHouseNumber(),sysBasicDataEntity.getHouseNumber()))*100);
                //车位空置率
                sysBasicDataEntity.setForSaleParkingSpaceScale(Double.valueOf(UtilHelper.DecimalFormatNumber(sysBasicDataEntity.getForSaleParkingSpace(),sysBasicDataEntity.getParkingSpace()))*100);
                //房屋空置率
                sysBasicDataEntity.setForSaleHouseScale(Double.valueOf(UtilHelper.DecimalFormatNumber(sysBasicDataEntity.getForSaleHouseNumber(),sysBasicDataEntity.getHouseNumber()))*100);
                return R.ok().putData(200,sysBasicDataEntity,"获取成功！");
            }else{
                return R.error(500,"获取失败，数据不存在1！");
            }

            //resultMap.put("data",sysBasicDataEntity);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取基础信息失败："+e.getMessage());
            return R.error(500,"获取失败，服务器异常，请联系系统管理员！");
        }
    }

    @Override
    public Map<String, Object> sysBasicDataAnalysisList(long companyId,int limit, int offset, int year) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        map = new HashMap<String, Object>();
        map.put("companyId",companyId);
        map.put("limit",limit);
        map.put("offset",offset);
        map.put("year",year);
        try {
            resultMap.put("total", sysBasicDataBusinessService.sysBasicDataAnalysisListTotal(map));
            resultMap.put("list", sysBasicDataBusinessService.sysBasicDataAnalysisList(map));
            return R.ok().putData(200,resultMap,"获取成功！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取基础列表信息失败："+e.getMessage());
            return R.error(500,"获取列表失败，服务器异常，请联系系统管理员！");
        }
    }

    @Override
    public Map<String, Object> addSysBasicData(int year, int month,double constructionArea,double chargeArea,int cityNumber,int projectNumber,int houseNumber,int acceptHouseNumber
            , int forSaleHouseNumber,int decorateHouseNumber,int parkingSpace,int forSaleParkingSpace,int salesDistribution,int companyId) {
        map = new HashMap<String, Object>();
        map.put("year", year);
        map.put("month", month);
        map.put("constructionArea", constructionArea);
        map.put("chargeArea", chargeArea);
        map.put("cityNumber", cityNumber);
        map.put("projectNumber", projectNumber);
        map.put("houseNumber", houseNumber);
        map.put("acceptHouseNumber", acceptHouseNumber);
        map.put("forSaleHouseNumber", forSaleHouseNumber);
        map.put("decorateHouseNumber", decorateHouseNumber);
        map.put("parkingSpace", parkingSpace);
        map.put("forSaleParkingSpace", forSaleParkingSpace);
        map.put("salesDistribution", salesDistribution);
        map.put("companyId", companyId);
        try {
            int count=sysBasicDataBusinessService.addSysBasicData(map);
            if(count>0){
                return R.ok(200,"新增成功！");
            }else{
                return R.error(500,"新增失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取基础列表信息失败："+e.getMessage());
            return R.error(500,"获取列表失败，服务器异常，请联系系统管理员！");
        }
    }

    @Override
    public Map<String, Object> updateSysBasicData(int basicId,int year, int month,double constructionArea,double chargeArea,int cityNumber,int projectNumber,int houseNumber,int acceptHouseNumber
            , int forSaleHouseNumber,int decorateHouseNumber,int parkingSpace,int forSaleParkingSpace,int salesDistribution,int companyId) {
        map = new HashMap<String, Object>();
        map.put("basicId", basicId);
        map.put("year", year);
        map.put("month", month);
        map.put("constructionArea", constructionArea);
        map.put("chargeArea", chargeArea);
        map.put("cityNumber", cityNumber);
        map.put("projectNumber", projectNumber);
        map.put("houseNumber", houseNumber);
        map.put("acceptHouseNumber", acceptHouseNumber);
        map.put("forSaleHouseNumber", forSaleHouseNumber);
        map.put("decorateHouseNumber", decorateHouseNumber);
        map.put("parkingSpace", parkingSpace);
        map.put("forSaleParkingSpace", forSaleParkingSpace);
        map.put("salesDistribution", salesDistribution);
        map.put("companyId", companyId);
        try {
            int count=sysBasicDataBusinessService.updateSysBasicData(map);
            if(count>0){
                return R.ok(200,"更新成功！");
            }else{
                return R.error(500,"更新失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("更新信息失败："+e.getMessage());
            return R.error(500,"更新失败，服务器异常，请联系系统管理员！");
        }
    }

    @Override
    public Map<String, Object> deleteSysBasicData(int basicId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("basicId", basicId);
        try {
            int count=sysBasicDataBusinessService.deleteSysBasicData(map);
            if(count>0){
                return R.ok(200,"删除成功！");
            }else{
                return R.error(500,"删除失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("更新信息失败："+e.getMessage());
            return R.error(500,"删除失败，服务器异常，请联系系统管理员！");
        }
    }

    @Override
    public Map<String, Object> findSysBasicDataById(int basicId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("basicId", basicId);
        try {
            SysBasicDataEntity sysBasicDataEntity=sysBasicDataBusinessService.findSysBasicDataById(map);
            if(null!=sysBasicDataEntity){
                return R.ok().putData(200,sysBasicDataEntity,"获取成功！");
            }else{
                return R.error(500,"没有找到对应的信息，请联系系统管理员进行操作！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("根据id查找基础信息失败："+e.getMessage());
            return R.error(500,"根据id查找基础信息失败，服务器异常，请联系系统管理员！");
        }
    }
}
