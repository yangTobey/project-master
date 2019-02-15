package com.spring.boot.controller;

import com.spring.boot.bean.master.SysBasicData;
import com.spring.boot.service.SysBasicDataService;
import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * 系统基础数据控制管理类
 * Created by Administrator on 2018/3/16.
 */
@RestController
@RequestMapping("/sysBasicData")
@Api(value = "/sysBasicData", tags = {"/sysBasicData"})
public class SysBasicDataController {
    private static final Logger logger = Logger.getLogger(SysBasicDataController.class);
    @Autowired
    private SysBasicDataService sysBasicDataService;

    /**
     * 查询公司基础数据分析结果信息
     *
     * @param companyId 公司id（0:全国）
     * @param year      年份
     * @param month     月份
     * @return
     */
    @RequestMapping(value = "/sysBasicDataAnalysisData", method = RequestMethod.POST)
    public R sysBasicDataAnalysisData(@RequestParam(value = "companyId", required = false) String companyId,
                                      @RequestParam(value = "year", required = false) String year,
                                      @RequestParam(value = "month", required = false) String month) {
        if (!UtilHelper.isLongNumer(companyId)) {
            return R.error(400, "公司id格式不正确，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(year)) {
            return R.error(400, "年份格式不正确，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(month)) {
            return R.error(400, "月份格式不正确，或者不符合常理！");
        }
        Map<String, Object> map = sysBasicDataService.sysBasicDataAnalysisData(Long.valueOf(companyId), Integer.valueOf(year), Integer.valueOf(month));
        return R.ok( map);
    }

    /**
     * 查询公司基础数据列表信息
     *
     * @param limit  每页限制条数
     * @param offset 页码
     * @return
     */
    @RequestMapping(value = "/sysBasicDataAnalysisList", method = RequestMethod.POST)
    public R sysBasicDataAnalysisList(@RequestParam(value = "companyId", required = false) String companyId,
            @RequestParam(value = "limit", required = false) String limit,
            @RequestParam(value = "offset", required = false) String offset,@RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year,@RequestParam(value = "projectName", required = false) String projectName) {
        if (!UtilHelper.isLongNumer(companyId)) {
            return R.error(400, "公司id格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(year)) {
            return R.error(400, "年份格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(month)) {
            return R.error(400, "月份格式不正确，或者不符合常理！");
        }
        Map<String, Object> map = sysBasicDataService.sysBasicDataAnalysisList(Long.valueOf(companyId),Integer.valueOf(limit), Integer.valueOf(offset), Integer.valueOf(year),projectName,Integer.valueOf(month));
        return R.ok().put(200, map,"获取成功！");
    }

    /**
     * 新增基础数据信息
     *
     * @param year                年份
     * @param month               月份
     * @param constructionArea    建筑面积
     * @param chargeArea          建筑收费面积
     * @param cityNumber          城市数量
     * @param projectNumber       项目数量
     * @param houseNumber         房屋数量
     * @param acceptHouseNumber   已收房屋数量
     * @param forSaleHouseNumber  待售房屋数量（空置）
     * @param decorateHouseNumber 装修房屋数量
     * @param parkingSpace        停车位总数量
     * @param forSaleParkingSpace 待售车位数量
     * @param salesDistribution   销售分配（销配）
     * @param companyId           公司id
     * @return
     */
    @RequestMapping(value = "/addSysBasicData", method = RequestMethod.POST)
    @ApiOperation(value = "新增基础数据信息", httpMethod = "POST")
    public R addSysBasicData( @ApiParam(name = "新增基础数据信息", value = "", required = true) @Valid SysBasicData sysBasicDataAdd) {
/*
        if (!UtilHelper.isIntegerNumer(year)) {
            return R.error(400, "年份格式不正确，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(month)) {
            return R.error(400, "月份格式不正确，或者不符合常理！");
        } else if (!UtilHelper.isDoubleNumer(constructionArea)) {
            return R.error(400, " 建筑面积格式不正确，只能保留两位小数，或者不符合常理！");
        }else if (!UtilHelper.isDoubleNumer(chargeArea)) {
            return R.error(400, "建筑收费面积格式不正确,只能保留两位小数，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(cityNumber)) {
            return R.error(400, "城市数量格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(projectNumber)) {
            return R.error(400, "项目数量格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(houseNumber)) {
            return R.error(400, "房屋数量格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(acceptHouseNumber)) {
            return R.error(400, "已收房屋数量格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(forSaleHouseNumber)) {
            return R.error(400, "待售房屋数量（空置）格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(decorateHouseNumber)) {
            return R.error(400, "装修房屋数量格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(parkingSpace)) {
            return R.error(400, "停车位总数量格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(forSaleParkingSpace)) {
            return R.error(400, "待售车位数量格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(salesDistribution)) {
            return R.error(400, "销配格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isLongNumer(companyId)) {
            return R.error(400, "公司id格式不正确，或者不符合常理！");
        }*//*else if (UtilHelper.isEmpty(parkingSpaceFileInfo)) {
            return R.error(400, "车位附件不能为空！");
        }else if (UtilHelper.isEmpty(salesDistributionFileInfo)) {
            return R.error(400, "销配附件不能为空！");
        }*/
        //异常捕捉，service层做事物管理回滚
        try{
            Map<String, Object> map = sysBasicDataService.addSysBasicData(sysBasicDataAdd);
            return R.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("新增失败："+e.getMessage());
            return R.error(500,"新增失败，服务器异常，请联系系统管理员！");
        }
    }

    /**
     * 更新基础数据信息
     *
     * @param basicId             基础信息id
     * @param year                年份
     * @param month               月份
     * @param constructionArea    建筑面积
     * @param chargeArea          建筑收费面积
     * @param cityNumber          城市数量
     * @param projectNumber       项目数量
     * @param houseNumber         房屋数量
     * @param acceptHouseNumber   已收房屋数量
     * @param forSaleHouseNumber  待售房屋数量（空置）
     * @param decorateHouseNumber 装修房屋数量
     * @param parkingSpace        停车位总数量
     * @param forSaleParkingSpace 待售车位数量
     * @param salesDistribution   销售分配（销配）
     * @param companyId           公司id
     * @return
     */
    @RequestMapping(value = "/updateSysBasicData", method = RequestMethod.POST)
    public R updateSysBasicData( @Valid SysBasicData sysBasicDataUpdate) {

        if (!UtilHelper.isLongNumer(sysBasicDataUpdate.getBasicId().toString())) {
            return R.error(400, "基础信息id格式不正确，或者不符合常理！");
        } /*else if (!UtilHelper.isIntegerNumer(year)) {
            return R.error(400, "年份格式不正确，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(month)) {
            return R.error(400, "月份格式不正确，或者不符合常理！");
        } else if (!UtilHelper.isDoubleNumer(constructionArea)) {
            return R.error(400, " 建筑面积格式不正确，只能保留两位小数，或者不符合常理！");
        }else if (!UtilHelper.isDoubleNumer(chargeArea)) {
            return R.error(400, "建筑收费面积格式不正确，只能保留两位小数，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(cityNumber)) {
            return R.error(400, "城市数量格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(projectNumber)) {
            return R.error(400, "项目数量格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(houseNumber)) {
            return R.error(400, "房屋数量格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(acceptHouseNumber)) {
            return R.error(400, "已收房屋数量格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(forSaleHouseNumber)) {
            return R.error(400, "待售房屋数量（空置）格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(decorateHouseNumber)) {
            return R.error(400, "装修房屋数量格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(parkingSpace)) {
            return R.error(400, "停车位总数量格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(forSaleParkingSpace)) {
            return R.error(400, "待售车位数量格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(salesDistribution)) {
            return R.error(400, "销配格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isLongNumer(companyId)) {
            return R.error(400, "公司id格式不正确，或者不符合常理！");
        }*//*else if (UtilHelper.isEmpty(parkingSpaceFileInfo)) {
            return R.error(400, "车位附件不能为空！");
        }else if (UtilHelper.isEmpty(salesDistributionFileInfo)) {
            return R.error(400, "销配附件不能为空！");
        }*/
        try {
            Map<String, Object> map = sysBasicDataService.updateSysBasicData(sysBasicDataUpdate);
            return R.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("更新失败："+e.getMessage());
            return R.error(500,"更新失败，服务器异常，请联系系统管理员！");
        }
    }

    /**
     * 根据基础数据id删除信息（只更新状态，不作删除处理）
     *
     * @param basicId 主键id
     * @return
     */
    @RequestMapping(value = "/deleteSysBasicData", method = RequestMethod.POST)
    public R deleteSysBasicData(@RequestParam(value = "basicId", required = false) String basicId) {
        if (!UtilHelper.isIntegerNumer(basicId)) {
            return R.error(400, "基础信息id格式不正确，或者不符合常理，请联系系统管理员！");
        }
        try {
            Map<String, Object> map = sysBasicDataService.deleteSysBasicData(Integer.valueOf(basicId));
            return R.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除失败："+e.getMessage());
            return R.error(500,"删除失败，服务器异常，请联系系统管理员！");
        }
    }
    /**
     * 根据基础数据id查找信息
     *
     * @param basicId 主键id
     * @return
     */
    @RequestMapping(value = "/findSysBasicDataById", method = RequestMethod.POST)
    public R findSysBasicDataById(@RequestParam(value = "basicId", required = false) String basicId) {
        if (!UtilHelper.isIntegerNumer(basicId)) {
            return R.error(400, "基础信息id格式不正确，或者不符合常理，请联系系统管理员！");
        }
        Map<String, Object> map = sysBasicDataService.findSysBasicDataById(Integer.valueOf(basicId));
        return R.ok(map);
    }
    /**
     * 根据公司id获取附件文档信息
     *
     * @param basicId
     * @return
     */
    @RequestMapping(value = "/findSysBasicDataFileById", method = RequestMethod.POST)
    public R findSysBasicDataFileById(@RequestParam(value = "basicId", required = false) String basicId) {
        if (!UtilHelper.isLongNumer(basicId)) {
            return R.error(400, "主键ID格式不正确，或者不符合常理，请联系系统管理员！");
        }
        Map<String, Object> map =sysBasicDataService.findSysBasicDataFileById(Long.valueOf(basicId));
        return R.ok(map);
    }
}
