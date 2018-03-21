package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysQualityManage;
import com.spring.boot.bean.master.entity.SysQualityManageEntity;
import com.spring.boot.service.SysCompanyService;
import com.spring.boot.service.SysQualityManageService;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.service.web.SysQualityManageBusinessService;
import com.spring.boot.util.UtilHelper;
import org.apache.commons.lang.math.RandomUtils;
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
public class SysQualityManageServiceImpl implements SysQualityManageService {
    @Autowired
    private SysQualityManageBusinessService sysQualityManageBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;
    Map<String, Object> resultMap = null;
    Map<String, Object> map = null;

    @Override
    public Map<String, Object> sysQualityManageAnalysisForYear(long companyId,String type) {
        resultMap = new HashMap<String, Object>();
        map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        map.put("year",UtilHelper.getYear());
        map.put("month",UtilHelper.getMonth());
        SysQualityManageEntity sysQualityManageEntity=null;
        if("year".equals(type)){
            //查找年度报表数据
            sysQualityManageEntity=sysQualityManageBusinessService.sysQualityManageAnalysisForYear(map);
            sysQualityManageEntity.setQualityCheckPassScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManageEntity.getQualityCheckPass(),sysQualityManageEntity.getQualityCheck())));
            sysQualityManageEntity.setModifiedPassScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManageEntity.getQualityCheckUnmodified(),sysQualityManageEntity.getQualityCheckFail())));
            resultMap.put("qualityManageYear", sysQualityManageEntity);
        }else{
            //查找月度报表数据
            sysQualityManageEntity=sysQualityManageBusinessService.sysQualityManageAnalysisForMonth(map);
            List<SysQualityManage> list=sysQualityManageBusinessService.sysQualityManageAnalysisList(map);
            //月度品质合格率
            double qualityCheckPassScale=0;
            //月度品质整改合格率
            double modifiedPassScale=0;
            if(list.size()>0){
                for(SysQualityManage sysQualityManage:list){
                    qualityCheckPassScale=UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManage.getQualityCheckPass(),sysQualityManage.getQualityCheck()));
                    modifiedPassScale=UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManage.getQualityCheckUnmodified(),sysQualityManage.getQualityCheckFail()));
                    switch (sysQualityManage.getMonth()){
                        case 1:
                            sysQualityManageEntity.setCheckPassScaleJan(qualityCheckPassScale);
                            sysQualityManageEntity.setModifiedPassScaleJan(modifiedPassScale);
                            break;
                        case 2:
                            sysQualityManageEntity.setCheckPassScaleFeb(qualityCheckPassScale);
                            sysQualityManageEntity.setModifiedPassScaleFeb(modifiedPassScale);
                            break;
                        case 3:
                            sysQualityManageEntity.setCheckPassScaleMar(qualityCheckPassScale);
                            sysQualityManageEntity.setModifiedPassScaleMar(modifiedPassScale);
                            break;
                        case 4:
                            sysQualityManageEntity.setCheckPassScaleApr(qualityCheckPassScale);
                            sysQualityManageEntity.setModifiedPassScaleApr(modifiedPassScale);
                            break;
                        case 5:
                            sysQualityManageEntity.setCheckPassScaleMay(qualityCheckPassScale);
                            sysQualityManageEntity.setModifiedPassScaleMay(modifiedPassScale);
                            break;
                        case 6:
                            sysQualityManageEntity.setCheckPassScaleJune(qualityCheckPassScale);
                            sysQualityManageEntity.setModifiedPassScaleJune(modifiedPassScale);
                            break;
                        case 7:
                            sysQualityManageEntity.setCheckPassScaleJuly(qualityCheckPassScale);
                            sysQualityManageEntity.setModifiedPassScaleJuly(modifiedPassScale);
                            break;
                        case 8:
                            sysQualityManageEntity.setCheckPassScaleAug(qualityCheckPassScale);
                            sysQualityManageEntity.setModifiedPassScaleAug(modifiedPassScale);
                            break;
                        case 9:
                            sysQualityManageEntity.setCheckPassScaleSept(qualityCheckPassScale);
                            sysQualityManageEntity.setModifiedPassScaleSept(modifiedPassScale);
                            break;
                        case 10:
                            sysQualityManageEntity.setCheckPassScaleOct(qualityCheckPassScale);
                            sysQualityManageEntity.setModifiedPassScaleOct(modifiedPassScale);
                            break;
                        case 11:
                            sysQualityManageEntity.setCheckPassScaleNov(qualityCheckPassScale);
                            sysQualityManageEntity.setModifiedPassScaleNov(modifiedPassScale);
                            break;
                        case 12:
                            sysQualityManageEntity.setCheckPassScaleDec(qualityCheckPassScale);
                            sysQualityManageEntity.setModifiedPassScaleDec(modifiedPassScale);
                            break;
                    }
                }
            }
            sysQualityManageEntity.setQualityCheckPassScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManageEntity.getQualityCheckPass(),sysQualityManageEntity.getQualityCheck())));
            sysQualityManageEntity.setModifiedPassScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManageEntity.getQualityCheckUnmodified(),sysQualityManageEntity.getQualityCheckFail())));
            resultMap.put("qualityManageMonth", sysQualityManageEntity);
        }

        return resultMap;
    }

    @Override
    public Map<String, Object> getSysQualityManageList(long companyId,int year,int limit, int offset) {
        resultMap = new HashMap<String, Object>();
        map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        map.put("year", year);
        map.put("limit", limit);
        map.put("offset", offset);
        resultMap.put("total", sysQualityManageBusinessService.getSysQualityManageListTotal(map));
        resultMap.put("list", sysQualityManageBusinessService.getSysQualityManageList(map));
        return resultMap;
    }

    @Override
    public int addSysQualityManage(long companyId, int year, int month, int qualityCheck, int qualityCheckPass, int qualityCheckFail, int securityEvent, int qualityCheckUnmodified) {
        map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        map.put("year", year);
        map.put("month", month);
        map.put("qualityCheck", qualityCheck);
        map.put("qualityCheckPass", qualityCheckPass);
        map.put("qualityCheckFail", qualityCheckFail);
        map.put("securityEvent", securityEvent);
        map.put("qualityCheckUnmodified", qualityCheckUnmodified);
        map.put("createTime", UtilHelper.getNowTimeStr());
        return sysQualityManageBusinessService.addSysQualityManage(map);
    }

    @Override
    public int updateSysQualityManage(long qualityId, long companyId, int year, int month, int qualityCheck, int qualityCheckPass, int qualityCheckFail, int securityEvent, int qualityCheckUnmodified) {
        map = new HashMap<String, Object>();
        map.put("qualityId", qualityId);
        map.put("companyId", companyId);
        map.put("year", year);
        map.put("month", month);
        map.put("qualityCheck", qualityCheck);
        map.put("qualityCheckPass", qualityCheckPass);
        map.put("qualityCheckFail", qualityCheckFail);
        map.put("securityEvent", securityEvent);
        map.put("qualityCheckUnmodified", qualityCheckUnmodified);
        return sysQualityManageBusinessService.updateSysQualityManage(map);
    }

    @Override
    public int deleteSysQualityManageById(long qualityId) {
        map = new HashMap<String, Object>();
        map.put("qualityId", qualityId);
        return sysQualityManageBusinessService.deleteSysQualityManageById(map);
    }

    @Override
    public Map<String, Object> findSysQualityManageById(long qualityId) {
        map = new HashMap<String, Object>();
        resultMap = new HashMap<String, Object>();
        map.put("qualityId", qualityId);
        SysQualityManage sysCompany = sysQualityManageBusinessService.findSysQualityManageById(map);
        resultMap.put("data", sysQualityManageBusinessService.findSysQualityManageById(map));
        return resultMap;
    }
}
