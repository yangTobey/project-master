package com.spring.boot.entity;

import com.spring.boot.validation.IsNotNull;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2018/3/12.
 */
public class SysLaborCostDetailsAddEntity {

    /*private Integer employeeTotal;
    private Integer headcountTotal;
    private Integer entryTotal;
    private Integer demissionTotal;
    private Double laborCostTotal;



    private Integer payPeopleTotal;
    private Integer beginMonthPeople;
    private Integer monthDeploy;*/

    private Long laborCostId;
    private Long detailsId;
    private Integer departmentType;

    /**
     * 公司id
     */
    @NotNull(message = "公司id不能为空")
    @Range(min = 1,max = Integer.MAX_VALUE,message = "公司id格式不正确，或者不符合常理！！")
    private Long companyId;
    /**
     * 年份
     */
    @NotNull(message = "年份不能为空！")
    @Range(message = "年份格式不正确，或者不符合常理！！")
    private Integer year;
    /**
     * 月份
     */
    @NotNull(message = "月份不能为空")
    @Range(min=1, max=12, message = "月份格式错误")
    private Integer month;
    /**
     * 物业月人工成本支出
     */
    @Range(min = 0,max = Integer.MAX_VALUE,message = "物业月人工成本支出格式不正确，或者不符合常理！！")
    @IsNotNull(message = "物业月人工成本支出格式不正确，只能保留两位小数，或者不符合常理(数值过大)！")
    private Double propertyLaborCost;
    /**
     * 物业编制总人数（月）
     */
    @NotNull(message = "物业编制总人数（月）不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "物业编制总人数（月）格式不正确，或者不符合常理！！")
    private Integer propertyHeadcountTotal;
    /**
     * 物业在职总人员（月）
     */
    @NotNull(message = "物业在职总人员（月）不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "物业在职总人员（月）格式不正确，或者不符合常理！！")
    private Integer propertyEmployeeTotal;
    /**
     * 物业入职总人数（月）
     */
    @NotNull(message = "物业入职总人数（月）不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "物业入职总人数（月）格式不正确，或者不符合常理！！")
    private Integer propertyEntryTotal;
    /**
     * 物业离职总人数（月）
     */
    @NotNull(message = "物业离职总人数（月不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "物业离职总人数（月)格式不正确，或者不符合常理！！")
    private Integer propertyDemissionTotal;
    /**
     * 物业发薪人员
     */
    @NotNull(message = "物业发薪人员不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "物业发薪人员格式不正确，或者不符合常理！！")
    private Integer propertyPayPeopleTotal;
    /**
     * 物业月初人数
     */
    @NotNull(message = "物业月初人数不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "物业月初人数格式不正确，或者不符合常理！！")
    private Integer propertyBeginMonthPeople;
    /**
     * 物业月调入人数
     */
    @NotNull(message = "物业月调入人数不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "物业月调入人数格式不正确，或者不符合常理！！")
    private Integer propertyMonthDeploy;
    /**
     * 物业月调出人数
     */
    @NotNull(message = "物业月调出人数不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "物业月调出人数格式不正确，或者不符合常理！！")
    private Integer propertyMonthTransfer;

    //--------------电商人员-------------
    private Double eBusinessLaborCost;
    private Integer eBusinessHeadcountTotal;
    private Integer eBusinessEmployeeTotal;
    private Integer eBusinessEntryTotal;
    private Integer eBusinessDemissionTotal;
    private Integer eBusinessPayPeopleTotal;
    private Integer eBusinessBeginMonthPeople;
    private Integer eBusinessMonthDeploy;
    private Integer eBusinessMonthTransfer;
    //--------------销配人员-------------
    private Double saleLaborCost;
    private Integer saleHeadcountTotal;
    private Integer saleEmployeeTotal;
    private Integer saleEntryTotal;
    private Integer saleDemissionTotal;
    private Integer salePayPeopleTotal;
    private Integer saleBeginMonthPeople;
    private Integer saleMonthDeploy;
    private Integer saleMonthTransfer;

    public Long getLaborCostId() {
        return laborCostId;
    }

    public void setLaborCostId(Long laborCostId) {
        this.laborCostId = laborCostId;
    }

    public Long getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(Long detailsId) {
        this.detailsId = detailsId;
    }

    public Integer getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(Integer departmentType) {
        this.departmentType = departmentType;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getPropertyLaborCost() {
        return propertyLaborCost;
    }

    public void setPropertyLaborCost(Double propertyLaborCost) {
        this.propertyLaborCost = propertyLaborCost;
    }

    public Integer getPropertyHeadcountTotal() {
        return propertyHeadcountTotal;
    }

    public void setPropertyHeadcountTotal(Integer propertyHeadcountTotal) {
        this.propertyHeadcountTotal = propertyHeadcountTotal;
    }

    public Integer getPropertyEmployeeTotal() {
        return propertyEmployeeTotal;
    }

    public void setPropertyEmployeeTotal(Integer propertyEmployeeTotal) {
        this.propertyEmployeeTotal = propertyEmployeeTotal;
    }

    public Integer getPropertyEntryTotal() {
        return propertyEntryTotal;
    }

    public void setPropertyEntryTotal(Integer propertyEntryTotal) {
        this.propertyEntryTotal = propertyEntryTotal;
    }

    public Integer getPropertyDemissionTotal() {
        return propertyDemissionTotal;
    }

    public void setPropertyDemissionTotal(Integer propertyDemissionTotal) {
        this.propertyDemissionTotal = propertyDemissionTotal;
    }

    public Integer getPropertyPayPeopleTotal() {
        return propertyPayPeopleTotal;
    }

    public void setPropertyPayPeopleTotal(Integer propertyPayPeopleTotal) {
        this.propertyPayPeopleTotal = propertyPayPeopleTotal;
    }

    public Integer getPropertyBeginMonthPeople() {
        return propertyBeginMonthPeople;
    }

    public void setPropertyBeginMonthPeople(Integer propertyBeginMonthPeople) {
        this.propertyBeginMonthPeople = propertyBeginMonthPeople;
    }

    public Integer getPropertyMonthDeploy() {
        return propertyMonthDeploy;
    }

    public void setPropertyMonthDeploy(Integer propertyMonthDeploy) {
        this.propertyMonthDeploy = propertyMonthDeploy;
    }

    public Integer getPropertyMonthTransfer() {
        return propertyMonthTransfer;
    }

    public void setPropertyMonthTransfer(Integer propertyMonthTransfer) {
        this.propertyMonthTransfer = propertyMonthTransfer;
    }

    public Double geteBusinessLaborCost() {
        return eBusinessLaborCost;
    }

    public void seteBusinessLaborCost(Double eBusinessLaborCost) {
        this.eBusinessLaborCost = eBusinessLaborCost;
    }

    public Integer geteBusinessHeadcountTotal() {
        return eBusinessHeadcountTotal;
    }

    public void seteBusinessHeadcountTotal(Integer eBusinessHeadcountTotal) {
        this.eBusinessHeadcountTotal = eBusinessHeadcountTotal;
    }

    public Integer geteBusinessEmployeeTotal() {
        return eBusinessEmployeeTotal;
    }

    public void seteBusinessEmployeeTotal(Integer eBusinessEmployeeTotal) {
        this.eBusinessEmployeeTotal = eBusinessEmployeeTotal;
    }

    public Integer geteBusinessEntryTotal() {
        return eBusinessEntryTotal;
    }

    public void seteBusinessEntryTotal(Integer eBusinessEntryTotal) {
        this.eBusinessEntryTotal = eBusinessEntryTotal;
    }

    public Integer geteBusinessDemissionTotal() {
        return eBusinessDemissionTotal;
    }

    public void seteBusinessDemissionTotal(Integer eBusinessDemissionTotal) {
        this.eBusinessDemissionTotal = eBusinessDemissionTotal;
    }

    public Integer geteBusinessPayPeopleTotal() {
        return eBusinessPayPeopleTotal;
    }

    public void seteBusinessPayPeopleTotal(Integer eBusinessPayPeopleTotal) {
        this.eBusinessPayPeopleTotal = eBusinessPayPeopleTotal;
    }

    public Integer geteBusinessBeginMonthPeople() {
        return eBusinessBeginMonthPeople;
    }

    public void seteBusinessBeginMonthPeople(Integer eBusinessBeginMonthPeople) {
        this.eBusinessBeginMonthPeople = eBusinessBeginMonthPeople;
    }

    public Integer geteBusinessMonthDeploy() {
        return eBusinessMonthDeploy;
    }

    public void seteBusinessMonthDeploy(Integer eBusinessMonthDeploy) {
        this.eBusinessMonthDeploy = eBusinessMonthDeploy;
    }

    public Integer geteBusinessMonthTransfer() {
        return eBusinessMonthTransfer;
    }

    public void seteBusinessMonthTransfer(Integer eBusinessMonthTransfer) {
        this.eBusinessMonthTransfer = eBusinessMonthTransfer;
    }

    public Double getSaleLaborCost() {
        return saleLaborCost;
    }

    public void setSaleLaborCost(Double saleLaborCost) {
        this.saleLaborCost = saleLaborCost;
    }

    public Integer getSaleHeadcountTotal() {
        return saleHeadcountTotal;
    }

    public void setSaleHeadcountTotal(Integer saleHeadcountTotal) {
        this.saleHeadcountTotal = saleHeadcountTotal;
    }

    public Integer getSaleEmployeeTotal() {
        return saleEmployeeTotal;
    }

    public void setSaleEmployeeTotal(Integer saleEmployeeTotal) {
        this.saleEmployeeTotal = saleEmployeeTotal;
    }

    public Integer getSaleEntryTotal() {
        return saleEntryTotal;
    }

    public void setSaleEntryTotal(Integer saleEntryTotal) {
        this.saleEntryTotal = saleEntryTotal;
    }

    public Integer getSaleDemissionTotal() {
        return saleDemissionTotal;
    }

    public void setSaleDemissionTotal(Integer saleDemissionTotal) {
        this.saleDemissionTotal = saleDemissionTotal;
    }

    public Integer getSalePayPeopleTotal() {
        return salePayPeopleTotal;
    }

    public void setSalePayPeopleTotal(Integer salePayPeopleTotal) {
        this.salePayPeopleTotal = salePayPeopleTotal;
    }

    public Integer getSaleBeginMonthPeople() {
        return saleBeginMonthPeople;
    }

    public void setSaleBeginMonthPeople(Integer saleBeginMonthPeople) {
        this.saleBeginMonthPeople = saleBeginMonthPeople;
    }

    public Integer getSaleMonthDeploy() {
        return saleMonthDeploy;
    }

    public void setSaleMonthDeploy(Integer saleMonthDeploy) {
        this.saleMonthDeploy = saleMonthDeploy;
    }

    public Integer getSaleMonthTransfer() {
        return saleMonthTransfer;
    }

    public void setSaleMonthTransfer(Integer saleMonthTransfer) {
        this.saleMonthTransfer = saleMonthTransfer;
    }
}
