package com.spring.boot.bean.master;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.boot.validation.IsNotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/3/2.
 */
public class SysContract {
    /**
     * 主键id
     */
    private Long contractId;
    /**
     * 合同名称
     */
    @NotBlank(message = "合同名称不能为空！！")
    //https://www.cnblogs.com/Terry-Wu/p/8134732.html https://www.cnblogs.com/softidea/p/6044123.html
    private String contractName;
    /**
     * 合同编码
     */
    @NotBlank(message = "合同编号不能为空！")
    private String contractCode;
    /**
     * 合同金额（单位：元）
     */
    @Range(min = 0,max = Integer.MAX_VALUE,message = "合同金额格式不正确，或者不符合常理！！")
    //@Pattern(regexp = "^[0-9]+(.[0-9]{0,2})?$",message = "合同金额格式不正确，只能保留两位小数，或者不符合常理！")
    @IsNotNull(message = "合同金额格式不正确，只能保留两位小数，或者不符合常理(数值过大)！")
    //@Digits(integer = Double.MAX_EXPONENT,fraction = 2,message = "数字格式不正确！！") 该注解也可以校验double格式
    private Double contractMoney;
    /**
     * 合同开始时间
     */
    @IsNotNull(message = "合同开始时间不能为空！")//自定义注解
    private Date contractStartTime;
    /**
     * 合同结束时间
     */
    @IsNotNull(message = "合同到期时间不能为空！")//自定义注解
    private Date contractEndTime;
    /**
     * 合同类型id
     */
    @NotNull(message = "合同类型id不能为空!")
    @Range(min = 1,max = Integer.MAX_VALUE,message = "合同类型id格式不正确，或者不符合常理！！")
    private Integer contractTypeId;
    /**
     * 甲方公司名称
     */
    @NotBlank(message = "甲方公司名称不能为空！")
    private String firstPartyCompany;
    /**
     * 乙方公司名称
     */
    @NotBlank(message = "甲方公司名称不能为空！")
    private String secondPartyCompany;
    /**
     * 合同管理人姓名
     */
    @NotBlank(message = "合同管理人姓名不能为空！")
    private String personLiableName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 合同状态（1：未执行，2：在执行，3：即将过期，4：已经过期，5：已经删除）
     */
    private Integer statusCode;
    /**
     * 所属公司id
     */
    @NotNull(message = "公司id不能为空！！")
    @Range(min = 1,max = Integer.MAX_VALUE,message = "公司id格式不正确，或者不符合常理！！")
    private Long companyId;
    /**
     * 联系人姓名
     */
    @NotBlank(message = "合同联系人姓名不能为空！")
    private String contactsName;

    /**
     * 合同总数
     */
    private Integer contractNumber;
    /**
     * 正在执行合同数
     */
    private Integer contractWorking;
    /**
     * 过期合同
     */
    private Integer contractexpired;
    /**
     * /正在执行或者过期合同总数（文件列表显示）
     */
    private Integer total;
    private String companyName;
    /**
     * /合同类型名称（用于查看文件时使用）
     */
    private String contractTypeName;
    /**
     * /附件文档数量（用于查看文件时使用）
     */
    private Integer fileNum;
    /**
     * 文件列表信息
     */
    List<SysContractFile> fileList;
    /**
     * 新增或者修改时，提交的文件信息
     */
    private String fileInfo;

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Double getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(Double contractMoney) {
        this.contractMoney = contractMoney;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getContractStartTime() {
        return contractStartTime;
    }

    public void setContractStartTime(Date contractStartTime) {
        this.contractStartTime = contractStartTime;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(Date contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public Integer getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(Integer contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    public String getFirstPartyCompany() {
        return firstPartyCompany;
    }

    public void setFirstPartyCompany(String firstPartyCompany) {
        this.firstPartyCompany = firstPartyCompany;
    }

    public String getSecondPartyCompany() {
        return secondPartyCompany;
    }

    public void setSecondPartyCompany(String secondPartyCompany) {
        this.secondPartyCompany = secondPartyCompany;
    }

    public String getPersonLiableName() {
        return personLiableName;
    }

    public void setPersonLiableName(String personLiableName) {
        this.personLiableName = personLiableName;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Integer getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(Integer contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Integer getContractWorking() {
        return contractWorking;
    }

    public void setContractWorking(Integer contractWorking) {
        this.contractWorking = contractWorking;
    }

    public Integer getContractexpired() {
        return contractexpired;
    }

    public void setContractexpired(Integer contractexpired) {
        this.contractexpired = contractexpired;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getContractTypeName() {
        return contractTypeName;
    }

    public void setContractTypeName(String contractTypeName) {
        this.contractTypeName = contractTypeName;
    }

    public Integer getFileNum() {
        return fileNum;
    }

    public void setFileNum(Integer fileNum) {
        this.fileNum = fileNum;
    }

    public List<SysContractFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<SysContractFile> fileList) {
        this.fileList = fileList;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(String fileInfo) {
        this.fileInfo = fileInfo;
    }
}
