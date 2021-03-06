package com.spring.boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.spring.boot.bean.PageInfoBean;
import com.spring.boot.bean.master.*;
import com.spring.boot.bean.master.entity.SysBasicDataEntity;
import com.spring.boot.bean.master.entity.SysBudgetDetailsEntity;
import com.spring.boot.bean.master.entity.SysReceivableAccountsOwnerEntity;
import com.spring.boot.service.SysDataAnalysisService;
import com.spring.boot.service.SysFinancialService;
import com.spring.boot.service.web.*;
import com.spring.boot.util.R;
import com.spring.boot.util.SysUtil;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
public class SysFinancialServiceImpl implements SysFinancialService {
    private static final Logger logger = Logger.getLogger(SysFinancialServiceImpl.class);
    @Autowired
    private SysChargeBusinessService sysChargeBusinessService;
    @Autowired
    private SysAccountsReceivableBusinessService sysAccountsReceivableBusinessService;
    @Autowired
    private SysBudgetDetailsBusinessService sysBudgetDetailsBusinessService;
    @Autowired
    private SysCompanyBusinessService sysCompanyBusinessService;

    @Autowired
    private SysDataAnalysisService sysDataAnalysisService;
    @Autowired
    private SysUpdateDataRulesBusinessService sysUpdateDataRulesBusinessService;
    //对象
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Map<String, Object> sysChargeDetails(Long companyId) {
        List<Long> sysUserCompanyIds = null;
        //注：当周显示的是上一周的数据，所以查找上一周的数据
        try {
            if (companyId == 0) {
                //获取用户权限下可操作的小区信息
                sysUserCompanyIds = SysUtil.getSysUserCompany();
            } else {
                sysUserCompanyIds = new ArrayList<Long>();
                sysUserCompanyIds.add(companyId);
            }
            //当年
            int year = UtilHelper.getYear();
            //当周
            int weekOfYear = UtilHelper.getWeekOfYear();
            //当年第一周时，查找上一年的最后一周
            if (weekOfYear == 1) {
                year = year - 1;
                weekOfYear = UtilHelper.getMaxWeekNumOfYear(year);
            } else {
                weekOfYear = weekOfYear - 1;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("year", year);
            map.put("weekOfYear", weekOfYear);
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
            } else {
                sysChargeDetails = new SysChargeDetails();
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
        if (null != sysChargeDetails) {
            return R.ok().putData(200, sysChargeDetails, "获取成功！");
        }
        return R.error(500, "获取失败，不存在该记录！");
    }

    @Override
    public Map<String, Object> sysChargeAddWeekOfYearByCompanyId(Long companyId) {
        Map<String, Integer> resultMap = new HashMap<String, Integer>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        SysCompany sysCompany = sysCompanyBusinessService.findSysCompanyByCompanyId(map);
        if (null != sysCompany) {
            //根据公司id、年份查找数据库当年周数最大的记录
            int year = UtilHelper.getYear();
            //判断是否存在
            boolean isExist = false;
            //针对2018年6月22周系统上线时，周数填写需求更改后，需要将22周以后的数据补充完整
            int weekOfYear = UtilHelper.getWeekOfYear();

            //针对2018年6月22周系统上线时，周数填写需求更改后，需要将22周以后的数据补充完整
            SysChargeDetails sysChargeDetails22 = sysChargeBusinessService.findWeekOfYearRecord(companyId, 2018, 22);
            if (null == sysChargeDetails22) {
                resultMap.put("year", 2018);
                resultMap.put("weekOfYear", 22);
            } else {
                sysChargeDetails22 = sysChargeBusinessService.findWeekOfYearRecord(companyId, 2018, 23);
                if (null == sysChargeDetails22) {
                    resultMap.put("year", 2018);
                    resultMap.put("weekOfYear", 23);
                } else {
                    sysChargeDetails22 = sysChargeBusinessService.findWeekOfYearRecord(companyId, 2018, 24);
                    if (null == sysChargeDetails22) {
                        resultMap.put("year", 2018);
                        resultMap.put("weekOfYear", 24);
                    }
                }
            }
            if (resultMap.size() == 0) {
                do {
                    SysChargeDetails sysChargeDetails = sysChargeBusinessService.findMaxweekOfYearByYear(companyId, year);
                    //假如当年没有数据，查找上一年的数据
                    if (null == sysChargeDetails) {
                        year = year - 1;
                        sysChargeDetails = sysChargeBusinessService.findMaxweekOfYearByYear(companyId, year);
                        //上一年没有数据
                        if (null == sysChargeDetails) {
                            //年份再减去一年
                            year = year - 1;
                            isExist = false;
                        } else {
                            //当上一年的数据已达到最大周数时，直接返回下一年的第一周数据
                            if (sysChargeDetails.getYear() == year && sysChargeDetails.getWeekOfYear() == UtilHelper.getMaxWeekNumOfYear(year)) {
                                resultMap.put("year", year + 1);
                                resultMap.put("weekOfYear", 1);
                                isExist = true;
                            } else {
                                resultMap.put("year", year);
                                resultMap.put("weekOfYear", sysChargeDetails.getWeekOfYear() + 1);
                                isExist = true;
                            }
                        }
                    } else {
                        //当年的数据已达到最大周数时，直接返回下一年的第一周数据
                        if (sysChargeDetails.getYear() == year && sysChargeDetails.getWeekOfYear() == UtilHelper.getMaxWeekNumOfYear(year)) {
                            resultMap.put("year", year + 1);
                            resultMap.put("weekOfYear", 1);
                            isExist = true;
                            //sysChargeDetails = sysChargeBusinessService.findMaxweekOfYearByYear(companyId,year);
                        } else {
                            resultMap.put("year", year);
                            resultMap.put("weekOfYear", sysChargeDetails.getWeekOfYear() + 1);
                            isExist = true;
                        }

                    }
                } while (!isExist);
            }
            if (resultMap.size() > 0) {
                //2018年6月22周到24周数据，添加不受限制，因22周系统上线，周数添加控制需求更改
                if (resultMap.get("year") == 2018 && (resultMap.get("weekOfYear") == 22 || resultMap.get("weekOfYear") == 23 || resultMap.get("weekOfYear") == 24)) {
                    return R.ok().putData(200, resultMap, "获取成功！");
                } else {
                    //当需要添加的数据年份和周数都与当前前年份、周数一致时，才允许添加，避免前端提前添加数据造成误导
                    if (UtilHelper.getYear() < resultMap.get("year")) {
                        return R.error(500, "本周数据已添加，下周数据暂不能添加，谢谢配合！");
                    } else if (UtilHelper.getYear() == resultMap.get("year") && UtilHelper.getWeekOfYear() < resultMap.get("weekOfYear")) {
                        return R.error(500, "本周数据已添加，下周数据暂不能添加，谢谢配合！");
                    } else {
                        return R.ok().putData(200, resultMap, "获取成功！");
                    }
                    /*if(UtilHelper.getYear()==resultMap.get("year")&&UtilHelper.getWeekOfYear()==resultMap.get("weekOfYear")){
                        return R.ok().putData(200, resultMap, "获取成功！");
                    }else{
                        return R.error(500, "本周数据已添加，下周数据暂不能添加，谢谢配合！");
                    }*/
                }

            } else {
                return R.error(500, "服务器异常，请联系系统管理员进行处理！");
            }
        } else {
            return R.error(500, "公司不存在，请联系系统管理员进行处理！");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addSysCharge(Long companyId, Double chargeMoney, Double chargeMoneyNow, Double chargeDebt, Double chargeDebtReturn, Integer year, Integer weekOfYear) {

        SysChargeDetails sysChargeDetails = sysChargeBusinessService.findSysChargeDetailsByWeekOfYear(year, weekOfYear, companyId);
        if (null != sysChargeDetails) {
            return R.error(500, "本周数据已经添加，不能再次添加，如有疑问，请联系系统管理员！");
        } else {
            sysChargeDetails = new SysChargeDetails();
            sysChargeDetails.setChargeDebt(chargeDebt);
            sysChargeDetails.setChargeDebtReturn(chargeDebtReturn);
            sysChargeDetails.setChargeMoney(chargeMoney);
            sysChargeDetails.setChargeMoneyNow(chargeMoneyNow);
            sysChargeDetails.setCompanyId(companyId);
            sysChargeDetails.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
            sysChargeDetails.setYear(year);
            sysChargeDetails.setWeekOfYear(weekOfYear);
            //月份为备用字段，假如数据及时添加，月份则对
            sysChargeDetails.setMonth(UtilHelper.getMonth());
        }
        int count = sysChargeBusinessService.addSysCharge(sysChargeDetails);
        if (count > 0) {
            //将信息放进redis缓存
            setSysChargeToRedis();
            return R.ok(200, "新增成功！");
        } else {
            return R.error(500, "新增失败，请联系管理员！");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> updateSysCharge(Long chargeId, Long companyId, Double chargeMoney, Double chargeMoneyNow, Double chargeDebt, Double chargeDebtReturn) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("chargeId", chargeId);
        map.put("chargeMoney", chargeMoney);
        map.put("chargeMoneyNow", chargeMoneyNow);
        map.put("chargeDebt", chargeDebt);
        map.put("chargeDebtReturn", chargeDebtReturn);
        map.put("companyId", companyId);

        int count = sysChargeBusinessService.updateSysCharge(map);
        if (count > 0) {
            //将信息放进redis缓存
            setSysChargeToRedis();
            return R.ok(200, "更新成功！");
        } else {
            return R.error(500, "更新失败，请联系管理员！");
        }

    }

    @Override
    public Map<String, Object> sysAccountsReceivableAnalysis(Long companyId, Integer selectYear, Integer selectMonth) {
        List<Long> sysUserCompanyIds = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> mapForMonth = new HashMap<String, Object>();
        Map<String, Object> mapForYear = new HashMap<String, Object>();

        Map<String, Object> mapForLastMonth = new HashMap<String, Object>();
        //int thisYear = UtilHelper.getYear();
        //int thisMonth = UtilHelper.getMonth();
        try {
            if (companyId == 0) {
                //获取用户权限下可操作的小区信息
                sysUserCompanyIds = SysUtil.getSysUserCompany();
            } else {
                sysUserCompanyIds = new ArrayList<Long>();
                sysUserCompanyIds.add(companyId);
            }
            SysUpdateDataRules sysUpdateDataRules = sysUpdateDataRulesBusinessService.findSysUpdateDataRules();
            //获取需要查询的年份和月份
            Map<String, Integer> yearAndMonthMap = SysUtil.getYearAndMonth(sysUpdateDataRules.getDay());
            if (selectYear == null || selectMonth == null) {
                selectYear = yearAndMonthMap.get("year");
                selectMonth = yearAndMonthMap.get("month");
            }
            mapForYear.put("year", selectYear);
            mapForYear.put("month", selectMonth);
            mapForYear.put("sysUserCompanyIds", sysUserCompanyIds);

            mapForMonth.put("year", selectYear);
            mapForMonth.put("month", selectMonth);
            mapForMonth.put("sysUserCompanyIds", sysUserCompanyIds);
            //注：应收账款里面的地产应收、已收款，小业主的应收、已收款，由原来的统计当月数据=当月数据，变更为当月数据=当月填写数据-上月填写数据（其中1月份不需要进行该操作） 2019-1-10修改
            if (selectMonth == 1) {
                mapForLastMonth.put("year", selectYear);
                mapForLastMonth.put("month", selectMonth);
                mapForLastMonth.put("sysUserCompanyIds", sysUserCompanyIds);
            } else {
                mapForLastMonth.put("year", selectYear);
                mapForLastMonth.put("month", selectMonth - 1);
                mapForLastMonth.put("sysUserCompanyIds", sysUserCompanyIds);
            }

            Double receivableAccountsOwnerData, completeAccountsOwnerData, completeCoupon, receivableCoupon, completeVacancy;
            Double receivableVacancy, completeSubsidy, receivableSubsidy, completeSales, receivableSales, receivableOpen;
            Double completeOpen, completePropertySubsidy, receivablePropertySubsidy, completeHouseOther, receivableHouseOther;
            Double completeHouse, receivableHouse;

            //10号之前为上上月的数据，10号之前为上月的数据（也可以封装成map传值）
            SysAccountsReceivable sysAccountsReceivable = sysAccountsReceivableBusinessService.sysAccountsReceivableAnalysis(mapForMonth);
            //（10号之前为上上月的数据，10号之前为上月的数据）在这基础上再上一个月数据，比如，3月9号，该处查询的数据为1月的数据，3月10号，查询的也是1月数据，但4月10号查询的为2月数据
            SysAccountsReceivable sysAccountsReceivableLsatMonth = null;
            if (selectMonth != 1) {
                sysAccountsReceivableLsatMonth = sysAccountsReceivableBusinessService.sysAccountsReceivableAnalysis(mapForLastMonth);
            }

            if (null != sysAccountsReceivable) {
                if (selectMonth != 1 && null != sysAccountsReceivableLsatMonth) {
                    //注：除了一月份，其他月份的数据需要减去上月数据
                    receivableAccountsOwnerData = sysAccountsReceivable.getReceivableAccountsOwner() - sysAccountsReceivableLsatMonth.getReceivableAccountsOwner();
                    completeAccountsOwnerData = sysAccountsReceivable.getCompleteAccountsOwner() - sysAccountsReceivableLsatMonth.getCompleteAccountsOwner();
                    completeCoupon = sysAccountsReceivable.getCompleteCoupon() - sysAccountsReceivableLsatMonth.getCompleteCoupon();
                    receivableCoupon = sysAccountsReceivable.getReceivableCoupon() - sysAccountsReceivableLsatMonth.getReceivableCoupon();
                    completeVacancy = sysAccountsReceivable.getCompleteVacancy() - sysAccountsReceivableLsatMonth.getCompleteVacancy();
                    receivableVacancy = sysAccountsReceivable.getReceivableVacancy() - sysAccountsReceivableLsatMonth.getReceivableVacancy();
                    completeSubsidy = sysAccountsReceivable.getCompleteSubsidy() - sysAccountsReceivableLsatMonth.getCompleteSubsidy();
                    receivableSubsidy = sysAccountsReceivable.getReceivableSubsidy() - sysAccountsReceivableLsatMonth.getReceivableSubsidy();
                    completeSales = sysAccountsReceivable.getCompleteSales() - sysAccountsReceivableLsatMonth.getCompleteSales();
                    receivableSales = sysAccountsReceivable.getReceivableSales() - sysAccountsReceivableLsatMonth.getReceivableSales();
                    receivableOpen = sysAccountsReceivable.getReceivableOpen() - sysAccountsReceivableLsatMonth.getReceivableOpen();
                    completeOpen = sysAccountsReceivable.getCompleteOpen() - sysAccountsReceivableLsatMonth.getCompleteOpen();
                    completePropertySubsidy = sysAccountsReceivable.getCompletePropertySubsidy() - sysAccountsReceivableLsatMonth.getCompletePropertySubsidy();
                    receivablePropertySubsidy = sysAccountsReceivable.getReceivablePropertySubsidy() - sysAccountsReceivableLsatMonth.getReceivablePropertySubsidy();
                    completeHouseOther = sysAccountsReceivable.getCompleteHouseOther() - sysAccountsReceivableLsatMonth.getCompleteHouseOther();
                    receivableHouseOther = sysAccountsReceivable.getReceivableHouseOther() - sysAccountsReceivableLsatMonth.getReceivableHouseOther();
                    completeHouse = sysAccountsReceivable.getCompleteHouse() - sysAccountsReceivableLsatMonth.getCompleteHouse();
                    receivableHouse = sysAccountsReceivable.getReceivableHouse() - sysAccountsReceivableLsatMonth.getReceivableHouse();
                    //completePropertySubsidy=sysAccountsReceivable.getReceivableAccountsOwner()-sysAccountsReceivableLsatMonth.getReceivableAccountsOwner();
                    //----------------------重新赋值-------------------------
                    sysAccountsReceivable = new SysAccountsReceivable(receivableAccountsOwnerData, completeAccountsOwnerData, completeCoupon, receivableCoupon, completeVacancy, receivableVacancy, completeSubsidy, receivableSubsidy,
                            completeSales, receivableSales, receivableOpen, completeOpen, completePropertySubsidy, receivablePropertySubsidy, completeHouseOther, receivableHouseOther, completeHouse, receivableHouse);

                }

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
            } else {
                sysAccountsReceivable = new SysAccountsReceivable();
            }
            List<SysAccountsReceivable> sysAccountsReceivableAnalysisList = sysAccountsReceivableBusinessService.sysAccountsReceivableAnalysisForMonth(mapForYear);
            //获取所在年份对应的所有数据的月份
            //String months=sysAccountsReceivableBusinessService.sysAccountsReceivableMonths(map);
            SysReceivableAccountsOwnerEntity sysReceivableAccountsOwnerEntity = null;
            //当年月份组装（非数据库查询，避免数据库数据库因忘记添加数据而照成月份丢失）
            //List<Integer> monthList=new ArrayList<Integer>();

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
                    if (null != month) {
                        receivableMap.put(month, receivableAccountsOwner);
                        completeMap.put(month, completeAccountsOwner);
                    }
                }
            }
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
    public Map<String, Object> sysAccountsReceivableList(Long companyId, Integer year, Integer limit, Integer offset, Integer month) {
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
        map.put("month", month);
        map.put("limit", limit);
        map.put("offset", offset);
        try {
            //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
            PageHelper.startPage((offset / limit) + 1, limit);
            List<SysAccountsReceivable> list = sysAccountsReceivableBusinessService.sysAccountsReceivableList(map);
            PageInfoBean result = new PageInfoBean(list);
            resultMap.put("total", result.getTotalOfData());
            resultMap.put("list", result.getList());

            //resultMap.put("total", sysAccountsReceivableBusinessService.sysAccountsReceivableListTotal(map));
            //resultMap.put("list", sysAccountsReceivableBusinessService.sysAccountsReceivableList(map));
            return R.ok().putData(200, resultMap, "获取成功！");
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
            if (null != sysAccountsReceivable) {
                return R.ok().putData(200, sysAccountsReceivable, "根据id查找品质管理数据成功！");
            } else {
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
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable) {

        //根据年份跟月份查找系统记录
        SysAccountsReceivable sysArDetails = sysAccountsReceivableBusinessService.findRecordByYearAndMonth(sysAccountsReceivable.getYear(), sysAccountsReceivable.getMonth(), sysAccountsReceivable.getCompanyId());
        //如果系统已存在年份和月份的数据，不给予新增操作
        if (null != sysArDetails) {
            return R.error(500, "新增失败，系统已存在" + sysAccountsReceivable.getYear() + "年" + sysAccountsReceivable.getMonth() + "月的数据，不能重复添加！！");
        }
        sysAccountsReceivable.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
        int count = sysAccountsReceivableBusinessService.addSysAccountsReceivable(sysAccountsReceivable);
        if (count > 0) {

            //SysUpdateDataRules sysUpdateDataRules=sysUpdateDataRulesBusinessService.findSysUpdateDataRules();
            //boolean updateToRedis=SysUtil.updateToRedis(sysUpdateDataRules.getDay(),sysAccountsReceivable.getYear(),sysAccountsReceivable.getMonth());

            //将信息放进redis缓存
            setSysAccountsReceivableToRedis();

            return R.ok(200, "新增成功！");
        } else {
            return R.error(500, "新增失败，请联系管理员！");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> updateSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable) {
        //根据年份跟月份查找系统记录
        SysAccountsReceivable sysArDetails = sysAccountsReceivableBusinessService.findRecordByYearAndMonth(sysAccountsReceivable.getYear(), sysAccountsReceivable.getMonth(), sysAccountsReceivable.getCompanyId());
        if (null != sysArDetails) {
            //如果系统已存在年份和月份的数据，不给予更新操作
            if (!sysArDetails.getAccountsId().equals(sysAccountsReceivable.getAccountsId())) {
                return R.error(500, "更新失败，系统已存在" + sysAccountsReceivable.getYear() + "年" + sysAccountsReceivable.getMonth() + "月的数据，不能重复添加！！");
            }
        }
        int count = sysAccountsReceivableBusinessService.updateSysAccountsReceivable(sysAccountsReceivable);
        if (count > 0) {

            //将信息放进redis缓存
            setSysAccountsReceivableToRedis();

            return R.ok(200, "更新成功！");
        } else {
            return R.error(500, "更新失败，请联系管理员！");
        }

    }

    @Override
    public Map<String, Object> deleteSysAccountsReceivable(Long accountsId) {

        int count = sysAccountsReceivableBusinessService.deleteSysAccountsReceivable(accountsId);
        if (count > 0) {
            //Map<String, Object> map = new HashMap<String, Object>();
            //map.put("accountsId", accountsId);
            //SysAccountsReceivable accountsReceivable = sysAccountsReceivableBusinessService.findSysAccountsReceivableById(map);
            //将信息放进redis缓存
            setSysAccountsReceivableToRedis();

            return R.ok(200, "删除成功！");
        } else {
            return R.error(500, "删除失败，请联系管理员！");
        }

    }

    @Override
    public Map<String, Object> sysBudgetDetailsAnalysis(Long companyId, Integer selectYear, Integer selectMonth) {
        List<Long> sysUserCompanyIds = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> mapForMonth = new HashMap<String, Object>();
        Map<String, Object> mapForYear = new HashMap<String, Object>();

        Map<String, Object> mapForLastMonth = new HashMap<String, Object>();
        //int thisYear = UtilHelper.getYear();
        //int thisMonth = UtilHelper.getMonth();

        try {
            if (companyId == 0) {
                //获取用户权限下可操作的小区信息
                sysUserCompanyIds = SysUtil.getSysUserCompany();
            } else {
                sysUserCompanyIds = new ArrayList<Long>();
                sysUserCompanyIds.add(companyId);
            }

            SysUpdateDataRules sysUpdateDataRules = sysUpdateDataRulesBusinessService.findSysUpdateDataRules();
            //获取需要查询的年份和月份
            Map<String, Integer> yearAndMonthMap = SysUtil.getYearAndMonth(sysUpdateDataRules.getDay());
            if (selectYear == null || selectMonth == null) {
                selectYear = yearAndMonthMap.get("year");
                selectMonth = yearAndMonthMap.get("month");
            }
            mapForMonth.put("year", selectYear);
            mapForMonth.put("month", selectMonth);
            mapForMonth.put("sysUserCompanyIds", sysUserCompanyIds);

            mapForYear.put("year", selectYear);
            mapForYear.put("month", selectMonth);
            mapForYear.put("sysUserCompanyIds", sysUserCompanyIds);
            if (selectMonth == 1) {
                mapForLastMonth.put("year", selectYear);
                mapForLastMonth.put("month", selectMonth);
                mapForLastMonth.put("sysUserCompanyIds", sysUserCompanyIds);
            } else {
                mapForLastMonth.put("year", selectYear);
                mapForLastMonth.put("month", selectMonth - 1);
                mapForLastMonth.put("sysUserCompanyIds", sysUserCompanyIds);
            }
            //mybatis涉及到in查询的，传参数时，可以一个或者多个，也可以封装成map进行传值
            SysBudgetDetails sysBudgetDetails = sysBudgetDetailsBusinessService.sysBudgetDetailsAnalysis(mapForMonth);
            //上月数据
            SysBudgetDetails sysBudgetLastMonthDetails = null;
            if (selectMonth != 1) {
                sysBudgetLastMonthDetails = sysBudgetDetailsBusinessService.sysBudgetDetailsAnalysis(mapForLastMonth);
            }
            Double budgetIncomeData, realIncomeData;
            Double budgetProfitsData;
            Double realProfitsData;
            Double budgetExpensesData;
            Double personnelCostData;
            Double administrativeCostData;
            Double materialCost;
            Double energyCost;
            Double equipmentCost;
            Double cleaningCost;
            Double afforestCost;
            Double orderMaintenanceCost;
            Double communityActivitiesCost;
            Double otherCost;
            Double realExpensesTotalData;

            if (null != sysBudgetDetails) {
                //除了1月份外，其他月份的数据计算规则为：当月数据=当月数据总和-上月数据总和（每月的数据累计相加填写，需要减去上月总和才得到当月实际数据） 2019-1-10修改
                if (selectMonth != 1 && sysBudgetLastMonthDetails != null) {
                    budgetIncomeData = sysBudgetDetails.getBudgetIncome() - sysBudgetLastMonthDetails.getBudgetIncome();
                    realIncomeData = sysBudgetDetails.getRealIncome() - sysBudgetLastMonthDetails.getRealIncome();
                    budgetProfitsData = sysBudgetDetails.getBudgetProfits() - sysBudgetLastMonthDetails.getBudgetProfits();
                    realProfitsData = sysBudgetDetails.getRealProfits() - sysBudgetLastMonthDetails.getRealProfits();
                    budgetExpensesData = sysBudgetDetails.getBudgetExpenses() - sysBudgetLastMonthDetails.getBudgetExpenses();
                    personnelCostData = sysBudgetDetails.getPersonnelCost() - sysBudgetLastMonthDetails.getPersonnelCost();
                    administrativeCostData = sysBudgetDetails.getAdministrativeCost() - sysBudgetLastMonthDetails.getAdministrativeCost();
                    materialCost = sysBudgetDetails.getMaterialCost() - sysBudgetLastMonthDetails.getMaterialCost();
                    energyCost = sysBudgetDetails.getEnergyCost() - sysBudgetLastMonthDetails.getEnergyCost();
                    equipmentCost = sysBudgetDetails.getEquipmentCost() - sysBudgetLastMonthDetails.getEquipmentCost();
                    cleaningCost = sysBudgetDetails.getCleaningCost() - sysBudgetLastMonthDetails.getCleaningCost();

                    afforestCost = sysBudgetDetails.getAfforestCost() - sysBudgetLastMonthDetails.getAfforestCost();
                    orderMaintenanceCost = sysBudgetDetails.getOrderMaintenanceCost() - sysBudgetLastMonthDetails.getOrderMaintenanceCost();
                    communityActivitiesCost = sysBudgetDetails.getCommunityActivitiesCost() - sysBudgetLastMonthDetails.getCommunityActivitiesCost();
                    otherCost = sysBudgetDetails.getOtherCost() - sysBudgetLastMonthDetails.getOtherCost();
                    realExpensesTotalData = sysBudgetDetails.getRealExpensesTotal() - sysBudgetLastMonthDetails.getRealExpensesTotal();
                    sysBudgetDetails = new SysBudgetDetails(budgetIncomeData, realIncomeData, budgetProfitsData, realProfitsData, budgetExpensesData, personnelCostData, administrativeCostData, materialCost, energyCost,
                            equipmentCost, cleaningCost, afforestCost, orderMaintenanceCost, communityActivitiesCost, otherCost, realExpensesTotalData);
                }

            } else {
                sysBudgetDetails = new SysBudgetDetails();
            }
            List<SysBudgetDetails> sysBudgetDetailsList = sysBudgetDetailsBusinessService.sysBudgetDetailsIncomeForMonth(mapForYear);

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
                //获取环比月份数据
                SysBudgetDetails lastMonthDetailis = null;

                //获取环比月份数据,上月的上个月数据
                SysBudgetDetails lastMonth_LastDetailis = null;
                //上个月利润
                Double lastMonthRealProfits = 0.00;
                //当月实际利润
                Double realProfits_month = 0.00;

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

                    lastMonthDetailis = null;
                    lastMonth_LastDetailis = null;
                    lastMonthRealProfits = 0.00;
                    if (null != month) {
                        realIncomeMap.put(month, realIncome);
                        budgetIncomeMap.put(month, budgetIncome);
                        realExpensesTotalMap.put(month, realExpensesTotal);
                        budgetExpensesMap.put(month, budgetExpenses);
                        realProfitsMap.put(month, realProfits);
                        if (month == 1) {
                            realProfits_month = realProfits;
                            lastMonthDetailis = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(selectYear - 1, 12, sysUserCompanyIds);
                            //1月份时，查询上年11月的数据，经过与上年的12月比较，获取真正12月的数据 2019-1-10修改
                            lastMonth_LastDetailis = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(selectYear - 1, 11, sysUserCompanyIds);
                            if (null != lastMonthDetailis && lastMonthDetailis.getRealProfits() != null) {
                                lastMonthRealProfits = getNum(lastMonthDetailis.getRealProfits() - (lastMonth_LastDetailis == null ? 0 : lastMonth_LastDetailis.getRealProfits()));
                            }
                            realProfitsScaleMap.put(month, UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber((realProfits_month - lastMonthRealProfits), lastMonthRealProfits)));
                        } else if (month == 2) {
                            lastMonthDetailis = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(selectYear, 1, sysUserCompanyIds);

                            if (null != lastMonthDetailis && lastMonthDetailis.getRealProfits() != null) {
                                lastMonthRealProfits = getNum(lastMonthDetailis.getRealProfits());
                                realProfits_month = realProfits - lastMonthDetailis.getRealProfits();
                            }
                            realProfitsScaleMap.put(month, UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber((realProfits_month - lastMonthRealProfits), lastMonthRealProfits)));
                        } else {
                            lastMonthDetailis = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(selectYear, month - 1, sysUserCompanyIds);

                            //查询上月的上月的数据，经过与当月比较，获取当月的数据 2019-1-10修改
                            lastMonth_LastDetailis = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(selectYear, month - 2, sysUserCompanyIds);
                            if (null != lastMonthDetailis && lastMonthDetailis.getRealProfits() != null) {
                                lastMonthRealProfits = getNum(lastMonthDetailis.getRealProfits() - (lastMonth_LastDetailis == null ? 0 : lastMonth_LastDetailis.getRealProfits()));
                                realProfits_month = realProfits - lastMonthDetailis.getRealProfits();
                            }
                            realProfitsScaleMap.put(month, UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber((realProfits_month - lastMonthRealProfits), lastMonthRealProfits)));
                        }
                    }
                }
            }
            //如果区域选择“全国”，讲返回的主键id设置为null
            if (companyId == 0) {
                sysBudgetDetails.setBudgetId(null);
            }
            sysBudgetDetails.setRealIncomeMap(realIncomeMap);
            sysBudgetDetails.setBudgetIncomeMap(budgetIncomeMap);
            sysBudgetDetails.setRealExpensesTotalMap(realExpensesTotalMap);
            sysBudgetDetails.setBudgetExpensesMap(budgetExpensesMap);
            sysBudgetDetails.setRealProfitsMap(realProfitsMap);
            sysBudgetDetails.setRealProfitsScaleMap(realProfitsScaleMap);

            //resultMap.put("incomeInfo", sysBudgetDetails);
            return R.ok().putData(200, sysBudgetDetails, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("收费情况报表统计详细信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> sysBudgetDetailsList(Long companyId, Integer year, Integer limit, Integer offset, Integer month) {
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
        map.put("month", month);
        map.put("limit", limit);
        map.put("offset", offset);
        try {
            //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
            PageHelper.startPage((offset / limit) + 1, limit);
            List<SysBudgetDetails> list = sysBudgetDetailsBusinessService.sysBudgetDetailsList(map);
            PageInfoBean result = new PageInfoBean(list);
            resultMap.put("total", result.getTotalOfData());
            resultMap.put("list", result.getList());
            //resultMap.put("total", sysBudgetDetailsBusinessService.sysBudgetDetailsListTotal(map));
            //resultMap.put("list", sysBudgetDetailsBusinessService.sysBudgetDetailsList(map));
            return R.ok().putData(200, resultMap, "获取成功！");
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
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addSysBudgetDetails(SysBudgetDetails sysBudgetDetails) {
        //根据年份跟月份查找系统记录
        SysBudgetDetails sysBudget = sysBudgetDetailsBusinessService.findRecordByYearAndMonth(sysBudgetDetails.getYear(), sysBudgetDetails.getMonth(), sysBudgetDetails.getCompanyId());
        //如果系统已存在年份和月份的数据，不给予新增操作
        if (null != sysBudget) {
            return R.error(500, "新增失败，系统已存在" + sysBudgetDetails.getYear() + "年" + sysBudgetDetails.getMonth() + "月的数据，不能重复添加！！");
        }
        sysBudgetDetails.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
        int count = sysBudgetDetailsBusinessService.addSysBudgetDetails(sysBudgetDetails);
        if (count > 0) {
            //将信息放进redis缓存
            setSysBudgetDetailsToRedis();

            return R.ok(200, "新增成功！");
        } else {
            return R.error(500, "新增失败，请联系管理员！");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> updateSysBudgetDetails(SysBudgetDetails sysBudgetDetails) {
        //根据年份跟月份查找系统记录
        SysBudgetDetails sysBudget = sysBudgetDetailsBusinessService.findRecordByYearAndMonth(sysBudgetDetails.getYear(), sysBudgetDetails.getMonth(), sysBudgetDetails.getCompanyId());
        //如果系统已存在年份和月份的数据，不给予新增操作
        if (null != sysBudget) {
            if (!(sysBudget.getBudgetId()).equals(sysBudgetDetails.getBudgetId())) {
                return R.error(500, "更新执行预算信息失败，系统已存在" + sysBudgetDetails.getYear() + "年" + sysBudgetDetails.getMonth() + "月的数据，不能重复添加！！");
            }
        }
        int count = sysBudgetDetailsBusinessService.updateSysBudgetDetails(sysBudgetDetails);
        if (count > 0) {

            //将信息放进redis缓存
            setSysBudgetDetailsToRedis();

            return R.ok(200, "更新成功！");
        } else {
            return R.error(500, "更新失败，请联系管理员！");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> deleteSysBudgetDetails(Long budgetId) {
        int count = sysBudgetDetailsBusinessService.deleteSysBudgetDetails(budgetId);
        if (count > 0) {
            //将信息放进redis缓存
            setSysBudgetDetailsToRedis();
            return R.ok(200, "删除成功！");
        } else {
            return R.error(500, "删除失败，请联系管理员！");
        }

    }

    /**
     * 保存收费情况详细信息到redis缓存
     */
    @Override
    public void setSysChargeToRedis() {
        List<Long> sysUserCompanyIds = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            //当年
            int year = UtilHelper.getYear();
            //当周
            int weekOfYear = UtilHelper.getWeekOfYear();
            //当年第一周时，查找上一年的最后一周
            if (weekOfYear == 1) {
                year = year - 1;
                weekOfYear = UtilHelper.getMaxWeekNumOfYear(year);
            } else {
                weekOfYear = weekOfYear - 1;
            }
            map.put("year", year);
            map.put("weekOfYear", weekOfYear);
            map.put("sysUserCompanyIds", null);
            //也可以封装成map传值
            SysChargeDetails sysChargeDetails = sysChargeBusinessService.sysChargeDetails(map);
            if (null != sysChargeDetails) {
                if (null != sysChargeDetails.getChargeMoneyNow() && null != sysChargeDetails.getChargeMoney()) {
                    sysChargeDetails.setChargeMoneyScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysChargeDetails.getChargeMoneyNow(), sysChargeDetails.getChargeMoney())));
                }
                if (null != sysChargeDetails.getChargeDebtReturn() && null != sysChargeDetails.getChargeDebt()) {
                    sysChargeDetails.setChargeDebtScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysChargeDetails.getChargeDebtReturn(), sysChargeDetails.getChargeDebt())));
                }
                //注：原为查找一年12个月的数据，现在修改为查找最近12周的数据
                List<SysChargeDetails> list = sysChargeBusinessService.sysChargeDetailsForYear(map);
                //一年当期收缴率（针对大屏数据展示页面）
                Map<Integer, Double> yearChargeMoneyScale = new HashMap<Integer, Double>();
                //一年清欠收缴率（针对大屏数据展示页面）
                Map<Integer, Double> yearChargeDebtScale = new HashMap<Integer, Double>();
                int listSize = list.size();
                //注：查询sql结果为倒序排序,即为第一条为最新一周数据，需求显示为12周，最后一周12周为最新的周数数据
                for (int i = 0; i < listSize; i++) {
                    yearChargeMoneyScale.put(listSize - i, list.get(i).getChargeMoneyNow());
                    yearChargeDebtScale.put(listSize - i, list.get(i).getChargeDebtReturn());
                }
                sysChargeDetails.setYearChargeMoneyScale(yearChargeMoneyScale);
                sysChargeDetails.setYearChargeDebtScale(yearChargeDebtScale);
                //存储到redis缓存，为大屏财务数据页面展示提供服务
                redisTemplate.opsForValue().set("sysChargeDetails", sysChargeDetails);
                //调取财务大屏数据接口
                sysDataAnalysisService.sysFinancialDataAnalysis();
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("统计信息失败！" + e.getMessage());
        }
    }

    /**
     * 保存应收账款到redis缓存
     */
    @Override
    public void setSysAccountsReceivableToRedis() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> mapForMonth = new HashMap<String, Object>();

        Map<String, Object> mapForYear = new HashMap<String, Object>();
        //int thisYear = UtilHelper.getYear();
        // int thisMonth = UtilHelper.getMonth();


        SysUpdateDataRules sysUpdateDataRules = sysUpdateDataRulesBusinessService.findSysUpdateDataRules();
        //获取需要查询的年份和月份
        Map<String, Integer> yearAndMonthMap = SysUtil.getYearAndMonth(sysUpdateDataRules.getDay());
        mapForYear.put("year", yearAndMonthMap.get("year"));
        mapForYear.put("month", yearAndMonthMap.get("month"));
        mapForYear.put("sysUserCompanyIds", null);

        mapForMonth.put("year", yearAndMonthMap.get("year"));
        mapForMonth.put("month", yearAndMonthMap.get("month"));
        mapForMonth.put("sysUserCompanyIds", null);
        try {
            //当年数据，也可以封装成map传值
            SysAccountsReceivable sysAccountsReceivable = sysAccountsReceivableBusinessService.sysAccountsReceivableAnalysis(mapForYear);
            //当月数据
            //SysAccountsReceivable sysAccountsReceivableMonth = sysAccountsReceivableBusinessService.sysAccountsReceivableAnalysis(mapForMonth);

            if (null != sysAccountsReceivable) {
                sysAccountsReceivable.setCouponScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompleteCoupon(), sysAccountsReceivable.getReceivableCoupon())));
                sysAccountsReceivable.setVacancyScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompleteVacancy(), sysAccountsReceivable.getReceivableVacancy())));
                sysAccountsReceivable.setSubsidyScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompleteSubsidy(), sysAccountsReceivable.getReceivableSubsidy())));
                sysAccountsReceivable.setSalesScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompleteSales(), sysAccountsReceivable.getReceivableSales())));
                sysAccountsReceivable.setOpenScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompleteOpen(), sysAccountsReceivable.getReceivableOpen())));
                sysAccountsReceivable.setPropertySubsidyScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompletePropertySubsidy(), sysAccountsReceivable.getReceivablePropertySubsidy())));
                sysAccountsReceivable.setHouseOtherScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompleteHouseOther(), sysAccountsReceivable.getReceivableHouseOther())));
            } else {
                sysAccountsReceivable = new SysAccountsReceivable();
            }
            List<SysAccountsReceivable> sysAccountsReceivableAnalysisList = sysAccountsReceivableBusinessService.sysAccountsReceivableAnalysisForMonth(mapForYear);
            //获取所在年份对应的所有数据的月份
            SysReceivableAccountsOwnerEntity sysReceivableAccountsOwnerEntity = null;

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
                    if (null != month) {
                        receivableMap.put(month, receivableAccountsOwner);
                        completeMap.put(month, completeAccountsOwner);
                    }
                }
            }
            //注：因财务统计界面需求，业主应收款、业主已收款、地产应收款、地产已收款为月度信息，需要将这几个字段的数据切换为月度数据
           /* if(sysAccountsReceivableMonth!=null){
                sysAccountsReceivable.setReceivableAccountsOwner(sysAccountsReceivableMonth.getReceivableAccountsOwner());
                sysAccountsReceivable.setCompleteAccountsOwner(sysAccountsReceivableMonth.getCompleteAccountsOwner());
                sysAccountsReceivable.setCompleteHouse(sysAccountsReceivableMonth.getCompleteHouse());
                sysAccountsReceivable.setReceivableHouse(sysAccountsReceivableMonth.getReceivableHouse());
            }*/
            sysReceivableAccountsOwnerEntity.setReceivableAccounts(receivableMap);
            sysReceivableAccountsOwnerEntity.setCompleteAccounts(completeMap);
            resultMap.put("sysAccountsReceivable", sysAccountsReceivable);
            resultMap.put("sysAccountsReceivableMonth", sysReceivableAccountsOwnerEntity);
            //存储到redis缓存，为大屏财务数据页面展示提供服务
            redisTemplate.opsForValue().set("sysReceivableAccount", resultMap);
            //调取财务大屏数据接口
            sysDataAnalysisService.sysFinancialDataAnalysis();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("收费情况报表统计详细信息失败！" + e.getMessage());
        }
    }

    /**
     * 保存预算信息到redis缓存
     */
    @Override
    public void setSysBudgetDetailsToRedis() {
        Map<String, Object> mapForYear = new HashMap<String, Object>();
        Map<String, Object> mapForMonth = new HashMap<String, Object>();
        //int thisYear = UtilHelper.getYear();
        //int thisMonth = UtilHelper.getMonth();
        SysUpdateDataRules sysUpdateDataRules = sysUpdateDataRulesBusinessService.findSysUpdateDataRules();
        //获取需要查询的年份和月份
        Map<String, Integer> yearAndMonthMap = SysUtil.getYearAndMonth(sysUpdateDataRules.getDay());
        mapForMonth.put("year", yearAndMonthMap.get("year"));
        mapForMonth.put("month", yearAndMonthMap.get("month"));
        mapForMonth.put("sysUserCompanyIds", null);

        mapForYear.put("year", yearAndMonthMap.get("year"));
        //注：原来一年数据为12个月全部相加，现在只需要最新一个月数据为全年数据（新规则为：当月总数据=上月总数据+当月实际数据），即为：12月份的数据，就是当年全年的总数据 2019-1-11修改
        mapForYear.put("month", yearAndMonthMap.get("month"));//如果最新一个月数据为总数据，需要传值月份，否则month赋值null，即：直接查询年份 2019-1-11修改
        mapForYear.put("sysUserCompanyIds", null);
        try {
            SysBudgetDetailsEntity sysBudgetDetailsEntity = new SysBudgetDetailsEntity();
            //mybatis涉及到in查询的，传参数时，可以一个或者多个，也可以封装成map进行传值
            SysBudgetDetails sysBudgetDetailsForyear = sysBudgetDetailsBusinessService.sysBudgetDetailsAnalysis(mapForYear);
            //mybatis涉及到in查询的，传参数时，可以一个或者多个，也可以封装成map进行传值
            //注：原需求实际收入、实际利润等为月度数据，后改为年度信息
            //SysBudgetDetails sysBudgetDetailsForMonth = sysBudgetDetailsBusinessService.sysBudgetDetailsAnalysis(mapForMonth);
            if (null == sysBudgetDetailsForyear) {
                sysBudgetDetailsForyear = new SysBudgetDetails();
            }
            List<SysBudgetDetails> sysBudgetDetailsList = sysBudgetDetailsBusinessService.sysBudgetDetailsIncomeForMonth(mapForYear);
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
                //获取环比月份数据
                SysBudgetDetails lastMonthDetailis = null;
                //获取环比月份数据,上月的上个月数据
                SysBudgetDetails lastMonth_LastDetailis = null;

                //上个月利润
                Double lastMonthRealProfits = 0.00;
                //当月实际利润
                Double realProfits_month = 0.00;
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
                    lastMonthDetailis = null;
                    lastMonth_LastDetailis = null;

                    lastMonthRealProfits = 0.00;
                    if (null != month) {
                        realIncomeMap.put(month, realIncome);
                        budgetIncomeMap.put(month, budgetIncome);
                        realExpensesTotalMap.put(month, realExpensesTotal);
                        budgetExpensesMap.put(month, budgetExpenses);
                        realProfitsMap.put(month, realProfits);
                        if (month == 1) {
                            lastMonthDetailis = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(yearAndMonthMap.get("year") - 1, 12, null);

                            realProfits_month = realProfits;
                            //1月份时，查询上年11月的数据，经过与上年的12月比较，获取真正12月的数据 2019-1-10修改
                            lastMonth_LastDetailis = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(yearAndMonthMap.get("year") - 1, 11, null);

                            if (null != lastMonthDetailis && lastMonthDetailis.getRealProfits() != null) {
                                lastMonthRealProfits = getNum(lastMonthDetailis.getRealProfits() - (lastMonth_LastDetailis == null ? 0 : lastMonth_LastDetailis.getRealProfits()));
                            }
                            realProfitsScaleMap.put(month, UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber((realProfits_month - lastMonthRealProfits), lastMonthRealProfits)));
                        } else if (month == 2) {
                            lastMonthDetailis = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(yearAndMonthMap.get("year"), 1, null);

                            if (null != lastMonthDetailis && lastMonthDetailis.getRealProfits() != null) {
                                lastMonthRealProfits = getNum(lastMonthDetailis.getRealProfits());
                                realProfits_month = realProfits - lastMonthDetailis.getRealProfits();
                            }
                            realProfitsScaleMap.put(month, UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber((realProfits_month - lastMonthRealProfits), lastMonthRealProfits)));
                        } else {
                            lastMonthDetailis = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(yearAndMonthMap.get("year"), month - 1, null);
                            //查询上月的上月的数据，经过与当月比较，获取当月的数据 2019-1-10修改
                            lastMonth_LastDetailis = sysBudgetDetailsBusinessService.sysBudgetRealProfitsByMonth(yearAndMonthMap.get("year"), month - 2, null);

                            if (null != lastMonthDetailis && lastMonthDetailis.getRealProfits() != null) {
                                lastMonthRealProfits = getNum(lastMonthDetailis.getRealProfits() - (lastMonth_LastDetailis == null ? 0 : lastMonth_LastDetailis.getRealProfits()));
                                realProfits_month = realProfits - lastMonthDetailis.getRealProfits();
                            }
                            realProfitsScaleMap.put(month, UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber((realProfits_month - lastMonthRealProfits), lastMonthRealProfits)));
                        }
                    }
                }
            }
            sysBudgetDetailsForyear.setRealIncomeMap(realIncomeMap);
            sysBudgetDetailsForyear.setBudgetIncomeMap(budgetIncomeMap);
            sysBudgetDetailsForyear.setRealExpensesTotalMap(realExpensesTotalMap);
            sysBudgetDetailsForyear.setBudgetExpensesMap(budgetExpensesMap);
            sysBudgetDetailsForyear.setRealProfitsMap(realProfitsMap);
            sysBudgetDetailsForyear.setRealProfitsScaleMap(realProfitsScaleMap);


            //sysBudgetDetailsEntity.setSysBudgetDetailsForMonth(sysBudgetDetailsForMonth);
            //sysBudgetDetailsEntity.setSysBudgetDetailsForYear(sysBudgetDetailsForyear);
            //存储到redis缓存，为大屏财务数据页面展示提供服务
            redisTemplate.opsForValue().set("sysBudgetDetails", sysBudgetDetailsForyear);
            //调取财务大屏数据接口
            sysDataAnalysisService.sysFinancialDataAnalysis();
            // return R.ok().putData(200, sysBudgetDetails, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("收费情况报表统计详细信息失败！" + e.getMessage());
        }
    }
}
