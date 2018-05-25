package com.spring.boot.controller;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.bean.master.SysBudgetDetails;
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
        try {
            Map<String, Object> map = sysFinancialService.addSysCharge(Long.valueOf(companyId), Double.valueOf(chargeMoney), Double.valueOf(chargeMoneyNow)
                    , Double.valueOf(chargeDebt), Double.valueOf(chargeDebtReturn));
            return R.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("新增收费信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
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
        try {
            Map<String, Object> map = sysFinancialService.updateSysCharge(Long.valueOf(chargeId), Long.valueOf(companyId), Double.valueOf(chargeMoney), Double.valueOf(chargeMoneyNow)
                    , Double.valueOf(chargeDebt), Double.valueOf(chargeDebtReturn));
            return R.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("更新收费信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }
    /*****************************************************应收账款模块**********************************************************/

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
        Map<String, Object> map = sysFinancialService.sysAccountsReceivableAnalysis(Long.valueOf(companyId));
        return R.ok(map);
    }

    /**
     * 应收账款列表
     *
     * @param companyId 公司id
     * @param year      年份
     * @param limit     每页数据条数限制
     * @param offset
     * @return
     */
    @RequestMapping(value = "/sysAccountsReceivableList", method = RequestMethod.GET)
    public R sysAccountsReceivableList(@RequestParam(value = "companyId", required = false) String companyId, @RequestParam(value = "year", required = false) String year
            , @RequestParam(value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset) {
        if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不正确！");
        } else if (!UtilHelper.isNumer(year)) {
            return R.error(400, "年份格式不正确！");
        } else if (!UtilHelper.isNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字！");
        } else if (!UtilHelper.isNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字！");
        }
        Map<String, Object> map = sysFinancialService.sysAccountsReceivableList(Long.valueOf(companyId), Integer.valueOf(year), Integer.valueOf(limit), Integer.valueOf(offset));
        return R.ok(map);
    }

    /**
     * 根据主键id查找数据
     *
     * @param accountsId 主键id
     * @return
     */
    @RequestMapping(value = "/findSysAccountsReceivableById", method = RequestMethod.GET)
    public R findSysAccountsReceivableById(@RequestParam(value = "accountsId", required = false) String accountsId) {
        if (!UtilHelper.isNumer(accountsId)) {
            return R.error(400, "主键id格式不正确！");
        }
        Map<String, Object> map = sysFinancialService.findSysAccountsReceivableById(Long.valueOf(accountsId));
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
        if(!UtilHelper.isNumer(String.valueOf(sysAccountsReceivable.getCompanyId()))){
            return R.error(400, "公司id格式不正确！！");
        }else if(!UtilHelper.isNumer(String.valueOf(sysAccountsReceivable.getMonth()))){
            return R.error(400, "月份格式不正确！！");
        }else if(!UtilHelper.isNumer(String.valueOf(sysAccountsReceivable.getYear()))){
            return R.error(400, "年份格式不正确！！");
        }
        Double receivableHouse = sysAccountsReceivable.getReceivableCoupon() + sysAccountsReceivable.getReceivableVacancy() + sysAccountsReceivable.getReceivableSubsidy()
                + sysAccountsReceivable.getReceivableSales() + sysAccountsReceivable.getReceivableOpen() + sysAccountsReceivable.getReceivablePropertySubsidy()
                + sysAccountsReceivable.getReceivableHouseOther();
        Double completeHouse = sysAccountsReceivable.getCompleteCoupon() + sysAccountsReceivable.getCompleteVacancy() + sysAccountsReceivable.getCompleteSubsidy()
                + sysAccountsReceivable.getCompleteSales() + sysAccountsReceivable.getCompleteOpen() + sysAccountsReceivable.getCompletePropertySubsidy()
                + sysAccountsReceivable.getCompleteHouseOther();
        if (!receivableHouse.equals(sysAccountsReceivable.getReceivableHouse())) {
            return R.error(400, "地产 已 收款总数不对，请联系管理员进行处理！");
        } else if (!completeHouse.equals(sysAccountsReceivable.getCompleteHouse())) {
            return R.error(400, "地产 应 收款总数不对，请联系管理员进行处理！");
        }
        try {
            Map<String, Object> map = sysFinancialService.addSysAccountsReceivable(sysAccountsReceivable);
            return R.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("新增信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    /**
     * 更新月度应收账款报表统计详细信息
     *
     * @param sysAccountsReceivable 实体信息
     * @return
     */
    @RequestMapping(value = "/updateSysAccountsReceivable", method = RequestMethod.GET)
    public R updateSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable) {
        if(!UtilHelper.isNumer(String.valueOf(sysAccountsReceivable.getCompanyId()))){
            return R.error(400, "公司id格式不正确！！");
        }else if(!UtilHelper.isNumer(String.valueOf(sysAccountsReceivable.getMonth()))){
            return R.error(400, "月份格式不正确！！");
        }else if(!UtilHelper.isNumer(String.valueOf(sysAccountsReceivable.getYear()))){
            return R.error(400, "年份格式不正确！！");
        }
        Double receivableHouse = sysAccountsReceivable.getReceivableCoupon() + sysAccountsReceivable.getReceivableVacancy() + sysAccountsReceivable.getReceivableSubsidy()
                + sysAccountsReceivable.getReceivableSales() + sysAccountsReceivable.getReceivableOpen() + sysAccountsReceivable.getReceivablePropertySubsidy()
                + sysAccountsReceivable.getReceivableHouseOther();
        Double completeHouse = sysAccountsReceivable.getCompleteCoupon() + sysAccountsReceivable.getCompleteVacancy() + sysAccountsReceivable.getCompleteSubsidy()
                + sysAccountsReceivable.getCompleteSales() + sysAccountsReceivable.getCompleteOpen() + sysAccountsReceivable.getCompletePropertySubsidy()
                + sysAccountsReceivable.getCompleteHouseOther();
        if (!receivableHouse.equals(sysAccountsReceivable.getReceivableHouse())) {
            return R.error(400, "地产 已 收款总数不对，请联系管理员进行处理！");
        } else if (!completeHouse.equals(sysAccountsReceivable.getCompleteHouse())) {
            return R.error(400, "地产 应 收款总数不对，请联系管理员进行处理！");
        }
        try {
            Map<String, Object> map = sysFinancialService.updateSysAccountsReceivable(sysAccountsReceivable);
            return R.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("更新信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    /**
     * 删除月度应收账款报表统计详细信息
     *
     * @param accountsId 主键id
     * @return
     */
    @RequestMapping(value = "/deleteSysAccountsReceivable", method = RequestMethod.GET)
    public R deleteSysAccountsReceivable(@RequestParam(value = "accountsId", required = false) String accountsId) {
        if (!UtilHelper.isNumer(accountsId)) {
            return R.error(400, "主键id格式不正确，请联系系统管理员进行处理！");
        }
        Map<String, Object> map = sysFinancialService.deleteSysAccountsReceivable(Long.valueOf(accountsId));
        return R.ok(map);
    }

    /*****************************************************预算执行模块**********************************************************/
    /**
     * 预算报表分析
     *
     * @param companyId 公司id
     * @return
     */
    @RequestMapping(value = "/sysBudgetDetailsAnalysis", method = RequestMethod.GET)
    public R sysBudgetDetailsAnalysis(@RequestParam(value = "companyId", required = false) String companyId) {
        if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不正确！");
        }
        try {
            Map<String, Object> map = sysFinancialService.sysBudgetDetailsAnalysis(Long.valueOf(companyId));
            return R.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除执行预算信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    /**
     * 预算执行列表信息
     *
     * @param companyId 公司id
     * @return
     */
    @RequestMapping(value = "/sysBudgetDetailsList", method = RequestMethod.GET)
    public R sysBudgetDetailsList(@RequestParam(value = "companyId", required = false) String companyId, @RequestParam(value = "year", required = false) String year
            , @RequestParam(value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset) {
        if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不正确！");
        } else if (!UtilHelper.isNumer(year)) {
            return R.error(400, "年份格式不正确！");
        } else if (!UtilHelper.isNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字！");
        } else if (!UtilHelper.isNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字！");
        }
        Map<String, Object> map = sysFinancialService.sysBudgetDetailsList(Long.valueOf(companyId), Integer.valueOf(year), Integer.valueOf(limit), Integer.valueOf(offset));
        return R.ok(map);
    }

    /**
     * 根据主键id查找数据
     *
     * @param budgetId 主键id
     * @return
     */
    @RequestMapping(value = "/findSysBudgetDetailsById", method = RequestMethod.GET)
    public R findSysBudgetDetailsById(@RequestParam(value = "budgetId", required = false) String budgetId) {
        if (!UtilHelper.isNumer(budgetId)) {
            return R.error(400, "主键id格式不正确！");
        }
        Map<String, Object> map = sysFinancialService.findSysBudgetDetailsById(Long.valueOf(budgetId));
        return R.ok(map);
    }

    /**
     * 新增执行预算信息
     *
     * @param sysBudgetDetails 实体信息
     * @return
     */
    @RequestMapping(value = "/addSysBudgetDetails", method = RequestMethod.GET)
    public R addSysBudgetDetails(SysBudgetDetails sysBudgetDetails) {
        if(!UtilHelper.isNumer(String.valueOf(sysBudgetDetails.getCompanyId()))){
            return R.error(400, "公司id格式不正确！！");
        }else if(!UtilHelper.isNumer(String.valueOf(sysBudgetDetails.getMonth()))){
            return R.error(400, "月份格式不正确！！");
        }else if(!UtilHelper.isNumer(String.valueOf(sysBudgetDetails.getYear()))){
            return R.error(400, "年份格式不正确！！");
        }
        Double realExpensesTotal = sysBudgetDetails.getPersonnelCost() + sysBudgetDetails.getAdministrativeCost() + sysBudgetDetails.getMaterialCost()
                + sysBudgetDetails.getEnergyCost() + sysBudgetDetails.getEquipmentCost() + sysBudgetDetails.getCleaningCost()
                + sysBudgetDetails.getAfforestCost() + sysBudgetDetails.getOrderMaintenanceCost() + sysBudgetDetails.getCommunityActivitiesCost() + sysBudgetDetails.getOtherCost();
        if (!realExpensesTotal.equals(sysBudgetDetails.getRealExpensesTotal())) {
            return R.error(400, "实际总支出与详细数据合计总数不对，请联系管理员进行处理！");
        }
        try {
            Map<String, Object> map = sysFinancialService.addSysBudgetDetails(sysBudgetDetails);
            return R.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("新增信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    /**
     * 更新执行预算信息
     *
     * @param sysBudgetDetails 实体信息
     * @return
     */
    @RequestMapping(value = "/updateSysBudgetDetails", method = RequestMethod.GET)
    public R updateSysBudgetDetails(SysBudgetDetails sysBudgetDetails) {
        if(!UtilHelper.isNumer(String.valueOf(sysBudgetDetails.getCompanyId()))){
            return R.error(400, "公司id格式不正确！！");
        }else if(!UtilHelper.isNumer(String.valueOf(sysBudgetDetails.getMonth()))){
            return R.error(400, "月份格式不正确！！");
        }else if(!UtilHelper.isNumer(String.valueOf(sysBudgetDetails.getYear()))){
            return R.error(400, "年份格式不正确！！");
        }
        Double realExpensesTotal = sysBudgetDetails.getPersonnelCost() + sysBudgetDetails.getAdministrativeCost() + sysBudgetDetails.getMaterialCost()
                + sysBudgetDetails.getEnergyCost() + sysBudgetDetails.getEquipmentCost() + sysBudgetDetails.getCleaningCost()
                + sysBudgetDetails.getAfforestCost() + sysBudgetDetails.getOrderMaintenanceCost() + sysBudgetDetails.getCommunityActivitiesCost() + sysBudgetDetails.getOtherCost();
        if (!UtilHelper.isNumer(String.valueOf(sysBudgetDetails.getBudgetId()))) {
            return R.error(400, "主键id格式不正确，请联系系统管理员进行处理！");
        } else if (!realExpensesTotal.equals(sysBudgetDetails.getRealExpensesTotal())) {
            return R.error(400, "实际总支出与详细数据合计总数不对，请联系管理员进行处理！");
        }
        try {
            Map<String, Object> map = sysFinancialService.updateSysBudgetDetails(sysBudgetDetails);
            return R.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("更新执行预算信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }

    }

    /**
     * 更新执行预算信息
     *
     * @param budgetId 主键id
     * @return
     */
    @RequestMapping(value = "/deleteSysBudgetDetails", method = RequestMethod.GET)
    public R deleteSysBudgetDetails(@RequestParam(value = "budgetId", required = false) String budgetId) {
        if (!UtilHelper.isNumer(budgetId)) {
            return R.error(400, "主键id格式不正确，请联系系统管理员进行处理！");
        }
        try {
            Map<String, Object> map = sysFinancialService.deleteSysBudgetDetails(Long.valueOf(budgetId));
            return R.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除执行预算信息失败！" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }
}
