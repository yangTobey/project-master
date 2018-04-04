package com.spring.boot.controller;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.service.SysCompanyService;
import com.spring.boot.service.SysFinancialService;
import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 财务管理控制类
 * Created by xiaoyang on 2018/4/2.
 */
@RestController
@RequestMapping("/sysFinancial")
public class SysFinancialController {
    private static final Logger logger = Logger.getLogger(SysFinancialController.class);
    @Autowired
    private SysFinancialService sysFinancialService;

    /**
     * 查询系统应收账款列表
     *
     * @param limit  每页限制条数
     * @param offset 页码
     * @return
     */
    @RequestMapping(value = "/getSysAccountsList", method = RequestMethod.GET)
    public R getSysCompanyList(@RequestParam(value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset) {
        if (!UtilHelper.isNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字！");
        } else if (!UtilHelper.isNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字！");
        }
        Map<String, Object> map = null;
        return R.ok(map);
    }

    /**
     * 收费情况报表统计详细信息
     *
     * @param companyId 公司id
     * @return
     */
    @RequestMapping(value = "/sysChargeDetails", method = RequestMethod.GET)
    public R sysChargeDetails(@RequestParam(value = "companyId", required = false) String companyId) {
        if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不正确！");
        }
        Map<String, Object> map = sysFinancialService.sysChargeDetails(Long.valueOf(companyId));
        return R.ok(map);
    }

    /**
     * 根据主键id查找信息
     *
     * @param chargeId 主键d
     * @return
     */
    @RequestMapping(value = "/findSysChargeDetailsById", method = RequestMethod.GET)
    public R findSysChargeDetailsById(@RequestParam(value = "chargeId", required = false) String chargeId) {
        if (!UtilHelper.isNumer(chargeId)) {
            return R.error(400, "主键id格式不正确！");
        }
        Map<String, Object> map = sysFinancialService.findSysChargeDetailsById(Long.valueOf(chargeId));
        return R.ok(map);
    }

    /**
     * 新增收费情况详细信息
     *
     * @param companyId        公司id
     * @param chargeMoney      收费任务额
     * @param chargeMoneyNow   当期收费
     * @param chargeDebt       清欠任务额
     * @param chargeDebtReturn 清欠回款
     * @return
     */
    @RequestMapping(value = "/addSysCharge", method = RequestMethod.GET)
    public R addSysCharge(@RequestParam(value = "companyId", required = false) String companyId, @RequestParam(value = "chargeMoney", required = false) String chargeMoney
            , @RequestParam(value = "chargeMoneyNow", required = false) String chargeMoneyNow, @RequestParam(value = "chargeDebt", required = false) String chargeDebt
            , @RequestParam(value = "chargeDebtReturn", required = false) String chargeDebtReturn) {
        if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不正确！");
        } else if (!UtilHelper.isDoubleNumer(chargeMoney)) {
            return R.error(400, "收费任务额格式不正确，只能保留两位小数！");
        } else if (!UtilHelper.isDoubleNumer(chargeMoneyNow)) {
            return R.error(400, "当期收费格式不正确，只能保留两位小数！");
        } else if (!UtilHelper.isDoubleNumer(chargeDebt)) {
            return R.error(400, "清欠任务额格式不正确，只能保留两位小数！");
        } else if (!UtilHelper.isDoubleNumer(chargeDebtReturn)) {
            return R.error(400, "清欠回款格式不正确，只能保留两位小数！");
        }
        Map<String, Object> map = sysFinancialService.addSysCharge(Long.valueOf(companyId), Double.valueOf(chargeMoney), Double.valueOf(chargeMoneyNow)
                , Double.valueOf(chargeDebt), Double.valueOf(chargeDebtReturn));
        return R.ok(map);
    }

    /**
     * 更新收费情况详细信息
     *
     * @param companyId        公司id
     * @param chargeMoney      收费任务额
     * @param chargeMoneyNow   当期收费
     * @param chargeDebt       清欠任务额
     * @param chargeDebtReturn 清欠回款
     * @return
     */
    @RequestMapping(value = "/updateSysCharge", method = RequestMethod.GET)
    public R updateSysCharge(@RequestParam(value = "chargeId", required = false) String chargeId, @RequestParam(value = "companyId", required = false) String companyId
            , @RequestParam(value = "chargeMoney", required = false) String chargeMoney, @RequestParam(value = "chargeMoneyNow", required = false) String chargeMoneyNow
            , @RequestParam(value = "chargeDebt", required = false) String chargeDebt, @RequestParam(value = "chargeDebtReturn", required = false) String chargeDebtReturn) {
        if (!UtilHelper.isNumer(chargeId)) {
            return R.error(400, "主键id格式不正确！");
        } else if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不正确！");
        } else if (!UtilHelper.isDoubleNumer(chargeMoney)) {
            return R.error(400, "收费任务额格式不正确，只能保留两位小数！");
        } else if (!UtilHelper.isDoubleNumer(chargeMoneyNow)) {
            return R.error(400, "当期收费格式不正确，只能保留两位小数！");
        } else if (!UtilHelper.isDoubleNumer(chargeDebt)) {
            return R.error(400, "清欠任务额格式不正确，只能保留两位小数！");
        } else if (!UtilHelper.isDoubleNumer(chargeDebtReturn)) {
            return R.error(400, "清欠回款格式不正确，只能保留两位小数！");
        }
        Map<String, Object> map = sysFinancialService.updateSysCharge(Long.valueOf(chargeId), Long.valueOf(companyId), Double.valueOf(chargeMoney), Double.valueOf(chargeMoneyNow)
                , Double.valueOf(chargeDebt), Double.valueOf(chargeDebtReturn));
        return R.ok(map);
    }
    /**
     * 月度应收账款报表统计详细信息
     *
     * @param companyId 公司id
     * @return
     */
    @RequestMapping(value = "/sysAccountsReceivableAnalysis", method = RequestMethod.GET)
    public R sysAccountsReceivableAnalysis(@RequestParam(value = "companyId", required = false) String companyId) {
        if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不正确！");
        }
        Map<String, Object> map = sysFinancialService.sysChargeDetails(Long.valueOf(companyId));
        return R.ok(map);
    }
    /**
     * 新增月度应收账款报表统计详细信息
     *
     * @param sysAccountsReceivable 实体信息
     * @return
     */
    @RequestMapping(value = "/addSysAccountsReceivable", method = RequestMethod.GET)
    public R addSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable) {

        Map<String, Object> map = sysFinancialService.addSysAccountsReceivable(sysAccountsReceivable);
        return R.ok(map);
    }
    /**
     * 更新月度应收账款报表统计详细信息
     *
     * @param sysAccountsReceivable 实体信息
     * @return
     */
    @RequestMapping(value = "/updateSysAccountsReceivable", method = RequestMethod.GET)
    public R updateSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable) {

        Map<String, Object> map = sysFinancialService.addSysAccountsReceivable(sysAccountsReceivable);
        return R.ok(map);
    }
}
