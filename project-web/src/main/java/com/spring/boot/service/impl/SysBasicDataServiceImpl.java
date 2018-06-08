package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysBasicData;
import com.spring.boot.bean.master.SysUpdateDataRules;
import com.spring.boot.bean.master.entity.SysBasicDataEntity;
import com.spring.boot.service.SysBasicDataService;
import com.spring.boot.service.SysDataAnalysisService;
import com.spring.boot.service.web.SysBasicDataBusinessService;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.service.web.SysUpdateDataRulesBusinessService;
import com.spring.boot.util.R;
import com.spring.boot.util.SysUtil;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private SysDataAnalysisService sysDataAnalysisService;
    @Autowired
    private SysUpdateDataRulesBusinessService sysUpdateDataRulesBusinessService;

    //字符串
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //对象
    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public Map<String, Object> sysBasicDataAnalysisData(long companyId, int year, int month) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        List<Long> sysUserCompanyIds = null;
        try {
            if (companyId == 0) {
                //获取用户权限下可操作的小区信息
                sysUserCompanyIds = SysUtil.getSysUserCompany();
            } else {
                sysUserCompanyIds = new ArrayList<Long>();
                sysUserCompanyIds.add(companyId);
            }
            map.put("sysUserCompanyIds", sysUserCompanyIds);
            map.put("year", year);
            map.put("month", month);
            /*注：type为1时，为按区域查询（小区）查询数据，type为2时，不考虑登录用户权限内小区，查询全国数据，即是物业大屏数据展示分析接口使用*/
            map.put("type", 1);
            SysBasicDataEntity sysBasicDataEntity = sysBasicDataBusinessService.sysBasicDataAnalysisData(map);
            if (sysBasicDataEntity != null) {
                if (companyId == 0) {
                    int querySubsidiaryCount = sysCompanyBusinessService.querySubsidiaryCount(map);
                    //分公司总数（全部公司数量，总公司算分公司）
                    sysBasicDataEntity.setSubsidiaryCount(querySubsidiaryCount);
                } else {
                    //分公司总数
                    sysBasicDataEntity.setSubsidiaryCount(0);
                }
                //房屋装修率
                sysBasicDataEntity.setDecorateHouseScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysBasicDataEntity.getDecorateHouseNumber(), sysBasicDataEntity.getHouseNumber())));
                //车位空置率
                sysBasicDataEntity.setForSaleParkingSpaceScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysBasicDataEntity.getForSaleParkingSpace(), sysBasicDataEntity.getParkingSpace())));
                //房屋空置率
                sysBasicDataEntity.setForSaleHouseScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysBasicDataEntity.getForSaleHouseNumber(), sysBasicDataEntity.getHouseNumber())));
                //入住率
                sysBasicDataEntity.setAcceptHouseNumberScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysBasicDataEntity.getAcceptHouseNumber(), sysBasicDataEntity.getHouseNumber())));
                return R.ok().putData(200, sysBasicDataEntity, "获取成功！");
            } else {
                sysBasicDataEntity=new SysBasicDataEntity();
                return R.ok().putData(200, sysBasicDataEntity, "数据不存在！");
            }

            //resultMap.put("data",sysBasicDataEntity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取基础信息失败：" + e.getMessage());
            return R.error(500, "获取失败，服务器异常，请联系系统管理员！");
        }
    }

    @Override
    public Map<String, Object> sysBasicDataAnalysisList(long companyId, int limit, int offset, int year) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        List<Long> sysUserCompanyIds = null;
        try {
            if (companyId == 0) {
                //获取用户权限下可操作的小区信息
                sysUserCompanyIds = SysUtil.getSysUserCompany();
            } else {
                sysUserCompanyIds = new ArrayList<Long>();
                sysUserCompanyIds.add(companyId);
            }
            map.put("sysUserCompanyIds", sysUserCompanyIds);
            map.put("limit", limit);
            map.put("offset", offset);
            map.put("year", year);
            resultMap.put("total", sysBasicDataBusinessService.sysBasicDataAnalysisListTotal(map));
            resultMap.put("list", sysBasicDataBusinessService.sysBasicDataAnalysisList(map));
            return R.ok().putData(200, resultMap, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取基础列表信息失败：" + e.getMessage());
            return R.error(500, "获取列表失败，服务器异常，请联系系统管理员！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addSysBasicData(Integer year, Integer month, Double constructionArea, Double chargeArea, Integer cityNumber, Integer projectNumber, Integer houseNumber, Integer acceptHouseNumber
            , Integer forSaleHouseNumber, Integer decorateHouseNumber, Integer parkingSpace, Integer forSaleParkingSpace, Integer salesDistribution, Long companyId) {
        SysBasicData sysBasicData = sysBasicDataBusinessService.sysBasicDataRecord(companyId, year, month);
        if (null != sysBasicData) {
            return R.error(500, "新增失败，系统已存在" + year + "年" + month + "月的记录，不能重复添加");
        }
        Map<String, Object> map = new HashMap<String, Object>();
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
        map.put("createTime", UtilHelper.getNowTimeStr());

        int count = sysBasicDataBusinessService.addSysBasicData(map);
        if (count > 0) {
            SysUpdateDataRules sysUpdateDataRules=sysUpdateDataRulesBusinessService.findSysUpdateDataRules();
            boolean updateToRedis=SysUtil.updateToRedis(sysUpdateDataRules.getDay(),year,month);
            if(updateToRedis){
                //存储统计信息到redis缓存
                setBasicDataAnalysisDataToRedis();
            }
            return R.ok(200, "新增成功！");
        } else {
            return R.error(500, "新增失败！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> updateSysBasicData(Long basicId, Integer year, Integer month, Double constructionArea, Double chargeArea, Integer cityNumber, Integer projectNumber, Integer houseNumber, Integer acceptHouseNumber
            , Integer forSaleHouseNumber, Integer decorateHouseNumber, Integer parkingSpace, Integer forSaleParkingSpace, Integer salesDistribution, Long companyId) {
        SysBasicData sysBasicData = sysBasicDataBusinessService.sysBasicDataRecord(companyId, year, month);
        if (null != sysBasicData) {
            if (!basicId.equals(sysBasicData.getBasicId())) {
                return R.error(500, "更新失败，系统已存在" + year + "年" + month + "月的记录，不能重复添加");
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
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

        int count = sysBasicDataBusinessService.updateSysBasicData(map);
        if (count > 0) {
            SysUpdateDataRules sysUpdateDataRules=sysUpdateDataRulesBusinessService.findSysUpdateDataRules();
            boolean updateToRedis=SysUtil.updateToRedis(sysUpdateDataRules.getDay(),year,month);
            if(updateToRedis){
                //存储统计信息到redis缓存
                setBasicDataAnalysisDataToRedis();
            }
            return R.ok(200, "更新成功！");
        } else {
            return R.error(500, "更新失败！");
        }

    }

    @Override
    public Map<String, Object> deleteSysBasicData(int basicId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("basicId", basicId);
        SysBasicDataEntity sysBasicDataEntity = sysBasicDataBusinessService.findSysBasicDataById(map);
        int count = sysBasicDataBusinessService.deleteSysBasicData(map);
        if (count > 0) {
            if (null != sysBasicDataEntity) {
                SysUpdateDataRules sysUpdateDataRules=sysUpdateDataRulesBusinessService.findSysUpdateDataRules();
                boolean updateToRedis=SysUtil.updateToRedis(sysUpdateDataRules.getDay(),sysBasicDataEntity.getYear(),sysBasicDataEntity.getMonth());
                if(updateToRedis){
                    //存储统计信息到redis缓存
                    setBasicDataAnalysisDataToRedis();
                }
            }
            return R.ok(200, "删除成功！");
        } else {
            return R.error(500, "删除失败！");
        }
    }

    @Override
    public Map<String, Object> findSysBasicDataById(int basicId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("basicId", basicId);
        try {
            SysBasicDataEntity sysBasicDataEntity = sysBasicDataBusinessService.findSysBasicDataById(map);
            if (null != sysBasicDataEntity) {
                return R.ok().putData(200, sysBasicDataEntity, "获取成功！");
            } else {
                return R.error(500, "没有找到对应的信息，请联系系统管理员进行操作！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("根据id查找基础信息失败：" + e.getMessage());
            return R.error(500, "根据id查找基础信息失败，服务器异常，请联系系统管理员！");
        }
    }

    /**
     * 将基础信息放进redis缓存
     */
    @Override
    public void setBasicDataAnalysisDataToRedis() {
        Map<String, Object> analysisMap = new HashMap<String, Object>();
        SysUpdateDataRules sysUpdateDataRules=sysUpdateDataRulesBusinessService.findSysUpdateDataRules();
        //获取需要查询的年份和月份
        Map<String,Integer> yearAndMonthMap=SysUtil.getYearAndMonth(sysUpdateDataRules.getDay());
        analysisMap.put("sysUserCompanyIds", null);
        analysisMap.put("year", yearAndMonthMap.get("year"));
        analysisMap.put("month", yearAndMonthMap.get("month"));
        /*注：type为1时，为按区域查询（小区）查询数据，type为2时，不考虑登录用户权限内小区，查询全国数据，即是物业大屏数据展示分析接口使用*/
        analysisMap.put("type", 2);
        SysBasicDataEntity sysBasicDataEntity = sysBasicDataBusinessService.sysBasicDataAnalysisData(analysisMap);
        if (sysBasicDataEntity != null) {
            int querySubsidiaryCount = sysCompanyBusinessService.querySubsidiaryCount(analysisMap);
            //分公司总数（全部公司数量，总公司算分公司）
            sysBasicDataEntity.setSubsidiaryCount(querySubsidiaryCount);
            //房屋装修率
            sysBasicDataEntity.setDecorateHouseScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysBasicDataEntity.getDecorateHouseNumber(), sysBasicDataEntity.getHouseNumber())));
            //车位空置率
            sysBasicDataEntity.setForSaleParkingSpaceScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysBasicDataEntity.getForSaleParkingSpace(), sysBasicDataEntity.getParkingSpace())));
            //房屋空置率
            sysBasicDataEntity.setForSaleHouseScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysBasicDataEntity.getForSaleHouseNumber(), sysBasicDataEntity.getHouseNumber())));
            //入住率
            sysBasicDataEntity.setAcceptHouseNumberScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysBasicDataEntity.getAcceptHouseNumber(), sysBasicDataEntity.getHouseNumber())));
            //设置基础信息redis缓存信息，为物业大屏数据分析统计做缓存服务
            redisTemplate.opsForValue().set("sysBasicData", sysBasicDataEntity);
            //调取物业大屏数据接口
            sysDataAnalysisService.sysPropertyDataAnalysis();
        }
    }
}
