package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysBudgetDetails;
import com.spring.boot.bean.master.SysLaborCost;
import com.spring.boot.bean.master.SysLaborCostDetails;
import com.spring.boot.bean.master.entity.SysLaborCostDetailsEntity;
import com.spring.boot.service.SysDataAnalysisService;
import com.spring.boot.service.SysLaborCostService;
import com.spring.boot.service.web.SysBudgetDetailsBusinessService;
import com.spring.boot.service.web.SysLaborCostBusinessService;
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
public class SysLaborCostServiceImpl implements SysLaborCostService {
    private static final Logger logger = Logger.getLogger(SysLaborCostServiceImpl.class);
    @Autowired
    private SysLaborCostBusinessService sysLaborCostBusinessService;
    @Autowired
    private SysBudgetDetailsBusinessService sysBudgetDetailsBusinessService;
    @Autowired
    private SysDataAnalysisService sysDataAnalysisService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> getSysLaborCostAnalysis(Long companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int month = UtilHelper.getMonth();
        int year = UtilHelper.getYear();
        SysLaborCostDetailsEntity sysLaborCostDetails = null;
        List<SysLaborCostDetails> sysLaborCostDepartmentList = null;
        List<Long> sysUserCompanyIds = null;
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
        try {
            sysLaborCostDetails = sysLaborCostBusinessService.getSysLaborCostTotal(map);
            if (null != sysLaborCostDetails) {
                Double laborCostTotal = sysLaborCostDetails.getLaborCostTotal();
                sysLaborCostDetails.setAverageLaborCost(UtilHelper.DecimalFormatForDouble(UtilHelper.DecimalFormatDoubleNumber(laborCostTotal, sysLaborCostDetails.getEmployeeTotal())));
                //(成本构成)获取和计算物业常态、电商、销配每月的人工成本支出占公司人工成本支出的百分比
                sysLaborCostDepartmentList = sysLaborCostBusinessService.getSysLaborCostDepartmentTotal(map);
                if (null != sysLaborCostDepartmentList) {
                    for (SysLaborCostDetails sysLaborCostDepartment : sysLaborCostDepartmentList) {
                        if (sysLaborCostDepartment.getDepartmentType() == 1) {
                            sysLaborCostDetails.setPropertyLaborCostScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysLaborCostDepartment.getLaborCostTotal(), laborCostTotal)));
                        } else if (sysLaborCostDepartment.getDepartmentType() == 2) {
                            sysLaborCostDetails.seteBusinessScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysLaborCostDepartment.getLaborCostTotal(), laborCostTotal)));
                        } else if (sysLaborCostDepartment.getDepartmentType() == 3) {
                            sysLaborCostDetails.setSaleLaborCostScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysLaborCostDepartment.getLaborCostTotal(), laborCostTotal)));
                        }
                    }
                }
                //总支出
                Double realExpensesTotal = 0.00;
                //人工费用
                Double personnelCost = 0.00;
                //总支出
                Double realExpensesLastMonthTotal = 0.00;
                //人工费用
                Double personnelCostLastMonth = 0.00;
                //根据年份跟月份查找系统预算记录
                SysBudgetDetails sysBudget = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(year, month, sysUserCompanyIds);
                //根据年份跟月份查找系统预算记录
                SysBudgetDetails sysBudgetLastMonth = null;
                if (month == 1) {
                    year = year - 1;
                    month = 12;
                } else {
                    month = month - 1;
                }
                sysBudgetLastMonth = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(year, month, sysUserCompanyIds);
                if (null != sysBudgetLastMonth && sysBudgetLastMonth.getRealProfits() != null) {
                    realExpensesLastMonthTotal = sysBudgetLastMonth.getRealExpensesTotal();
                    personnelCostLastMonth = sysBudgetLastMonth.getPersonnelCost();
                }
                //流失率
                sysLaborCostDetails.setSysDemissionScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysLaborCostDetails.getDemissionTotal() - sysLaborCostDetails.getEntryTotal(), sysLaborCostDetails.getEmployeeTotal())));
                //缺编率
                sysLaborCostDetails.setSysEmployeeScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysLaborCostDetails.getEmployeeTotal()-sysLaborCostDetails.getHeadcountTotal(), sysLaborCostDetails.getHeadcountTotal())));
                if (null != sysBudget) {
                    realExpensesTotal = sysBudget.getRealExpensesTotal();
                    personnelCost = sysBudget.getPersonnelCost();
                }
                //人工支出占比
                sysLaborCostDetails.setSysLaborCostScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(personnelCost, realExpensesTotal)));
                //上月人工支出占比
                sysLaborCostDetails.setSysLaborCostLastMonthScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(personnelCostLastMonth, realExpensesLastMonthTotal)));
                return R.ok().putData(200, sysLaborCostDetails, "获取成功！");
            }else{
                sysLaborCostDetails=new SysLaborCostDetailsEntity();
                return R.ok().putData(200, sysLaborCostDetails, "数据不存在！");
            }

            //resultMap.put("sysLaborCostTotal", sysLaborCostDetails);
            //人工支出占比
            // resultMap.put("sysLaborCostScale", 0);
            //人员缺编率
            // resultMap.put("sysEmployeeScale", (UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysLaborCostDetails.getEmployeeTotal(), sysLaborCostDetails.getHeadcountTotal())) ) );
            ////人员流失率
            //resultMap.put("sysDemissionScale", (UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysLaborCostDetails.getDemissionTotal(),sysLaborCostDetails.getEntryTotal()))));
            //成本构成
            //resultMap.put("sysLaborCostsDepartmentList", sysLaborCostDepartment);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取人员成本统计信息错误：" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> getSysLaborCostList(Integer limit, Integer offset, Long companyId, Integer year) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SysLaborCostDetailsEntity sysLaborCostDetailsEntity = null;
        //List<SysLaborCostDetailsEntity> list = null;
        List<Long> sysUserCompanyIds = null;
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
        //resultMap.put("sysLaborCostDetailsList", list);
        try {
            resultMap.put("total", sysLaborCostBusinessService.getSysLaborCostListTotal(map));
            resultMap.put("list", sysLaborCostBusinessService.getSysLaborCostList(map));
            return R.ok().putData(200, resultMap, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取公司列表出错：" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addSysLaborCost(Long companyId, Integer year, Integer month, Double propertyLaborCost, Integer propertyHeadcountTotal, Integer propertyEmployeeTotal, Integer propertyEntryTotal, Integer propertyDemissionTotal,
                                               Double eBusinessLaborCost, Integer eBusinessHeadcountTotal, Integer eBusinessEmployeeTotal, Integer eBusinessEntryTotal, Integer eBusinessDemissionTotal,
                                               Double saleLaborCost, Integer saleHeadcountTotal, Integer saleEmployeeTotal, Integer saleEntryTotal, Integer saleDemissionTotal) {

        SysLaborCost sysLaborCost = sysLaborCostBusinessService.findRecordByYearAndMonthAndCompanyId(companyId, year, month);
        if (null != sysLaborCost) {
            return R.error(500, "新增失败，系统已存在" + year + "年" + month + "月的记录，不能重复添加");
        } else {
            sysLaborCost = new SysLaborCost();
        }
        sysLaborCost.setCompanyId(companyId);
        sysLaborCost.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
        sysLaborCost.setYear(year);
        sysLaborCost.setMonth(month);
        int count = sysLaborCostBusinessService.addSysLaborCost(sysLaborCost);
        if (count > 0) {
            long laborCostId = sysLaborCost.getLaborCostId();
            SysLaborCostDetails propertySysLaborCostDetails = new SysLaborCostDetails();
            propertySysLaborCostDetails.setDemissionTotal(propertyDemissionTotal);
            propertySysLaborCostDetails.setDepartmentType(1);
            propertySysLaborCostDetails.setEmployeeTotal(propertyEmployeeTotal);
            propertySysLaborCostDetails.setEntryTotal(propertyEntryTotal);
            propertySysLaborCostDetails.setHeadcountTotal(propertyHeadcountTotal);
            propertySysLaborCostDetails.setLaborCostId(laborCostId);
            propertySysLaborCostDetails.setLaborCostTotal(propertyLaborCost);
            int propertyCount = sysLaborCostBusinessService.addSysLaborCostDetails(propertySysLaborCostDetails);

            SysLaborCostDetails eBusinessSysLaborCostDetails = new SysLaborCostDetails();
            eBusinessSysLaborCostDetails.setDemissionTotal(eBusinessDemissionTotal);
            eBusinessSysLaborCostDetails.setDepartmentType(2);
            eBusinessSysLaborCostDetails.setEmployeeTotal(eBusinessEmployeeTotal);
            eBusinessSysLaborCostDetails.setEntryTotal(eBusinessEntryTotal);
            eBusinessSysLaborCostDetails.setHeadcountTotal(eBusinessHeadcountTotal);
            eBusinessSysLaborCostDetails.setLaborCostId(laborCostId);
            eBusinessSysLaborCostDetails.setLaborCostTotal(eBusinessLaborCost);
            int eBusinessCount = sysLaborCostBusinessService.addSysLaborCostDetails(eBusinessSysLaborCostDetails);

            SysLaborCostDetails saleSysLaborCostDetails = new SysLaborCostDetails();
            saleSysLaborCostDetails.setDemissionTotal(saleDemissionTotal);
            saleSysLaborCostDetails.setDepartmentType(3);
            saleSysLaborCostDetails.setEmployeeTotal(saleEmployeeTotal);
            saleSysLaborCostDetails.setEntryTotal(saleEntryTotal);
            saleSysLaborCostDetails.setHeadcountTotal(saleHeadcountTotal);
            saleSysLaborCostDetails.setLaborCostId(laborCostId);
            saleSysLaborCostDetails.setLaborCostTotal(saleLaborCost);
            sysLaborCostBusinessService.addSysLaborCostDetails(saleSysLaborCostDetails);
            //将统计信息存储到redis缓存中
            setDateToRedis();
            return R.ok(200, "添加人员成本信息成功！！");
        } else {
            return R.error(500, "添加人员成本信息失败，请联系系统管理员！！");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> updateSysLaborCostInfo(Long laborCostId, Long companyId, Integer year, Integer month, Double propertyLaborCost, Integer propertyHeadcountTotal, Integer propertyEmployeeTotal,
                                                      Integer propertyEntryTotal, Integer propertyDemissionTotal, Double eBusinessLaborCost, Integer eBusinessHeadcountTotal, Integer eBusinessEmployeeTotal,
                                                      Integer eBusinessEntryTotal, Integer eBusinessDemissionTotal, Double saleLaborCost, Integer saleHeadcountTotal, Integer saleEmployeeTotal,
                                                      Integer saleEntryTotal, Integer saleDemissionTotal) {
        SysLaborCost sysLaborCost = sysLaborCostBusinessService.findRecordByYearAndMonthAndCompanyId(companyId, year, month);
        if (null != sysLaborCost) {
            if (!laborCostId.equals(sysLaborCost.getLaborCostId())) {
                return R.error(500, "更新失败，系统已存在" + year + "年" + month + "月的记录，不能出现重复数据！");
            }
        }
        sysLaborCost = sysLaborCostBusinessService.findSysLaborCostByLaborCostId(Long.valueOf(laborCostId));
        if (sysLaborCost != null) {
            sysLaborCost.setMonth(month);
            sysLaborCost.setYear(year);
            sysLaborCost.setCompanyId(companyId);
            sysLaborCostBusinessService.updateSysLaborCostInfo(sysLaborCost);

            long laborCostIdUpdate = Long.valueOf(laborCostId);
            SysLaborCostDetails propertySysLaborCostDetails = new SysLaborCostDetails();
            propertySysLaborCostDetails.setDemissionTotal(propertyDemissionTotal);
            propertySysLaborCostDetails.setEmployeeTotal(propertyEmployeeTotal);
            propertySysLaborCostDetails.setEntryTotal(propertyEntryTotal);
            propertySysLaborCostDetails.setDepartmentType(1);
            propertySysLaborCostDetails.setHeadcountTotal(propertyHeadcountTotal);
            propertySysLaborCostDetails.setLaborCostId(laborCostIdUpdate);
            propertySysLaborCostDetails.setLaborCostTotal(propertyLaborCost);
            int propertyCount = sysLaborCostBusinessService.updateSysLaborCostDetailsInfo(propertySysLaborCostDetails);

            SysLaborCostDetails eBusinessSysLaborCostDetails = new SysLaborCostDetails();
            eBusinessSysLaborCostDetails.setDemissionTotal(eBusinessDemissionTotal);
            eBusinessSysLaborCostDetails.setEmployeeTotal(eBusinessEmployeeTotal);
            eBusinessSysLaborCostDetails.setEntryTotal(eBusinessEntryTotal);
            eBusinessSysLaborCostDetails.setDepartmentType(2);
            eBusinessSysLaborCostDetails.setHeadcountTotal(eBusinessHeadcountTotal);
            eBusinessSysLaborCostDetails.setLaborCostId(laborCostIdUpdate);
            eBusinessSysLaborCostDetails.setLaborCostTotal(eBusinessLaborCost);
            int eBusinessCount = sysLaborCostBusinessService.updateSysLaborCostDetailsInfo(eBusinessSysLaborCostDetails);

            SysLaborCostDetails saleSysLaborCostDetails = new SysLaborCostDetails();
            saleSysLaborCostDetails.setDemissionTotal(saleDemissionTotal);
            saleSysLaborCostDetails.setEmployeeTotal(saleEmployeeTotal);
            saleSysLaborCostDetails.setEntryTotal(saleEntryTotal);
            saleSysLaborCostDetails.setDepartmentType(3);
            saleSysLaborCostDetails.setHeadcountTotal(saleHeadcountTotal);
            saleSysLaborCostDetails.setLaborCostId(laborCostIdUpdate);
            saleSysLaborCostDetails.setLaborCostTotal(saleLaborCost);
            sysLaborCostBusinessService.updateSysLaborCostDetailsInfo(saleSysLaborCostDetails);
            //将统计信息存储到redis缓存中
            setDateToRedis();
        } else {
            return R.error(500, "更新失败，系统不存在该记录信息，请联系系统管理员进行处理！！");
        }

        return R.ok(200, "更新人员成本信息成功！！！");

    }

    @Override
    public Map<String, Object> findSysLaborCostById(Long laborCostId) {
        try {
            SysLaborCost sysLaborCost = sysLaborCostBusinessService.findSysLaborCostByLaborCostId(laborCostId);
            if (null != sysLaborCost) {
                List<SysLaborCostDetails> list = sysLaborCostBusinessService.findSysLaborCostDetailsByLaborCostId(laborCostId);
                if (null != list) {
                    sysLaborCost.setList(list);
                }
                return R.ok().putData(200, sysLaborCost, "获取成功！");
            } else {
                return R.error(500, "获取信息失败，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取信息出错：" + e.getMessage());
            return R.error(500, "获取信息失败，服务器异常，请联系管理员！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> deleteSysLaborCostInfo(Long laborCostId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("laborCostId", laborCostId);

        int count = sysLaborCostBusinessService.deleteSysLaborCostInfo(map);
        if (count > 0) {
            //将统计信息存储到redis缓存中
            setDateToRedis();
            return R.ok(200, "删除成功！");
        } else {
            return R.error(500, "删除失败，请联系管理员！");
        }

    }

    /**
     * 将统计信息存储到redis缓存中
     */
    public void setDateToRedis() {
        Map<String, Object> map = new HashMap<String, Object>();
        int month = UtilHelper.getMonth();
        int year = UtilHelper.getYear();
        SysLaborCostDetailsEntity sysLaborCostDetails = null;
        List<SysLaborCostDetails> sysLaborCostDepartmentList = null;
        List<Long> sysUserCompanyIds = null;
        map.put("sysUserCompanyIds", null);
        map.put("year", year);
        map.put("month", month);
        sysLaborCostDetails = sysLaborCostBusinessService.getSysLaborCostTotal(map);
        if (null != sysLaborCostDetails) {
            Double laborCostTotal = sysLaborCostDetails.getLaborCostTotal();
            sysLaborCostDetails.setAverageLaborCost(UtilHelper.DecimalFormatForDouble(UtilHelper.DecimalFormatDoubleNumber(laborCostTotal, sysLaborCostDetails.getEmployeeTotal())));
            //(成本构成)获取和计算物业常态、电商、销配每月的人工成本支出占公司人工成本支出的百分比
            sysLaborCostDepartmentList = sysLaborCostBusinessService.getSysLaborCostDepartmentTotal(map);
            if (null != sysLaborCostDepartmentList) {
                for (SysLaborCostDetails sysLaborCostDepartment : sysLaborCostDepartmentList) {
                    if (sysLaborCostDepartment.getDepartmentType() == 1) {
                        sysLaborCostDetails.setPropertyLaborCostScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysLaborCostDepartment.getLaborCostTotal(), laborCostTotal)));
                    } else if (sysLaborCostDepartment.getDepartmentType() == 2) {
                        sysLaborCostDetails.seteBusinessScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysLaborCostDepartment.getLaborCostTotal(), laborCostTotal)));
                    } else if (sysLaborCostDepartment.getDepartmentType() == 3) {
                        sysLaborCostDetails.setSaleLaborCostScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysLaborCostDepartment.getLaborCostTotal(), laborCostTotal)));
                    }
                }
            }
            //总支出
            Double realExpensesTotal = 0.00;
            //人工费用
            Double personnelCost = 0.00;
            //总支出
            Double realExpensesLastMonthTotal = 0.00;
            //人工费用
            Double personnelCostLastMonth = 0.00;
            //根据年份跟月份查找系统预算记录
            SysBudgetDetails sysBudget = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(year, month, sysUserCompanyIds);
            //根据年份跟月份查找系统预算记录
            SysBudgetDetails sysBudgetLastMonth = null;
            if (month == 1) {
                year = year - 1;
                month = 12;
            } else {
                month = month - 1;
            }
            sysBudgetLastMonth = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(year, month, sysUserCompanyIds);
            if (null != sysBudgetLastMonth && sysBudgetLastMonth.getRealProfits() != null) {
                realExpensesLastMonthTotal = sysBudgetLastMonth.getRealExpensesTotal();
                personnelCostLastMonth = sysBudgetLastMonth.getPersonnelCost();
            }
            //流失率
            sysLaborCostDetails.setSysDemissionScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysLaborCostDetails.getDemissionTotal() - sysLaborCostDetails.getEntryTotal(), sysLaborCostDetails.getEmployeeTotal())));
            //缺编率
            sysLaborCostDetails.setSysEmployeeScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysLaborCostDetails.getEmployeeTotal()-sysLaborCostDetails.getHeadcountTotal() , sysLaborCostDetails.getHeadcountTotal())));
            if (null != sysBudget) {
                realExpensesTotal = sysBudget.getRealExpensesTotal();
                personnelCost = sysBudget.getPersonnelCost();
            }
            //人工支出占比
            sysLaborCostDetails.setSysLaborCostScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(personnelCost, realExpensesTotal)));
            //上月人工支出占比
            sysLaborCostDetails.setSysLaborCostLastMonthScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(personnelCostLastMonth, realExpensesLastMonthTotal)));

            //将统计信息存储到redis缓存中
            redisTemplate.opsForValue().set("sysLaborCostDetails", sysLaborCostDetails);
            //调取物业大屏数据接口
            sysDataAnalysisService.sysPropertyDataAnalysis();
        }

    }
}
