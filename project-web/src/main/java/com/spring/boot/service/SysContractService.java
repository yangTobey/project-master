package com.spring.boot.service;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 *
 */
public interface SysContractService {
    /**
     * 添加合同分类
     * @param contractTypeName
     * @return
     */
    int addContractType(String contractTypeName);

    /**
     * 更新合同分类
     * @param contractTypeId
     * @return
     */
    int updateContractType(String contractTypeId, String contractTypeName);
    /**
     * 删除合同分类
     * @param contractTypeId
     * @return
     */
    int deleteContractType(String contractTypeId);

    /**
     * 添加合同
     * @param contractName
     * @return
             */
    Map<String, Object> sysContractDataList(String contractName, String contractCode,  String contractEndTime,
                                       String contractTypeId, String firstPartyCompany, String secondPartyCompany, Integer limit,Integer offset,Long companyId);
    /**
     * 添加合同
     * @param contractName
     * @return
     */
    Map<String, Object> addSysContract(String contractName, String contractCode, String contractMoney, String contractStartTime, String contractEndTime,
                                       String contractTypeId, String firstPartyCompany, String secondPartyCompany, String personLiableName);
    /**
     * 更新合同
     * @param contractName
     * @return
     */
    int updateSysContract(Long contractId,String contractName,String contractCode,String contractMoney,String contractStartTime, String contractEndTime,
                       String contractTypeId,String firstPartyCompany,String secondPartyCompany,String personLiableName);
    /**
     * 删除合同
     * @param contractId
     * @return
     */
    int deleteSysContract(Long contractId);

}
