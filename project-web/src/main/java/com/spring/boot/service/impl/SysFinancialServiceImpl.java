package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.bean.master.SysChargeDetails;
import com.spring.boot.service.SysFinancialService;
import com.spring.boot.service.web.SysAccountsReceivableBusinessService;
import com.spring.boot.service.web.SysChargeBusinessService;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.service.web.SysUserCompanyBusinessService;
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
