package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysQualityManage;
import com.spring.boot.bean.master.SysQualityManageFile;
import com.spring.boot.bean.master.SysUpdateDataRules;
import com.spring.boot.bean.master.entity.SysQualityManageEntity;
import com.spring.boot.service.SysDataAnalysisService;
import com.spring.boot.service.SysQualityManageService;
import com.spring.boot.service.web.SysQualityManageBusinessService;
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
public class SysQualityManageServiceImpl implements SysQualityManageService {
    private static final Logger logger = Logger.getLogger(SysQualityManageServiceImpl.class);
    @Autowired
    private SysQualityManageBusinessService sysQualityManageBusinessService;
    @Autowired
    private SysDataAnalysisService sysDataAnalysisService;

    @Autowired
    private SysUpdateDataRulesBusinessService sysUpdateDataRulesBusinessService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //对象
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Map<String, Object> sysQualityManageAnalysis(long companyId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Long> sysUserCompanyIds = null;
        Map<String, Object> mapForYear = new HashMap<String, Object>();
        Map<String, Object> mapForMonth = new HashMap<String, Object>();
        SysQualityManageEntity sysQualityManageEntityForYear = null;
        SysQualityManageEntity sysQualityManageEntityForMonth = null;
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
            //***************************查询年数据条件************************************//
            mapForYear.put("sysUserCompanyIds", sysUserCompanyIds);
            mapForYear.put("year", UtilHelper.getYear());
            mapForYear.put("month", UtilHelper.getMonth());
            /*注：type为1时，为按区域查询（小区）查询数据，type为2时，不考虑登录用户权限内小区，查询全国数据，即是物业大屏数据展示分析接口使用*/
            mapForYear.put("type", 1);

            //***************************查询月份数据条件************************************//
            mapForMonth.put("sysUserCompanyIds", sysUserCompanyIds);
            mapForMonth.put("year", yearAndMonthMap.get("year"));
            mapForMonth.put("month", yearAndMonthMap.get("month"));
            /*注：type为1时，为按区域查询（小区）查询数据，type为2时，不考虑登录用户权限内小区，查询全国数据，即是物业大屏数据展示分析接口使用*/
            mapForMonth.put("type", 1);
            //查找年度报表数据
            sysQualityManageEntityForYear = sysQualityManageBusinessService.sysQualityManageAnalysisForYear(mapForYear);
            if (sysQualityManageEntityForYear != null) {
                sysQualityManageEntityForYear.setQualityCheckPassScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManageEntityForYear.getQualityCheckPass(), sysQualityManageEntityForYear.getQualityCheck())));
                sysQualityManageEntityForYear.setModifiedPassScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber((sysQualityManageEntityForYear.getQualityCheckFail()-sysQualityManageEntityForYear.getQualityCheckUnmodified()), sysQualityManageEntityForYear.getQualityCheckFail())));
            } else {
                sysQualityManageEntityForYear=new SysQualityManageEntity();
                //resultMap.put("qualityManageYear", null);
                //return R.error(500, "获取年度信息失败,不存在数据！");
            }
            resultMap.put("qualityManageYear", sysQualityManageEntityForYear);
            //查找月度报表数据
            sysQualityManageEntityForMonth = sysQualityManageBusinessService.sysQualityManageAnalysisForMonth(mapForMonth);
            if (sysQualityManageEntityForMonth != null) {
                List<SysQualityManage> list = sysQualityManageBusinessService.sysQualityManageAnalysisList(mapForYear);
                //月度品质合格率
                double qualityCheckPassScale = 0;
                //月度品质整改合格率
                double modifiedPassScale = 0;
                //合格率(月)
                Map<Integer, Double> checkPassScaleMap = null;
                //整改合格率(月)
                Map<Integer, Double> modifiedPassScaleMap = null;
                if (list.size() > 0) {
                    checkPassScaleMap = new HashMap<Integer, Double>();
                    modifiedPassScaleMap = new HashMap<Integer, Double>();
                    Integer month = 0;
                    for (SysQualityManage sysQualityManage : list) {
                        qualityCheckPassScale = UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManage.getQualityCheckPass(), sysQualityManage.getQualityCheck()));
                        modifiedPassScale = UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManage.getQualityCheckUnmodified(), sysQualityManage.getQualityCheckFail()));
                        month = sysQualityManage.getMonth();
                        checkPassScaleMap.put(month, qualityCheckPassScale);
                        modifiedPassScaleMap.put(month, modifiedPassScale);
                    }
                }
                sysQualityManageEntityForMonth.setCheckPassScaleMap(checkPassScaleMap);
                sysQualityManageEntityForMonth.setModifiedPassScaleMap(modifiedPassScaleMap);
                sysQualityManageEntityForMonth.setQualityCheckPassScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManageEntityForMonth.getQualityCheckPass(), sysQualityManageEntityForMonth.getQualityCheck())));
                sysQualityManageEntityForMonth.setModifiedPassScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManageEntityForMonth.getQualityCheckUnmodified(), sysQualityManageEntityForMonth.getQualityCheckFail())));
                //resultMap.put("qualityManageMonth", sysQualityManageEntityForMonth);
            } else {
                sysQualityManageEntityForMonth=new SysQualityManageEntity();
                //return R.error(500, "获取月度信息失败，不存在数据！");
            }
            resultMap.put("qualityManageMonth", sysQualityManageEntityForMonth);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取品质管理数据失败：" + e.getMessage());
            return R.error(500, "获取信息失败，服务器异常，请联系系统管理员！");
        }
        return R.ok().putData(200, resultMap, "获取信息成功！");
    }

    @Override
    public Map<String, Object> getSysQualityManageList(long companyId, int year, int limit, int offset) {
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
            map.put("limit", limit);
            map.put("offset", offset);
            resultMap.put("total", sysQualityManageBusinessService.getSysQualityManageListTotal(map));
            resultMap.put("list", sysQualityManageBusinessService.getSysQualityManageList(map));
            return R.ok().putData(200, resultMap, "获取数据成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取品质管理列表数据失败：" + e.getMessage());
            return R.error(500, "获取品质管理列表数据失败，服务器异常，请联系系统管理员！");
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addSysQualityManage(Long companyId, Integer year, Integer month, Integer qualityCheck, Integer qualityCheckPass, Integer securityEvent, Integer qualityCheckUnmodified, String fileInfo) {
        SysQualityManage sysQualityManage = new SysQualityManage();
        sysQualityManage.setCompanyId(companyId);
        sysQualityManage.setYear(year);
        sysQualityManage.setMonth(month);
        sysQualityManage.setQualityCheck(qualityCheck);
        sysQualityManage.setQualityCheckPass(qualityCheckPass);
        sysQualityManage.setQualityCheckFail(qualityCheck-qualityCheckPass);
        sysQualityManage.setSecurityEvent(securityEvent);
        sysQualityManage.setQualityCheckUnmodified(qualityCheckUnmodified);
        sysQualityManage.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
        SysQualityManage record=sysQualityManageBusinessService.sysQualityManageRecord(companyId,year,month);
        if(null!=record){
            return R.error(500, "新增失败，系统已存在" + year + "年" + month + "月的记录，不能重复添加");
        }
        int count = sysQualityManageBusinessService.addSysQualityManage(sysQualityManage);
        if (count > 0) {
            if (!UtilHelper.isEmpty(fileInfo)) {
                String[] fileInfoArray;
                //去掉最后那个逗号，在进行获取数据
                fileInfoArray = fileInfo.substring(0, fileInfo.length()).split(";");
                SysQualityManageFile sysQualityManageFile = null;
                String[] fileData;
                for (String fileUrl : fileInfoArray) {
                    sysQualityManageFile = new SysQualityManageFile();
                    //根据，逗号分隔，获取文件的地址和文件大小（文件数据格式：文件地址，文件大小）
                    fileData = fileUrl.substring(0, fileUrl.length()).split(",");
                    sysQualityManageFile.setQualityId(sysQualityManage.getQualityId());
                    sysQualityManageFile.setFileName(fileData[0].substring(fileData[0].lastIndexOf("/") + 1, fileData[0].lastIndexOf(".")));
                    sysQualityManageFile.setFileSize(Double.valueOf(fileData[1]));
                    sysQualityManageFile.setFileUrl(fileData[0]);
                    sysQualityManageFile.setUploadTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
                    sysQualityManageBusinessService.addSysQualityManageFile(sysQualityManageFile);
                }
            }

            //添加统计信息到redis缓存（包含年度统计信息和月度统计信息）
            setDataToRedis();

            return R.ok(200, "新增数据成功！");
        } else {
            return R.error(500, "获取数据失败，服务器异常！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> updateSysQualityManage(Long qualityId, Long companyId, Integer year, Integer month, Integer qualityCheck, Integer qualityCheckPass
            ,  Integer securityEvent, Integer qualityCheckUnmodified, String fileInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("qualityId", qualityId);
        map.put("companyId", companyId);
        map.put("year", year);
        map.put("month", month);
        map.put("qualityCheck", qualityCheck);
        map.put("qualityCheckPass", qualityCheckPass);
        map.put("qualityCheckFail", qualityCheck-qualityCheckPass);
        map.put("securityEvent", securityEvent);
        map.put("qualityCheckUnmodified", qualityCheckUnmodified);
        SysQualityManage record=sysQualityManageBusinessService.sysQualityManageRecord(companyId,year,month);
        if(null!=record){
            if(!qualityId.equals(record.getQualityId())){
                return R.error(500, "更新失败，系统已存在" + year + "年" + month + "月的记录，不能重复添加");
            }
        }
        int count = sysQualityManageBusinessService.updateSysQualityManage(map);
        if (count > 0) {
            if (!UtilHelper.isEmpty(fileInfo)) {
                //先删除原有数据库记录
                //sysQualityManageBusinessService.deleteSysQualityManageFileByQualityId(qualityId);
                String[] fileInfoArray;
                //去掉最后那个逗号，在进行获取数据
                fileInfoArray = fileInfo.substring(0, fileInfo.length()).split(";");
                SysQualityManageFile sysQualityManageFile = null;
                String[] fileData;
                for (String fileUrl : fileInfoArray) {
                    sysQualityManageFile = new SysQualityManageFile();
                    //根据，逗号分隔，获取文件的地址和文件大小（文件数据格式：文件地址，文件大小）
                    fileData = fileUrl.substring(0, fileUrl.length()).split(",");
                    sysQualityManageFile.setQualityId(qualityId);
                    sysQualityManageFile.setFileName(fileData[0].substring(fileData[0].lastIndexOf("/") + 1, fileData[0].lastIndexOf(".")));
                    sysQualityManageFile.setFileSize(Double.valueOf(fileData[1]));
                    sysQualityManageFile.setFileUrl(fileData[0]);
                    sysQualityManageFile.setUploadTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
                    sysQualityManageBusinessService.addSysQualityManageFile(sysQualityManageFile);
                }
            }

            //添加统计信息到redis缓存（包含年度统计信息和月度统计信息）
            setDataToRedis();

            return R.ok(200, "更新数据成功！");
        } else {
            return R.error(500, "更新数据失败，服务器异常！");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> deleteSysQualityManageById(long qualityId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("qualityId", qualityId);
        int count = sysQualityManageBusinessService.deleteSysQualityManageById(map);
        if (count > 0) {
            //SysQualityManage sysQualityManage = sysQualityManageBusinessService.findSysQualityManageById(map);

            //添加统计信息到redis缓存（包含年度统计信息和月度统计信息）
            setDataToRedis();

            return R.ok(200, "删除数据成功！");
        } else {
            return R.error(500, "删除数据失败，服务器异常！");
        }
    }

    @Override
    public Map<String, Object> findSysQualityManageById(Long qualityId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        map.put("qualityId", qualityId);
        //SysQualityManage sysCompany = sysQualityManageBusinessService.findSysQualityManageById(map);
        try {
            SysQualityManage sysQualityManage = sysQualityManageBusinessService.findSysQualityManageById(map);
            if(null!=sysQualityManage){
                List<SysQualityManageFile> sysQualityManageFiles=sysQualityManageBusinessService.findSysQualityManageFileById(qualityId);
                sysQualityManage.setFileList(sysQualityManageFiles);
                return R.ok().putData(200, sysQualityManage, "根据id查找品质管理数据成功！");
            }else{
                return R.error(500, "根据id查找品质管理数据失败，服务器异常，请联系系统管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("根据id查找品质管理数据失败：" + e.getMessage());
            return R.error(500, "根据id查找品质管理数据失败，服务器异常，请联系系统管理员！");
        }
    }

    @Override
    public Map<String, Object> findSysQualityManageFileById(long qualityId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            List<SysQualityManageFile> fileList = sysQualityManageBusinessService.findSysQualityManageFileById(qualityId);
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

    /**
     * 将年度、月度统计报表信息放进redis缓存
     */
    public void setDataToRedis() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SysQualityManageEntity sysQualityManageEntityForYear = null;
        SysQualityManageEntity sysQualityManageEntityForMonth = null;
        Map<String, Object> mapForYear = new HashMap<String, Object>();
        Map<String, Object> mapForMonth = new HashMap<String, Object>();
        SysUpdateDataRules sysUpdateDataRules=sysUpdateDataRulesBusinessService.findSysUpdateDataRules();
        //获取需要查询的年份和月份
        Map<String,Integer> yearAndMonthMap=SysUtil.getYearAndMonth(sysUpdateDataRules.getDay());
        //***************************查询年数据条件************************************//
        mapForYear.put("sysUserCompanyIds", null);
        mapForYear.put("year", UtilHelper.getYear());
        mapForYear.put("month", UtilHelper.getMonth());
            /*注：type为1时，为按区域查询（小区）查询数据，type为2时，不考虑登录用户权限内小区，查询全国数据，即是物业大屏数据展示分析接口使用*/
        mapForYear.put("type", 2);

        //***************************查询月份数据条件************************************//
        mapForMonth.put("sysUserCompanyIds", null);
        mapForMonth.put("year", yearAndMonthMap.get("year"));
        mapForMonth.put("month", yearAndMonthMap.get("month"));
            /*注：type为1时，为按区域查询（小区）查询数据，type为2时，不考虑登录用户权限内小区，查询全国数据，即是物业大屏数据展示分析接口使用*/
        mapForMonth.put("type", 2);

        //查找年度报表数据
        sysQualityManageEntityForYear = sysQualityManageBusinessService.sysQualityManageAnalysisForYear(mapForYear);
        if (sysQualityManageEntityForYear != null) {
            sysQualityManageEntityForYear.setQualityCheckPassScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManageEntityForYear.getQualityCheckPass(), sysQualityManageEntityForYear.getQualityCheck())));
            sysQualityManageEntityForYear.setModifiedPassScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber((sysQualityManageEntityForYear.getQualityCheckFail()-sysQualityManageEntityForYear.getQualityCheckUnmodified()), sysQualityManageEntityForYear.getQualityCheckFail())));
        }else{
            sysQualityManageEntityForYear=new SysQualityManageEntity();
        }
        //将年度统计信息放入redis缓存
        redisTemplate.opsForValue().set("qualityManageYear", sysQualityManageEntityForYear);
        //resultMap.put("qualityManageYear", sysQualityManageEntityForYear);
        //查找月度报表数据
        sysQualityManageEntityForMonth = sysQualityManageBusinessService.sysQualityManageAnalysisForMonth(mapForMonth);
        if (sysQualityManageEntityForMonth != null) {
            List<SysQualityManage> list = sysQualityManageBusinessService.sysQualityManageAnalysisList(mapForYear);
            //月度品质合格率
            double qualityCheckPassScale = 0;
            //月度品质整改合格率
            double modifiedPassScale = 0;
            //合格率(月)
            Map<Integer, Double> checkPassScaleMap = null;
            //整改合格率(月)
            Map<Integer, Double> modifiedPassScaleMap = null;
            if (list.size() > 0) {
                checkPassScaleMap = new HashMap<Integer, Double>();
                modifiedPassScaleMap = new HashMap<Integer, Double>();
                Integer month = 0;
                for (SysQualityManage sysQualityManage : list) {
                    qualityCheckPassScale = UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManage.getQualityCheckPass(), sysQualityManage.getQualityCheck()));
                    modifiedPassScale = UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManage.getQualityCheckUnmodified(), sysQualityManage.getQualityCheckFail()));
                    month = sysQualityManage.getMonth();
                    checkPassScaleMap.put(month, qualityCheckPassScale);
                    modifiedPassScaleMap.put(month, modifiedPassScale);
                }
            }
            sysQualityManageEntityForMonth.setCheckPassScaleMap(checkPassScaleMap);
            sysQualityManageEntityForMonth.setModifiedPassScaleMap(modifiedPassScaleMap);
            sysQualityManageEntityForMonth.setQualityCheckPassScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManageEntityForMonth.getQualityCheckPass(), sysQualityManageEntityForMonth.getQualityCheck())));
            sysQualityManageEntityForMonth.setModifiedPassScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysQualityManageEntityForMonth.getQualityCheckUnmodified(), sysQualityManageEntityForMonth.getQualityCheckFail())));
        }else{
            sysQualityManageEntityForMonth=new SysQualityManageEntity();
        }
        //resultMap.put("qualityManageMonth", sysQualityManageEntityForMonth);
        //将月度统计报表信息放入redis缓存
        redisTemplate.opsForValue().set("qualityManageMonth", sysQualityManageEntityForMonth);
        //调取物业大屏数据接口
        sysDataAnalysisService.sysPropertyDataAnalysis();

    }
}
