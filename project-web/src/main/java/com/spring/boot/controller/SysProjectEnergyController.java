package com.spring.boot.controller;

import com.spring.boot.service.SysProjectEnergyService;
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
 * 工程能耗管理控制类
 * Created by xiaoyang on 2018/4/13.
 */
@RestController
@RequestMapping("/sysProjectEnergy")
public class SysProjectEnergyController {
    private static final Logger logger = Logger.getLogger(SysProjectEnergyController.class);
    @Autowired
    private SysProjectEnergyService sysProjectEnergyService;

    /**
     * 新增工程能耗信息
     *
     * @param companyId                   公司id
     * @param year
     * @param month
     * @param projectUnfinishedTotal
     * @param projectFinishedTotal
     * @param monthConsumptionElectricity
     * @param monthConsumptionWater
     * @param fileInfo
     * @return
     */
    @RequestMapping(value = "/addSysProjectEnergy", method = RequestMethod.GET)
    public R addSysProjectEnergy(@RequestParam(value = "companyId", required = false) String companyId, @RequestParam(value = "year", required = false) String year
            , @RequestParam(value = "month", required = false) String month, @RequestParam(value = "projectUnfinishedTotal", required = false) String projectUnfinishedTotal
            , @RequestParam(value = "projectFinishedTotal", required = false) String projectFinishedTotal, @RequestParam(value = "monthConsumptionElectricity", required = false) String monthConsumptionElectricity
            , @RequestParam(value = "monthConsumptionWater", required = false) String monthConsumptionWater, @RequestParam(value = "fileInfo", required = false) String fileInfo) {
        if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不合理！");
        } else if (!UtilHelper.isNumer(year)) {
            return R.error(400, "年份格式不合理！");
        } else if (!UtilHelper.isNumer(month)) {
            return R.error(400, "月份格式不合理！");
        } else if (!UtilHelper.isNumer(projectUnfinishedTotal)) {
            return R.error(400, "工程遗留数量格式不合理！");
        } else if (!UtilHelper.isNumer(projectFinishedTotal)) {
            return R.error(400, "已处理工程遗留问题数量格式不合理！");
        } else if (!UtilHelper.isNumer(monthConsumptionElectricity)) {
            return R.error(400, "月耗电量格式不合理！");
        } else if (!UtilHelper.isNumer(monthConsumptionWater)) {
            return R.error(400, "月耗水量格式不合理！");
        }
        Map<String, Object> map = sysProjectEnergyService.addSysProjectEnergy(Long.valueOf(companyId), Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(projectUnfinishedTotal)
                , Integer.valueOf(projectFinishedTotal), Integer.valueOf(monthConsumptionElectricity), Integer.valueOf(monthConsumptionWater), fileInfo);
        return R.ok(map);
    }

    /**
     * 更新工程能耗信息
     *
     * @param companyId
     * @param year
     * @param month
     * @param projectUnfinishedTotal
     * @param projectFinishedTotal
     * @param monthConsumptionElectricity
     * @param monthConsumptionWater
     * @param fileInfo
     * @param projectId                   主键id
     * @return
     */
    @RequestMapping(value = "/updateSysProjectEnergy", method = RequestMethod.GET)
    public R updateSysProjectEnergy(@RequestParam(value = "companyId", required = false) String companyId, @RequestParam(value = "year", required = false) String year
            , @RequestParam(value = "month", required = false) String month, @RequestParam(value = "projectUnfinishedTotal", required = false) String projectUnfinishedTotal
            , @RequestParam(value = "projectFinishedTotal", required = false) String projectFinishedTotal, @RequestParam(value = "monthConsumptionElectricity", required = false) String monthConsumptionElectricity
            , @RequestParam(value = "monthConsumptionWater", required = false) String monthConsumptionWater, @RequestParam(value = "fileInfo", required = false) String fileInfo
            , @RequestParam(value = "projectId", required = false) String projectId) {
        if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不合理！");
        } else if (!UtilHelper.isNumer(year)) {
            return R.error(400, "年份格式不合理！");
        } else if (!UtilHelper.isNumer(month)) {
            return R.error(400, "月份格式不合理！");
        } else if (!UtilHelper.isNumer(projectUnfinishedTotal)) {
            return R.error(400, "工程遗留数量格式不合理！");
        } else if (!UtilHelper.isNumer(projectFinishedTotal)) {
            return R.error(400, "已处理工程遗留问题数量格式不合理！");
        } else if (!UtilHelper.isNumer(monthConsumptionElectricity)) {
            return R.error(400, "月耗电量格式不合理！");
        } else if (!UtilHelper.isNumer(monthConsumptionWater)) {
            return R.error(400, "月耗水量格式不合理！");
        } else if (!UtilHelper.isNumer(projectId)) {
            return R.error(400, "主键id格式不正确！");
        }
        Map<String, Object> map = sysProjectEnergyService.updateSysProjectEnergy(Long.valueOf(projectId), Long.valueOf(companyId), Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(projectUnfinishedTotal)
                , Integer.valueOf(projectFinishedTotal), Integer.valueOf(monthConsumptionElectricity), Integer.valueOf(monthConsumptionWater), fileInfo);
        return R.ok(map);
    }

    /**
     * 删除信息
     * @param projectId
     * @return
     */
    @RequestMapping(value = "/deleteSysProject", method = RequestMethod.GET)
    public R deleteSysProject(@RequestParam(value = "projectId", required = false) String projectId) {
        if (!UtilHelper.isNumer(projectId)) {
            return R.error(400, "主键id格式不合理！");
        }
        Map<String, Object> map = sysProjectEnergyService.deleteSysProject(Long.valueOf(projectId));
        return R.ok(map);
    }

    /**
     * 根据主键id查找信息
     * @param projectId
     * @return
     */
    @RequestMapping(value = "/findSysProjectEnergyById", method = RequestMethod.GET)
    public R findSysProjectEnergyById(@RequestParam(value = "projectId", required = false) String projectId) {
        if (!UtilHelper.isNumer(projectId)) {
            return R.error(400, "主键id格式不合理！");
        }
        Map<String, Object> map = sysProjectEnergyService.findSysProjectEnergyById(Long.valueOf(projectId));
        return R.ok(map);
    }

    /**
     * 查找列表信息
     * @param companyId
     * @param year
     * @return
     */
    @RequestMapping(value = "/sysProjectEnergyList", method = RequestMethod.GET)
    public R sysProjectEnergyList(@RequestParam(value = "companyId", required = false) String companyId,@RequestParam(value = "year", required = false) String year
    ,@RequestParam(value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset) {
        if (!UtilHelper.isNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字！");
        } else if (!UtilHelper.isNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字！");
        }else if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不合理！");
        }else if (!UtilHelper.isNumer(year)) {
            return R.error(400, "年份格式不合理！");
        }
        Map<String, Object> map = sysProjectEnergyService.sysProjectEnergyList(Long.valueOf(companyId),Integer.valueOf(year),Integer.valueOf(limit),Integer.valueOf(offset));
        return R.ok(map);
    }
    /**
     * 查找报表统计信息
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/sysProjectEnergyAnalysis", method = RequestMethod.GET)
    public R sysProjectEnergyAnalysis(@RequestParam(value = "companyId", required = false) String companyId) {
        if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不合理！");
        }
        Map<String, Object> map = sysProjectEnergyService.sysProjectEnergyAnalysis(Long.valueOf(companyId));
        return R.ok(map);
    }
}
