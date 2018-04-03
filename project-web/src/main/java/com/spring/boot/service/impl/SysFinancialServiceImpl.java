package com.spring.boot.service.impl;

import com.spring.boot.bean.master.*;
import com.spring.boot.service.SysFinancialService;
import com.spring.boot.service.SysUserService;
import com.spring.boot.service.web.*;
import com.spring.boot.util.R;
import com.spring.boot.util.ShiroUtils;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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
    private SysUserRoleBusinessService sysUserRoleBusinessService;
    @Autowired
    private SysUserCompanyBusinessService sysUserCompanyBusinessService;
    @Autowired
    private SysCompanyBusinessService sysCompanyBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Map<String, Object> addSysCharge(Long companyId, Double chargeMoney, Double chargeMoneyNow, Double chargeDebt, Double chargeDebtReturn) {
        SysChargeDetails sysChargeDetails=new SysChargeDetails();
        sysChargeDetails.setChargeDebt(chargeDebt);
        sysChargeDetails.setChargeDebtReturn(chargeDebtReturn);
        sysChargeDetails.setChargeMoney(chargeMoney);
        sysChargeDetails.setChargeMoneyNow(chargeMoneyNow);
        sysChargeDetails.setCompanyId(companyId);
        sysChargeDetails.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
        try {
            int count=sysChargeBusinessService.addSysCharge(sysChargeDetails);
            if(count>0){
                return R.ok(200,"新增成功！");
            }else{
                return R.error(500,"新增失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("新增收费信息失败！"+e.getMessage());
            return R.error(500,"服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> updateSysCharge(Long chargeId, Long companyId, Double chargeMoney, Double chargeMoneyNow, Double chargeDebt, Double chargeDebtReturn) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("chargeMoney", chargeMoney);
        map.put("chargeMoneyNow", chargeMoneyNow);
        map.put("chargeDebt", chargeDebt);
        map.put("chargeDebtReturn", chargeDebtReturn);
        map.put("companyId", companyId);
        try {
            int count=sysChargeBusinessService.updateSysCharge(map);
            if(count>0){
                return R.ok(200,"更新成功！");
            }else{
                return R.error(500,"更新失败，请联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("更新收费信息失败！"+e.getMessage());
            return R.error(500,"服务器异常，请联系管理员！");
        }
    }
}
