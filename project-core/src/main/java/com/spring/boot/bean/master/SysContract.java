package com.spring.boot.bean.master;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/2.
 */
public class SysContract {

    private Long contractId;
    private String contractName;
    private String contractCode;
    private Double contractMoney;
    private Timestamp contractStartTime;
    private Timestamp contractEndTime;
    private Integer contractTypeId;
    private String firstPartyCompany;
    private String secondPartyCompany;
    private String personLiableName;
    private Date createTime;
    private Integer statusCode;
    private Long companyId;

    //合同总数
    private Integer contractNumber;
    //正在执行合同数
    private Integer contractWorking;
    //过期合同
    private Integer contractexpired;
    //正在执行或者过期合同总数
    private Integer total;
    private String companyName;
    //合同类型名称
    private String contractTypeName;
    //附件文档数量
    private Integer fileNum;

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

    public Timestamp getContractStartTime() {
        return contractStartTime;
    }

    public void setContractStartTime(Timestamp contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    public Timestamp getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(Timestamp contractEndTime) {
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
}
