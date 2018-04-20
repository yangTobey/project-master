package com.spring.boot.controller;

import com.spring.boot.service.SysLaborCostService;
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
 * 人员成本控制类
 * Created by Administrator on 2018/1/25.
 */
@RestController
@RequestMapping("/sysLaborCost")
public class SysLaborCostController {
    private static final Logger logger = Logger.getLogger(SysLaborCostController.class);
    @Autowired
    private SysLaborCostService sysLaborCostService;

    /**
     * 查询人员成本信息（数据分析图表数据）
     *
     * @param companyId 公司id
     * @return
     */
    @RequestMapping(value = "/getSysLaborCostAnalysis", method = RequestMethod.GET)
    public R getSysLaborCostAnalysis(@RequestParam(value = "companyId", required = false) String companyId) {
        if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不合理！");
        }
        Map<String, Object> map = sysLaborCostService.getSysLaborCostAnalysis(Long.valueOf(companyId));
        return R.ok(map);
    }

    /**
     * 查询人员成本信息（列表数据）
     *
     * @param companyId 公司id
     * @param year      年份
     * @return
     */
    @RequestMapping(value = "/getSysLaborCostList", method = RequestMethod.GET)
    public R getSysLaborCostList(@RequestParam(value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset,
                                 @RequestParam(value = "companyId", required = false) String companyId, @RequestParam(value = "year", required = false) String year) {
        if (!UtilHelper.isNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字！");
        } else if (!UtilHelper.isNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字！");
        } else if (!UtilHelper.isNumer(year)) {
            return R.error(400, "年份格式不合理！");
        } else if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不合理！");
        }
        Map<String, Object> map = sysLaborCostService.getSysLaborCostList(Integer.valueOf(limit), Integer.valueOf(offset), Long.valueOf(companyId), Integer.valueOf(year));
        return R.ok(map);
    }

    /**
     * 新增人员成本信息
     *
     * @param companyId               公司id
     * @param year                    年份
     * @param month                   月份
     * @param propertyLaborCost       物业月人工成本支出
     * @param propertyHeadcountTotal  物业月人员编制
     * @param propertyEmployeeTotal   物业月人员在职人数
     * @param propertyEntryTotal      物业月入职人数
     * @param propertyDemissionTotal  物业月离职人数
     * @param eBusinessLaborCost      电商月人工成本支出
     * @param eBusinessHeadcountTotal 电商月人员编制
     * @param eBusinessEmployeeTotal  电商月人员在职人数
     * @param eBusinessEntryTotal     电商月入职人数
     * @param eBusinessDemissionTotal 电商月离职人数
     * @param saleLaborCost           销配月人工成本支出
     * @param saleHeadcountTotal      销配月人员编制
     * @param saleEmployeeTotal       销配月人员在职人数
     * @param saleEntryTotal          销配月入职人数
     * @param saleDemissionTotal      销配月离职人数
     * @return
     */
    @RequestMapping(value = "/addSysLaborCost", method = RequestMethod.GET)
    public R addSysLaborCost(@RequestParam(value = "companyId", required = false) String companyId, @RequestParam(value = "year", required = false) String year
            , @RequestParam(value = "month", required = false) String month, @RequestParam(value = "propertyLaborCost", required = false) String propertyLaborCost
            , @RequestParam(value = "propertyHeadcountTotal", required = false) String propertyHeadcountTotal, @RequestParam(value = "propertyEmployeeTotal", required = false) String propertyEmployeeTotal
            , @RequestParam(value = "propertyEntryTotal", required = false) String propertyEntryTotal, @RequestParam(value = "propertyDemissionTotal", required = false) String propertyDemissionTotal
            , @RequestParam(value = "eBusinessLaborCost", required = false) String eBusinessLaborCost, @RequestParam(value = "eBusinessHeadcountTotal", required = false) String eBusinessHeadcountTotal
            , @RequestParam(value = "eBusinessEmployeeTotal", required = false) String eBusinessEmployeeTotal, @RequestParam(value = "eBusinessEntryTotal", required = false) String eBusinessEntryTotal
            , @RequestParam(value = "eBusinessDemissionTotal", required = false) String eBusinessDemissionTotal, @RequestParam(value = "saleLaborCost", required = false) String saleLaborCost
            , @RequestParam(value = "saleHeadcountTotal", required = false) String saleHeadcountTotal, @RequestParam(value = "saleEmployeeTotal", required = false) String saleEmployeeTotal
            , @RequestParam(value = "saleEntryTotal", required = false) String saleEntryTotal, @RequestParam(value = "saleDemissionTotal", required = false) String saleDemissionTotal) {
        if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不合理！");
        } else if (!UtilHelper.isNumer(year)) {
            return R.error(400, "年份格式不合理！");
        } else if (!UtilHelper.isNumer(month)) {
            return R.error(400, "月份格式不合理！");
        } else if (!UtilHelper.isDoubleNumer(propertyLaborCost)) {
            return R.error(400, "物业月人工成本支出格式不正确！");
        } else if (!UtilHelper.isNumer(propertyHeadcountTotal)) {
            return R.error(400, "物业月编制人数格式不正确！");
        } else if (!UtilHelper.isNumer(propertyEmployeeTotal)) {
            return R.error(400, "物业月在职人数格式不正确！");
        } else if (!UtilHelper.isNumer(propertyEntryTotal)) {
            return R.error(400, "物业月入职人数格式不正确！");
        } else if (!UtilHelper.isNumer(propertyDemissionTotal)) {
            return R.error(400, "物业月离职人数格式不正确！");
        }

        try {
            Map<String, Object> map = sysLaborCostService.addSysLaborCost(Long.valueOf(companyId), Integer.valueOf(year), Integer.valueOf(month), Double.valueOf(propertyLaborCost), Integer.valueOf(propertyHeadcountTotal)
                    , Integer.valueOf(propertyEmployeeTotal), Integer.valueOf(propertyEntryTotal), Integer.valueOf(propertyDemissionTotal),
                    UtilHelper.toDoubleNum(eBusinessLaborCost), UtilHelper.toIntegerNum(eBusinessHeadcountTotal), UtilHelper.toIntegerNum(eBusinessEmployeeTotal), UtilHelper.toIntegerNum(eBusinessEntryTotal), UtilHelper.toIntegerNum(eBusinessDemissionTotal),
                    UtilHelper.toDoubleNum(saleLaborCost), UtilHelper.toIntegerNum(saleHeadcountTotal), UtilHelper.toIntegerNum(saleEmployeeTotal), UtilHelper.toIntegerNum(saleEntryTotal), UtilHelper.toIntegerNum(saleDemissionTotal));
            return R.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("新增人员成本信息信息出错：" + e.getMessage());
            return R.error(500, "删除信息失败，服务器异常，请联系管理员！");
        }
    }

    /**
     * 更新人员成本信息
     *
     * @param laborCostId             人员成本id
     * @param companyId               公司id
     * @param year                    年份
     * @param month                   月份
     * @param propertyLaborCost       物业月人工成本支出
     * @param propertyHeadcountTotal  物业月人员编制
     * @param propertyEmployeeTotal   物业月人员在职人数
     * @param propertyEntryTotal      物业月入职人数
     * @param propertyDemissionTotal  物业月离职人数
     * @param eBusinessLaborCost      电商月人工成本支出
     * @param eBusinessHeadcountTotal 电商月人员编制
     * @param eBusinessEmployeeTotal  电商月人员在职人数
     * @param eBusinessEntryTotal     电商月入职人数
     * @param eBusinessDemissionTotal 电商月离职人数
     * @param saleLaborCost           销配月人工成本支出
     * @param saleHeadcountTotal      销配月人员编制
     * @param saleEmployeeTotal       销配月人员在职人数
     * @param saleEntryTotal          销配月入职人数
     * @param saleDemissionTotal      销配月离职人数
     * @return
     */
    @RequestMapping(value = "/updateSysLaborCost", method = RequestMethod.GET)
    public R updateSysLaborCost(@RequestParam(value = "laborCostId", required = false) String laborCostId, @RequestParam(value = "companyId", required = false) String companyId
            , @RequestParam(value = "year", required = false) String year, @RequestParam(value = "month", required = false) String month
            , @RequestParam(value = "propertyLaborCost", required = false) String propertyLaborCost, @RequestParam(value = "propertyHeadcountTotal", required = false) String propertyHeadcountTotal
            , @RequestParam(value = "propertyEmployeeTotal", required = false) String propertyEmployeeTotal, @RequestParam(value = "propertyEntryTotal", required = false) String propertyEntryTotal
            , @RequestParam(value = "propertyDemissionTotal", required = false) String propertyDemissionTotal, @RequestParam(value = "eBusinessLaborCost", required = false) String eBusinessLaborCost
            , @RequestParam(value = "eBusinessHeadcountTotal", required = false) String eBusinessHeadcountTotal, @RequestParam(value = "eBusinessEmployeeTotal", required = false) String eBusinessEmployeeTotal
            , @RequestParam(value = "eBusinessEntryTotal", required = false) String eBusinessEntryTotal, @RequestParam(value = "eBusinessDemissionTotal", required = false) String eBusinessDemissionTotal
            , @RequestParam(value = "saleLaborCost", required = false) String saleLaborCost, @RequestParam(value = "saleHeadcountTotal", required = false) String saleHeadcountTotal
            , @RequestParam(value = "saleEmployeeTotal", required = false) String saleEmployeeTotal, @RequestParam(value = "saleEntryTotal", required = false) String saleEntryTotal
            , @RequestParam(value = "saleDemissionTotal", required = false) String saleDemissionTotal) {
        if (UtilHelper.isEmpty(laborCostId)) {
            return R.error(400, "人员成本id不能为空，请联系系统管理员进行修改！");
        } else if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不合理！");
        } else if (!UtilHelper.isNumer(year)) {
            return R.error(400, "年份格式不合理！");
        } else if (!UtilHelper.isNumer(month)) {
            return R.error(400, "月份格式不合理！");
        } else if (!UtilHelper.isDoubleNumer(propertyLaborCost)) {
            return R.error(400, "物业月人工成本支出格式不正确！");
        } else if (!UtilHelper.isNumer(propertyHeadcountTotal)) {
            return R.error(400, "物业月编制人数格式不正确！");
        } else if (!UtilHelper.isNumer(propertyEmployeeTotal)) {
            return R.error(400, "物业月在职人数格式不正确！");
        } else if (!UtilHelper.isNumer(propertyEntryTotal)) {
            return R.error(400, "物业月入职人数格式不正确！");
        } else if (!UtilHelper.isNumer(propertyDemissionTotal)) {
            return R.error(400, "物业月离职人数格式不正确！");
        }
        try {
            Map<String, Object> map = sysLaborCostService.updateSysLaborCostInfo(Long.valueOf(laborCostId), Long.valueOf(companyId), Integer.valueOf(year), Integer.valueOf(month), Double.valueOf(propertyLaborCost), Integer.valueOf(propertyHeadcountTotal), Integer.valueOf(propertyEmployeeTotal), Integer.valueOf(propertyEntryTotal), Integer.valueOf(propertyDemissionTotal),
                    UtilHelper.toDoubleNum(eBusinessLaborCost), UtilHelper.toIntegerNum(eBusinessHeadcountTotal), UtilHelper.toIntegerNum(eBusinessEmployeeTotal), UtilHelper.toIntegerNum(eBusinessEntryTotal), UtilHelper.toIntegerNum(eBusinessDemissionTotal),
                    UtilHelper.toDoubleNum(saleLaborCost), UtilHelper.toIntegerNum(saleHeadcountTotal), UtilHelper.toIntegerNum(saleEmployeeTotal), UtilHelper.toIntegerNum(saleEntryTotal), UtilHelper.toIntegerNum(saleDemissionTotal));
            return R.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("更新人员成本信息出错：" + e.getMessage());
            return R.error(500, "删除信息失败，服务器异常，请联系管理员！");
        }
    }

    /**
     * 根据人员成本id查找详细信息
     *
     * @param laborCostId
     * @return
     */
    @RequestMapping(value = "/findSysLaborCostById", method = RequestMethod.GET)
    public R findSysLaborCostById(@RequestParam(value = "laborCostId", required = false) String laborCostId) {
        if (!UtilHelper.isNumer(laborCostId)) {
            return R.error(400, "人员成本id编号不能为空，请联系系统管理员！");
        }
        Map<String, Object> map = sysLaborCostService.findSysLaborCostById(Long.valueOf(laborCostId));
        return R.ok(map);

    }

    /**
     * 根据人员成本id删除人员成本（只状态，不作删除处理）
     *
     * @param laborCostId
     * @return
     */
    @RequestMapping(value = "/deleteSysLaborCost", method = RequestMethod.GET)
    public R deleteSysLaborCost(@RequestParam(value = "laborCostId", required = false) String laborCostId) {
        if (!UtilHelper.isNumer(laborCostId)) {
            return R.error(400, "人员成本id编号不能为空，请联系系统管理员！");
        }
        try {
            Map<String, Object> map = sysLaborCostService.deleteSysLaborCostInfo(Long.valueOf(laborCostId));
            return R.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除信息出错：" + e.getMessage());
            return R.error(500, "删除信息失败，服务器异常，请联系管理员！");
        }
    }
}
