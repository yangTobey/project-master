package com.spring.boot.bean.master;

/**
 * Created by Administrator on 2018/3/2.
 */
public class ContractType {

    private long contractTypeId;
    private String contractTypeName;
    private String contractTypeCode;
    private int status;

    public long getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(long contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    public String getContractTypeName() {
        return contractTypeName;
    }

    public void setContractTypeName(String contractTypeName) {
        this.contractTypeName = contractTypeName;
    }

    public String getContractTypeCode() {
        return contractTypeCode;
    }

    public void setContractTypeCode(String contractTypeCode) {
        this.contractTypeCode = contractTypeCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
