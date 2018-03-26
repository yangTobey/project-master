package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysQualityManage;
import com.spring.boot.bean.master.SysQualityManageFile;
import com.spring.boot.bean.master.entity.SysQualityManageEntity;
import com.spring.boot.service.SysCompanyService;
import com.spring.boot.service.SysQualityManageService;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.service.web.SysQualityManageBusinessService;
import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
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
    private static final Logger logger = Logger.getLogger(SysQualityManageServiceImpl.class);
    @Autowired
    private SysQualityManageBusinessService sysQualityManageBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;
    Map<String, Object> resultMap = null;
    Map<String, Object> map = null;

    @Override
    public Map<String, Object> sysQualityManageAnalysis(long companyId) {
        resultMap = new HashMap<String, Object>();
        map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        map.put("year",UtilHelper.getYear());
        map.put("month",UtilHelper.getMonth());
        SysQualityManageEntity sysQualityManageEntityForYear=null;
        SysQualityManageEntity sysQualityManageEntityForMonth=null;
        try {
                //查找年度报表数据
                sysQualityManageEntityForYear=sysQualityManageBusinessService.sysQualityManageAnalysisForYear(map);
                if(sysQualityManageEntityForYear!=null){
                    sysQualityManageEntityForYear.setQualityCheckPassScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManageEntityForYear.getQualityCheckPass(),sysQualityManageEntityForYear.getQualityCheck())));
                    sysQualityManageEntityForYear.setModifiedPassScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManageEntityForYear.getQualityCheckUnmodified(),sysQualityManageEntityForYear.getQualityCheckFail())));
                    resultMap.put("qualityManageYear", sysQualityManageEntityForYear);
                    //return R.ok().putData(200,sysQualityManageEntityForYear,"获取信息成功！");
                }else{
                    return R.error(500,"获取信息失败,不存在数据！");
                }
                //resultMap.put("data", sysQualityManageEntity);

                //查找月度报表数据
                sysQualityManageEntityForMonth=sysQualityManageBusinessService.sysQualityManageAnalysisForMonth(map);
                if(sysQualityManageEntityForMonth!=null){
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
                                    sysQualityManageEntityForMonth.setCheckPassScaleJan(qualityCheckPassScale);
                                    sysQualityManageEntityForMonth.setModifiedPassScaleJan(modifiedPassScale);
                                    break;
                                case 2:
                                    sysQualityManageEntityForMonth.setCheckPassScaleFeb(qualityCheckPassScale);
                                    sysQualityManageEntityForMonth.setModifiedPassScaleFeb(modifiedPassScale);
                                    break;
                                case 3:
                                    sysQualityManageEntityForMonth.setCheckPassScaleMar(qualityCheckPassScale);
                                    sysQualityManageEntityForMonth.setModifiedPassScaleMar(modifiedPassScale);
                                    break;
                                case 4:
                                    sysQualityManageEntityForMonth.setCheckPassScaleApr(qualityCheckPassScale);
                                    sysQualityManageEntityForMonth.setModifiedPassScaleApr(modifiedPassScale);
                                    break;
                                case 5:
                                    sysQualityManageEntityForMonth.setCheckPassScaleMay(qualityCheckPassScale);
                                    sysQualityManageEntityForMonth.setModifiedPassScaleMay(modifiedPassScale);
                                    break;
                                case 6:
                                    sysQualityManageEntityForMonth.setCheckPassScaleJune(qualityCheckPassScale);
                                    sysQualityManageEntityForMonth.setModifiedPassScaleJune(modifiedPassScale);
                                    break;
                                case 7:
                                    sysQualityManageEntityForMonth.setCheckPassScaleJuly(qualityCheckPassScale);
                                    sysQualityManageEntityForMonth.setModifiedPassScaleJuly(modifiedPassScale);
                                    break;
                                case 8:
                                    sysQualityManageEntityForMonth.setCheckPassScaleAug(qualityCheckPassScale);
                                    sysQualityManageEntityForMonth.setModifiedPassScaleAug(modifiedPassScale);
                                    break;
                                case 9:
                                    sysQualityManageEntityForMonth.setCheckPassScaleSept(qualityCheckPassScale);
                                    sysQualityManageEntityForMonth.setModifiedPassScaleSept(modifiedPassScale);
                                    break;
                                case 10:
                                    sysQualityManageEntityForMonth.setCheckPassScaleOct(qualityCheckPassScale);
                                    sysQualityManageEntityForMonth.setModifiedPassScaleOct(modifiedPassScale);
                                    break;
                                case 11:
                                    sysQualityManageEntityForMonth.setCheckPassScaleNov(qualityCheckPassScale);
                                    sysQualityManageEntityForMonth.setModifiedPassScaleNov(modifiedPassScale);
                                    break;
                                case 12:
                                    sysQualityManageEntityForMonth.setCheckPassScaleDec(qualityCheckPassScale);
                                    sysQualityManageEntityForMonth.setModifiedPassScaleDec(modifiedPassScale);
                                    break;
                            }
                        }
                    }
                    sysQualityManageEntityForMonth.setQualityCheckPassScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManageEntityForMonth.getQualityCheckPass(),sysQualityManageEntityForMonth.getQualityCheck())));
                    sysQualityManageEntityForMonth.setModifiedPassScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManageEntityForMonth.getQualityCheckUnmodified(),sysQualityManageEntityForMonth.getQualityCheckFail())));
                    resultMap.put("qualityManageMonth", sysQualityManageEntityForMonth);
                }else{
                    return R.error(500,"获取信息失败，不存在数据！");
                }

        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取品质管理数据失败："+e.getMessage());
            return R.error(500,"获取信息失败，服务器异常，请联系系统管理员！");
        }
        return R.ok().putData(200,resultMap,"获取信息成功！");
    }

    @Override
    public Map<String, Object> getSysQualityManageList(long companyId,int year,int limit, int offset) {
        resultMap = new HashMap<String, Object>();
        map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        map.put("year", year);
        map.put("limit", limit);
        map.put("offset", offset);
        try {
            resultMap.put("total", sysQualityManageBusinessService.getSysQualityManageListTotal(map));
            resultMap.put("list", sysQualityManageBusinessService.getSysQualityManageList(map));
            return R.ok().putData(200,resultMap,"获取数据成功！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取品质管理列表数据失败："+e.getMessage());
            return R.error(500,"获取品质管理列表数据失败，服务器异常，请联系系统管理员！");
        }
    }

    @Override
    public Map<String,Object> addSysQualityManage(long companyId, int year, int month, int qualityCheck, int qualityCheckPass, int qualityCheckFail, int securityEvent, int qualityCheckUnmodified) {
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
        try {
            int count=sysQualityManageBusinessService.addSysQualityManage(map);
            if(count>0){
                return R.ok(200,"新增数据成功！");
            }else{
                return R.error(500,"获取数据失败，服务器异常！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("新增品质管理数据失败："+e.getMessage());
            return R.error(500,"新增品质管理数据失败，服务器异常，请联系系统管理员！");
        }
    }

    @Override
    public Map<String,Object> updateSysQualityManage(long qualityId, long companyId, int year, int month, int qualityCheck, int qualityCheckPass, int qualityCheckFail, int securityEvent, int qualityCheckUnmodified) {
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
        try {
            int count=sysQualityManageBusinessService.updateSysQualityManage(map);
            if(count>0){
                return R.ok(200,"更新数据成功！");
            }else{
                return R.error(500,"更新数据失败，服务器异常！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("更新品质管理数据失败："+e.getMessage());
            return R.error(500,"更新品质管理数据失败，服务器异常，请联系系统管理员！");
        }
    }

    @Override
    public Map<String,Object> deleteSysQualityManageById(long qualityId) {
        map = new HashMap<String, Object>();
        map.put("qualityId", qualityId);
        try {
            int count=sysQualityManageBusinessService.deleteSysQualityManageById(map);
            if(count>0){
                return R.ok(200,"删除数据成功！");
            }else{
                return R.error(500,"删除数据失败，服务器异常！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除品质管理数据失败："+e.getMessage());
            return R.error(500,"删除品质管理数据失败，服务器异常，请联系系统管理员！");
        }
    }

    @Override
    public Map<String, Object> findSysQualityManageById(long qualityId) {
        map = new HashMap<String, Object>();
        resultMap = new HashMap<String, Object>();
        map.put("qualityId", qualityId);
        //SysQualityManage sysCompany = sysQualityManageBusinessService.findSysQualityManageById(map);
        try {
            SysQualityManage sysQualityManage=sysQualityManageBusinessService.findSysQualityManageById(map);
            return R.ok().putData(200,sysQualityManage,"根据id查找品质管理数据成功！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("根据id查找品质管理数据失败："+e.getMessage());
            return R.error(500,"根据id查找品质管理数据失败，服务器异常，请联系系统管理员！");
        }
    }

    @Override
    public Map<String, Object> findSysQualityManageFileById(long qualityId) {
        try {
            List<SysQualityManageFile> fileList=sysQualityManageBusinessService.findSysQualityManageFileById(qualityId);
            if(fileList!=null){
                resultMap.put("list", fileList);
                return R.ok().putData(200,resultMap,"根据id查找品质管理数据成功！");
            }else{
                return R.error(500,"不存在文件！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取品质管理文件失败："+e.getMessage());
            return R.error(500,"获取品质管理文件，服务器异常，请联系系统管理员！");
        }
    }
}
