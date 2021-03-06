
package com.spring.boot.controller;

import com.spring.boot.bean.master.SysContract;
import com.spring.boot.exception.SysException;
import com.spring.boot.service.SysContractService;
import com.spring.boot.service.SysDepartmentService;
import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 合同档案信息管理控制类
 * Created by Administrator on 2018/2/6.
 */
@Controller
@RequestMapping("/sysContract")
public class SysContractController {

    @Autowired
    private SysContractService sysContractService;

    /**
     * 获取合同类型列表
     * @param limit 每页限制条数
     * @param offset 页码
     * @return
     */
    @RequestMapping(value = "/sysContractTypeList", method = RequestMethod.POST)
    @ResponseBody
    public R sysContractTypeList(@RequestParam(value = "limit", required = false) String limit
            ,@RequestParam(value = "offset", required = false) String offset){
        if (!UtilHelper.isIntegerNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字，或者不符合常理！");
        }
        Map<String, Object> map=sysContractService.sysContractTypeList(Integer.valueOf(limit),Integer.valueOf(offset));

        return R.ok(map);
    }
    /**
     * 获取系统全部合同类型
     * @return
     */
    @RequestMapping(value = "/sysAllContractType", method = RequestMethod.POST)
    @ResponseBody
    public R sysAllContractType(){
        Map<String, Object> map=sysContractService.sysAllContractType();

        return R.ok(map);
    }

    /**
     * 添加合同分类
     * @return
     */
    @RequestMapping(value = "/addSysContractType", method = RequestMethod.POST)
    @ResponseBody
    public R addSysContractType(@RequestParam(value = "contractTypeName", required = false)String contractTypeName,@RequestParam(value = "companyId", required = false)String companyId) {
        if(!UtilHelper.isLongNumer(companyId)){
            return R.error(400, "公司id格式不正确，或者不符合常理！");
        }else if(UtilHelper.isEmpty(contractTypeName)){
            return R.error(400, "合同类型名称不能为空！");
        }
        Map<String, Object> map=sysContractService.addSysContractType(contractTypeName);
        return R.ok(map);
    }
    /**
     * 更新合同分类信息
     * @return
     */
    @RequestMapping(value = "/updateSysContractType", method = RequestMethod.POST)
    @ResponseBody
    public R updateSysContractType(@RequestParam(value = "contractTypeId", required = false)String contractTypeId,@RequestParam(value = "contractTypeName", required = false) String contractTypeName) {
        if(!UtilHelper.isLongNumer(contractTypeId)){
            return R.error(400, "合同类型id格式不正确，或者不符合常理！");
        }else if(UtilHelper.isEmpty(contractTypeName)){
            return R.error(400, "合同类型名称不能为空！");
        }
        Map<String, Object> map=sysContractService.updateSysContractType(contractTypeId,contractTypeName);
        return R.ok(map);
    }
    /**
     * 删除合同分类
     * @return
     */
    @RequestMapping(value = "/deleteSysContractType", method = RequestMethod.POST)
    @ResponseBody
    public R deleteSysContractType(@RequestParam(value = "contractTypeId", required = false)String contractTypeId) {
        if(!UtilHelper.isLongNumer(contractTypeId)){
            return R.error(400, "合同类型id格式不正确，或者不符合常理！");
        }
        Map<String, Object> map=sysContractService.deleteSysContractType(Long.valueOf(contractTypeId));
        return R.ok(map);
    }
    /**
     * 获取合同报表统计数据
     * @return
     */
    @RequestMapping(value = "/sysContractAnalysisData", method = RequestMethod.POST)
    @ResponseBody
    public R sysContractAnalysisData(@RequestParam(value = "companyId", required = false) String companyId) {
        if(!UtilHelper.isLongNumer(companyId)){
            return R.error(400, "公司id格式不正确，或者不符合常理！");
        }
        Map<String, Object> map=sysContractService.sysContractAnalysisData(Long.valueOf(companyId));

        return R.ok(map);
    }

    /**
     * 查找合同列表
     * @param contractName
     * @param contractCode
     * @param statusCode
     * @param contractEndTime
     * @param contractTypeId
     * @param firstPartyCompany
     * @param secondPartyCompany
     * @param limit
     * @param offset
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/sysContractDataList", method = RequestMethod.POST)
    @ResponseBody
    public R sysContractDataList(@RequestParam(value = "contractName", required = false)String contractName,@RequestParam(value = "contractCode", required = false) String contractCode
            ,@RequestParam(value = "statusCode", required = false) String statusCode,  @RequestParam(value = "contractStartTime", required = false)String contractStartTime,@RequestParam(value = "contractEndTime", required = false)String contractEndTime
            ,@RequestParam(value = "contractTypeId", required = false)String contractTypeId ,@RequestParam(value = "firstPartyCompany", required = false) String firstPartyCompany
            ,@RequestParam(value = "secondPartyCompany", required = false) String secondPartyCompany,@RequestParam(value = "limit", required = false) String limit
            ,@RequestParam(value = "offset", required = false) String offset,@RequestParam(value = "companyId", required = false) String companyId) {
        /*if(UtilHelper.isEmpty(contractName)){
            return R.error(400, "合同名称不能为空！");
        }else if(UtilHelper.isEmpty(contractCode)){
            return R.error(400, "合同编号不能为空！");
        }else if(UtilHelper.isEmpty(contractEndTime)){
            return R.error(400, "合同到期时间不能为空！");
        }else if(UtilHelper.isEmpty(firstPartyCompany)){
            return R.error(400, "合同甲方名称不能为空！");
        }else if(UtilHelper.isEmpty(secondPartyCompany)){
            return R.error(400, "合同乙方名称不能为空！");
        }*/

        if(!UtilHelper.isLongNumer(contractTypeId)){
            return R.error(400, "合同类型id格式不正确，或者不符合常理！");
        }else if(!UtilHelper.isLongNumer(companyId)){
            return R.error(400, "公司id格式不正确，或者不符合常理！");
        }else if(!UtilHelper.isIntegerNumer(statusCode)){
            return R.error(400, "合同状态格式不正确，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字，或者不符合常理！");
        }else if (!UtilHelper.isIntegerNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字，或者不符合常理！");
        }
        Map<String, Object> map=sysContractService.sysContractDataList(contractName,contractCode,Integer.valueOf(statusCode),contractStartTime, contractEndTime,
                contractTypeId,firstPartyCompany,secondPartyCompany,Integer.valueOf(limit),Integer.valueOf(offset),Long.valueOf(companyId));

        return R.ok(map);
    }
    /**
     * 添加合同
     * @return
     */
    @RequestMapping(value = "/addSysContract", method = RequestMethod.POST)
    @ResponseBody
    public R addSysContract( @Valid SysContract sysContract) {
       /* if(UtilHelper.isEmpty(contractName)){
            return R.error(400, "合同名称不能为空！");
        }else if(UtilHelper.isEmpty(contractCode)){
            return R.error(400, "合同编号不能为空！");
        }else if(!UtilHelper.isDoubleNumer(contractMoney)){
            return R.error(400, "合同金额格式不正确！");
        }else if(UtilHelper.isEmpty(contractStartTime)){
            return R.error(400, "合同开始时间不能为空！");
        }else if(UtilHelper.isEmpty(contractEndTime)){
            return R.error(400, "合同到期时间不能为空！");
        }else if(!UtilHelper.isIntegerNumer(contractTypeId)){
            return R.error(400, "合同类型id格式不正确，或者不符合常理！");
        }else if(UtilHelper.isEmpty(firstPartyCompany)){
            return R.error(400, "合同甲方名称不能为空！");
        }else if(UtilHelper.isEmpty(secondPartyCompany)){
            return R.error(400, "合同乙方名称不能为空！");
        }else if(UtilHelper.isEmpty(personLiableName)){
            return R.error(400, "合同负责人不能为空！");
        }else if(!UtilHelper.isLongNumer(companyId)){
            return R.error(400, "公司id格式不正确，或者不符合常理，请联系系统管理员！");
        }*/
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //Date startTime = format.parse(sysContract.getContractStartTime());
            //Date endTime = format.parse(contractEndTime);
            if(sysContract.getContractStartTime().getTime()-sysContract.getContractEndTime().getTime()>0){
                return R.error(400, "合同开始时间不能大于合同到期时间！");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new SysException("系统异常，请联系系统管理员进行处理，谢谢！！");
        }
        Map<String, Object> map=sysContractService.addSysContract(sysContract);

        return R.ok(map);
    }
    /**
     * 更新合同
     * @return
     */
    @RequestMapping(value = "/updateSysContract", method = RequestMethod.POST)
    @ResponseBody
    public R updateSysContract( @Valid SysContract sysContract) {
        if (!UtilHelper.isLongNumer(sysContract.getContractId().toString())) {
            return R.error(400, "合同id格式不正确，或者不符合常理！");
        }/*else if(UtilHelper.isEmpty(contractName)){
            return R.error(400, "合同名称不能为空！");
        }else if(UtilHelper.isEmpty(contractCode)){
            return R.error(400, "合同编号不能为空！");
        }else if(!UtilHelper.isDoubleNumer(contractMoney)){
            return R.error(400, "合同金额格式不正确！");
        }else if(UtilHelper.isEmpty(contractStartTime)){
            return R.error(400, "合同开始时间不能为空！");
        }else if(UtilHelper.isEmpty(contractEndTime)){
            return R.error(400, "合同到期时间不能为空！");
        }else if(!UtilHelper.isLongNumer(contractTypeId)){
            return R.error(400, "合同类型id格式不正确，或者不符合常理！");
        }else if(UtilHelper.isEmpty(firstPartyCompany)){
            return R.error(400, "合同甲方名称不能为空！");
        }else if(UtilHelper.isEmpty(secondPartyCompany)){
            return R.error(400, "合同乙方名称不能为空！");
        }else if(UtilHelper.isEmpty(personLiableName)){
            return R.error(400, "合同负责人不能为空！");
        }else if(!UtilHelper.isLongNumer(companyId)){
            return R.error(400, "公司id格式不正确，或者不符合常理，请联系系统管理员！");
        }*/
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //Date startTime = format.parse(sysContract.getContractStartTime());
            //Date endTime = format.parse(contractEndTime);
            if(sysContract.getContractStartTime().getTime()-sysContract.getContractEndTime().getTime()>0){
                return R.error(400, "合同开始时间不能大于合同到期时间！");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new SysException("系统异常，请联系系统管理员进行处理，谢谢！！");
        }
        Map<String, Object> map=sysContractService.updateSysContract(sysContract);

        return R.ok(map);
    }
    /**
     * 删除合同
     * @return
     */
    @RequestMapping(value = "/deleteSysContract", method = RequestMethod.POST)
    @ResponseBody
    public R  deleteSysContract(@RequestParam(value = "contractId", required = false)String contractId) {
        if (!UtilHelper.isLongNumer(contractId)) {
            return R.error(400, "合同id格式不正确，或者不符合常理！");
        }
        Map<String, Object> map=sysContractService.deleteSysContract(Long.valueOf(contractId));

        return R.ok(map);
    }
    /**
     * 根据主键id获取信息
     *
     * @param contractId
     * @return
     */
    @RequestMapping(value = "/findSysContractById", method = RequestMethod.POST)
    @ResponseBody
    public R findSysContractById(@RequestParam(value = "contractId", required = false) String contractId) {
        if (!UtilHelper.isLongNumer(contractId)) {
            return R.error(400, "主键id格式不正确，或者不符合常理，请联系系统管理员！");
        }
        Map<String, Object> map = sysContractService.findSysContractById(Long.valueOf(contractId));
        return R.ok(map);
    }
    /**
     * 根据id获取附件文档信息
     *
     * @param contractId
     * @return
     */
    @RequestMapping(value = "/findSysContractFileById", method = RequestMethod.POST)
    @ResponseBody
    public R findSysContractFileById(@RequestParam(value = "contractId", required = false) String contractId) {
        if (!UtilHelper.isLongNumer(contractId)) {
            return R.error(400, "主键id格式不正确，或者不符合常理，请联系系统管理员！");
        }
        Map<String, Object> map = sysContractService.findSysContractFileById(Long.valueOf(contractId));
        return R.ok(map);
    }

    /**
     * 根据公司id查询即将过期的合同
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/getSysContractExpireDataTotal", method = RequestMethod.POST)
    @ResponseBody
    public R getSysContractExpireDataTotal(@RequestParam(value = "companyId", required = false) String companyId){
        if (!UtilHelper.isLongNumer(companyId)) {
            return R.error(400, "公司id格式不正确，或者不符合常理！");
        }
        Map<String,Object> map=sysContractService.getSysContractExpireDataTotal(Long.valueOf(companyId));
        return R.ok(map);
    }
}
