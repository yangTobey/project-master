package com.spring.boot.service.impl;

import com.spring.boot.bean.master.*;
import com.spring.boot.service.SysProjectEnergyService;
import com.spring.boot.service.web.SysEnergyBusinessService;
import com.spring.boot.service.web.SysProjectBusinessService;
import com.spring.boot.service.web.SysProjectEnergyBusinessService;
import com.spring.boot.util.R;
import com.spring.boot.util.SysUtil;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

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
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addSysProjectEnergy(Long companyId, Integer year, Integer month, Integer projectUnfinishedTotal, Integer projectFinishedTotal, Integer monthConsumptionElectricity, Integer monthConsumptionWater, String fileInfo) {
        try {
            //先根据年份、月份、公司id查找系统是不是已经存在该月的记录
            SysProject sysProject = sysProjectBusinessService.findSysProjectRecord(companyId, year, month);
            if (null != sysProject) {
                return R.error(500, "新增失败，系统已存在" + year + "年" + month + "月的记录，不能重复添加");
            } else {
                Date date = Timestamp.valueOf(UtilHelper.getNowTimeStr());
                /*sysProjectEnergy=new SysProjectEnergy();
                sysProjectEnergy.setCompanyId(companyId);
                sysProjectEnergy.setMonth(month);
                sysProjectEnergy.setYear(year);
                sysProjectEnergy.setCreateTime(date);
                sysProjectEnergyBusinessService.addSysProjectEnergy(sysProjectEnergy);*/
                sysProject = new SysProject();
                sysProject.setCompanyId(companyId);
                sysProject.setYear(year);
                sysProject.setMonth(month);
                sysProject.setCreateTime(date);
                sysProject.setProjectFinishedTotal(projectFinishedTotal);
                sysProject.setProjectUnfinishedTotal(projectUnfinishedTotal);
                int count = sysProjectBusinessService.addSysProject(sysProject);
                if (count > 0) {
                    SysEnergy sysEnergy = new SysEnergy();
                    sysEnergy.setCompanyId(companyId);
                    sysEnergy.setYear(year);
                    sysEnergy.setMonth(month);
                    sysEnergy.setProjectId(sysProject.getProjectId());
                    sysEnergy.setMonthConsumptionElectricity(monthConsumptionElectricity);
                    sysEnergy.setMonthConsumptionWater(monthConsumptionWater);
                    sysEnergy.setCreateTime(date);
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
                            sysProjectEnergyFile.setProjectId(sysProject.getProjectId());
                            sysProjectEnergyFile.setFileName(fileData[0].substring(fileData[0].lastIndexOf("/") + 1, fileData[0].lastIndexOf(".")));
                            sysProjectEnergyFile.setFileSize(Double.valueOf(fileData[1]));
                            sysProjectEnergyFile.setFileUrl(fileData[0]);
                            sysProjectEnergyFile.setUploadTime(date);
                            sysProjectBusinessService.addSysProjectEnergyFile(sysProjectEnergyFile);
                        }
                    }
                    return R.ok(200, "添加信息成功！！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("新增工程能耗出错：" + e.getMessage());
            throw new RuntimeException();
           // return R.error(500, "添加失败，服务器异常，请联系管理员！");
        }
        return R.error(500, "新增工程能耗信息失败，请联系系统管理员！！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> updateSysProjectEnergy(Long projectId, Long companyId, Integer year, Integer month, Integer projectUnfinishedTotal, Integer projectFinishedTotal, Integer monthConsumptionElectricity, Integer monthConsumptionWater, String fileInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectId", projectId);
        map.put("companyId", companyId);
        map.put("year", year);
        map.put("month", month);
        map.put("projectUnfinishedTotal", projectUnfinishedTotal);
        map.put("projectFinishedTotal", projectFinishedTotal);
        map.put("monthConsumptionElectricity", monthConsumptionElectricity);
        map.put("monthConsumptionWater", monthConsumptionWater);
        try {
            //先根据年份、月份、公司id查找系统是不是已经存在该月的记录
            SysProject sysProject = sysProjectBusinessService.findSysProjectRecord(companyId, year, month);
            if (null != sysProject) {
                if (!projectId.equals(sysProject.getProjectId())) {
                    return R.error(500, "新增失败，系统已存在" + year + "年" + month + "月的记录，不能重复添加");
                }
            }
            //sysProjectEnergyBusinessService.updateSysProjectEnergy(map);
            sysProjectBusinessService.updateSysProject(map);
            sysEnergyBusinessService.updateSysEnergy(map);
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
                    sysProjectEnergyFile.setProjectId(projectId);
                    sysProjectEnergyFile.setFileName(fileData[0].substring(fileData[0].lastIndexOf("/") + 1, fileData[0].lastIndexOf(".")));
                    sysProjectEnergyFile.setFileSize(Double.valueOf(fileData[1]));
                    sysProjectEnergyFile.setFileUrl(fileData[0]);
                    sysProjectEnergyFile.setUploadTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
                    sysProjectBusinessService.addSysProjectEnergyFile(sysProjectEnergyFile);
                }
            }
            //sysProjectBusinessService.updateSysProjectEnergyFile(map);
            return R.ok(200, "更新成功！！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("更新工程能耗出错：" + e.getMessage());
            throw new RuntimeException();
            //return R.error(500, "更新失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> deleteSysProject(Long projectId) {
        try {
            int count = sysProjectBusinessService.deleteSysProject(projectId);
            if (count > 0) {
                return R.ok(200, "删除成功！！");
            } else {
                return R.error(500, "删除失败，服务器异常，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除工程能耗出错：" + e.getMessage());
            return R.error(500, "删除失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> findSysProjectEnergyById(Long projectId) {
        try {
            SysProject sysProject = sysProjectBusinessService.findSysProjectEnergyById(projectId);
            if (null != sysProject) {
                List<SysProjectEnergyFile> sysProjectEnergyFile = sysProjectBusinessService.findSysProjectEnergyFileById(projectId);
                sysProject.setFileList(sysProjectEnergyFile);
                return R.ok().putData(200, sysProject, "删除成功！！");
            } else {
                return R.error(500, "删除失败，服务器异常，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除工程能耗出错：" + e.getMessage());
            return R.error(500, "删除失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> sysProjectEnergyList(Long companyId, Integer year,Integer limit, Integer offset) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Long> sysUserCompanyIds = null;
        if (companyId == 0) {
            //获取用户权限下可操作的小区信息
            sysUserCompanyIds = SysUtil.getSysUserCompany();
        } else {
            sysUserCompanyIds = new ArrayList<Long>();
            sysUserCompanyIds.add(companyId);
        }
        map.put("limit", limit);
        map.put("offset", offset);
        map.put("sysUserCompanyIds", sysUserCompanyIds);
        map.put("year", year);
        try {
            resultMap.put("total", sysProjectBusinessService.sysProjectEnergyListTotal(map));
            resultMap.put("list", sysProjectBusinessService.sysProjectEnergyList(map));
            return R.ok().putData(200, resultMap, "获取数据成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取工程能耗信息列表出错：" + e.getMessage());
            return R.error(500, "获取工程能耗信息列表失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> sysProjectEnergyAnalysis(Long companyId) {
        return null;
    }
}
