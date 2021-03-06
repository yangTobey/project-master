package com.spring.boot.controller;


import com.spring.boot.entity.SysProjectEnergyInfoEntity;
import com.spring.boot.service.SysProjectEnergyService;
import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
     * @param companyId 公司id
     * @param year
     * @param month
     * @param projectUnfinishedTotal
     * @param projectFinishedTotal
     * @param monthConsumptionElectricity
     * @param monthConsumptionWater
     * @param fileInfo
     * @return
     */
    @RequestMapping(value = "/addSysProjectEnergy", method = RequestMethod.POST)
    public R addSysProjectEnergy(@Valid SysProjectEnergyInfoEntity sysProjectEnergyEntity) {
        /*if (!UtilHelper.isLongNumer(companyId)) {
            return R.error(400, "公司id格式不合理，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(year)) {
            return R.error(400, "年份格式不合理，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(month)) {
            return R.error(400, "月份格式不合理，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(projectUnfinishedTotal)) {
            return R.error(400, "工程遗留数量格式不合理，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(projectFinishedTotal)) {
            return R.error(400, "已处理工程遗留问题数量格式不合理，或者不符合常理！");
        } else if (!UtilHelper.isDoubleNumer(monthConsumptionElectricity)) {
            return R.error(400, "月耗电量格式不合理,只能保留两位小数！");
        } else if (!UtilHelper.isDoubleNumer(monthConsumptionWater)) {
            return R.error(400, "月耗水量格式不合理,只能保留两位小数！");
        }*/
        try {
            Map<String, Object> map = sysProjectEnergyService.addSysProjectEnergy(sysProjectEnergyEntity);
            return R.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("新增工程能耗出错：" + e.getMessage());
            return R.error(500, "添加失败，服务器异常，请联系管理员！");
        }
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
     * @param projectId  主键id
     * @return
     */
    @RequestMapping(value = "/updateSysProjectEnergy", method = RequestMethod.POST)
    public R updateSysProjectEnergy(@Valid SysProjectEnergyInfoEntity sysProjectEnergyEntity) {
        /*if (!UtilHelper.isLongNumer(companyId)) {
            return R.error(400, "公司id格式不合理，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(year)) {
            return R.error(400, "年份格式不合理，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(month)) {
            return R.error(400, "月份格式不合理，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(projectUnfinishedTotal)) {
            return R.error(400, "工程遗留数量格式不合理，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(projectFinishedTotal)) {
            return R.error(400, "已处理工程遗留问题数量格式不合理，或者不符合常理！");
        } else if (!UtilHelper.isDoubleNumer(monthConsumptionElectricity)) {
            return R.error(400, "月耗电量格式不合理,只能保留两位小数！");
        } else if (!UtilHelper.isDoubleNumer(monthConsumptionWater)) {
            return R.error(400, "月耗水量格式不合理,只能保留两位小数！");
        } else*/ if (!UtilHelper.isLongNumer(sysProjectEnergyEntity.getProjectId().toString())) {
            return R.error(400, "主键id格式不正确，或者不符合常理！");
        }
        try {
            Map<String, Object> map = sysProjectEnergyService.updateSysProjectEnergy(sysProjectEnergyEntity);
            return R.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("更新工程能耗出错：" + e.getMessage());
            return R.error(500, "更新失败，服务器异常，请联系管理员！");
        }
    }

    /**
     * 删除信息
     * @param projectId
     * @return
     */
    @RequestMapping(value = "/deleteSysProject", method = RequestMethod.POST)
    public R deleteSysProject(@RequestParam(value = "projectId", required = false) String projectId) {
        if (!UtilHelper.isLongNumer(projectId)) {
            return R.error(400, "主键id格式不合理，或者不符合常理！");
        }
        try {
            Map<String, Object> map = sysProjectEnergyService.deleteSysProject(Long.valueOf(projectId));
            return R.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除工程能耗出错：" + e.getMessage());
            return R.error(500, "删除失败，服务器异常，请联系管理员！");
        }
    }

    /**
     * 根据主键id查找信息
     * @param projectId
     * @return
     */
    @RequestMapping(value = "/findSysProjectEnergyById", method = RequestMethod.POST)
    public R findSysProjectEnergyById(@RequestParam(value = "projectId", required = false) String projectId) {
        if (!UtilHelper.isLongNumer(projectId)) {
            return R.error(400, "主键id格式不合理，或者不符合常理！");
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
    @RequestMapping(value = "/sysProjectEnergyList", method = RequestMethod.POST)
    public R sysProjectEnergyList(@RequestParam(value = "companyId", required = false) String companyId,@RequestParam(value = "year", required = false) String year
    ,@RequestParam(value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset
            , @RequestParam(value = "month", required = false) String month) {
        if (!UtilHelper.isIntegerNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字，或者不符合常理！");
        }else if (!UtilHelper.isLongNumer(companyId)) {
            return R.error(400, "公司id格式不合理，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(year)) {
            return R.error(400, "年份格式不合理，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(month)) {
            return R.error(400, "月份格式不正确，或者不符合常理！");
        }
        Map<String, Object> map = sysProjectEnergyService.sysProjectEnergyList(Long.valueOf(companyId),Integer.valueOf(year),Integer.valueOf(limit),Integer.valueOf(offset),Integer.valueOf(month));
        return R.ok(map);
    }
    /**
     * 查找年度和月度报表统计信息
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/sysProjectEnergyAnalysis", method = RequestMethod.POST)
    public R sysProjectEnergyAnalysis(@RequestParam(value = "companyId", required = false) String companyId,
                                      @RequestParam(value = "year", required = false) String year,
                                      @RequestParam(value = "month", required = false) String month) {
        if (!UtilHelper.isLongNumer(companyId)) {
            return R.error(400, "公司id格式不合理，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(year)) {
            return R.error(400, "年份格式不正确，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(month)) {
            return R.error(400, "月份格式不正确，或者不符合常理！");
        }
        Map<String, Object> map = sysProjectEnergyService.sysProjectEnergyAnalysis(Long.valueOf(companyId), Integer.valueOf(year), Integer.valueOf(month));
        return R.ok(map);
    }
    /**
     * 查找年度每月报表统计信息
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/sysProjectEnergyAnalysisForMonth", method = RequestMethod.POST)
    public R sysProjectEnergyAnalysisForMonth(@RequestParam(value = "companyId", required = false) String companyId,
                                              @RequestParam(value = "year", required = false) String year,
                                              @RequestParam(value = "month", required = false) String month) {
        if (!UtilHelper.isLongNumer(companyId)) {
            return R.error(400, "公司id格式不合理，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(year)) {
            return R.error(400, "年份格式不正确，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(month)) {
            return R.error(400, "月份格式不正确，或者不符合常理！");
        }
        Map<String, Object> map = sysProjectEnergyService.sysProjectEnergyAnalysisForMonth(Long.valueOf(companyId), Integer.valueOf(year), Integer.valueOf(month));
        return R.ok(map);
    }
    /**
     * 根据公司id获取附件文档信息
     *
     * @param projectId
     * @return
     */
    @RequestMapping(value = "/findSysProjectFileById", method = RequestMethod.POST)
    public R findSysProjectFileById(@RequestParam(value = "projectId", required = false) String projectId) {
        if (!UtilHelper.isLongNumer(projectId)) {
            return R.error(400, "主键ID格式不正确，或者不符合常理，请联系系统管理员！");
        }
        Map<String, Object> map = sysProjectEnergyService.findSysProjectFileById(Long.valueOf(projectId));
        return R.ok(map);
    }
}
