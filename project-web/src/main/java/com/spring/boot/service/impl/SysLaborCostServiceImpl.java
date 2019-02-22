package com.spring.boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.spring.boot.bean.PageInfoBean;
import com.spring.boot.bean.master.*;
import com.spring.boot.bean.master.entity.SysLaborCostDetailsEntity;
import com.spring.boot.entity.SysLaborCostDetailsAddEntity;
import com.spring.boot.service.SysDataAnalysisService;
import com.spring.boot.service.SysLaborCostService;
import com.spring.boot.service.web.SysBudgetDetailsBusinessService;
import com.spring.boot.service.web.SysLaborCostBusinessService;
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
public class SysLaborCostServiceImpl implements SysLaborCostService {
    private static final Logger logger = Logger.getLogger(SysLaborCostServiceImpl.class);
    @Autowired
    private SysLaborCostBusinessService sysLaborCostBusinessService;
    @Autowired
    private SysBudgetDetailsBusinessService sysBudgetDetailsBusinessService;
    @Autowired
    private SysDataAnalysisService sysDataAnalysisService;
    @Autowired
    private SysUpdateDataRulesBusinessService sysUpdateDataRulesBusinessService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> getSysLaborCostAnalysis(Long companyId, Integer selectYear, Integer selectMonth) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int month = 0;
        int year = 0;
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
        SysUpdateDataRules sysUpdateDataRules=sysUpdateDataRulesBusinessService.findSysUpdateDataRules();
        //获取需要查询的年份和月份
        Map<String,Integer> yearAndMonthMap=SysUtil.getYearAndMonth(sysUpdateDataRules.getDay());
        if(selectYear==null||selectMonth==null){
            selectYear=yearAndMonthMap.get("year");
            selectMonth=yearAndMonthMap.get("month");
        }
        map.put("sysUserCompanyIds", sysUserCompanyIds);
        map.put("year", selectYear);
        map.put("month", selectMonth);
        try {
            sysLaborCostDetails = sysLaborCostBusinessService.getSysLaborCostTotal(map);
            if (null != sysLaborCostDetails) {
                Double laborCostTotal = sysLaborCostDetails.getLaborCostTotal();
                sysLaborCostDetails.setAverageLaborCost(UtilHelper.DecimalFormatForDouble(UtilHelper.DecimalFormatDoubleNumber(laborCostTotal, sysLaborCostDetails.getPayPeopleTotal())));
                //(成本构成)获取和计算物业常态、电商、销配每月的人工成本支出占公司人工成本支出的百分比
                sysLaborCostDepartmentList = sysLaborCostBusinessService.getSysLaborCostDepartmentTotal(map);
                if (null != sysLaborCostDepartmentList) {
                    for (SysLaborCostDetails sysLaborCostDepartment : sysLaborCostDepartmentList) {
                        if (sysLaborCostDepartment.getDepartmentType() == 1) {
                            sysLaborCostDetails.setPropertyLaborCostScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysLaborCostDepartment.getLaborCostTotal(), laborCostTotal)));
                            sysLaborCostDetails.setPropertyLaborCost(sysLaborCostDepartment.getLaborCostTotal());
                        } else if (sysLaborCostDepartment.getDepartmentType() == 2) {
                            sysLaborCostDetails.seteBusinessScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysLaborCostDepartment.getLaborCostTotal(), laborCostTotal)));
                            sysLaborCostDetails.seteBusinessCost(sysLaborCostDepartment.getLaborCostTotal());
                        } else if (sysLaborCostDepartment.getDepartmentType() == 3) {
                            sysLaborCostDetails.setSaleLaborCostScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysLaborCostDepartment.getLaborCostTotal(), laborCostTotal)));
                            sysLaborCostDetails.setSaleLaborCost(sysLaborCostDepartment.getLaborCostTotal());
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
                SysBudgetDetails sysBudget = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(selectYear, selectMonth, sysUserCompanyIds);
                //根据年份跟月份查找系统预算记录
                SysBudgetDetails sysBudgetLastMonth = null;
                if (selectMonth == 1) {
                    month = 12;
                    year = selectYear - 1;
                } else {
                    month = selectMonth - 1;
                    year = selectYear;
                }
                sysBudgetLastMonth = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(year, month, sysUserCompanyIds);
                if (null != sysBudgetLastMonth && sysBudgetLastMonth.getRealProfits() != null) {
                    realExpensesLastMonthTotal = sysBudgetLastMonth.getRealExpensesTotal();
                    personnelCostLastMonth = sysBudgetLastMonth.getPersonnelCost();
                }
                //流失率
                sysLaborCostDetails.setSysDemissionScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysLaborCostDetails.getDemissionTotal(),sysLaborCostDetails.getBeginMonthPeople()+sysLaborCostDetails.getEntryTotal()+sysLaborCostDetails.getMonthDeploy())));
                //缺编率
                sysLaborCostDetails.setSysEmployeeScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysLaborCostDetails.getHeadcountTotal()-sysLaborCostDetails.getEmployeeTotal(), sysLaborCostDetails.getHeadcountTotal())));
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
    public Map<String, Object> getSysLaborCostList(Integer limit, Integer offset, Long companyId, Integer year,Integer month) {
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
        map.put("month", month);
        //map.put("year", month);
        //resultMap.put("sysLaborCostDetailsList", list);
        try {
            //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
            PageHelper.startPage((offset/limit)+1,limit);
            List<SysLaborCostDetailsEntity> list=sysLaborCostBusinessService.getSysLaborCostList(map);
            PageInfoBean result = new PageInfoBean(list);
            resultMap.put("total", result.getTotalOfData());
            resultMap.put("list", result.getList());

            //resultMap.put("total", sysLaborCostBusinessService.getSysLaborCostListTotal(map));
            //resultMap.put("list", sysLaborCostBusinessService.getSysLaborCostList(map));
            return R.ok().putData(200, resultMap, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取公司列表出错：" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addSysLaborCost(SysLaborCostDetailsAddEntity sysLaborCostDetailsEntity) {

        SysLaborCost sysLaborCost = sysLaborCostBusinessService.findRecordByYearAndMonthAndCompanyId(sysLaborCostDetailsEntity.getCompanyId(), sysLaborCostDetailsEntity.getYear(), sysLaborCostDetailsEntity.getMonth());
        if (null != sysLaborCost) {
            return R.error(500, "新增失败，系统已存在" + sysLaborCostDetailsEntity.getYear() + "年" + sysLaborCostDetailsEntity.getMonth() + "月的记录，不能重复添加");
        } else {
            sysLaborCost = new SysLaborCost();
        }
        sysLaborCost.setCompanyId(sysLaborCostDetailsEntity.getCompanyId());
        sysLaborCost.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
        sysLaborCost.setYear(sysLaborCostDetailsEntity.getYear());
        sysLaborCost.setMonth(sysLaborCostDetailsEntity.getMonth());
        int count = sysLaborCostBusinessService.addSysLaborCost(sysLaborCost);
        if (count > 0) {
            long laborCostId = sysLaborCost.getLaborCostId();
            SysLaborCostDetails propertySysLaborCostDetails = new SysLaborCostDetails();
            propertySysLaborCostDetails.setDemissionTotal(sysLaborCostDetailsEntity.getPropertyDemissionTotal());
            propertySysLaborCostDetails.setDepartmentType(1);
            propertySysLaborCostDetails.setEmployeeTotal(sysLaborCostDetailsEntity.getPropertyEmployeeTotal());
            propertySysLaborCostDetails.setEntryTotal(sysLaborCostDetailsEntity.getPropertyEntryTotal());
            propertySysLaborCostDetails.setHeadcountTotal(sysLaborCostDetailsEntity.getPropertyHeadcountTotal());
            propertySysLaborCostDetails.setLaborCostId(laborCostId);
            propertySysLaborCostDetails.setLaborCostTotal(sysLaborCostDetailsEntity.getPropertyLaborCost());
            propertySysLaborCostDetails.setPayPeopleTotal(sysLaborCostDetailsEntity.getPropertyPayPeopleTotal());
            propertySysLaborCostDetails.setBeginMonthPeople(sysLaborCostDetailsEntity.getPropertyBeginMonthPeople());
            propertySysLaborCostDetails.setMonthDeploy(sysLaborCostDetailsEntity.getPropertyMonthDeploy());
            propertySysLaborCostDetails.setMonthTransfer(sysLaborCostDetailsEntity.getPropertyMonthTransfer());
            int propertyCount = sysLaborCostBusinessService.addSysLaborCostDetails(propertySysLaborCostDetails);

            SysLaborCostDetails eBusinessSysLaborCostDetails = new SysLaborCostDetails();
            eBusinessSysLaborCostDetails.setDemissionTotal(sysLaborCostDetailsEntity.geteBusinessDemissionTotal()==null?0:sysLaborCostDetailsEntity.geteBusinessDemissionTotal());
            eBusinessSysLaborCostDetails.setDepartmentType(2);
            eBusinessSysLaborCostDetails.setEmployeeTotal(sysLaborCostDetailsEntity.geteBusinessEmployeeTotal()==null?0:sysLaborCostDetailsEntity.geteBusinessEmployeeTotal());
            eBusinessSysLaborCostDetails.setEntryTotal(sysLaborCostDetailsEntity.geteBusinessEntryTotal()==null?0:sysLaborCostDetailsEntity.geteBusinessEntryTotal());
            eBusinessSysLaborCostDetails.setHeadcountTotal(sysLaborCostDetailsEntity.geteBusinessHeadcountTotal()==null?0:sysLaborCostDetailsEntity.geteBusinessHeadcountTotal());
            eBusinessSysLaborCostDetails.setLaborCostId(laborCostId);
            eBusinessSysLaborCostDetails.setLaborCostTotal(sysLaborCostDetailsEntity.geteBusinessLaborCost()==null?0:sysLaborCostDetailsEntity.geteBusinessLaborCost());
            eBusinessSysLaborCostDetails.setPayPeopleTotal(sysLaborCostDetailsEntity.geteBusinessPayPeopleTotal()==null?0:sysLaborCostDetailsEntity.geteBusinessPayPeopleTotal());
            eBusinessSysLaborCostDetails.setBeginMonthPeople(sysLaborCostDetailsEntity.geteBusinessBeginMonthPeople()==null?0:sysLaborCostDetailsEntity.geteBusinessBeginMonthPeople());
            eBusinessSysLaborCostDetails.setMonthDeploy(sysLaborCostDetailsEntity.geteBusinessMonthDeploy()==null?0:sysLaborCostDetailsEntity.geteBusinessMonthDeploy());
            eBusinessSysLaborCostDetails.setMonthTransfer(sysLaborCostDetailsEntity.geteBusinessMonthTransfer()==null?0:sysLaborCostDetailsEntity.geteBusinessMonthTransfer());
            int eBusinessCount = sysLaborCostBusinessService.addSysLaborCostDetails(eBusinessSysLaborCostDetails);

            SysLaborCostDetails saleSysLaborCostDetails = new SysLaborCostDetails();
            saleSysLaborCostDetails.setDemissionTotal(sysLaborCostDetailsEntity.getSaleDemissionTotal()==null?0:sysLaborCostDetailsEntity.getSaleDemissionTotal());
            saleSysLaborCostDetails.setDepartmentType(3);
            saleSysLaborCostDetails.setEmployeeTotal(sysLaborCostDetailsEntity.getSaleEmployeeTotal()==null?0:sysLaborCostDetailsEntity.getSaleEmployeeTotal());
            saleSysLaborCostDetails.setEntryTotal(sysLaborCostDetailsEntity.getSaleEntryTotal()==null?0:sysLaborCostDetailsEntity.getSaleEntryTotal());
            saleSysLaborCostDetails.setHeadcountTotal(sysLaborCostDetailsEntity.getSaleHeadcountTotal()==null?0:sysLaborCostDetailsEntity.getSaleHeadcountTotal());
            saleSysLaborCostDetails.setLaborCostId(laborCostId);
            saleSysLaborCostDetails.setLaborCostTotal(sysLaborCostDetailsEntity.getSaleLaborCost()==null?0:sysLaborCostDetailsEntity.getSaleLaborCost());
            saleSysLaborCostDetails.setPayPeopleTotal(sysLaborCostDetailsEntity.getSalePayPeopleTotal()==null?0:sysLaborCostDetailsEntity.getSalePayPeopleTotal());
            saleSysLaborCostDetails.setBeginMonthPeople(sysLaborCostDetailsEntity.getSaleBeginMonthPeople()==null?0:sysLaborCostDetailsEntity.getSaleBeginMonthPeople());
            saleSysLaborCostDetails.setMonthDeploy(sysLaborCostDetailsEntity.getSaleMonthDeploy()==null?0:sysLaborCostDetailsEntity.getSaleMonthDeploy());
            saleSysLaborCostDetails.setMonthTransfer(sysLaborCostDetailsEntity.getSaleMonthTransfer()==null?0:sysLaborCostDetailsEntity.getSaleMonthTransfer());
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
    public Map<String, Object> updateSysLaborCostInfo(SysLaborCostDetailsAddEntity sysLaborCostDetailsEntity) {
        SysLaborCost sysLaborCost = sysLaborCostBusinessService.findRecordByYearAndMonthAndCompanyId(sysLaborCostDetailsEntity.getCompanyId(), sysLaborCostDetailsEntity.getYear(), sysLaborCostDetailsEntity.getMonth());
        if (null != sysLaborCost) {
            if (!sysLaborCostDetailsEntity.getLaborCostId().equals(sysLaborCost.getLaborCostId())) {
                return R.error(500, "更新失败，系统已存在" + sysLaborCostDetailsEntity.getYear() + "年" + sysLaborCostDetailsEntity.getMonth() + "月的记录，不能出现重复数据！");
            }
        }
        sysLaborCost = sysLaborCostBusinessService.findSysLaborCostByLaborCostId(sysLaborCostDetailsEntity.getLaborCostId());
        if (sysLaborCost != null) {
            sysLaborCost.setMonth(sysLaborCostDetailsEntity.getMonth());
            sysLaborCost.setYear(sysLaborCostDetailsEntity.getYear());
            sysLaborCost.setCompanyId(sysLaborCostDetailsEntity.getCompanyId());
            sysLaborCostBusinessService.updateSysLaborCostInfo(sysLaborCost);

            long laborCostIdUpdate = Long.valueOf(sysLaborCostDetailsEntity.getLaborCostId());
            SysLaborCostDetails propertySysLaborCostDetails = new SysLaborCostDetails();
            propertySysLaborCostDetails.setDemissionTotal(sysLaborCostDetailsEntity.getPropertyDemissionTotal());
            propertySysLaborCostDetails.setEmployeeTotal(sysLaborCostDetailsEntity.getPropertyEmployeeTotal());
            propertySysLaborCostDetails.setEntryTotal(sysLaborCostDetailsEntity.getPropertyEntryTotal());
            propertySysLaborCostDetails.setDepartmentType(1);
            propertySysLaborCostDetails.setHeadcountTotal(sysLaborCostDetailsEntity.getPropertyHeadcountTotal());
            propertySysLaborCostDetails.setLaborCostId(laborCostIdUpdate);
            propertySysLaborCostDetails.setLaborCostTotal(sysLaborCostDetailsEntity.getPropertyLaborCost());
            propertySysLaborCostDetails.setPayPeopleTotal(sysLaborCostDetailsEntity.getPropertyPayPeopleTotal());
            propertySysLaborCostDetails.setBeginMonthPeople(sysLaborCostDetailsEntity.getPropertyBeginMonthPeople());
            propertySysLaborCostDetails.setMonthDeploy(sysLaborCostDetailsEntity.getPropertyMonthDeploy());
            propertySysLaborCostDetails.setMonthTransfer(sysLaborCostDetailsEntity.getPropertyMonthTransfer());
            int propertyCount = sysLaborCostBusinessService.updateSysLaborCostDetailsInfo(propertySysLaborCostDetails);

            SysLaborCostDetails eBusinessSysLaborCostDetails = new SysLaborCostDetails();
            eBusinessSysLaborCostDetails.setDemissionTotal(sysLaborCostDetailsEntity.geteBusinessDemissionTotal()==null?0:sysLaborCostDetailsEntity.geteBusinessDemissionTotal());
            eBusinessSysLaborCostDetails.setEmployeeTotal(sysLaborCostDetailsEntity.geteBusinessEmployeeTotal()==null?0:sysLaborCostDetailsEntity.geteBusinessEmployeeTotal());
            eBusinessSysLaborCostDetails.setEntryTotal(sysLaborCostDetailsEntity.geteBusinessEntryTotal()==null?0:sysLaborCostDetailsEntity.geteBusinessEntryTotal());
            eBusinessSysLaborCostDetails.setDepartmentType(2);
            eBusinessSysLaborCostDetails.setHeadcountTotal(sysLaborCostDetailsEntity.geteBusinessHeadcountTotal()==null?0:sysLaborCostDetailsEntity.geteBusinessHeadcountTotal());
            eBusinessSysLaborCostDetails.setLaborCostId(laborCostIdUpdate);
            eBusinessSysLaborCostDetails.setLaborCostTotal(sysLaborCostDetailsEntity.geteBusinessLaborCost()==null?0:sysLaborCostDetailsEntity.geteBusinessLaborCost());
            eBusinessSysLaborCostDetails.setPayPeopleTotal(sysLaborCostDetailsEntity.geteBusinessPayPeopleTotal()==null?0:sysLaborCostDetailsEntity.geteBusinessPayPeopleTotal());
            eBusinessSysLaborCostDetails.setBeginMonthPeople(sysLaborCostDetailsEntity.geteBusinessBeginMonthPeople()==null?0:sysLaborCostDetailsEntity.geteBusinessBeginMonthPeople());
            eBusinessSysLaborCostDetails.setMonthDeploy(sysLaborCostDetailsEntity.geteBusinessMonthDeploy()==null?0:sysLaborCostDetailsEntity.geteBusinessMonthDeploy());
            eBusinessSysLaborCostDetails.setMonthTransfer(sysLaborCostDetailsEntity.geteBusinessMonthTransfer()==null?0:sysLaborCostDetailsEntity.geteBusinessMonthTransfer());
            int eBusinessCount = sysLaborCostBusinessService.updateSysLaborCostDetailsInfo(eBusinessSysLaborCostDetails);

            SysLaborCostDetails saleSysLaborCostDetails = new SysLaborCostDetails();
            saleSysLaborCostDetails.setDemissionTotal(sysLaborCostDetailsEntity.getSaleDemissionTotal()==null?0:sysLaborCostDetailsEntity.getSaleDemissionTotal());
            saleSysLaborCostDetails.setEmployeeTotal(sysLaborCostDetailsEntity.getSaleEmployeeTotal()==null?0:sysLaborCostDetailsEntity.getSaleEmployeeTotal());
            saleSysLaborCostDetails.setEntryTotal(sysLaborCostDetailsEntity.getSaleEntryTotal()==null?0:sysLaborCostDetailsEntity.getSaleEntryTotal());
            saleSysLaborCostDetails.setDepartmentType(3);
            saleSysLaborCostDetails.setHeadcountTotal(sysLaborCostDetailsEntity.getSaleHeadcountTotal()==null?0:sysLaborCostDetailsEntity.getSaleHeadcountTotal());
            saleSysLaborCostDetails.setLaborCostId(laborCostIdUpdate);
            saleSysLaborCostDetails.setLaborCostTotal(sysLaborCostDetailsEntity.getSaleLaborCost()==null?0:sysLaborCostDetailsEntity.getSaleLaborCost());
            saleSysLaborCostDetails.setPayPeopleTotal(sysLaborCostDetailsEntity.getSalePayPeopleTotal()==null?0:sysLaborCostDetailsEntity.getSalePayPeopleTotal());
            saleSysLaborCostDetails.setBeginMonthPeople(sysLaborCostDetailsEntity.getSaleBeginMonthPeople()==null?0:sysLaborCostDetailsEntity.getSaleBeginMonthPeople());
            saleSysLaborCostDetails.setMonthDeploy(sysLaborCostDetailsEntity.getSaleMonthDeploy()==null?0:sysLaborCostDetailsEntity.getSaleMonthDeploy());
            saleSysLaborCostDetails.setMonthTransfer(sysLaborCostDetailsEntity.getSaleMonthTransfer()==null?0:sysLaborCostDetailsEntity.getSaleMonthTransfer());
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
            SysLaborCost sysLaborCost = sysLaborCostBusinessService.findSysLaborCostByLaborCostId(laborCostId);
            if (null != sysLaborCost) {
                //SysUpdateDataRules sysUpdateDataRules=sysUpdateDataRulesBusinessService.findSysUpdateDataRules();
                //boolean updateToRedis=SysUtil.updateToRedis(sysUpdateDataRules.getDay(),sysLaborCost.getYear(),sysLaborCost.getMonth());

                //将统计信息存储到redis缓存中
                setDateToRedis();

            }
            return R.ok(200, "删除成功！");
        } else {
            return R.error(500, "删除失败，请联系管理员！");
        }

    }

    /**
     * 将统计信息存储到redis缓存中
     */
    @Override
    public void setDateToRedis() {
        Map<String, Object> map = new HashMap<String, Object>();
        int month = 0;
        int year = 0;
        SysLaborCostDetailsEntity sysLaborCostDetails = null;
        List<SysLaborCostDetails> sysLaborCostDepartmentList = null;
        List<Long> sysUserCompanyIds = null;
        SysUpdateDataRules sysUpdateDataRules=sysUpdateDataRulesBusinessService.findSysUpdateDataRules();
        //获取需要查询的年份和月份
        Map<String,Integer> yearAndMonthMap=SysUtil.getYearAndMonth(sysUpdateDataRules.getDay());
        map.put("sysUserCompanyIds", null);
        map.put("year", yearAndMonthMap.get("year"));
        map.put("month", yearAndMonthMap.get("month"));
        sysLaborCostDetails = sysLaborCostBusinessService.getSysLaborCostTotal(map);
        if (null != sysLaborCostDetails) {
            Double laborCostTotal = sysLaborCostDetails.getLaborCostTotal();
            sysLaborCostDetails.setAverageLaborCost(UtilHelper.DecimalFormatForDouble(UtilHelper.DecimalFormatDoubleNumber(laborCostTotal, sysLaborCostDetails.getPayPeopleTotal())));
            //(成本构成)获取和计算物业常态、电商、销配每月的人工成本支出占公司人工成本支出的百分比
            sysLaborCostDepartmentList = sysLaborCostBusinessService.getSysLaborCostDepartmentTotal(map);
            if (null != sysLaborCostDepartmentList) {
                for (SysLaborCostDetails sysLaborCostDepartment : sysLaborCostDepartmentList) {
                    if (sysLaborCostDepartment.getDepartmentType() == 1) {
                        sysLaborCostDetails.setPropertyLaborCostScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysLaborCostDepartment.getLaborCostTotal(), laborCostTotal)));
                        sysLaborCostDetails.setPropertyLaborCost(sysLaborCostDepartment.getLaborCostTotal());
                    } else if (sysLaborCostDepartment.getDepartmentType() == 2) {
                        sysLaborCostDetails.seteBusinessScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysLaborCostDepartment.getLaborCostTotal(), laborCostTotal)));
                        sysLaborCostDetails.seteBusinessCost(sysLaborCostDepartment.getLaborCostTotal());
                    } else if (sysLaborCostDepartment.getDepartmentType() == 3) {
                        sysLaborCostDetails.setSaleLaborCostScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysLaborCostDepartment.getLaborCostTotal(), laborCostTotal)));
                        sysLaborCostDetails.setSaleLaborCost(sysLaborCostDepartment.getLaborCostTotal());
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
            SysBudgetDetails sysBudget = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(yearAndMonthMap.get("year"), yearAndMonthMap.get("month"), sysUserCompanyIds);
            //根据年份跟月份查找系统预算记录
            SysBudgetDetails sysBudgetLastMonth = null;

            if (yearAndMonthMap.get("month") == 1) {
                month = 12;
                year = yearAndMonthMap.get("year") - 1;
            } else {
                month = yearAndMonthMap.get("month") - 1;
                year = yearAndMonthMap.get("year");
            }
            sysBudgetLastMonth = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(year, month, sysUserCompanyIds);
            if (null != sysBudgetLastMonth && sysBudgetLastMonth.getRealProfits() != null) {
                realExpensesLastMonthTotal = sysBudgetLastMonth.getRealExpensesTotal();
                personnelCostLastMonth = sysBudgetLastMonth.getPersonnelCost();
            }
            //流失率
            sysLaborCostDetails.setSysDemissionScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysLaborCostDetails.getDemissionTotal(),sysLaborCostDetails.getBeginMonthPeople()+sysLaborCostDetails.getEntryTotal()+sysLaborCostDetails.getMonthDeploy())));
            //缺编率
            sysLaborCostDetails.setSysEmployeeScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysLaborCostDetails.getHeadcountTotal()-sysLaborCostDetails.getEmployeeTotal(), sysLaborCostDetails.getHeadcountTotal())));
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
