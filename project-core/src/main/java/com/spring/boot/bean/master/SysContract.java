package com.spring.boot.bean.master;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/3/2.
 */
public class SysContract {

    private Long contractId;
    private String contractName;
    private String contractCode;
    private Integer contractMoney;
    private Timestamp contractStartTime;
    private Timestamp contractEndTime;
    private Integer contractTypeId;
    private String firstPartyCompany;
    private String secondPartyCompany;
    private String personLiableName;
    private Timestamp createTime;
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

    public Integer getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(Integer contractMoney) {
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
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
}
