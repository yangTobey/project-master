package com.spring.boot.bean.master;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/3/2.
 */
public class SysContract {

    private long contractId;
    private String contractName;
    private String contractCode;
    private int contractMoney;
    private Timestamp contractStartTime;
    private Timestamp contractEndTime;
    private int contractTypeId;
    private String firstPartyCompany;
    private String secondPartyCompany;
    private String personLiableName;
    private Timestamp createTime;
    private int statusCode;

    public long getContractId() {
        return contractId;
    }

    public void setContractId(long contractId) {
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

    public int getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(int contractMoney) {
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

    public int getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(int contractTypeId) {
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

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
