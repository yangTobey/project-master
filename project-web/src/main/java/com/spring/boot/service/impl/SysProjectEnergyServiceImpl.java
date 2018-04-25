package com.spring.boot.service.impl;

import com.spring.boot.bean.master.*;
import com.spring.boot.bean.master.entity.SysProjectEnergyEntity;
import com.spring.boot.service.SysDataAnalysisService;
import com.spring.boot.service.SysProjectEnergyService;
import com.spring.boot.service.web.SysEnergyBusinessService;
import com.spring.boot.service.web.SysProjectBusinessService;
import com.spring.boot.service.web.SysProjectEnergyBusinessService;
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
    private SysDataAnalysisService sysDataAnalysisService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate redisTemplate;

    Map<String, Object> map = null;
    Map<String, Object> resultMap = null;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addSysProjectEnergy(Long companyId, Integer year, Integer month, Integer projectUnfinishedTotal, Integer projectFinishedTotal, Double monthConsumptionElectricity, Double monthConsumptionWater, String fileInfo) {
        //先根据年份、月份、公司id查找系统是不是已经存在该月的记录
        SysProject sysProject = sysProjectBusinessService.findSysProjectRecord(companyId, year, month);
        if (null != sysProject) {
            return R.error(500, "新增失败，系统已存在" + year + "年" + month + "月的记录，不能重复添加");
        } else {
            Date date = Timestamp.valueOf(UtilHelper.getNowTimeStr());
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
                //存储统计信息到redis缓存
                setDataToRedis();
                return R.ok(200, "添加信息成功！！");
            }
        }
        return R.error(500, "新增工程能耗信息失败，请联系系统管理员！！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> updateSysProjectEnergy(Long projectId, Long companyId, Integer year, Integer month, Integer projectUnfinishedTotal, Integer projectFinishedTotal, Double monthConsumptionElectricity, Double monthConsumptionWater, String fileInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectId", projectId);
        map.put("companyId", companyId);
        map.put("year", year);
        map.put("month", month);
        map.put("projectUnfinishedTotal", projectUnfinishedTotal);
        map.put("projectFinishedTotal", projectFinishedTotal);
        map.put("monthConsumptionElectricity", monthConsumptionElectricity);
        map.put("monthConsumptionWater", monthConsumptionWater);

        //先根据年份、月份、公司id查找系统是不是已经存在该月的记录
        SysProject sysProject = sysProjectBusinessService.findSysProjectRecord(companyId, year, month);
        if (null != sysProject) {
            if (!projectId.equals(sysProject.getProjectId())) {
                return R.error(500, "更新失败，系统已存在" + year + "年" + month + "月的记录，不能重复添加");
            }
        }
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
        //存储统计信息到redis缓存
        setDataToRedis();
        return R.ok(200, "更新成功！！");

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> deleteSysProject(Long projectId) {
        int count = sysProjectBusinessService.deleteSysProject(projectId);
        if (count > 0) {
            //存储统计信息到redis缓存
            setDataToRedis();
            return R.ok(200, "删除成功！！");
        } else {
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
    public Map<String, Object> sysProjectEnergyList(Long companyId, Integer year, Integer limit, Integer offset) {
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
            int year = UtilHelper.getYear();
            int month = UtilHelper.getMonth();
            int lastYear = 0;
            int lastMonth = 0;
            if (month == 1) {
                lastMonth = 12;
                lastYear = year - 1;
            } else {
                lastMonth = month - 1;
                lastYear = year;
            }
            map.put("sysUserCompanyIds", sysUserCompanyIds);
            map.put("year", year);
            map.put("month", month);
            /*注：type为1时，为按区域查询（小区）查询数据，type为2时，不考虑登录用户权限内小区，查询全国数据，即是物业大屏数据展示分析接口使用*/
            map.put("type", 1);
            SysProject sysProjectForYear = sysProjectBusinessService.sysProjectEnergyAnalysisForYear(map);

            if (null != sysProjectForYear) {
                sysProjectForYear.setYearProjectUnfinishedScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysProjectForYear.getYearProjectUnfinishedTotal(), sysProjectForYear.getYearProjectFinishedTotal())));
                //当月数据
                SysProject sysProjectForMonth = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(year, month, sysUserCompanyIds);
                if (null != sysProjectForMonth) {
                    sysProjectForYear.setMonthConsumptionElectricity(sysProjectForMonth.getMonthConsumptionElectricity());
                    sysProjectForYear.setMonthConsumptionWater(sysProjectForMonth.getMonthConsumptionWater());
                    //上个月数据
                    SysProject sysProjectForLastMonth = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(lastYear, lastMonth, sysUserCompanyIds);
                    if (null != sysProjectForLastMonth) {
                        sysProjectForYear.setMtoMtConsumptionElectricityScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProjectForMonth.getMonthConsumptionElectricity() - sysProjectForLastMonth.getMonthConsumptionElectricity(), sysProjectForLastMonth.getMonthConsumptionElectricity())));
                        sysProjectForYear.setMtoMtConsumptionWaterScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProjectForMonth.getMonthConsumptionWater() - sysProjectForLastMonth.getMonthConsumptionWater(), sysProjectForLastMonth.getMonthConsumptionWater())));
                        //上一年同月数据
                        SysProject sysProjectForLastYear = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(year - 1, month, sysUserCompanyIds);
                        if (null != sysProjectForLastYear) {
                            sysProjectForYear.setYoYConsumptionElectricityScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProjectForMonth.getMonthConsumptionElectricity() - sysProjectForLastYear.getMonthConsumptionWater(), sysProjectForLastYear.getMonthConsumptionWater())));
                            sysProjectForYear.setYoYConsumptionWaterScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProjectForMonth.getMonthConsumptionWater() - sysProjectForLastYear.getMonthConsumptionWater(), sysProjectForLastYear.getMonthConsumptionWater())));
                        }
                    }
                }
                return R.ok().putData(200, sysProjectForYear, "获取统计数据成功！");
            }
            return R.error(500, "获取工程能耗信息报表信息失败，请联系管理员！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取工程能耗信息报表信息出错：" + e.getMessage());
            return R.error(500, "获取工程能耗信息报表信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> sysProjectEnergyAnalysisForMonth(Long companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Long> sysUserCompanyIds = null;
        try {
            if (companyId == 0) {
                //获取用户权限下可操作的小区信息
                sysUserCompanyIds = SysUtil.getSysUserCompany();
            } else {
                sysUserCompanyIds = new ArrayList<Long>();
                sysUserCompanyIds.add(companyId);
            }
            int year = UtilHelper.getYear();
            int month = UtilHelper.getMonth();
            map.put("sysUserCompanyIds", sysUserCompanyIds);
            map.put("year", year);
            /*注：type为1时，为按区域查询（小区）查询数据，type为2时，不考虑登录用户权限内小区，查询全国数据，即是物业大屏数据展示分析接口使用*/
            map.put("type", 1);
            //耗电量环比
            Map<Integer, Double> mtOMtCsElectricityScaleMap = null;
            //耗水量环比
            Map<Integer, Double> mtOMtCsWaterScaleMap = null;
            //耗电量(月)
            Map<Integer, Double> monthCsElectricityMap = null;
            //耗水量(月)
            Map<Integer, Double> monthCsWaterMap = null;
            String monthsInfo = "";
            for (int i = 1; i <= month; i++) {
                //月份组装
                if (i == month) {
                    monthsInfo += i;
                } else {
                    monthsInfo += i + ",";
                }
            }
            SysProjectEnergyEntity sysProjectEnergyEntity = new SysProjectEnergyEntity();
            sysProjectEnergyEntity.setMonthInfo(monthsInfo);
            //查询得到的信息列表里面的月份值
            Integer infoMonth = 0;
            List<SysProject> sysProjectList = sysProjectBusinessService.sysProjectEnergyAnalysisForMonth(map);
            if (null != sysProjectList) {
                mtOMtCsElectricityScaleMap = new HashMap<Integer, Double>();
                mtOMtCsWaterScaleMap = new HashMap<Integer, Double>();
                monthCsElectricityMap = new HashMap<Integer, Double>();
                monthCsWaterMap = new HashMap<Integer, Double>();
                SysProject sysProjectForMonth = null;
                for (SysProject sysProject : sysProjectList) {
                    infoMonth = sysProject.getMonth();
                    if (null != infoMonth) {
                        monthCsElectricityMap.put(infoMonth, sysProject.getMonthConsumptionElectricity());
                        monthCsWaterMap.put(infoMonth, sysProject.getMonthConsumptionWater());
                        if (infoMonth == 1) {
                            sysProjectForMonth = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(year - 1, 12, sysUserCompanyIds);
                        } else {
                            sysProjectForMonth = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(year, infoMonth - 1, sysUserCompanyIds);
                        }
                        if (null != sysProjectForMonth) {
                            mtOMtCsElectricityScaleMap.put(infoMonth, UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProject.getMonthConsumptionElectricity() - sysProjectForMonth.getMonthConsumptionElectricity(), sysProjectForMonth.getMonthConsumptionElectricity())));
                            mtOMtCsWaterScaleMap.put(infoMonth, UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProject.getMonthConsumptionWater() - sysProjectForMonth.getMonthConsumptionWater(), sysProjectForMonth.getMonthConsumptionWater())));
                        }
                    }
                }
            }
            sysProjectEnergyEntity.setMonthCsElectricityMap(monthCsElectricityMap);
            sysProjectEnergyEntity.setMonthCsWaterMap(monthCsWaterMap);
            sysProjectEnergyEntity.setMtoMtCsElectricityScaleMap(mtOMtCsElectricityScaleMap);
            sysProjectEnergyEntity.setMtoMtCsWaterScaleMap(mtOMtCsWaterScaleMap);
            return R.ok().putData(200, sysProjectEnergyEntity, "获取信息成功！！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取工程能耗信息报表信息出错：" + e.getMessage());
            return R.error(500, "获取工程能耗信息报表信息失败，服务器异常，请联系管理员！");
        }
    }

    /**
     * 存储统计报表信息到redis缓存
     */
    public void setDataToRedis() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Long> sysUserCompanyIds = null;
        int year = UtilHelper.getYear();
        int month = UtilHelper.getMonth();
        int lastYear = 0;
        int lastMonth = 0;
        if (month == 1) {
            lastMonth = 12;
            lastYear = year - 1;
        } else {
            lastMonth = month - 1;
            lastYear = year;
        }
        map.put("sysUserCompanyIds", null);
        map.put("year", year);
        map.put("month", month);
        /*注：type为1时，为按区域查询（小区）查询数据，type为2时，不考虑登录用户权限内小区，查询全国数据，即是物业大屏数据展示分析接口使用*/
        map.put("type", 2);
        SysProject sysProjectForYear = sysProjectBusinessService.sysProjectEnergyAnalysisForYear(map);

        if (null != sysProjectForYear) {
            sysProjectForYear.setYearProjectUnfinishedScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysProjectForYear.getYearProjectUnfinishedTotal(), sysProjectForYear.getYearProjectFinishedTotal())));
            //当月数据
            SysProject sysProjectForMonth = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(year, month, sysUserCompanyIds);
            if (null != sysProjectForMonth) {
                sysProjectForYear.setMonthConsumptionElectricity(sysProjectForMonth.getMonthConsumptionElectricity());
                sysProjectForYear.setMonthConsumptionWater(sysProjectForMonth.getMonthConsumptionWater());
                //月度遗留问题
                sysProjectForYear.setProjectUnfinishedTotal(sysProjectForMonth.getProjectUnfinishedTotal());
                //月度已处理遗留问题
                sysProjectForYear.setProjectFinishedTotal(sysProjectForMonth.getYearProjectFinishedTotal());
                //上个月数据
                SysProject sysProjectForLastMonth = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(lastYear, lastMonth, sysUserCompanyIds);
                if (null != sysProjectForLastMonth) {
                    sysProjectForYear.setMtoMtConsumptionElectricityScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProjectForMonth.getMonthConsumptionElectricity() - sysProjectForLastMonth.getMonthConsumptionElectricity(), sysProjectForLastMonth.getMonthConsumptionElectricity())));
                    sysProjectForYear.setMtoMtConsumptionWaterScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProjectForMonth.getMonthConsumptionWater() - sysProjectForLastMonth.getMonthConsumptionWater(), sysProjectForLastMonth.getMonthConsumptionWater())));
                    //上一年同月数据
                    SysProject sysProjectForLastYear = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(year - 1, month, sysUserCompanyIds);
                    if (null != sysProjectForLastYear) {
                        sysProjectForYear.setYoYConsumptionElectricityScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProjectForMonth.getMonthConsumptionElectricity() - sysProjectForLastYear.getMonthConsumptionWater(), sysProjectForLastYear.getMonthConsumptionWater())));
                        sysProjectForYear.setYoYConsumptionWaterScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProjectForMonth.getMonthConsumptionWater() - sysProjectForLastYear.getMonthConsumptionWater(), sysProjectForLastYear.getMonthConsumptionWater())));
                    }
                }
            }
        }
        //将组装的数据存储到redis缓存
        //redisTemplate.opsForValue().set("sysProjectForYear", sysProjectForYear);
        resultMap.put("sysProjectForYear", sysProjectForYear);
        /********************************获取年度耗水量、耗电量统计报表信息*******************************/
        //耗电量环比
        Map<Integer, Double> mtOMtCsElectricityScaleMap = null;
        //耗水量环比
        Map<Integer, Double> mtOMtCsWaterScaleMap = null;
        //耗电量(月)
        Map<Integer, Double> monthCsElectricityMap = null;
        //耗水量(月)
        Map<Integer, Double> monthCsWaterMap = null;
        String monthsInfo = "";
        for (int i = 1; i <= month; i++) {
            //月份组装
            if (i == month) {
                monthsInfo += i;
            } else {
                monthsInfo += i + ",";
            }
        }
        SysProjectEnergyEntity sysProjectEnergyEntity = new SysProjectEnergyEntity();
        sysProjectEnergyEntity.setMonthInfo(monthsInfo);
        //查询得到的信息列表里面的月份值
        Integer infoMonth = 0;
        List<SysProject> sysProjectList = sysProjectBusinessService.sysProjectEnergyAnalysisForMonth(map);
        if (null != sysProjectList) {
            mtOMtCsElectricityScaleMap = new HashMap<Integer, Double>();
            mtOMtCsWaterScaleMap = new HashMap<Integer, Double>();
            monthCsElectricityMap = new HashMap<Integer, Double>();
            monthCsWaterMap = new HashMap<Integer, Double>();
            SysProject sysProjectForMonth = null;
            for (SysProject sysProject : sysProjectList) {
                infoMonth = sysProject.getMonth();
                if (null != infoMonth) {
                    monthCsElectricityMap.put(infoMonth, sysProject.getMonthConsumptionElectricity());
                    monthCsWaterMap.put(infoMonth, sysProject.getMonthConsumptionWater());
                    if (infoMonth == 1) {
                        sysProjectForMonth = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(year - 1, 12, sysUserCompanyIds);
                    } else {
                        sysProjectForMonth = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(year, infoMonth - 1, sysUserCompanyIds);
                    }
                    if (null != sysProjectForMonth) {
                        mtOMtCsElectricityScaleMap.put(infoMonth, UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProject.getMonthConsumptionElectricity() - sysProjectForMonth.getMonthConsumptionElectricity(), sysProjectForMonth.getMonthConsumptionElectricity())));
                        mtOMtCsWaterScaleMap.put(infoMonth, UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProject.getMonthConsumptionWater() - sysProjectForMonth.getMonthConsumptionWater(), sysProjectForMonth.getMonthConsumptionWater())));
                    }
                }
            }
        }
        sysProjectEnergyEntity.setMonthCsElectricityMap(monthCsElectricityMap);
        sysProjectEnergyEntity.setMonthCsWaterMap(monthCsWaterMap);
        sysProjectEnergyEntity.setMtoMtCsElectricityScaleMap(mtOMtCsElectricityScaleMap);
        sysProjectEnergyEntity.setMtoMtCsWaterScaleMap(mtOMtCsWaterScaleMap);
        resultMap.put("sysProjectForMonth", sysProjectEnergyEntity);
        //将组装的数据存储到redis缓存
        redisTemplate.opsForValue().set("sysProjectEnergy", resultMap);
        //调取物业大屏数据接口
        sysDataAnalysisService.sysPropertyDataAnalysis();
    }
}
