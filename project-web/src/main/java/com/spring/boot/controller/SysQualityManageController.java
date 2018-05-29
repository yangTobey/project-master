package com.spring.boot.controller;

import com.spring.boot.service.SysCompanyService;
import com.spring.boot.service.SysQualityManageService;
import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@RestController
@RequestMapping("/sysQualityManage")
public class SysQualityManageController {
    private static final Logger logger = Logger.getLogger(SysQualityManageController.class);
    @Autowired
    private SysQualityManageService sysQualityManageService;

    /**
     * 获取品质管理年度报表数据
     *
     * @param companyId 公司id
     * @return
     */
    @RequestMapping(value = "/sysQualityManageAnalysis", method = RequestMethod.GET)
    public R sysQualityManageAnalysisForYear(@RequestParam(value = "companyId", required = false) String companyId) {
        if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不正确！");
        }
        Map<String, Object> map = sysQualityManageService.sysQualityManageAnalysis(Long.valueOf(companyId));
        return R.ok(map);
    }

    /**
     * 查询品质管理列表信息
     *
     * @param companyId 公司id
     * @param year      年份
     * @param limit     每页限制条数
     * @param offset    页码
     * @return
     */
    @RequestMapping(value = "/getSysQualityManageList", method = RequestMethod.GET)
    public R getSysQualityManageList(@RequestParam(value = "companyId", required = false) String companyId, @RequestParam(value = "year", required = false) String year
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
        Map<String, Object> map = sysQualityManageService.getSysQualityManageList(Long.valueOf(companyId), Integer.valueOf(year), Integer.valueOf(limit), Integer.valueOf(offset));
        return R.ok(map);
    }

    /**
     * 新增质量管理信息
     *
     * @param companyId
     * @param year
     * @param month
     * @param qualityCheck
     * @param qualityCheckPass
     * @param securityEvent
     * @param qualityCheckUnmodified
     * @return
     */
    @RequestMapping(value = "/addSysQualityManage", method = RequestMethod.GET)
    public R addSysQualityManage(@RequestParam(value = "companyId", required = false) String companyId, @RequestParam(value = "year", required = false) String year
            , @RequestParam(value = "month", required = false) String month, @RequestParam(value = "qualityCheck", required = false) String qualityCheck
            , @RequestParam(value = "qualityCheckPass", required = false) String qualityCheckPass
            , @RequestParam(value = "securityEvent", required = false) String securityEvent, @RequestParam(value = "qualityCheckUnmodified", required = false) String qualityCheckUnmodified
            , @RequestParam(value = "fileInfo", required = false) String fileInfo) {
        if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不正确！");
        } else if (!UtilHelper.isNumer(year)) {
            return R.error(400, "年份格式不正确！");
        } else if (!UtilHelper.isNumer(month)) {
            return R.error(400, "月份格式不正确！");
        } else if (!UtilHelper.isNumer(qualityCheck)) {
            return R.error(400, "月品质检查项格式不正确！");
        } else if (!UtilHelper.isNumer(qualityCheckPass)) {
            return R.error(400, "月品质检查合格项格式不正确！");
        } else if (Integer.valueOf(qualityCheck)<Integer.valueOf(qualityCheckPass)) {
            return R.error(400, "月品质检查 合格项 不能 大于 月品质检查项！");
        } else if (!UtilHelper.isNumer(securityEvent)) {
            return R.error(400, "月安全事故数量项格式不正确！");
        } else if (!UtilHelper.isNumer(qualityCheckUnmodified)) {
            return R.error(400, "月品质检查未整改项格式不正确！");
        }
        try {
            Map<String, Object> map = sysQualityManageService.addSysQualityManage(Long.valueOf(companyId), Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(qualityCheck), Integer.valueOf(qualityCheckPass)
                    , Integer.valueOf(securityEvent), Integer.valueOf(qualityCheckUnmodified), fileInfo);
            return R.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("新增品质管理数据失败：" + e.getMessage());
            return R.error(500, "新增品质管理数据失败，服务器异常，请联系系统管理员！");
        }
    }

    /**
     * 根据id更新质量管理信息
     *
     * @param qualityId
     * @param companyId
     * @param year
     * @param month
     * @param qualityCheck
     * @param qualityCheckPass
     * @param securityEvent
     * @param qualityCheckUnmodified
     * @return
     */
    @RequestMapping(value = "/updateSysQualityManage", method = RequestMethod.GET)
    public R updateSysQualityManage(@RequestParam(value = "qualityId", required = false) String qualityId, @RequestParam(value = "companyId", required = false) String companyId
            , @RequestParam(value = "year", required = false) String year, @RequestParam(value = "month", required = false) String month
            , @RequestParam(value = "qualityCheck", required = false) String qualityCheck, @RequestParam(value = "qualityCheckPass", required = false) String qualityCheckPass
            ,  @RequestParam(value = "securityEvent", required = false) String securityEvent
            , @RequestParam(value = "qualityCheckUnmodified", required = false) String qualityCheckUnmodified, @RequestParam(value = "fileInfo", required = false) String fileInfo) {
        logger.info("信息更新操作！");
        if (!UtilHelper.isNumer(qualityId)) {
            return R.error(400, "质量管理编号格式不正确，请联系系统管理员！");
        } else if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司编号格式不正确，请联系系统管理员！");
        } else if (!UtilHelper.isNumer(year)) {
            return R.error(400, "年份格式不正确！");
        } else if (!UtilHelper.isNumer(month)) {
            return R.error(400, "月份格式不正确！");
        } else if (!UtilHelper.isNumer(qualityCheck)) {
            return R.error(400, "月品质检查项格式不正确！");
        } else if (!UtilHelper.isNumer(qualityCheckPass)) {
            return R.error(400, "月品质检查合格项格式不正确！");
        } else if (Integer.valueOf(qualityCheck)<Integer.valueOf(qualityCheckPass)) {
            return R.error(400, "月品质检查 合格项 不能 大于 月品质检查项！");
        } else if (!UtilHelper.isNumer(securityEvent)) {
            return R.error(400, "月安全事故数量项格式不正确！");
        } else if (!UtilHelper.isNumer(qualityCheckUnmodified)) {
            return R.error(400, "月品质检查未整改项格式不正确！");
        }
        try {
            Map<String, Object> map = sysQualityManageService.updateSysQualityManage(Long.valueOf(qualityId), Long.valueOf(companyId), Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(qualityCheck), Integer.valueOf(qualityCheckPass)
                    , Integer.valueOf(securityEvent), Integer.valueOf(qualityCheckUnmodified), fileInfo);
            return R.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("更新品质管理数据失败：" + e.getMessage());
            return R.error(500, "更新品质管理数据失败，服务器异常，请联系系统管理员！");
        }
    }

    /**
     * 根据id删除信息（只更新状态，不作删除处理）
     *
     * @param qualityId
     * @return
     */
    @RequestMapping(value = "/deleteSysQualityManageById", method = RequestMethod.GET)
    public R deleteSysQualityManageById(@RequestParam(value = "qualityId", required = false) String qualityId) {
        if (!UtilHelper.isNumer(qualityId)) {
            return R.error(400, "质量管理编号格式不正确，请联系系统管理员！");
        }
        try {
            Map<String, Object> map = sysQualityManageService.deleteSysQualityManageById(Long.valueOf(qualityId));
            return R.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除品质管理数据失败：" + e.getMessage());
            return R.error(500, "删除品质管理数据失败，服务器异常，请联系系统管理员！");
        }
    }

    /**
     * 根据id获取信息
     *
     * @param qualityId
     * @return
     */
    @RequestMapping(value = "/findSysQualityManageById", method = RequestMethod.GET)
    public R findSysQualityManageById(@RequestParam(value = "qualityId", required = false) String qualityId) {
        if (!UtilHelper.isNumer(qualityId)) {
            return R.error(400, "主键ID格式不正确，请联系系统管理员！");
        }
        Map<String, Object> map = sysQualityManageService.findSysQualityManageById(Long.valueOf(qualityId));
        return R.ok(map);
    }

    /**
     * 根据公司id获取附件文档信息
     *
     * @param qualityId
     * @return
     */
    @RequestMapping(value = "/findSysQualityManageFileById", method = RequestMethod.GET)
    public R findSysQualityManageFileById(@RequestParam(value = "qualityId", required = false) String qualityId) {
        if (!UtilHelper.isNumer(qualityId)) {
            return R.error(400, "主键ID格式不正确，请联系系统管理员！");
        }
        Map<String, Object> map = sysQualityManageService.findSysQualityManageFileById(Long.valueOf(qualityId));
        return R.ok(map);
    }
}
