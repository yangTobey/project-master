package com.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**界面跳转控制类
 * Created by Administrator on 2018/2/1.
 */
@Controller
@RequestMapping("/sysPage")
public class IndexController {
    /**
     * 登录成功后，跳转系统主页面
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        return "views/main/index";
    }
    /**
     * 系统右边页面展示主页
     * @return
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(){
        return "views/main/main";
    }
    /**
     * 品质管理
     * @return
     */
    @RequestMapping(value = "/sysQualityManageIndex", method = RequestMethod.GET)
    public String sysQualityManage(){
        return "views/quality/QualityManager";
    }
    /**
     * 财务管理
     * @return
     */
    @RequestMapping(value = "/sysFinancialIndex", method = RequestMethod.GET)
    public String sysFinancialIndex(){
        return "views/financial/FinancialManage";
    }
    /**
     * 合同档案管理
     * @return
     */
    @RequestMapping(value = "/sysContractIndex", method = RequestMethod.GET)
    public String sysContractIndex(){
        return "views/contract/ContractArchives";
    }
    /**
     * 公司管理
     * @return
     */
    @RequestMapping(value = "/sysCompanyIndex", method = RequestMethod.GET)
    public String sysCompanyIndex(){
        return "views/otherManage/CompanyManage";
    }
    /**
     * 部门管理
     * @return
     */
    @RequestMapping(value = "/sysDepartmentIndex", method = RequestMethod.GET)
    public String sysDepartmentIndex(){
        return "views/otherManage/DepartmentManage";
    }
    /**
     * 合同分类管理
     * @return
     */
    @RequestMapping(value = "/sysContractTypeIndex", method = RequestMethod.GET)
    public String sysContractTypeIndex(){
        return "views/otherManage/ContractType";
    }
    /**
     * 账号管理
     * @return
     */
    @RequestMapping(value = "/sysUserIndex", method = RequestMethod.GET)
    public String sysUserIndex(){
        return "views/otherManage/AccountManage";
    }
    /**
     * 角色管理
     * @return
     */
    @RequestMapping(value = "/sysRoleIndex", method = RequestMethod.GET)
    public String sysRoleIndex(){
        return "views/otherManage/RoleManage";
    }
    /**
     * 人员成本
     * @return
     */
    @RequestMapping(value = "/sysLaborCostIndex", method = RequestMethod.GET)
    public String sysLaborCostIndex(){
        return "views/personer/PersonnelCost";
    }

    /**
     * 工程能耗
     * @return
     */
    @RequestMapping(value = "/sysProjectEnergy", method = RequestMethod.GET)
    public String sysProjectEnergy(){
        return "views/energy/EnergyConsumption";
    }

    /**
     * 物业数据大屏
     * @return
     */
    @RequestMapping(value = "/sysPropertyDetails", method = RequestMethod.GET)
    public String sysPropertyDetails(){
        return "views/dataStatistics/index";
    }
    /**
     * 财务数据大屏
     * @return
     */
    @RequestMapping(value = "/sysfinancialDetails", method = RequestMethod.GET)
    public String sysfinancialDetails(){
        return "views/dataStatistics/index-area";
    }

    /**
     * 测试链接
     * @return
     */
    @RequestMapping(value = "/tesWebsocket", method = RequestMethod.GET)
    public String tesWebsocket(){
        return "views/login";
    }
}
