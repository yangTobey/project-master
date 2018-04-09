package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.bean.master.SysBudgetDetails;
import com.spring.boot.bean.master.SysChargeDetails;
import com.spring.boot.bean.master.entity.SysReceivableAccountsOwnerEntity;
import com.spring.boot.service.SysFinancialService;
import com.spring.boot.service.web.SysAccountsReceivableBusinessService;
import com.spring.boot.service.web.SysBudgetDetailsBusinessService;
import com.spring.boot.service.web.SysChargeBusinessService;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.util.R;
import com.spring.boot.util.SysUtil;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysFinancialServiceImpl implements SysFinancialService {
    private static final Logger logger = Logger.getLogger(SysFinancialServiceImpl.class);
    @Autowired
    private SysChargeBusinessService sysChargeBusinessService;
    @Autowired
    private SysAccountsReceivableBusinessService sysAccountsReceivableBusinessService;
    @Autowired
    private SysBudgetDetailsBusinessService sysBudgetDetailsBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Map<String, Object> sysChargeDetails(Long companyId) {
        List<Long> sysUserCompanyIds = null;
        try {
            if (companyId == 0) {
                //获取用户权限下可操作的小区信息
                sysUserCompanyIds = SysUtil.getSysUserCompany();
            } else {
                sysUserCompanyIds = new ArrayList<Long>();
                sysUserCompanyIds.add(companyId);
            }
            //也可以封装成map传值
            SysChargeDetails sysChargeDetails = sysChargeBusinessService.sysChargeDetails(sysUserCompanyIds);
            if (null != sysChargeDetails) {
                if (null != sysChargeDetails.getChargeMoneyNow() && null != sysChargeDetails.getChargeMoney()) {
                    sysChargeDetails.setChargeMoneyScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysChargeDetails.getChargeMoneyNow(), sysChargeDetails.getChargeMoney())));
                }
                if (null != sysChargeDetails.getChargeDebtReturn() && null != sysChargeDetails.getChargeDebt()) {
                    sysChargeDetails.setChargeDebtScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysChargeDetails.getChargeDebtReturn(), sysChargeDetails.getChargeDebt())));
                }
            }
            return R.ok().putData(200, sysChargeDetails, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("收费情况报表统计详细信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> findSysChargeDetailsById(Long chargeId) {
        SysChargeDetails sysChargeDetails = sysChargeBusinessService.findSysChargeDetailsById(chargeId);
        return R.ok().putData(200, sysChargeDetails, "获取成功！");
    }

    @Override
    public Map<String, Object> addSysCharge(Long companyId, Double chargeMoney, Double chargeMoneyNow, Double chargeDebt, Double chargeDebtReturn) {
        SysChargeDetails sysChargeDetails = new SysChargeDetails();
        sysChargeDetails.setChargeDebt(chargeDebt);
        sysChargeDetails.setChargeDebtReturn(chargeDebtReturn);
        sysChargeDetails.setChargeMoney(chargeMoney);
        sysChargeDetails.setChargeMoneyNow(chargeMoneyNow);
        sysChargeDetails.setCompanyId(companyId);
        sysChargeDetails.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
        try {
            int count = sysChargeBusinessService.addSysCharge(sysChargeDetails);
            if (count > 0) {
                return R.ok(200, "新增成功！");
            } else {
                return R.error(500, "新增失败，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("新增收费信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> updateSysCharge(Long chargeId, Long companyId, Double chargeMoney, Double chargeMoneyNow, Double chargeDebt, Double chargeDebtReturn) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("chargeId", chargeId);
        map.put("chargeMoney", chargeMoney);
        map.put("chargeMoneyNow", chargeMoneyNow);
        map.put("chargeDebt", chargeDebt);
        map.put("chargeDebtReturn", chargeDebtReturn);
        map.put("companyId", companyId);
        try {
            int count = sysChargeBusinessService.updateSysCharge(map);
            if (count > 0) {
                return R.ok(200, "更新成功！");
            } else {
                return R.error(500, "更新失败，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("更新收费信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> sysAccountsReceivableAnalysis(Long companyId) {
        List<Long> sysUserCompanyIds = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        int thisYear = UtilHelper.getYear();
        int thisMonth = UtilHelper.getMonth();
        map.put("year", thisYear);
        map.put("month", thisMonth);
        try {
            if (companyId == 0) {
                //获取用户权限下可操作的小区信息
                sysUserCompanyIds = SysUtil.getSysUserCompany();
            } else {
                sysUserCompanyIds = new ArrayList<Long>();
                sysUserCompanyIds.add(companyId);
            }
            map.put("sysUserCompanyIds", sysUserCompanyIds);
            //也可以封装成map传值
            SysAccountsReceivable sysAccountsReceivable = sysAccountsReceivableBusinessService.sysAccountsReceivableAnalysis(map);
            if (null != sysAccountsReceivable) {
                sysAccountsReceivable.setCouponScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompleteCoupon(), sysAccountsReceivable.getReceivableCoupon())));
                sysAccountsReceivable.setVacancyScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompleteVacancy(), sysAccountsReceivable.getReceivableVacancy())));
                sysAccountsReceivable.setSubsidyScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompleteSubsidy(), sysAccountsReceivable.getReceivableSubsidy())));
                sysAccountsReceivable.setSalesScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompleteSales(), sysAccountsReceivable.getReceivableSales())));
                sysAccountsReceivable.setOpenScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompleteOpen(), sysAccountsReceivable.getReceivableOpen())));
                sysAccountsReceivable.setPropertySubsidyScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompletePropertySubsidy(), sysAccountsReceivable.getReceivablePropertySubsidy())));
                sysAccountsReceivable.setHouseOtherScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompleteHouseOther(), sysAccountsReceivable.getReceivableHouseOther())));
            }
            List<SysAccountsReceivable> sysAccountsReceivableAnalysisList = sysAccountsReceivableBusinessService.sysAccountsReceivableAnalysisForMonth(map);
            //获取所在年份对应的所有数据的月份
            //String months=sysAccountsReceivableBusinessService.sysAccountsReceivableMonths(map);
            SysReceivableAccountsOwnerEntity sysReceivableAccountsOwnerEntity = null;
            //当年月份组装（非数据库查询，避免数据库数据库因忘记添加数据而照成月份丢失）
            //List<Integer> monthList=new ArrayList<Integer>();
            String monthsInfo = "";
            for (int i = 1; i <= thisMonth; i++) {
                //月份组装
                //monthList.add(i);
                if (i == thisMonth) {
                    monthsInfo += i;
                } else {
                    monthsInfo += i + ",";
                }
            }
            sysReceivableAccountsOwnerEntity = new SysReceivableAccountsOwnerEntity();
            Map<Integer, Object> receivableMap = null;
            Map<Integer, Object> completeMap = null;
            if (null != sysAccountsReceivableAnalysisList) {
                receivableMap = new HashMap<Integer, Object>();
                completeMap = new HashMap<Integer, Object>();
                for (SysAccountsReceivable sysAccountsReceivableForMonth : sysAccountsReceivableAnalysisList) {
                    Double receivableAccountsOwner = getNum(sysAccountsReceivableForMonth.getReceivableAccountsOwner());
                    Double completeAccountsOwner = getNum(sysAccountsReceivableForMonth.getCompleteAccountsOwner());
                    Integer month = sysAccountsReceivableForMonth.getMonth();
                    //sysReceivableAccountsOwnerEntity.setMonth(month);
                    switch (month) {
                        case 1:
                            receivableMap.put(1, receivableAccountsOwner);
                            completeMap.put(1, completeAccountsOwner);
                            //sysReceivableAccountsOwnerEntity.setCompleteAccountsJan(completeAccountsOwner);
                            //sysReceivableAccountsOwnerEntity.setReceivableAccountsJan(receivableAccountsOwner);
                            break;
                        case 2:
                            receivableMap.put(2, receivableAccountsOwner);
                            completeMap.put(2, completeAccountsOwner);
                            //sysReceivableAccountsOwnerEntity.setReceivableAccountsFeb(receivableAccountsOwner);
                            //sysReceivableAccountsOwnerEntity.setCompleteAccountsFeb(completeAccountsOwner);
                            break;
                        case 3:
                            receivableMap.put(3, receivableAccountsOwner);
                            completeMap.put(3, completeAccountsOwner);
                            // sysReceivableAccountsOwnerEntity.setReceivableAccountsMar(receivableAccountsOwner);
                            // sysReceivableAccountsOwnerEntity.setCompleteAccountsMar(completeAccountsOwner);
                            break;
                        case 4:
                            receivableMap.put(4, receivableAccountsOwner);
                            completeMap.put(4, completeAccountsOwner);
                            //sysReceivableAccountsOwnerEntity.setReceivableAccountsApr(receivableAccountsOwner);
                            //sysReceivableAccountsOwnerEntity.setCompleteAccountsApr(completeAccountsOwner);
                            break;
                        case 5:
                            receivableMap.put(5, receivableAccountsOwner);
                            completeMap.put(5, completeAccountsOwner);
                            //sysReceivableAccountsOwnerEntity.setReceivableAccountsMay(receivableAccountsOwner);
                            // sysReceivableAccountsOwnerEntity.setCompleteAccountsMay(completeAccountsOwner);
                            break;
                        case 6:
                            receivableMap.put(6, receivableAccountsOwner);
                            completeMap.put(6, completeAccountsOwner);
                            // sysReceivableAccountsOwnerEntity.setReceivableAccountsJune(receivableAccountsOwner);
                            // sysReceivableAccountsOwnerEntity.setCompleteAccountsJune(completeAccountsOwner);
                            break;
                        case 7:
                            receivableMap.put(7, receivableAccountsOwner);
                            completeMap.put(7, completeAccountsOwner);
                            //sysReceivableAccountsOwnerEntity.setReceivableAccountsJuly(receivableAccountsOwner);
                            // sysReceivableAccountsOwnerEntity.setCompleteAccountsJuly(completeAccountsOwner);
                            break;
                        case 8:
                            receivableMap.put(8, receivableAccountsOwner);
                            completeMap.put(8, completeAccountsOwner);
                            //sysReceivableAccountsOwnerEntity.setReceivableAccountsAug(receivableAccountsOwner);
                            //sysReceivableAccountsOwnerEntity.setCompleteAccountsAug(completeAccountsOwner);
                            break;
                        case 9:
                            receivableMap.put(9, receivableAccountsOwner);
                            completeMap.put(9, completeAccountsOwner);
                            //sysReceivableAccountsOwnerEntity.setReceivableAccountsSept(receivableAccountsOwner);
                            // sysReceivableAccountsOwnerEntity.setCompleteAccountsSept(completeAccountsOwner);
                            break;
                        case 10:
                            receivableMap.put(10, receivableAccountsOwner);
                            completeMap.put(10, completeAccountsOwner);
                            //sysReceivableAccountsOwnerEntity.setReceivableAccountsOct(receivableAccountsOwner);
                            //sysReceivableAccountsOwnerEntity.setCompleteAccountsOct(completeAccountsOwner);
                            break;
                        case 11:
                            receivableMap.put(11, receivableAccountsOwner);
                            completeMap.put(11, completeAccountsOwner);
                            //sysReceivableAccountsOwnerEntity.setReceivableAccountsNov(receivableAccountsOwner);
                            //sysReceivableAccountsOwnerEntity.setCompleteAccountsNov(completeAccountsOwner);
                            break;
                        case 12:
                            receivableMap.put(12, receivableAccountsOwner);
                            completeMap.put(12, completeAccountsOwner);
                            // sysReceivableAccountsOwnerEntity.setReceivableAccountsDec(receivableAccountsOwner);
                            //sysReceivableAccountsOwnerEntity.setCompleteAccountsDec(completeAccountsOwner);
                            break;
                    }
                }
            }
            sysReceivableAccountsOwnerEntity.setMonthsInfo(monthsInfo);
            sysReceivableAccountsOwnerEntity.setReceivableAccounts(receivableMap);
            sysReceivableAccountsOwnerEntity.setCompleteAccounts(completeMap);
            resultMap.put("sysAccountsReceivable", sysAccountsReceivable);
            resultMap.put("sysAccountsReceivableMonth", sysReceivableAccountsOwnerEntity);
            return R.ok().putData(200, resultMap, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("收费情况报表统计详细信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    /**
     * 讲空置过滤，把值为空的赋值为0，不为空的原路返回
     *
     * @param num
     * @return
     */
    public Double getNum(Double num) {
        return (num == null) ? 0.00 : num;
    }

    @Override
    public Map<String, Object> addSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable) {
        try {
            //根据年份跟月份查找系统记录
            SysAccountsReceivable sysArDetails = sysAccountsReceivableBusinessService.findRecordByYearAndMonth(sysAccountsReceivable.getYear(), sysAccountsReceivable.getMonth());
            //如果系统已存在年份和月份的数据，不给予新增操作
            if (null != sysArDetails) {
                return R.error(500, "新增失败，系统已存在" + sysAccountsReceivable.getYear() + "年" + sysAccountsReceivable.getMonth() + "月的数据，不能重复添加！！");
            }
            int count = sysAccountsReceivableBusinessService.addSysAccountsReceivable(sysAccountsReceivable);
            if (count > 0) {
                return R.ok(200, "新增成功！");
            } else {
                return R.error(500, "新增失败，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("新增信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> updateSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable) {
        try {
            //根据年份跟月份查找系统记录
            SysAccountsReceivable sysArDetails = sysAccountsReceivableBusinessService.findRecordByYearAndMonth(sysAccountsReceivable.getYear(), sysAccountsReceivable.getMonth());
            if (null != sysArDetails) {
                //如果系统已存在年份和月份的数据，不给予更新操作
                if (sysArDetails.getAccountsId() != sysAccountsReceivable.getAccountsId()) {
                    return R.error(500, "更新失败，系统已存在" + sysAccountsReceivable.getYear() + "年" + sysAccountsReceivable.getMonth() + "月的数据，不能重复添加！！");
                }
            }
            int count = sysAccountsReceivableBusinessService.updateSysAccountsReceivable(sysAccountsReceivable);
            if (count > 0) {
                return R.ok(200, "更新成功！");
            } else {
                return R.error(500, "更新失败，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("更新信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> sysBudgetDetailsAnalysis(Long companyId) {
        List<Long> sysUserCompanyIds = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        int thisYear = UtilHelper.getYear();
        int thisMonth = UtilHelper.getMonth();
        map.put("year", thisYear);
        map.put("month", thisMonth);
        try {
            if (companyId == 0) {
                //获取用户权限下可操作的小区信息
                sysUserCompanyIds = SysUtil.getSysUserCompany();
            } else {
                sysUserCompanyIds = new ArrayList<Long>();
                sysUserCompanyIds.add(companyId);
            }
            map.put("sysUserCompanyIds", sysUserCompanyIds);
            //也可以封装成map传值
            SysBudgetDetails sysBudgetDetails = sysBudgetDetailsBusinessService.sysBudgetDetailsAnalysis(map);
            List<SysBudgetDetails> sysBudgetDetailsList = sysBudgetDetailsBusinessService.sysBudgetDetailsIncomeForMonth(map);
            //当年月份组装（非数据库查询，避免数据库数据库因忘记添加数据而照成月份丢失）
            String monthsInfo = "";
            for (int i = 1; i <= thisMonth; i++) {
                //月份组装
                if (i == thisMonth) {
                    monthsInfo += i;
                } else {
                    monthsInfo += i + ",";
                }
            }
            //实际收入
            Map<Integer, Object> realIncomeMap = null;
            //预算收入
            Map<Integer, Object> budgetIncomeMap = null;
            //实际支出
            Map<Integer, Object> realExpensesTotalMap = null;
            //预算支出
            Map<Integer, Object> budgetExpensesMap = null;
            //实际利润
            Map<Integer, Object> realProfitsMap = null;
            //实际利润环比
            Map<Integer, Object> realProfitsScaleMap = null;
            if (null != sysBudgetDetailsList) {
                //获取上年12月份数据
                SysBudgetDetails lastYearDetailis =null;
                Double lastYearRealProfits = 0.00;
                if(null!=lastYearDetailis&&lastYearDetailis.getRealProfits()!=null){
                    lastYearRealProfits = getNum(lastYearDetailis.getRealProfits());
                }
                realIncomeMap = new HashMap<Integer, Object>();
                budgetIncomeMap = new HashMap<Integer, Object>();
                realExpensesTotalMap = new HashMap<Integer, Object>();
                budgetExpensesMap = new HashMap<Integer, Object>();
                realProfitsMap = new HashMap<Integer, Object>();
                realProfitsScaleMap = new HashMap<Integer, Object>();


                for (SysBudgetDetails sysBudgetDetailsMonth : sysBudgetDetailsList) {
                    Double realIncome = getNum(sysBudgetDetailsMonth.getRealIncome());
                    Double budgetIncome = getNum(sysBudgetDetailsMonth.getBudgetIncome());
                    Double realExpensesTotal = getNum(sysBudgetDetailsMonth.getRealExpensesTotal());
                    Double budgetExpenses = getNum(sysBudgetDetailsMonth.getBudgetExpenses());
                    Double realProfits = getNum(sysBudgetDetailsMonth.getRealProfits());
                    Integer month = sysBudgetDetailsMonth.getMonth();
                    if(null!=month){
                        realIncomeMap.put(month, realIncome);
                        budgetIncomeMap.put(month, budgetIncome);
                        realExpensesTotalMap.put(month,realExpensesTotal);
                        budgetExpensesMap.put(month,budgetExpenses);
                        realProfitsMap.put(month,realProfits);

                        if(month==1){
                            lastYearDetailis = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(thisYear-1,12,sysUserCompanyIds);
                            realProfitsScaleMap.put(month,UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber((realProfits-lastYearRealProfits),lastYearRealProfits)));
                        }else{
                            lastYearDetailis = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(thisYear,month-1,sysUserCompanyIds);
                            realProfitsScaleMap.put(month,UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber((realProfits-lastYearRealProfits),lastYearRealProfits)));
                        }
                    }

                    /*switch (month) {
                        case 1:
                            realIncomeMap.put(1, realIncome);
                            budgetIncomeMap.put(1, budgetIncome);
                            realExpensesTotalMap.put(1,realExpensesTotal);
                            budgetExpensesMap.put(1,budgetExpenses);
                            realProfitsMap.put(1,realProfits);
                            realProfitsScaleMap.put(1,UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber((realProfits-lastYearRealProfits),lastYearRealProfits)));
                            break;
                        case 2:
                            realIncomeMap.put(2, realIncome);
                            budgetIncomeMap.put(2, budgetIncome);
                            realExpensesTotalMap.put(2,realExpensesTotal);
                            budgetExpensesMap.put(2,budgetExpenses);
                            break;
                        case 3:
                            realIncomeMap.put(3, realIncome);
                            budgetIncomeMap.put(3, budgetIncome);
                            realExpensesTotalMap.put(3,realExpensesTotal);
                            budgetExpensesMap.put(3,budgetExpenses);
                            break;
                        case 4:
                            realIncomeMap.put(4, realIncome);
                            budgetIncomeMap.put(4, budgetIncome);
                            realExpensesTotalMap.put(4,realExpensesTotal);
                            budgetExpensesMap.put(4,budgetExpenses);
                            break;
                        case 5:
                            realIncomeMap.put(5, realIncome);
                            budgetIncomeMap.put(5, budgetIncome);
                            realExpensesTotalMap.put(5,realExpensesTotal);
                            budgetExpensesMap.put(5,budgetExpenses);
                            break;
                        case 6:
                            realIncomeMap.put(6, realIncome);
                            budgetIncomeMap.put(6, budgetIncome);
                            realExpensesTotalMap.put(6,realExpensesTotal);
                            budgetExpensesMap.put(6,budgetExpenses);
                            break;
                        case 7:
                            realIncomeMap.put(7, realIncome);
                            budgetIncomeMap.put(7, budgetIncome);
                            realExpensesTotalMap.put(7,realExpensesTotal);
                            budgetExpensesMap.put(7,budgetExpenses);
                            break;
                        case 8:
                            realIncomeMap.put(8, realIncome);
                            budgetIncomeMap.put(8, budgetIncome);
                            realExpensesTotalMap.put(8,realExpensesTotal);
                            budgetExpensesMap.put(8,budgetExpenses);
                            break;
                        case 9:
                            realIncomeMap.put(9, realIncome);
                            budgetIncomeMap.put(9, budgetIncome);
                            realExpensesTotalMap.put(9,realExpensesTotal);
                            budgetExpensesMap.put(9,budgetExpenses);
                            break;
                        case 10:
                            realIncomeMap.put(10, realIncome);
                            budgetIncomeMap.put(10, budgetIncome);
                            realExpensesTotalMap.put(10,realExpensesTotal);
                            budgetExpensesMap.put(10,budgetExpenses);
                            break;
                        case 11:
                            realIncomeMap.put(11, realIncome);
                            budgetIncomeMap.put(11, budgetIncome);
                            realExpensesTotalMap.put(21,realExpensesTotal);
                            budgetExpensesMap.put(11,budgetExpenses);
                            break;
                        case 12:
                            realIncomeMap.put(12, realIncome);
                            budgetIncomeMap.put(12, budgetIncome);
                            realExpensesTotalMap.put(12,realExpensesTotal);
                            budgetExpensesMap.put(12,budgetExpenses);
                            break;
                    }*/
                }
            }
            sysBudgetDetails.setMonthsInfo(monthsInfo);
            sysBudgetDetails.setRealIncomeMap(realIncomeMap);
            sysBudgetDetails.setBudgetIncomeMap(budgetIncomeMap);
            sysBudgetDetails.setRealExpensesTotalMap(realExpensesTotalMap);
            sysBudgetDetails.setBudgetExpensesMap(budgetExpensesMap);
            sysBudgetDetails.setRealProfitsMap(realProfitsMap);
            sysBudgetDetails.setRealProfitsScaleMap(realProfitsScaleMap);
            resultMap.put("incomeInfo", sysBudgetDetails);
            return R.ok().putData(200, resultMap, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("收费情况报表统计详细信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }
}
