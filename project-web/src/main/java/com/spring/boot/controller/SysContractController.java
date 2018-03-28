
package com.spring.boot.controller;

import com.spring.boot.service.SysContractService;
import com.spring.boot.service.SysDepartmentService;
import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
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
    @RequestMapping(value = "/sysContractTypeList", method = RequestMethod.GET)
    @ResponseBody
    public R sysContractTypeList(@RequestParam(value = "limit", required = false) String limit
            ,@RequestParam(value = "offset", required = false) String offset){
        if (!UtilHelper.isNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字！");
        }else if (!UtilHelper.isNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字！");
        }
        Map<String, Object> map=sysContractService.sysContractTypeList(Integer.valueOf(limit),Integer.valueOf(offset));

        return R.ok(map);
    }

    /**
     * 添加合同分类
     * @return
     */
    @RequestMapping(value = "/addSysContractType", method = RequestMethod.GET)
    @ResponseBody
    public R addSysContractType(@RequestParam(value = "contractTypeName", required = false)String contractTypeName,@RequestParam(value = "companyId", required = false)String companyId) {
        if(!UtilHelper.isNumer(companyId)){
            return R.error(400, "公司id格式不正确！");
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
    @RequestMapping(value = "/updateSysContractType", method = RequestMethod.GET)
    @ResponseBody
    public R updateSysContractType(@RequestParam(value = "contractTypeId", required = false)String contractTypeId,@RequestParam(value = "contractTypeName", required = false) String contractTypeName) {
        if(!UtilHelper.isNumer(contractTypeId)){
            return R.error(400, "合同类型id格式不正确！");
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
    @RequestMapping(value = "/deleteSysContractType", method = RequestMethod.GET)
    @ResponseBody
    public R deleteSysContractType(@RequestParam(value = "contractTypeId", required = false)String contractTypeId) {
        if(!UtilHelper.isNumer(contractTypeId)){
            return R.error(400, "合同类型id格式不正确！");
        }
        Map<String, Object> map=sysContractService.deleteSysContractType(contractTypeId);
        return R.ok(map);
    }
    /**
     * 获取合同报表统计数据
     * @return
     */
    @RequestMapping(value = "/sysContractAnalysisData", method = RequestMethod.GET)
    @ResponseBody
    public R sysContractAnalysisData(@RequestParam(value = "companyId", required = false) String companyId) {
        if(!UtilHelper.isNumer(companyId)){
            return R.error(400, "公司id格式不正确！");
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
    @RequestMapping(value = "/sysContractDataList", method = RequestMethod.GET)
    @ResponseBody
    public R sysContractDataList(@RequestParam(value = "contractName", required = false)String contractName,@RequestParam(value = "contractCode", required = false) String contractCode
            ,@RequestParam(value = "statusCode", required = false) String statusCode,  @RequestParam(value = "contractStartTime", required = false)String contractStartTime,@RequestParam(value = "contractEndTime", required = false)String contractEndTime
            ,@RequestParam(value = "contractTypeId", required = false)String contractTypeId ,@RequestParam(value = "firstPartyCompany", required = false) String firstPartyCompany
            ,@RequestParam(value = "secondPartyCompany", required = false) String secondPartyCompany,@RequestParam(value = "limit", required = false) String limit
            ,@RequestParam(value = "offset", required = false) String offset,@RequestParam(value = "companyId", required = false) String companyId) {
        if(UtilHelper.isEmpty(contractName)){
            return R.error(400, "合同名称不能为空！");
        }else if(UtilHelper.isEmpty(contractCode)){
            return R.error(400, "合同编号不能为空！");
        }else if(UtilHelper.isEmpty(contractEndTime)){
            return R.error(400, "合同到期时间不能为空！");
        }else if(!UtilHelper.isNumer(contractTypeId)){
            return R.error(400, "合同类型id格式不正确！");
        }else if(UtilHelper.isEmpty(firstPartyCompany)){
            return R.error(400, "合同甲方名称不能为空！");
        }else if(UtilHelper.isEmpty(secondPartyCompany)){
            return R.error(400, "合同乙方名称不能为空！");
        }else if(!UtilHelper.isNumer(companyId)){
            return R.error(400, "公司id格式不正确！");
        }else if(!UtilHelper.isNumer(statusCode)){
            return R.error(400, "合同状态格式不正确！");
        }else if (!UtilHelper.isNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字！");
        }else if (!UtilHelper.isNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字！");
        }
        Map<String, Object> map=sysContractService.sysContractDataList(contractName,contractCode,Integer.valueOf(statusCode),contractStartTime, contractEndTime,
                contractTypeId,firstPartyCompany,secondPartyCompany,Integer.valueOf(limit),Integer.valueOf(offset),Long.valueOf(companyId));

        return R.ok(map);
    }
    /**
     * 添加合同
     * @return
     */
    @RequestMapping(value = "/addSysContract", method = RequestMethod.GET)
    @ResponseBody
    public R addSysContract(@RequestParam(value = "contractName", required = false)String contractName,@RequestParam(value = "contractCode", required = false) String contractCode
            ,@RequestParam(value = "contractMoney", required = false) String contractMoney, @RequestParam(value = "contractStartTime", required = false)String contractStartTime
            , @RequestParam(value = "contractEndTime", required = false)String contractEndTime,@RequestParam(value = "contractTypeId", required = false)String contractTypeId
            ,@RequestParam(value = "firstPartyCompany", required = false) String firstPartyCompany,@RequestParam(value = "secondPartyCompany", required = false) String secondPartyCompany
            , @RequestParam(value = "personLiableName", required = false)String personLiableName) {
        if(UtilHelper.isEmpty(contractName)){
            return R.error(400, "合同名称不能为空！");
        }else if(UtilHelper.isEmpty(contractCode)){
            return R.error(400, "合同编号不能为空！");
        }else if(UtilHelper.isEmpty(contractMoney)){
            return R.error(400, "合同金额不能为空！");
        }else if(UtilHelper.isEmpty(contractStartTime)){
            return R.error(400, "合同开始时间不能为空！");
        }else if(UtilHelper.isEmpty(contractEndTime)){
            return R.error(400, "合同到期时间不能为空！");
        }else if(!UtilHelper.isNumer(contractTypeId)){
            return R.error(400, "合同类型id格式不正确！");
        }else if(UtilHelper.isEmpty(firstPartyCompany)){
            return R.error(400, "合同甲方名称不能为空！");
        }else if(UtilHelper.isEmpty(secondPartyCompany)){
            return R.error(400, "合同乙方名称不能为空！");
        }else if(UtilHelper.isEmpty(personLiableName)){
            return R.error(400, "合同负责人不能为空！");
        }
        Map<String, Object> map=sysContractService.addSysContract(contractName,contractCode,contractMoney,contractStartTime, contractEndTime,
                contractTypeId,firstPartyCompany,secondPartyCompany,personLiableName);

        return R.ok(map);
    }
    /**
     * 更新合同
     * @return
     */
    @RequestMapping(value = "/updateSysContract", method = RequestMethod.GET)
    @ResponseBody
    public R updateSysContract(@RequestParam(value = "contracId", required = false)String contracId,@RequestParam(value = "contractName", required = false)String contractName
            ,@RequestParam(value = "contractCode", required = false) String contractCode,@RequestParam(value = "contractMoney", required = false) String contractMoney
            , @RequestParam(value = "contractStartTime", required = false)String contractStartTime, @RequestParam(value = "contractEndTime", required = false)String contractEndTime
            ,@RequestParam(value = "contractTypeId", required = false)String contractTypeId,@RequestParam(value = "firstPartyCompany", required = false) String firstPartyCompany
            ,@RequestParam(value = "secondPartyCompany", required = false) String secondPartyCompany, @RequestParam(value = "personLiableName", required = false)String personLiableName) {
        if (!UtilHelper.isNumer(contracId)) {
            return R.error(400, "合同id格式不正确！");
        }else if(UtilHelper.isEmpty(contractName)){
            return R.error(400, "合同名称不能为空！");
        }else if(UtilHelper.isEmpty(contractCode)){
            return R.error(400, "合同编号不能为空！");
        }else if(UtilHelper.isEmpty(contractMoney)){
            return R.error(400, "合同金额不能为空！");
        }else if(UtilHelper.isEmpty(contractStartTime)){
            return R.error(400, "合同开始时间不能为空！");
        }else if(UtilHelper.isEmpty(contractEndTime)){
            return R.error(400, "合同到期时间不能为空！");
        }else if(!UtilHelper.isNumer(contractTypeId)){
            return R.error(400, "合同类型id格式不正确！");
        }else if(UtilHelper.isEmpty(firstPartyCompany)){
            return R.error(400, "合同甲方名称不能为空！");
        }else if(UtilHelper.isEmpty(secondPartyCompany)){
            return R.error(400, "合同乙方名称不能为空！");
        }else if(UtilHelper.isEmpty(personLiableName)){
            return R.error(400, "合同负责人不能为空！");
        }
        Map<String, Object> map=sysContractService.updateSysContract(Long.valueOf(contracId),contractName,contractCode,contractMoney,contractStartTime, contractEndTime,
                contractTypeId,firstPartyCompany,secondPartyCompany,personLiableName);

        return R.ok(map);
    }
    /**
     * 删除合同
     * @return
     */
    @RequestMapping(value = "/deleteSysContract", method = RequestMethod.GET)
    @ResponseBody
    public R  deleteSysContract(@RequestParam(value = "contractId", required = false)String contractId) {
        if (!UtilHelper.isNumer(contractId)) {
            return R.error(400, "合同id格式不正确！");
        }
        Map<String, Object> map=sysContractService.deleteSysContract(Long.valueOf(contractId));

        return R.ok(map);
    }

    /**
     * 根据公司id查询即将过期的合同
     * @param companyId
     * @return
     */
    public R getSysContractExpireDataTotal(@RequestParam(value = "companyId", required = false) String companyId){
        if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不正确！");
        }
        Map<String,Object> map=sysContractService.getSysContractExpireDataTotal(Long.valueOf(companyId));
        return R.ok(map);
    }
}
