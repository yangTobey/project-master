package com.spring.boot.service.impl;

import com.spring.boot.service.SysBasicDataService;
import com.spring.boot.service.SysCompanyService;
import com.spring.boot.service.web.SysBasicDataBusinessService;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.util.UtilHelper;
import org.apache.commons.lang.math.RandomUtils;
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
    @Autowired
    private SysBasicDataBusinessService sysBasicDataBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    Map<String, Object> map = null;

    @Override
    public Map<String, Object> sysBasicDataAnalysisData(int companyId, int year,int month) {
        Map<String, Object> mapData = new HashMap<String, Object>();
        map = new HashMap<String, Object>();
        map.put("companyId",companyId);
        map.put("year",year);
        map.put("month",month);
        mapData.put("total", sysBasicDataBusinessService.sysBasicDataAnalysisData(map));
        //mapData.put("list", sysBasicDataBusinessService.getSysCompanyList(map));
        return mapData;
    }

    @Override
    public Map<String, Object> sysBasicDataAnalysisList(int limit, int offset, int year) {
        Map<String, Object> mapData = new HashMap<String, Object>();
        map = new HashMap<String, Object>();
        map.put("limit",limit);
        map.put("offset",offset);
        map.put("year",year);
        //mapData.put("total", sysCompanyBusinessService.getSysCompanyListTotal(map));
        //mapData.put("list", sysCompanyBusinessService.getSysCompanyList(map));
        return mapData;
    }

    @Override
    public int addSysBasicData(int year, int month,double constructionArea,double chargeArea,int cityNumber,int projectNumber,int houseNumber,int acceptHouseNumber
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
        return sysBasicDataBusinessService.addSysBasicData(map);
    }

    @Override
    public int updateSysBasicData(int basicId,int year, int month,double constructionArea,double chargeArea,int cityNumber,int projectNumber,int houseNumber,int acceptHouseNumber
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
        return sysBasicDataBusinessService.updateSysBasicData(map);
    }

    @Override
    public int deleteSysBasicData(int basicId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("basicId", basicId);
        return sysBasicDataBusinessService.deleteSysBasicData(map);
    }
}
