package com.spring.boot.service;

import com.spring.boot.bean.master.SysUser;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysFinancialService {
    /**
     * 新增收费请款详细信息
     *
     * @return
     */
    Map<String, Object> addSysCharge(Long companyId, Double chargeMoney, Double chargeMoneyNow, Double chargeDebt, Double chargeDebtReturn);
    /**
     * 更新收费请款详细信息
     *
     * @return
     */
    Map<String, Object> updateSysCharge(Long chargeId,Long companyId, Double chargeMoney, Double chargeMoneyNow, Double chargeDebt, Double chargeDebtReturn);


}
