package com.spring.boot.service;

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

}
