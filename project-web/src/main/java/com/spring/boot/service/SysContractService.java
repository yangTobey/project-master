package com.spring.boot.service;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 *
 */
public interface SysContractService {
    /**
     * 获取合同类型列表
     * @param limit
     * @param offset
     * @return
     */
    Map<String, Object> sysContractTypeList(Integer limit, Integer offset);
    /**
     * 获取系统全部合同类型
     * @return
     */
    Map<String, Object> sysAllContractType();
    /**
     * 添加合同分类
     * @param contractTypeName
     * @return
     */
    Map<String, Object> addSysContractType(String contractTypeName);

    /**
     * 更新合同分类
     * @param contractTypeId
     * @return
     */
    Map<String, Object> updateSysContractType(String contractTypeId, String contractTypeName);
    /**
     * 删除合同分类
     * @param contractTypeId
     * @return
     */
    Map<String, Object> deleteSysContractType(Long contractTypeId);

    /**
     * 添加合同
     * @param contractName
     * @return
             */
    Map<String, Object> sysContractDataList(String contractName, String contractCode,Integer statusCode,String contractStartTime,  String contractEndTime,
                                       String contractTypeId, String firstPartyCompany, String secondPartyCompany, Integer limit,Integer offset,Long companyId);
    /**
     * 添加合同
     * @param contractName
     * @return
     */
    Map<String, Object> addSysContract(String contractName, String contractCode, String contractMoney, String contractStartTime, String contractEndTime,
                                       Integer contractTypeId, String firstPartyCompany, String secondPartyCompany, String personLiableName,String fileInfo,Long companyId);
    /**
     * 更新合同
     * @param contractName
     * @return
     */
    Map<String, Object> updateSysContract(Long contractId,String contractName,String contractCode,String contractMoney,String contractStartTime, String contractEndTime,
                       String contractTypeId,String firstPartyCompany,String secondPartyCompany,String personLiableName,String fileInfo,Long companyId);
    /**
     * 删除合同
     * @param contractId
     * @return
     */
    Map<String, Object> deleteSysContract(Long contractId);
    /**
     * 根据id获取信息
     * @return
     */
    Map<String,Object> findSysContractById(Long contractId);
    /**
     * 根据id获取附件文档信息
     * @return
     */
    Map<String,Object> findSysContractFileById(long contractId);
    /**
     * 获取即将过期的合同
     * @param companyId
     * @return
     */
    Map<String, Object> getSysContractExpireDataTotal(Long companyId);
    /**
     * 获取报表统计数据
     * @param companyId
     * @return
     */
    Map<String, Object> sysContractAnalysisData(Long companyId);

    /**
     * 更新快到期的合同状态
     */
    void updateSysContractExpire();

}
