package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.bean.master.SysBudgetDetails;
import com.spring.boot.bean.master.SysChargeDetails;
import com.spring.boot.bean.master.entity.SysReceivableAccountsOwnerEntity;
import com.spring.boot.service.SysFinancialService;
import com.spring.boot.service.web.SysAccountsReceivableBusinessService;
import com.spring.boot.service.web.SysBudgetDetailsBusinessService;
import com.spring.boot.service.web.SysChargeBusinessService;
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
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("year", UtilHelper.getYear());
            map.put("weekOfYear", UtilHelper.getWeekOfYear());
            map.put("sysUserCompanyIds", sysUserCompanyIds);
            //也可以封装成map传值
            SysChargeDetails sysChargeDetails = sysChargeBusinessService.sysChargeDetails(map);
            if (null != sysChargeDetails) {
                //查找全国时，将主键id设置为null
                if (companyId == 0) {
                    sysChargeDetails.setChargeId(null);
                }
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
        if(null!=sysChargeDetails){
            return R.ok().putData(200, sysChargeDetails, "获取成功！");
        }
        return R.error(500, "获取失败，不存在该记录！");
    }

    @Override
    public Map<String, Object> addSysCharge(Long companyId, Double chargeMoney, Double chargeMoneyNow, Double chargeDebt, Double chargeDebtReturn) {
        try {
            SysChargeDetails sysChargeDetails=sysChargeBusinessService.findSysChargeDetailsByWeekOfYear(UtilHelper.getYear(),UtilHelper.getWeekOfYear(),companyId);
            if(null!=sysChargeDetails){
                return R.error(500, "本周数据已经添加，不能再次添加，如有疑问，请联系系统管理员！");
            }else {
                sysChargeDetails = new SysChargeDetails();
                sysChargeDetails.setChargeDebt(chargeDebt);
                sysChargeDetails.setChargeDebtReturn(chargeDebtReturn);
                sysChargeDetails.setChargeMoney(chargeMoney);
                sysChargeDetails.setChargeMoneyNow(chargeMoneyNow);
                sysChargeDetails.setCompanyId(companyId);
                sysChargeDetails.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
                sysChargeDetails.setYear(UtilHelper.getYear());
                sysChargeDetails.setWeekOfYear(UtilHelper.getWeekOfYear());
            }
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
                //选择全国时，需要讲主键设置为空，避免前端错误操作
                if (companyId == 0) {
                    sysAccountsReceivable.setAccountsId(null);
                }
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
                    if(null!=month){
                        receivableMap.put(month, receivableAccountsOwner);
                        completeMap.put(month, completeAccountsOwner);
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

    @Override
    public Map<String, Object> sysAccountsReceivableList(Long companyId, Integer year, Integer limit, Integer offset) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
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
        map.put("limit", limit);
        map.put("offset", offset);
        try {
            resultMap.put("total", sysAccountsReceivableBusinessService.sysAccountsReceivableListTotal(map));
            resultMap.put("list", sysAccountsReceivableBusinessService.sysAccountsReceivableList(map));
            return R.ok().putData(200,resultMap,"获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("新增信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> findSysAccountsReceivableById(Long accountsId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("accountsId", accountsId);
        try {
            SysAccountsReceivable sysAccountsReceivable = sysAccountsReceivableBusinessService.findSysAccountsReceivableById(map);
            if(null!=sysAccountsReceivable){
                return R.ok().putData(200, sysAccountsReceivable, "根据id查找品质管理数据成功！");
            }else{
                return R.error(500, "获取数据失败，请联系系统管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("根据id查找数据失败：" + e.getMessage());
            return R.error(500, "根据id数据失败，服务器异常，请联系系统管理员！");
        }
    }

    /**
     * 将空置过滤，把值为空的赋值为0，不为空的原路返回
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
            SysAccountsReceivable sysArDetails = sysAccountsReceivableBusinessService.findRecordByYearAndMonth(sysAccountsReceivable.getYear(), sysAccountsReceivable.getMonth(),sysAccountsReceivable.getCompanyId());
            //如果系统已存在年份和月份的数据，不给予新增操作
            if (null != sysArDetails) {
                return R.error(500, "新增失败，系统已存在" + sysAccountsReceivable.getYear() + "年" + sysAccountsReceivable.getMonth() + "月的数据，不能重复添加！！");
            }
            sysAccountsReceivable.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
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
            SysAccountsReceivable sysArDetails = sysAccountsReceivableBusinessService.findRecordByYearAndMonth(sysAccountsReceivable.getYear(), sysAccountsReceivable.getMonth(),sysAccountsReceivable.getCompanyId());
            if (null != sysArDetails) {
                //如果系统已存在年份和月份的数据，不给予更新操作
                if (!sysArDetails.getAccountsId().equals( sysAccountsReceivable.getAccountsId())) {
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
    public Map<String, Object> deleteSysAccountsReceivable(Long accountsId) {
        try {
            int count = sysAccountsReceivableBusinessService.deleteSysAccountsReceivable(accountsId);
            if (count > 0) {
                return R.ok(200, "删除成功！");
            } else {
                return R.error(500, "删除失败，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除执行预算信息失败！" + e.getMessage());
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
            //mybatis涉及到in查询的，传参数时，可以一个或者多个，也可以封装成map进行传值
            SysBudgetDetails sysBudgetDetails = sysBudgetDetailsBusinessService.sysBudgetDetailsAnalysis(map);
            if(null!=sysBudgetDetails){
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

                        lastYearDetailis =null;
                        lastYearRealProfits = 0.00;
                        if(null!=month){
                            realIncomeMap.put(month, realIncome);
                            budgetIncomeMap.put(month, budgetIncome);
                            realExpensesTotalMap.put(month,realExpensesTotal);
                            budgetExpensesMap.put(month,budgetExpenses);
                            realProfitsMap.put(month,realProfits);
                            if(month==1){
                                lastYearDetailis = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(thisYear-1,12,sysUserCompanyIds);
                                if(null!=lastYearDetailis&&lastYearDetailis.getRealProfits()!=null){
                                    lastYearRealProfits = getNum(lastYearDetailis.getRealProfits());
                                }
                                realProfitsScaleMap.put(month,UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber((realProfits-lastYearRealProfits),lastYearRealProfits)));
                            }else{
                                lastYearDetailis = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(thisYear,month-1,sysUserCompanyIds);
                                if(null!=lastYearDetailis&&lastYearDetailis.getRealProfits()!=null){
                                    lastYearRealProfits = getNum(lastYearDetailis.getRealProfits());
                                }
                                realProfitsScaleMap.put(month,UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber((realProfits-lastYearRealProfits),lastYearRealProfits)));
                            }
                        }
                    }
                }
                //如果区域选择“全国”，讲返回的主键id设置为null
                if (companyId == 0) {
                    sysBudgetDetails.setBudgetId(null);
                }
                sysBudgetDetails.setMonthsInfo(monthsInfo);
                sysBudgetDetails.setRealIncomeMap(realIncomeMap);
                sysBudgetDetails.setBudgetIncomeMap(budgetIncomeMap);
                sysBudgetDetails.setRealExpensesTotalMap(realExpensesTotalMap);
                sysBudgetDetails.setBudgetExpensesMap(budgetExpensesMap);
                sysBudgetDetails.setRealProfitsMap(realProfitsMap);
                sysBudgetDetails.setRealProfitsScaleMap(realProfitsScaleMap);
            }

            resultMap.put("incomeInfo", sysBudgetDetails);
            return R.ok().putData(200, sysBudgetDetails, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("收费情况报表统计详细信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> sysBudgetDetailsList(Long companyId, Integer year,Integer limit,Integer offset) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
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
        map.put("limit", limit);
        map.put("offset", offset);
        try {
            resultMap.put("total", sysBudgetDetailsBusinessService.sysBudgetDetailsListTotal(map));
            resultMap.put("list", sysBudgetDetailsBusinessService.sysBudgetDetailsList(map));
            return R.ok().putData(200,resultMap,"获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("新增信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> findSysBudgetDetailsById(Long budgetId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("budgetId", budgetId);
        try {
            SysBudgetDetails sysBudgetDetails = sysBudgetDetailsBusinessService.findSysBudgetDetailsById(map);
            return R.ok().putData(200, sysBudgetDetails, "根据id查找品质管理数据成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("根据id查找品质管理数据失败：" + e.getMessage());
            return R.error(500, "根据id查找品质管理数据失败，服务器异常，请联系系统管理员！");
        }
    }

    @Override
    public Map<String, Object> addSysBudgetDetails(SysBudgetDetails sysBudgetDetails) {
        try {
            //根据年份跟月份查找系统记录
            SysBudgetDetails sysBudget = sysBudgetDetailsBusinessService.findRecordByYearAndMonth(sysBudgetDetails.getYear(), sysBudgetDetails.getMonth(),sysBudgetDetails.getCompanyId());
            //如果系统已存在年份和月份的数据，不给予新增操作
            if (null != sysBudget) {
                return R.error(500, "新增失败，系统已存在" + sysBudgetDetails.getYear() + "年" + sysBudgetDetails.getMonth() + "月的数据，不能重复添加！！");
            }
            sysBudgetDetails.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
            int count = sysBudgetDetailsBusinessService.addSysBudgetDetails(sysBudgetDetails);
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
    public Map<String, Object> updateSysBudgetDetails(SysBudgetDetails sysBudgetDetails) {
        try {
            //根据年份跟月份查找系统记录
            SysBudgetDetails sysBudget = sysBudgetDetailsBusinessService.findRecordByYearAndMonth(sysBudgetDetails.getYear(), sysBudgetDetails.getMonth(),sysBudgetDetails.getCompanyId());
            //如果系统已存在年份和月份的数据，不给予新增操作
            if (null != sysBudget) {
                if(!(sysBudget.getBudgetId()).equals(sysBudgetDetails.getBudgetId())){
                    return R.error(500, "更新执行预算信息失败，系统已存在" + sysBudgetDetails.getYear() + "年" + sysBudgetDetails.getMonth() + "月的数据，不能重复添加！！");
                }
            }
            int count = sysBudgetDetailsBusinessService.updateSysBudgetDetails(sysBudgetDetails);
            if (count > 0) {
                return R.ok(200, "更新成功！");
            } else {
                return R.error(500, "更新失败，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("更新执行预算信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> deleteSysBudgetDetails(Long budgetId) {
        try {
            int count = sysBudgetDetailsBusinessService.deleteSysBudgetDetails(budgetId);
            if (count > 0) {
                return R.ok(200, "删除成功！");
            } else {
                return R.error(500, "删除失败，请联系管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除执行预算信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }
}
