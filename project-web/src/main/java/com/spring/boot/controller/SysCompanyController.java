package com.spring.boot.controller;

import com.spring.boot.service.SysCompanyService;
import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@RestController
@RequestMapping("/sysCompany")
@CrossOrigin
public class SysCompanyController {
    private static final Logger logger = Logger.getLogger(SysCompanyController.class);
    @Autowired
    private SysCompanyService sysCompanyService;

    /**
     * 查询公司信息
     *
     * @param limit  每页限制条数
     * @param offset 页码
     * @return
     */
    @RequestMapping(value = "/getSysCompanyList", method = RequestMethod.POST)
    public R getSysCompanyList(@RequestParam(value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset) {
        if (!UtilHelper.isIntegerNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字，或者不符合常理！");
        }
        Map<String, Object> map = sysCompanyService.getSysCompanyList(Integer.valueOf(limit), Integer.valueOf(offset));
        return R.ok(map);
    }

    /**
     * 新增公司信息
     *
     * @param companyName
     * @param companyPhone
     * @param companyAddress
     * @param principal 负责人
     * @param fileInfo 附件文档
     * @return
     */
    @RequestMapping(value = "/addSysCompany", method = RequestMethod.POST)
    public R addSysCompany(@RequestParam(value = "companyName", required = false) String companyName, @RequestParam(value = "companyPhone", required = false) String companyPhone
            , @RequestParam(value = "companyAddress", required = false) String companyAddress, @RequestParam(value = "principal", required = false) String principal
            , @RequestParam(value = "fileInfo", required = false) String fileInfo) {
        if (UtilHelper.isEmpty(companyName)) {
            return R.error(400, "公司名称不能为空！");
        }else if (UtilHelper.isEmpty(principal)) {
            return R.error(400, "公司负责人不能为空！");
        }else if (UtilHelper.isEmpty(fileInfo)) {
            return R.error(400, "附件文档不能为空！");
        }
        Map<String, Object> map = sysCompanyService.addSysCompany(companyName, companyPhone, companyAddress,principal,fileInfo);
        return R.ok(map);
    }

    /**
     * 更新公司信息
     *
     * @param companyId      公司id
     * @param companyName    公司名称
     * @param companyPhone   公司电话
     * @param companyAddress 公司地址
     * @return
     */
    @RequestMapping(value = "/updateSysCompanyInfo", method = RequestMethod.POST)
    public R updateSysCompanyInfo(@RequestParam(value = "companyId", required = false) String companyId, @RequestParam(value = "companyName", required = false) String companyName
            , @RequestParam(value = "companyPhone", required = false) String companyPhone, @RequestParam(value = "companyAddress", required = false) String companyAddress
            , @RequestParam(value = "principal", required = false) String principal, @RequestParam(value = "fileInfo", required = false) String fileInfo) {
        logger.info("公司信息更新操作！");
        if (!UtilHelper.isLongNumer(companyId)) {
            return R.error(400, "公司编号格式不正确，或者不符合常理，请联系系统管理员！");
        } else if (UtilHelper.isEmpty(companyName)) {
            return R.error(400, "公司名称不能为空！");
        }else if (UtilHelper.isEmpty(principal)) {
            return R.error(400, "公司负责人不能为空！");
        }else if (UtilHelper.isEmpty(fileInfo)) {
            return R.error(400, "附件文档不能为空！");
        }
        Map<String, Object> map = sysCompanyService.updateSysCompanyInfo(companyId, companyName, companyPhone, companyAddress,principal,fileInfo);
        return R.ok(map);

    }

    /**
     * 根据公司id删除公司信息（只更新公司状态，不作删除处理）
     *
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/deleteSysCompanyById", method = RequestMethod.POST)
    public R deleteSysCompanyById(@RequestParam(value = "companyId", required = false) String companyId) {
        if (!UtilHelper.isLongNumer(companyId)) {
            return R.error(400, "公司编号格式不正确，或者不符合常理，请联系系统管理员！");
        }
        Map<String, Object> map = sysCompanyService.deleteSysCompanyById(Long.valueOf(companyId));
        return R.ok(map);
    }

    /**
     * 根据公司id获取信息
     *
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/findSysCompanyByCompanyId", method = RequestMethod.POST)
    public R findSysCompanyByCompanyId(@RequestParam(value = "companyId", required = false) String companyId) {
        if (!UtilHelper.isLongNumer(companyId)) {
            return R.error(400, "公司编号格式不正确，或者不符合常理，请联系系统管理员！");
        }
        Map<String, Object> map = sysCompanyService.findSysCompanyByCompanyId(Long.valueOf(companyId));
        return R.ok().put(200, map, "获取成功！");
    }

    /**
     * 获取全部公司信息
     *
     * @return
     */
    @RequestMapping(value = "/getAllSysCompany", method = RequestMethod.POST)
    public R getAllSysCompany() {
        Map<String, Object> map = sysCompanyService.getAllSysCompany();
        return R.ok().put(200, map, "获取成功！");
    }
}
