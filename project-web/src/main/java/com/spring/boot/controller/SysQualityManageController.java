package com.spring.boot.controller;

import com.spring.boot.service.SysCompanyService;
import com.spring.boot.service.SysQualityManageService;
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
     * @param type 查找类型（year:年度报表，month:月度报表）
     * @return
     */
    @RequestMapping(value = "/sysQualityManageAnalysisForYear", method = RequestMethod.GET)
    public R sysQualityManageAnalysisForYear(@RequestParam(value = "companyId", required = false) String companyId,@RequestParam(value = "type", required = false) String type) {
        if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不正确！");
        }else if(UtilHelper.isEmpty(type)){
            return R.error(400, "请求类型type参数不能为空！");
        }
        Map<String, Object> map = sysQualityManageService.sysQualityManageAnalysisForYear(Long.valueOf(companyId),type);
        return R.ok().put(200, map, "获取成功！");
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
        return R.ok().put(200, map, "获取成功！");
    }

    /**
     * 新增质量管理信息
     *
     * @param companyId
     * @param year
     * @param month
     * @param qualityCheck
     * @param qualityCheckPass
     * @param qualityCheckFail
     * @param securityEvent
     * @param qualityCheckUnmodified
     * @return
     */
    @RequestMapping(value = "/addSysQualityManage", method = RequestMethod.GET)
    public R addSysQualityManage(@RequestParam(value = "companyId", required = false) String companyId, @RequestParam(value = "year", required = false) String year
            , @RequestParam(value = "month", required = false) String month, @RequestParam(value = "qualityCheck", required = false) String qualityCheck
            , @RequestParam(value = "qualityCheckPass", required = false) String qualityCheckPass, @RequestParam(value = "qualityCheckFail", required = false) String qualityCheckFail
            , @RequestParam(value = "securityEvent", required = false) String securityEvent, @RequestParam(value = "qualityCheckUnmodified", required = false) String qualityCheckUnmodified) {
        int count = sysQualityManageService.addSysQualityManage(Long.valueOf(companyId), Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(qualityCheck), Integer.valueOf(qualityCheckPass)
                , Integer.valueOf(qualityCheckFail), Integer.valueOf(securityEvent), Integer.valueOf(qualityCheckUnmodified));
        if (count > 0) {
            return R.ok(200, "新增成功！");
        }
        return R.error(500, "新增失败，服务器异常，请联系系统管理员！");
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
     * @param qualityCheckFail
     * @param securityEvent
     * @param qualityCheckUnmodified
     * @return
     */
    @RequestMapping(value = "/updateSysQualityManage", method = RequestMethod.GET)
    public R updateSysQualityManage(@RequestParam(value = "qualityId", required = false) String qualityId, @RequestParam(value = "companyId", required = false) String companyId
            , @RequestParam(value = "year", required = false) String year, @RequestParam(value = "month", required = false) String month, @RequestParam(value = "qualityCheck"
            , required = false) String qualityCheck, @RequestParam(value = "qualityCheckPass", required = false) String qualityCheckPass, @RequestParam(value = "qualityCheckFail"
            , required = false) String qualityCheckFail, @RequestParam(value = "securityEvent", required = false) String securityEvent
            , @RequestParam(value = "qualityCheckUnmodified", required = false) String qualityCheckUnmodified) {
        logger.info("信息更新操作！");
        if (!UtilHelper.isNumer(qualityId)) {
            return R.error(400, "质量管理编号格式不正确，请联系系统管理员！");
        } else if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司编号格式不正确，请联系系统管理员！");
        }
        int count = sysQualityManageService.updateSysQualityManage(Long.valueOf(qualityId), Long.valueOf(companyId), Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(qualityCheck), Integer.valueOf(qualityCheckPass)
                , Integer.valueOf(qualityCheckFail), Integer.valueOf(securityEvent), Integer.valueOf(qualityCheckUnmodified));
        if (count > 0) {
            return R.ok(200, "更新成功！");
        }
        return R.error(500, "更新失败，服务器异常，请联系系统管理员！");

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
        int count = sysQualityManageService.deleteSysQualityManageById(Long.valueOf(qualityId));
        if (count > 0) {
            return R.ok(200, "更新成功！");
        }
        return R.error(500, "删除失败，系统异常，请联系系统管理员！");
    }

    /**
     * 根据公司id获取信息
     *
     * @param qualityId
     * @return
     */
    @RequestMapping(value = "/findSysQualityManageById", method = RequestMethod.GET)
    public R findSysQualityManageById(@RequestParam(value = "companyId", required = false) String qualityId) {
        if (!UtilHelper.isNumer(qualityId)) {
            return R.error(400, "公司编号格式不正确，请联系系统管理员！");
        }
        Map<String, Object> map = sysQualityManageService.findSysQualityManageById(Long.valueOf(qualityId));
        return R.ok().put(200, map, "获取成功！");
    }
}
