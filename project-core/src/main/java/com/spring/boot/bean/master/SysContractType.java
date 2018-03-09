package com.spring.boot.bean.master;

/**
 * Created by Administrator on 2018/3/2.
 */
public class SysContractType {

    private long contractTypeId;
    private String contractTypeName;
    private String contractTypeCode;
    private int statusCode;

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

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
