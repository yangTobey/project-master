package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.bean.master.SysChargeDetails;
import com.spring.boot.bean.master.entity.SysReceivableAccountsOwnerEntity;
import com.spring.boot.service.SysFinancialService;
import com.spring.boot.service.web.SysAccountsReceivableBusinessService;
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
    private SysCompanyBusinessService sysCompanyBusinessService;

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
        map.put("year", UtilHelper.getYear());
        map.put("month", UtilHelper.getMonth());
        try {
            if (companyId == 0) {
                //获取用户权限下可操作的小区信息
                sysUserCompanyIds = SysUtil.getSysUserCompany();
            } else {
                sysUserCompanyIds = new ArrayList<Long>();
                sysUserCompanyIds.add(companyId);
            }
            map.put("sysUserCompanyIds",sysUserCompanyIds);
            //也可以封装成map传值
            SysAccountsReceivable sysAccountsReceivable = sysAccountsReceivableBusinessService.sysAccountsReceivableAnalysis(map);
            if(null!=sysAccountsReceivable){
                sysAccountsReceivable.setCouponScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompleteCoupon(),sysAccountsReceivable.getReceivableCoupon())));
                sysAccountsReceivable.setVacancyScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompleteVacancy(),sysAccountsReceivable.getReceivableVacancy())));
                sysAccountsReceivable.setSubsidyScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompleteSubsidy(),sysAccountsReceivable.getReceivableSubsidy())));
                sysAccountsReceivable.setSalesScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompleteSales(),sysAccountsReceivable.getReceivableSales())));
                sysAccountsReceivable.setOpenScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompleteOpen(),sysAccountsReceivable.getReceivableOpen())));
                sysAccountsReceivable.setPropertySubsidyScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompletePropertySubsidy(),sysAccountsReceivable.getReceivablePropertySubsidy())));
                sysAccountsReceivable.setHouseOtherScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatDoubleNumber(sysAccountsReceivable.getCompleteHouseOther(),sysAccountsReceivable.getReceivableHouseOther())));
            }
            List<SysAccountsReceivable> sysAccountsReceivableAnalysisList=sysAccountsReceivableBusinessService.sysAccountsReceivableAnalysisForMonth(sysUserCompanyIds);
            //获取所在年份对应的所有数据的月份
            String months=sysAccountsReceivableBusinessService.sysAccountsReceivableMonths(map);
            SysReceivableAccountsOwnerEntity sysReceivableAccountsOwnerEntity=null;
            if(null!=sysAccountsReceivableAnalysisList){
                for(SysAccountsReceivable sysAccountsReceivableForMonth:sysAccountsReceivableAnalysisList){
                    sysReceivableAccountsOwnerEntity=new SysReceivableAccountsOwnerEntity();
                    Double receivableAccountsOwner=sysAccountsReceivableForMonth.getReceivableAccountsOwner();
                    Double completeAccountsOwner=sysAccountsReceivableForMonth.getCompleteAccountsOwner();
                    Integer month=sysAccountsReceivableForMonth.getMonth();
                    sysReceivableAccountsOwnerEntity.setMonth(month);
                    switch (month) {
                        case 1:
                            sysReceivableAccountsOwnerEntity.setCompleteAccountsJan(completeAccountsOwner);
                            sysReceivableAccountsOwnerEntity.setReceivableAccountsJan(receivableAccountsOwner);
                            break;
                        case 2:
                            sysReceivableAccountsOwnerEntity.setReceivableAccountsFeb(receivableAccountsOwner);
                            sysReceivableAccountsOwnerEntity.setCompleteAccountsFeb(completeAccountsOwner);
                            break;
                        case 3:
                            sysReceivableAccountsOwnerEntity.setReceivableAccountsMar(receivableAccountsOwner);
                            sysReceivableAccountsOwnerEntity.setCompleteAccountsMar(completeAccountsOwner);
                            break;
                        case 4:
                            sysReceivableAccountsOwnerEntity.setReceivableAccountsApr(receivableAccountsOwner);
                            sysReceivableAccountsOwnerEntity.setCompleteAccountsApr(completeAccountsOwner);
                            break;
                        case 5:
                            sysReceivableAccountsOwnerEntity.setReceivableAccountsMay(receivableAccountsOwner);
                            sysReceivableAccountsOwnerEntity.setCompleteAccountsMay(completeAccountsOwner);
                            break;
                        case 6:
                            sysReceivableAccountsOwnerEntity.setReceivableAccountsJune(receivableAccountsOwner);
                            sysReceivableAccountsOwnerEntity.setCompleteAccountsJune(completeAccountsOwner);
                            break;
                        case 7:
                            sysReceivableAccountsOwnerEntity.setReceivableAccountsJuly(receivableAccountsOwner);
                            sysReceivableAccountsOwnerEntity.setCompleteAccountsJuly(completeAccountsOwner);
                            break;
                        case 8:
                            sysReceivableAccountsOwnerEntity.setReceivableAccountsAug(receivableAccountsOwner);
                            sysReceivableAccountsOwnerEntity.setCompleteAccountsAug(completeAccountsOwner);
                            break;
                        case 9:
                            sysReceivableAccountsOwnerEntity.setReceivableAccountsSept(receivableAccountsOwner);
                            sysReceivableAccountsOwnerEntity.setCompleteAccountsSept(completeAccountsOwner);
                            break;
                        case 10:
                            sysReceivableAccountsOwnerEntity.setReceivableAccountsOct(receivableAccountsOwner);
                            sysReceivableAccountsOwnerEntity.setCompleteAccountsOct(completeAccountsOwner);
                            break;
                        case 11:
                            sysReceivableAccountsOwnerEntity.setReceivableAccountsNov(receivableAccountsOwner);
                            sysReceivableAccountsOwnerEntity.setCompleteAccountsNov(completeAccountsOwner);
                            break;
                        case 12:
                            sysReceivableAccountsOwnerEntity.setReceivableAccountsDec(receivableAccountsOwner);
                            sysReceivableAccountsOwnerEntity.setCompleteAccountsDec(completeAccountsOwner);
                            break;
                    }
                }

            }
            sysReceivableAccountsOwnerEntity.setMonths(months);
            resultMap.put("sysAccountsReceivable",sysAccountsReceivable);
            resultMap.put("sysAccountsReceivableMonth",sysReceivableAccountsOwnerEntity);
            return R.ok().putData(200, resultMap, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("收费情况报表统计详细信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> addSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable) {
        try {
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
}
