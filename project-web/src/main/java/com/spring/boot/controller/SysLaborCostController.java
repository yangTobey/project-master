package com.spring.boot.controller;

import com.spring.boot.service.SysCompanyService;
import com.spring.boot.service.SysLaborCostService;
import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
     * 查询公司信息
     *
     * @return
     */
    @RequestMapping(value = "/getSysCompanyList", method = RequestMethod.GET)
    public R getSysCompanyList() {
        Map<String, Object> map = sysLaborCostService.getSysLaborCostList();
        return R.ok().put("200", map);
    }

    /**
     * 新增人员成本信息
     * @param companyId 公司id
     * @param year 年份
     * @param month 月份
     * @param propertyLaborCost 物业月人工成本支出
     * @param propertyHeadcountTotal 物业月人员编制
     * @param propertyEmployeeTotal 物业月人员在职人数
     * @param propertyEntryTotal 物业月入职人数
     * @param propertyDemissionTotal 物业月离职人数
     * @param eBusinessLaborCost 电商月人工成本支出
     * @param eBusinessHeadcountTotal 电商月人员编制
     * @param eBusinessEmployeeTotal 电商月人员在职人数
     * @param eBusinessEntryTotal 电商月入职人数
     * @param eBusinessDemissionTotal 电商月离职人数
     * @param saleLaborCost 销配月人工成本支出
     * @param saleHeadcountTotal 销配月人员编制
     * @param saleEmployeeTotal 销配月人员在职人数
     * @param saleEntryTotal 销配月入职人数
     * @param saleDemissionTotal 销配月离职人数
     * @return
     */
    @RequestMapping(value = "/addSysLaborCost", method = RequestMethod.GET)
    public String addSysLaborCost(String companyId, String year, String month,String propertyLaborCost,String propertyHeadcountTotal,String propertyEmployeeTotal,String propertyEntryTotal,String propertyDemissionTotal,
                                  String eBusinessLaborCost,String eBusinessHeadcountTotal,String eBusinessEmployeeTotal,String eBusinessEntryTotal,String eBusinessDemissionTotal,
                                  String saleLaborCost,String saleHeadcountTotal,String saleEmployeeTotal,String saleEntryTotal,String saleDemissionTotal) {
        int count = sysLaborCostService.addSysLaborCost(companyId, year, month,propertyLaborCost,propertyHeadcountTotal,propertyEmployeeTotal,propertyEntryTotal,propertyDemissionTotal,
                eBusinessLaborCost,eBusinessHeadcountTotal,eBusinessEmployeeTotal,eBusinessEntryTotal,eBusinessDemissionTotal,
                saleLaborCost,saleHeadcountTotal,saleEmployeeTotal,saleEntryTotal,saleDemissionTotal);
        if (count > 0) {
            System.out.println("新增成功！");
        }
        return "success";
    }

    /**
     * 更新人员成本信息
     *
     * @param companyId      公司id
     * @param companyName    公司名称
     * @param companyPhone   公司电话
     * @param companyAddress 公司地址
     * @return
     */
    @RequestMapping(value = "/updateSysLaborCost", method = RequestMethod.GET)
    public String updateSysLaborCost(String companyId, String companyName, String companyPhone, String companyAddress) {
        logger.info("hello world");
        if (UtilHelper.isEmpty(companyId)) {
            return "公司id不能为空，请联系系统管理员进行修改！";
        }
        if (UtilHelper.isEmpty(companyName)) {
            return "新密码不能为空！";
        }
        int count = sysLaborCostService.updateSysLaborCostInfo(companyId, companyName, companyPhone, companyAddress);
        if (count > 0) {
            System.out.println("新增成功！");
        }
        return "success";

    }

    /**
     * 根据人员成本id删除人员成本（只状态，不作删除处理）
     *
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/deleteSysLaborCost", method = RequestMethod.GET)
    public R deleteSysLaborCost(String companyId) {
        if (UtilHelper.isEmpty(companyId)) {
            return R.error(201, "公司编号不能为空，请联系系统管理员！");
        }
        int count = sysLaborCostService.deleteSysLaborCostInfo(companyId);
        if (count > 0) {
            return R.ok(200, "公司编号不能为空，请联系系统管理员！");
        }
        return R.error(201, "系统异常，请联系系统管理员！");

    }

}
