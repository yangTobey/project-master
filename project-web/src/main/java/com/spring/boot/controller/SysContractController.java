
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
@RequestMapping("/contract")
public class SysContractController {

    @Autowired
    private SysContractService sysContractService;

    /**
     * 登录系统
     * @param userName 账号
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/getDepartmentInfo", method = RequestMethod.GET)
    public String login(String userName,String password){

        return "views/login/login";
    }

    /**
     * 添加合同分类
     * @return
     */
    @RequestMapping(value = "/addContractType", method = RequestMethod.GET)
    public String addContractType(String contractTypeName,String companyId) {
        sysContractService.addContractType(contractTypeName);
        return "views/login/login";
    }
    /**
     * 更新合同分类信息
     * @return
     */
    @RequestMapping(value = "/updateContractType", method = RequestMethod.GET)
    public String updateContractType(String contractTypeId, String contractTypeName) {
        sysContractService.updateContractType(contractTypeId,contractTypeName);
        return "views/login/login";
    }
    /**
     * 删除合同分类
     * @return
     */
    @RequestMapping(value = "/deleteContractType", method = RequestMethod.GET)
    public String deleteContractType(String contractTypeId) {
        int count=sysContractService.deleteContractType(contractTypeId);
        System.out.println("删除条数："+count);
        return "views/login/login";
    }

    /**
     * 查找合同列表
     * @return
     */
    @RequestMapping(value = "/addSysContract", method = RequestMethod.GET)
    @ResponseBody
    public R sysContractDataList(@RequestParam(value = "contractName", required = false)String contractName,@RequestParam(value = "contractCode", required = false) String contractCode
            ,@RequestParam(value = "statusCode", required = false) String statusCode,  @RequestParam(value = "contractEndTime", required = false)String contractEndTime
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
        }
        Map<String, Object> map=sysContractService.sysContractDataList(contractName,contractCode, contractEndTime,
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
        int count=sysContractService.updateSysContract(Long.valueOf(contracId),contractName,contractCode,contractMoney,contractStartTime, contractEndTime,
                contractTypeId,firstPartyCompany,secondPartyCompany,personLiableName);
        if (count > 0) {
            return R.ok(200, "更新成功！");
        }
        return R.error(500, "更新失败，请联系系统管理员！");
    }
    /**
     * 删除合同
     * @return
     */
    @RequestMapping(value = "/deleteSysContract", method = RequestMethod.GET)
    @ResponseBody
    public R  deleteSysContract(@RequestParam(value = "contractTypeId", required = false)String contractTypeId) {
        if (!UtilHelper.isNumer(contractTypeId)) {
            return R.error(400, "合同id格式不正确！");
        }
        int count=sysContractService.deleteSysContract(Long.valueOf(contractTypeId));
        if (count > 0) {
            return R.ok(200, "删除成功！");
        }
        return R.error(500, "删除失败，请联系系统管理员！");
    }
}
