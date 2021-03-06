package com.spring.boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.spring.boot.bean.PageInfoBean;
import com.spring.boot.bean.master.*;
import com.spring.boot.bean.master.entity.SysProjectEnergyEntity;
import com.spring.boot.entity.SysProjectEnergyInfoEntity;
import com.spring.boot.service.SysDataAnalysisService;
import com.spring.boot.service.SysProjectEnergyService;
import com.spring.boot.service.web.SysEnergyBusinessService;
import com.spring.boot.service.web.SysProjectBusinessService;
import com.spring.boot.service.web.SysProjectEnergyBusinessService;
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
    private SysUpdateDataRulesBusinessService sysUpdateDataRulesBusinessService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate redisTemplate;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addSysProjectEnergy(SysProjectEnergyInfoEntity sysProjectEnergyEntity) {
        //先根据年份、月份、公司id查找系统是不是已经存在该月的记录
        SysProject sysProject = sysProjectBusinessService.findSysProjectRecord(sysProjectEnergyEntity.getCompanyId(), sysProjectEnergyEntity.getYear(), sysProjectEnergyEntity.getMonth());
        if (null != sysProject) {
            return R.error(500, "新增失败，系统已存在" + sysProjectEnergyEntity.getYear() + "年" + sysProjectEnergyEntity.getMonth() + "月的记录，不能重复添加");
        } else {
            Date date = Timestamp.valueOf(UtilHelper.getNowTimeStr());
            sysProject = new SysProject();
            sysProject.setCompanyId(sysProjectEnergyEntity.getCompanyId());
            sysProject.setYear(sysProjectEnergyEntity.getYear());
            sysProject.setMonth(sysProjectEnergyEntity.getMonth());
            sysProject.setCreateTime(date);
            sysProject.setProjectFinishedTotal(sysProjectEnergyEntity.getProjectFinishedTotal());
            sysProject.setProjectUnfinishedTotal(sysProjectEnergyEntity.getProjectUnfinishedTotal());
            sysProject.setPreviousFileMes(sysProjectEnergyEntity.getPreviousFileMes());
            sysProject.setAcceptFileMes(sysProjectEnergyEntity.getAcceptFileMes());
            int count = sysProjectBusinessService.addSysProject(sysProject);
            if (count > 0) {
                SysEnergy sysEnergy = new SysEnergy();
                sysEnergy.setCompanyId(sysProjectEnergyEntity.getCompanyId());
                sysEnergy.setYear(sysProjectEnergyEntity.getYear());
                sysEnergy.setMonth(sysProjectEnergyEntity.getMonth());
                sysEnergy.setProjectId(sysProject.getProjectId());
                sysEnergy.setMonthConsumptionElectricity(sysProjectEnergyEntity.getMonthConsumptionElectricity());
                sysEnergy.setMonthConsumptionWater(sysProjectEnergyEntity.getMonthConsumptionWater());
                sysEnergy.setCreateTime(date);
                sysEnergyBusinessService.addSysEergy(sysEnergy);
                //添加附件信息
                if (!UtilHelper.isEmpty(sysProjectEnergyEntity.getPreviousFileInfo())) {
                    saveFile(sysProjectEnergyEntity.getPreviousFileInfo(), sysProject.getProjectId(), 3);
                    /*String[] fileInfoArray;
                    //去掉最后那个逗号，在进行获取数据
                    fileInfoArray = sysProjectEnergyEntity.getPreviousFileInfo().substring(0, sysProjectEnergyEntity.getPreviousFileInfo().length()).split(";");
                    SysProjectEnergyFile sysProjectEnergyFile = null;
                    String[] fileData;
                    for (String fileUrl : fileInfoArray) {
                        sysProjectEnergyFile = new SysProjectEnergyFile();
                        //根据，逗号分隔，获取文件的地址和文件大小（文件数据格式：文件名称，文件地址，文件大小）
                        fileData = fileUrl.substring(0, fileUrl.length()).split(",");
                        sysProjectEnergyFile.setProjectId(sysProject.getProjectId());
                        sysProjectEnergyFile.setFileName(fileData[0]);
                        sysProjectEnergyFile.setFileSize(Double.valueOf(fileData[2]));
                        sysProjectEnergyFile.setFileUrl(fileData[1]);
                        sysProjectEnergyFile.setUploadTime(date);
                        sysProjectBusinessService.addSysProjectEnergyFile(sysProjectEnergyFile);
                    }*/
                }
                if (!UtilHelper.isEmpty(sysProjectEnergyEntity.getAcceptFileInfo())) {
                    saveFile(sysProjectEnergyEntity.getAcceptFileInfo(), sysProject.getProjectId(), 4);
                }
                if (!UtilHelper.isEmpty(sysProjectEnergyEntity.getUnfinishedFileInfo())) {
                    saveFile(sysProjectEnergyEntity.getUnfinishedFileInfo(), sysProject.getProjectId(), 1);
                }
                if (!UtilHelper.isEmpty(sysProjectEnergyEntity.getFinishedFileInfo())) {
                    saveFile(sysProjectEnergyEntity.getFinishedFileInfo(), sysProject.getProjectId(), 2);
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
    public Map<String, Object> updateSysProjectEnergy(SysProjectEnergyInfoEntity sysProjectEnergyEntity) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectId", sysProjectEnergyEntity.getProjectId());
        map.put("companyId", sysProjectEnergyEntity.getCompanyId());
        map.put("year", sysProjectEnergyEntity.getYear());
        map.put("month", sysProjectEnergyEntity.getMonth());
        map.put("projectUnfinishedTotal", sysProjectEnergyEntity.getProjectUnfinishedTotal());
        map.put("projectFinishedTotal", sysProjectEnergyEntity.getProjectFinishedTotal());
        map.put("monthConsumptionElectricity", sysProjectEnergyEntity.getMonthConsumptionElectricity());
        map.put("monthConsumptionWater", sysProjectEnergyEntity.getMonthConsumptionWater());

        map.put("previousFileMes", sysProjectEnergyEntity.getPreviousFileMes());
        map.put("acceptFileMes", sysProjectEnergyEntity.getAcceptFileMes());

        //先根据年份、月份、公司id查找系统是不是已经存在该月的记录
        SysProject sysProject = sysProjectBusinessService.findSysProjectRecord(sysProjectEnergyEntity.getCompanyId(), sysProjectEnergyEntity.getYear(), sysProjectEnergyEntity.getMonth());
        if (null != sysProject) {
            if (!sysProjectEnergyEntity.getProjectId().equals(sysProject.getProjectId())) {
                return R.error(500, "更新失败，系统已存在" + sysProjectEnergyEntity.getYear() + "年" + sysProjectEnergyEntity.getMonth() + "月的记录，不能重复添加");
            }
        }
        sysProjectBusinessService.updateSysProject(map);
        sysEnergyBusinessService.updateSysEnergy(map);
        //添加附件信息
        if (!UtilHelper.isEmpty(sysProjectEnergyEntity.getPreviousFileInfo())) {
            saveFile(sysProjectEnergyEntity.getPreviousFileInfo(),sysProjectEnergyEntity.getProjectId(),3);
           /* String[] fileInfoArray;
            //去掉最后那个逗号，在进行获取数据
            fileInfoArray = sysProjectEnergyEntity.getPreviousFileInfo().substring(0, sysProjectEnergyEntity.getPreviousFileInfo().length()).split(";");
            SysProjectEnergyFile sysProjectEnergyFile = null;
            String[] fileData;
            for (String fileUrl : fileInfoArray) {
                sysProjectEnergyFile = new SysProjectEnergyFile();
                //根据，逗号分隔，获取文件的地址和文件大小（文件数据格式：文件名称，文件地址，文件大小）
                fileData = fileUrl.substring(0, fileUrl.length()).split(",");
                sysProjectEnergyFile.setProjectId(sysProjectEnergyEntity.getProjectId());
                sysProjectEnergyFile.setFileName(fileData[0]);
                sysProjectEnergyFile.setFileSize(Double.valueOf(fileData[2]));
                sysProjectEnergyFile.setFileUrl(fileData[1]);
                sysProjectEnergyFile.setUploadTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
                sysProjectBusinessService.addSysProjectEnergyFile(sysProjectEnergyFile);
            }*/
        }
        if (!UtilHelper.isEmpty(sysProjectEnergyEntity.getAcceptFileInfo())) {
            saveFile(sysProjectEnergyEntity.getAcceptFileInfo(), sysProjectEnergyEntity.getProjectId(), 4);
        }
        if (!UtilHelper.isEmpty(sysProjectEnergyEntity.getUnfinishedFileInfo())) {
            saveFile(sysProjectEnergyEntity.getUnfinishedFileInfo(), sysProjectEnergyEntity.getProjectId(), 1);
        }
        if (!UtilHelper.isEmpty(sysProjectEnergyEntity.getFinishedFileInfo())) {
            saveFile(sysProjectEnergyEntity.getFinishedFileInfo(), sysProjectEnergyEntity.getProjectId(), 2);
        }

        //存储统计信息到redis缓存
        setDataToRedis();

        return R.ok(200, "更新成功！！");

    }
    /**
     * 保存文件信息
     * @param fileInfo 文件详细信息
     * @param projectId 基础数据主键id
     * @param type 类型 文件类型（1：车位附件，2：销配附件,3：管理面积，4：前期文件）
     */
    public void saveFile(String fileInfo,Long projectId,Integer type){
        String[] fileInfoArray;
        //去掉最后那个逗号，在进行获取数据
        fileInfoArray = fileInfo.substring(0, fileInfo.length()).split(";");
        SysProjectEnergyFile sysProjectEnergyFile = null;
        String[] fileData;
        for (String fileUrl : fileInfoArray) {
            sysProjectEnergyFile = new SysProjectEnergyFile();
            //根据，逗号分隔，获取文件的地址和文件大小（文件数据格式：文件名称，文件地址，文件大小）
            fileData = fileUrl.substring(0, fileUrl.length()).split(",");
            sysProjectEnergyFile.setProjectId(projectId);
            sysProjectEnergyFile.setFileName(fileData[0]);
            sysProjectEnergyFile.setFileSize(Double.valueOf(fileData[2]));
            sysProjectEnergyFile.setFileUrl(fileData[1]);
            sysProjectEnergyFile.setUploadTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
            if(type==1){
                sysProjectEnergyFile.setFileType(1);
            }else if(type==2){
                sysProjectEnergyFile.setFileType(2);
            }else if(type==3){
                sysProjectEnergyFile.setFileType(3);
            }else if(type==4){
                sysProjectEnergyFile.setFileType(4);
            }
            sysProjectBusinessService.addSysProjectEnergyFile(sysProjectEnergyFile);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> deleteSysProject(Long projectId) {
        int count = sysProjectBusinessService.deleteSysProject(projectId);
        if (count > 0) {
            //SysProject sysProject = sysProjectBusinessService.findSysProjectEnergyById(projectId);
            //SysUpdateDataRules sysUpdateDataRules=sysUpdateDataRulesBusinessService.findSysUpdateDataRules();
            //boolean updateToRedis=SysUtil.updateToRedis(sysUpdateDataRules.getDay(),sysProject.getYear(),sysProject.getMonth());

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
                return R.ok().putData(200, sysProject, "获取成功！！");
            } else {
                return R.error(500, "获取失败，服务器异常，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取信息工程能耗出错：" + e.getMessage());
            return R.error(500, "获取工程能耗信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> sysProjectEnergyList(Long companyId, Integer year, Integer limit, Integer offset,Integer month) {
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
        map.put("month", month);
        try {
            //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
            PageHelper.startPage((offset/limit)+1,limit);
            List<SysProject> list=sysProjectBusinessService.sysProjectEnergyList(map);
            PageInfoBean result = new PageInfoBean(list);
            resultMap.put("total", result.getTotalOfData());
            resultMap.put("list", result.getList());

            //resultMap.put("total", sysProjectBusinessService.sysProjectEnergyListTotal(map));
            //resultMap.put("list", sysProjectBusinessService.sysProjectEnergyList(map));
            return R.ok().putData(200, resultMap, "获取数据成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取工程能耗信息列表出错：" + e.getMessage());
            return R.error(500, "获取工程能耗信息列表失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> findSysProjectFileById(long projectId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            List<SysProjectEnergyFile> fileList = sysProjectBusinessService.findSysProjectEnergyFileById(projectId);
            if (fileList != null) {
                resultMap.put("list", fileList);
                return R.ok().putData(200, resultMap, "根据id查找品质管理数据成功！");
            } else {
                return R.error(500, "不存在文件！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取品质管理文件失败：" + e.getMessage());
            return R.error(500, "获取品质管理文件失败，服务器异常，请联系系统管理员！");
        }
    }

    @Override
    public Map<String, Object> sysProjectEnergyAnalysis(Long companyId, Integer selectYear, Integer selectMonth) {
        Map<String, Object> map = new HashMap<String, Object>();

        Map<String, Object> unfinishedMap = new HashMap<String, Object>();
        List<Long> sysUserCompanyIds = null;
        try {
            if (companyId == 0) {
                //获取用户权限下可操作的小区信息
                sysUserCompanyIds = SysUtil.getSysUserCompany();
            } else {
                sysUserCompanyIds = new ArrayList<Long>();
                sysUserCompanyIds.add(companyId);
            }


            SysUpdateDataRules sysUpdateDataRules=sysUpdateDataRulesBusinessService.findSysUpdateDataRules();
            //获取需要查询的年份和月份
            Map<String,Integer> yearAndMonthMap=SysUtil.getYearAndMonth(sysUpdateDataRules.getDay());
            unfinishedMap.put("year",yearAndMonthMap.get("year"));
            unfinishedMap.put("month",yearAndMonthMap.get("month"));
            if(selectYear==null||selectMonth==null){
                selectYear = yearAndMonthMap.get("year");
                selectMonth = yearAndMonthMap.get("month");
            }

            //某一个月的上一年数据（每月10号到下个月10号显示上一个月的数据，即5月10号到6月10号显示4月份数据。）
            int lastYear = 0;
            //某一个月的上个月数据（每月10号到下个月10号显示上一个月的数据，即5月10号到6月10号显示4月份数据。）
            int lastMonth = 0;
            if (selectMonth == 1) {
                lastMonth = 12;
                lastYear = selectYear - 1;
            } else {
                lastMonth = selectMonth - 1;
                lastYear = selectYear;
            }
            //选择的年份与当前年一致
            if(selectYear.equals(yearAndMonthMap.get("year"))){
                map.put("sysUserCompanyIds", sysUserCompanyIds);
                map.put("year", yearAndMonthMap.get("year"));
                map.put("month", yearAndMonthMap.get("month"));
            }else{//选择的年份往年
                map.put("sysUserCompanyIds", sysUserCompanyIds);
                map.put("year", selectYear);
                //注：假如选择的是往年数据，直接获取12月的数据作为整年的数据总和（假如没有数据，请规范数据填写者进行数据输入，因查询和效率问题，这里不做没数据月份然后对月份递减查询排查问题）2019-1-10修改
                map.put("month", 12);
            }

            /*注：type为1时，为按区域查询（小区）查询数据，type为2时，不考虑登录用户权限内小区，查询全国数据，即是物业大屏数据展示分析接口使用*/
            map.put("type", 1);
            //获取耗水等年度信息
            SysProject sysProjectForYear = sysProjectBusinessService.sysProjectEnergyAnalysisForYear(map);

            //获取工程遗留问题等年度信息（该处经过需求变更，已经由原来的12个月相加，变为获取当上月的数据：当月数据已经累计相加后包含了上月数据，根据10号更新的规格）2019-1-9号修改
            SysProject sysProjectUnfinishedForYear = sysProjectBusinessService.sysProjectUnfinishedForYear(unfinishedMap);

            if (null != sysProjectForYear) {
                //sysProjectForYear.setYearProjectUnfinishedScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysProjectForYear.getYearProjectFinishedTotal(),sysProjectForYear.getYearProjectUnfinishedTotal())));

               if(null != sysProjectUnfinishedForYear){
                    sysProjectForYear.setYearProjectFinishedTotal(sysProjectUnfinishedForYear.getProjectFinishedTotal());
                    sysProjectForYear.setYearProjectUnfinishedTotal(sysProjectUnfinishedForYear.getProjectUnfinishedTotal());
                    sysProjectForYear.setYearProjectUnfinishedScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysProjectUnfinishedForYear.getProjectFinishedTotal(),sysProjectUnfinishedForYear.getProjectUnfinishedTotal())));
                }else{
                    sysProjectForYear.setYearProjectFinishedTotal(0);
                    sysProjectForYear.setYearProjectUnfinishedTotal(0);
                    sysProjectForYear.setYearProjectUnfinishedScale(0D);
                }

                //某一个月数据（每月10号到下个月10号显示上一个月的数据，即5月10号到6月10号显示4月份数据。）
                SysProject sysProjectForMonth = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(selectYear, selectMonth, sysUserCompanyIds);
                if (null != sysProjectForMonth) {
                    sysProjectForYear.setMonthConsumptionElectricity(sysProjectForMonth.getMonthConsumptionElectricity());
                    sysProjectForYear.setMonthConsumptionWater(sysProjectForMonth.getMonthConsumptionWater());
                    //上个月数据
                    SysProject sysProjectForLastMonth = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(lastYear, lastMonth, sysUserCompanyIds);
                    if (null != sysProjectForLastMonth) {
                        sysProjectForYear.setMtoMtConsumptionElectricityScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProjectForMonth.getMonthConsumptionElectricity() - sysProjectForLastMonth.getMonthConsumptionElectricity(), sysProjectForLastMonth.getMonthConsumptionElectricity())));
                        sysProjectForYear.setMtoMtConsumptionWaterScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProjectForMonth.getMonthConsumptionWater() - sysProjectForLastMonth.getMonthConsumptionWater(), sysProjectForLastMonth.getMonthConsumptionWater())));
                        //上一年同月数据
                        SysProject sysProjectForLastYear = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(selectYear - 1, selectMonth, sysUserCompanyIds);
                        if (null != sysProjectForLastYear) {
                            sysProjectForYear.setYoYConsumptionElectricityScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProjectForMonth.getMonthConsumptionElectricity() - sysProjectForLastYear.getMonthConsumptionElectricity(), sysProjectForLastYear.getMonthConsumptionElectricity())));
                            sysProjectForYear.setYoYConsumptionWaterScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProjectForMonth.getMonthConsumptionWater() - sysProjectForLastYear.getMonthConsumptionWater(), sysProjectForLastYear.getMonthConsumptionWater())));
                        }
                    }
                }
                return R.ok().putData(200, sysProjectForYear, "获取统计数据成功！");
            }else{
                sysProjectForYear=new SysProject();
                return R.ok().putData(200, sysProjectForYear, "数据不存在！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取工程能耗信息报表信息出错：" + e.getMessage());
            return R.error(500, "获取工程能耗信息报表信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> sysProjectEnergyAnalysisForMonth(Long companyId, Integer selectYear, Integer selectMonth) {
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
            SysUpdateDataRules sysUpdateDataRules=sysUpdateDataRulesBusinessService.findSysUpdateDataRules();
            //获取需要查询的年份和月份
            Map<String,Integer> yearAndMonthMap=SysUtil.getYearAndMonth(sysUpdateDataRules.getDay());

            if(selectYear==null|selectMonth==null){
                selectYear = yearAndMonthMap.get("year");
                selectMonth = yearAndMonthMap.get("month");
            }

            //int year = UtilHelper.getYear();
            //int month = UtilHelper.getMonth();
            map.put("sysUserCompanyIds", sysUserCompanyIds);
            map.put("year", selectYear);
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
            SysProjectEnergyEntity sysProjectEnergyEntity = new SysProjectEnergyEntity();
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
                            sysProjectForMonth = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(selectYear - 1, 12, sysUserCompanyIds);
                        } else {
                            sysProjectForMonth = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(selectYear, infoMonth - 1, sysUserCompanyIds);
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
    @Override
    public void setDataToRedis() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Long> sysUserCompanyIds = null;
        //int year = UtilHelper.getYear();
        //int month = UtilHelper.getMonth();
        SysUpdateDataRules sysUpdateDataRules=sysUpdateDataRulesBusinessService.findSysUpdateDataRules();
        //获取需要查询的年份和月份
        Map<String,Integer> yearAndMonthMap=SysUtil.getYearAndMonth(sysUpdateDataRules.getDay());
        //某一个月的上一年数据（每月10号到下个月10号显示上一个月的数据，即5月10号到6月10号显示4月份数据。）
        int lastYear = 0;
        //某一个月的上个月数据（每月10号到下个月10号显示上一个月的数据，即5月10号到6月10号显示4月份数据。）
        int lastMonth = 0;
        if (yearAndMonthMap.get("month") == 1) {
            lastMonth = 12;
            lastYear = yearAndMonthMap.get("year") - 1;
        } else {
            lastMonth = yearAndMonthMap.get("month") - 1;
            lastYear = yearAndMonthMap.get("year");
        }
        map.put("sysUserCompanyIds", null);
        map.put("year", yearAndMonthMap.get("year"));
        map.put("month", yearAndMonthMap.get("month"));


        /*注：type为1时，为按区域查询（小区）查询数据，type为2时，不考虑登录用户权限内小区，查询全国数据，即是物业大屏数据展示分析接口使用*/
        map.put("type", 2);
        SysProject sysProjectForYear = sysProjectBusinessService.sysProjectEnergyAnalysisForYear(map);
        //获取工程遗留问题等年度信息（该处经过需求变更，已经由原来的12个月相加，变为获取当上月的数据：当月数据已经累计相加后包含了上月数据，根据10号更新的规格）2019-1-9号修改
        SysProject sysProjectUnfinishedForYear = sysProjectBusinessService.sysProjectUnfinishedForYear(map);
        if (null != sysProjectForYear) {
            //sysProjectForYear.setYearProjectUnfinishedScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysProjectForYear.getYearProjectUnfinishedTotal(), sysProjectForYear.getYearProjectFinishedTotal())));

            if(null != sysProjectUnfinishedForYear){
                sysProjectForYear.setYearProjectFinishedTotal(sysProjectUnfinishedForYear.getProjectFinishedTotal());
                sysProjectForYear.setYearProjectUnfinishedTotal(sysProjectUnfinishedForYear.getProjectUnfinishedTotal());
                sysProjectForYear.setYearProjectUnfinishedScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysProjectUnfinishedForYear.getProjectFinishedTotal(),sysProjectUnfinishedForYear.getProjectUnfinishedTotal())));
            }else{
                sysProjectForYear.setYearProjectFinishedTotal(0);
                sysProjectForYear.setYearProjectUnfinishedTotal(0);
                sysProjectForYear.setYearProjectUnfinishedScale(0D);
            }

            //某一个月数据（每月10号到下个月10号显示上一个月的数据，即5月10号到6月10号显示4月份数据。）
            SysProject sysProjectForMonth = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(yearAndMonthMap.get("year"), yearAndMonthMap.get("month"), sysUserCompanyIds);
            if (null != sysProjectForMonth) {
                sysProjectForYear.setMonthConsumptionElectricity(sysProjectForMonth.getMonthConsumptionElectricity());
                sysProjectForYear.setMonthConsumptionWater(sysProjectForMonth.getMonthConsumptionWater());
                //月度遗留问题
                sysProjectForYear.setProjectUnfinishedTotal(sysProjectForMonth.getProjectUnfinishedTotal());
                //月度已处理遗留问题
                sysProjectForYear.setProjectFinishedTotal(sysProjectForMonth.getProjectFinishedTotal());
                //上个月数据
                SysProject sysProjectForLastMonth = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(lastYear, lastMonth, sysUserCompanyIds);
                if (null != sysProjectForLastMonth) {
                    sysProjectForYear.setMtoMtConsumptionElectricityScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProjectForMonth.getMonthConsumptionElectricity() - sysProjectForLastMonth.getMonthConsumptionElectricity(), sysProjectForLastMonth.getMonthConsumptionElectricity())));
                    sysProjectForYear.setMtoMtConsumptionWaterScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProjectForMonth.getMonthConsumptionWater() - sysProjectForLastMonth.getMonthConsumptionWater(), sysProjectForLastMonth.getMonthConsumptionWater())));
                    //上一年同月数据
                    SysProject sysProjectForLastYear = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(yearAndMonthMap.get("year") - 1, yearAndMonthMap.get("month"), sysUserCompanyIds);
                    if (null != sysProjectForLastYear) {
                        sysProjectForYear.setYoYConsumptionElectricityScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProjectForMonth.getMonthConsumptionElectricity() - sysProjectForLastYear.getMonthConsumptionElectricity(), sysProjectForLastYear.getMonthConsumptionElectricity())));
                        sysProjectForYear.setYoYConsumptionWaterScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysProjectForMonth.getMonthConsumptionWater() - sysProjectForLastYear.getMonthConsumptionWater(), sysProjectForLastYear.getMonthConsumptionWater())));
                    }
                }
            }
        }else{
            sysProjectForYear=new SysProject();
        }
        //将组装的数据存储到redis缓存
        redisTemplate.opsForValue().set("sysProjectForYear", sysProjectForYear);
        //resultMap.put("sysProjectForYear", sysProjectForYear);
        /********************************获取年度耗水量、耗电量统计报表信息*******************************/
        //耗电量环比
        Map<Integer, Double> mtOMtCsElectricityScaleMap = null;
        //耗水量环比
        Map<Integer, Double> mtOMtCsWaterScaleMap = null;
        //耗电量(月)
        Map<Integer, Double> monthCsElectricityMap = null;
        //耗水量(月)
        Map<Integer, Double> monthCsWaterMap = null;

        SysProjectEnergyEntity sysProjectEnergyEntity = new SysProjectEnergyEntity();
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
                        sysProjectForMonth = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(yearAndMonthMap.get("year") - 1, 12, sysUserCompanyIds);
                    } else {
                        sysProjectForMonth = sysProjectBusinessService.sysProjectEnergyByYearAndMonthAndCompanyId(yearAndMonthMap.get("year"), infoMonth - 1, sysUserCompanyIds);
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
        //resultMap.put("sysProjectForMonth", sysProjectEnergyEntity);
        //将组装的数据存储到redis缓存
        redisTemplate.opsForValue().set("sysProjectForMonth", sysProjectEnergyEntity);
        //调取物业大屏数据接口
        sysDataAnalysisService.sysPropertyDataAnalysis();
    }
}
