package com.spring.boot.service.impl;

import com.spring.boot.bean.master.*;
import com.spring.boot.service.SysProjectEnergyService;
import com.spring.boot.service.web.SysEnergyBusinessService;
import com.spring.boot.service.web.SysProjectBusinessService;
import com.spring.boot.service.web.SysProjectEnergyBusinessService;
import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysProjectEnergyServiceImpl implements SysProjectEnergyService {
    private static final Logger logger = Logger.getLogger(SysProjectEnergyServiceImpl.class);
    @Autowired
    private SysProjectEnergyBusinessService sysProjectEnergyBusinessService;
    @Autowired
    private SysProjectBusinessService sysProjectBusinessService;
    @Autowired
    private SysEnergyBusinessService sysEnergyBusinessService;


    @Autowired
    private StringRedisTemplate redisTemplate;

    Map<String, Object> map = null;
    Map<String, Object> resultMap = null;

    @Override
    public Map<String, Object> addSysProjectEnergy(Long companyId, Integer year, Integer month, Integer projectUnfinishedTotal, Integer projectFinishedTotal, Integer monthConsumptionElectricity, Integer monthConsumptionWater, String fileInfo) {
        try {
            //先根据年份、月份、公司id查找系统是不是已经存在该月的记录
            SysProjectEnergy sysProjectEnergy=sysProjectEnergyBusinessService.findSysProjectEnergyRecord(companyId,year,month);
            if(null!=sysProjectEnergy){
                return R.error(500, "新增失败，系统已存在" + year + "年" + month + "月的记录，不能重复添加");
            }else{
                Date date=Timestamp.valueOf(UtilHelper.getNowTimeStr());
                sysProjectEnergy=new SysProjectEnergy();
                sysProjectEnergy.setCompanyId(companyId);
                sysProjectEnergy.setMonth(month);
                sysProjectEnergy.setYear(year);
                sysProjectEnergy.setCreateTime(date);
                int count=sysProjectEnergyBusinessService.addSysProjectEnergy(sysProjectEnergy);
                if(count>0){
                    SysProject sysProject=new SysProject();
                    sysProject.setProjectEnergyId(sysProjectEnergy.getDetailsId());
                    sysProject.setCompanyId(companyId);
                    sysProject.setYear(year);
                    sysProject.setMonth(month);
                    sysProject.setProjectFinishedTotal(projectFinishedTotal);
                    sysProject.setProjectUnfinishedTotal(projectUnfinishedTotal);
                    sysProjectBusinessService.addSysProject(sysProject);

                    SysEnergy sysEnergy=new SysEnergy();
                    sysEnergy.setCompanyId(companyId);
                    sysEnergy.setYear(year);
                    sysEnergy.setMonth(month);
                    sysEnergy.setMonthConsumptionElectricity(monthConsumptionElectricity);
                    sysEnergy.setMonthConsumptionWater(monthConsumptionWater);
                    sysEnergyBusinessService.addSysEergy(sysEnergy);
                    //添加附件信息
                    if (!UtilHelper.isEmpty(fileInfo)) {
                        String[] fileInfoArray;
                        //去掉最后那个逗号，在进行获取数据
                        fileInfoArray = fileInfo.substring(0, fileInfo.length() - 1).split(";");
                        SysProjectEnergyFile sysProjectEnergyFile = null;
                        String[] fileData;
                        for (String fileUrl : fileInfoArray) {
                            sysProjectEnergyFile = new SysProjectEnergyFile();
                            //根据，逗号分隔，获取文件的地址和文件大小（文件数据格式：文件地址，文件大小）
                            fileData = fileUrl.substring(0, fileUrl.length()).split(",");
                            sysProjectEnergyFile.setDetailsId(sysProjectEnergy.getDetailsId());
                            sysProjectEnergyFile.setFileName(fileData[0].substring(fileData[0].lastIndexOf("/") + 1, fileData[0].lastIndexOf(".")));
                            sysProjectEnergyFile.setFileSize(Double.valueOf(fileData[1]));
                            sysProjectEnergyFile.setFileUrl(fileData[0]);
                            sysProjectEnergyFile.setUploadTime(date);
                            sysProjectEnergyBusinessService.addSysProjectEnergyFile(sysProjectEnergyFile);
                        }
                    }
                    return R.ok(200, "添加信息成功！！");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("新增工程能耗出错："+e.getMessage());
            return R.error(500,"添加失败，服务器异常，请联系管理员！");
        }
        return R.error(500, "新增工程能耗信息失败，请联系系统管理员！！");
    }

    @Override
    public Map<String, Object> updateSysProjectEnergy(Long detailsId, Long companyId, Integer year, Integer month, Integer projectUnfinishedTotal, Integer projectFinishedTotal, Integer monthConsumptionElectricity, Integer monthConsumptionWater, String fileInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("detailsId", detailsId);
        map.put("companyId", companyId);
        map.put("year", year);
        map.put("month", month);
        map.put("projectUnfinishedTotal", projectUnfinishedTotal);
        map.put("projectFinishedTotal", projectFinishedTotal);
        map.put("monthConsumptionElectricity", monthConsumptionElectricity);
        map.put("monthConsumptionWater", monthConsumptionWater);
        map.put("detailsId", detailsId);
        try {
            //先根据年份、月份、公司id查找系统是不是已经存在该月的记录
            SysProjectEnergy sysProjectEnergy=sysProjectEnergyBusinessService.findSysProjectEnergyRecord(companyId,year,month);
            if(null!=sysProjectEnergy){
                if(detailsId!=sysProjectEnergy.getDetailsId()){
                    return R.error(500, "新增失败，系统已存在" + year + "年" + month + "月的记录，不能重复添加");
                }
            }
            sysProjectEnergyBusinessService.updateSysProjectEnergy(map);
            sysProjectBusinessService.updateSysProject(map);
            sysEnergyBusinessService.updateSysEnergy(map);
            sysProjectEnergyBusinessService.updateSysProjectEnergyFile(map);
            return R.ok(200, "更新成功！！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("更新工程能耗出错："+e.getMessage());
            return R.error(500,"更新失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> deleteSysProjectEnergy(Long detailsId) {
        try {
            int count=sysProjectEnergyBusinessService.deleteSysProjectEnergy(detailsId);
            if(count>0){
                return R.ok(200, "删除成功！！");
            }else {
                return R.error(500,"删除失败，服务器异常，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除工程能耗出错："+e.getMessage());
            return R.error(500,"删除失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> findSysProjectEnergyById(Long detailsId) {
        try {
            SysProjectEnergy sysProjectEnergy=sysProjectEnergyBusinessService.findSysProjectEnergyById(detailsId);
            if(null!=sysProjectEnergy){
                return R.ok().putData(200, sysProjectEnergy,"删除成功！！");
            }else {
                return R.error(500,"删除失败，服务器异常，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除工程能耗出错："+e.getMessage());
            return R.error(500,"删除失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> sysProjectEnergyList(Long companyId, Integer year) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        map.put("year", year);
        try {
            return R.ok(200, "更新成功！！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("更新工程能耗出错："+e.getMessage());
            return R.error(500,"更新失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> sysProjectEnergyAnalysis(Long companyId) {
        return null;
    }
}
